package remote_authenticator

import (
	"bytes"
	"context"
	"encoding/json"
	"errors"
	"fmt"
	"io"
	"net/http"
	"net/url"
	"time"

	"github.com/treeverse/lakefs/pkg/auth"
	"github.com/treeverse/lakefs/pkg/auth/model"
	"github.com/treeverse/lakefs/pkg/config"
	"github.com/treeverse/lakefs/pkg/logging"
)

const RemoteAuthSource = "remote_authenticator"

// Request object that will be sent to the remote authenticator service as JSON payload in a POST request
type AuthenticationRequest struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

// Expected response from the remote authenticator service
type AuthenticationResponse struct {
	// ExternalUserIdentifier is optional, if returned then the user will be used as the official username in lakeFS
	ExternalUserIdentifier string `json:"external_user_identifier"`
}

// RemoteAuthenticator client
type RemoteAuthenticator struct {
	AuthService auth.Service
	Logger      logging.Logger
	Config      *config.RemoteAuthenticator
	serviceURL  string
	c           *http.Client
}

func NewRemoteAuthenticator(conf *config.RemoteAuthenticator, authService auth.Service, logger logging.Logger) (auth.Authenticator, error) {
	serviceURL, err := url.JoinPath(conf.BaseURL, conf.AuthEndpoint)
	if err != nil {
		return nil, err
	}
	logger.WithField("service_url", serviceURL).Info("initializing remote authenticator")

	return &RemoteAuthenticator{
		Logger:      logger,
		Config:      conf,
		serviceURL:  serviceURL,
		AuthService: authService,
	}, nil
}

func (ra *RemoteAuthenticator) client() *http.Client {
	if ra.c == nil {
		ra.c = http.DefaultClient
	}
	return ra.c
}

func (ra *RemoteAuthenticator) doRequest(ctx context.Context, username, password string, log logging.Logger) ([]byte, error) {
	// build the request
	payload, err := json.Marshal(&AuthenticationRequest{Username: username, Password: password})

	if err != nil {
		log.WithError(err).Error("failed marshaling request")
		return nil, auth.ErrInvalidRequest
	}

	req, err := http.NewRequestWithContext(ctx, "POST", ra.serviceURL, bytes.NewBuffer(payload))

	if err != nil {
		log.WithError(err).Error("failed building new request")
		return nil, auth.ErrInvalidRequest
	}

	req.Header.Set("Content-Type", "application/json")

	// do the request
	client := ra.client()
	log = log.WithField("url", req.URL.String())
	log.Debug("starting http request to remote authenticator")

	resp, err := client.Do(req)

	if err != nil {
		log.WithError(err).Debug("failed sending request to remote authenticator")
		return nil, err
	}
	log = log.WithField("status_code", resp.StatusCode)
	log.Debug("got response from remote authenticator")

	defer resp.Body.Close()
	body, err := io.ReadAll(resp.Body)
	if err != nil {
		log.WithError(err).Debug("failed reading response body")
		return nil, err
	}
	return body, nil
}

func (ra *RemoteAuthenticator) AuthenticateUser(ctx context.Context, username, password string) (string, error) {
	// TODO(isan) logger: verify username appears here in ctx && verify that password doesnt
	log := ra.Logger.WithContext(ctx)
	data, err := ra.doRequest(ctx, username, password, log)

	if err != nil {
		return "", fmt.Errorf("doing http request %s: %w", username, err)
	}

	// TODO(isan) check that AuthenticationResponse{} does not brake memorty allocation
	var res AuthenticationResponse

	if err := json.Unmarshal(data, &res); err != nil {
		return "", fmt.Errorf("unmarshaling authenticator response %s: %w", username, err)
	}

	dbUsername := username

	// if the external authentication service provided an external user identifier, use it as the username
	log = ra.Logger.WithField("external_user_identifier", res.ExternalUserIdentifier)
	if res.ExternalUserIdentifier != "" {
		dbUsername = res.ExternalUserIdentifier
	}

	user, err := ra.AuthService.GetUser(ctx, dbUsername)

	if err == nil {
		log.WithField("user", fmt.Sprintf("%+v", user)).Debug("got existing user")
		return user.Username, nil
	}
	if !errors.Is(err, auth.ErrNotFound) {
		log.WithError(err).Info("Could not get user; creating them")
	}

	newUser := &model.User{
		CreatedAt:    time.Now(),
		Username:     dbUsername,
		FriendlyName: &username,
		Source:       RemoteAuthSource,
	}

	_, err = ra.AuthService.CreateUser(ctx, newUser)
	if err != nil {
		return "", fmt.Errorf("create backing user for remote auth user %s: %w", dbUsername, err)
	}
	_, err = ra.AuthService.CreateCredentials(ctx, dbUsername)
	if err != nil {
		return "", fmt.Errorf("create credentials for remote auth user %s: %w", dbUsername, err)
	}

	err = ra.AuthService.AddUserToGroup(ctx, dbUsername, ra.Config.DefaultUserGroup)
	if err != nil {
		return "", fmt.Errorf("add newly created remote auth user %s to %s: %w", dbUsername, ra.Config.DefaultUserGroup, err)
	}
	return newUser.Username, nil
}

func (la *RemoteAuthenticator) String() string {
	return RemoteAuthSource
}
# coding: utf-8

"""
    lakeFS API

    lakeFS HTTP API

    The version of the OpenAPI document: 1.0.0
    Contact: services@treeverse.io
    Generated by OpenAPI Generator (https://openapi-generator.tech)

    Do not edit the class manually.
"""  # noqa: E501


import re  # noqa: F401
import io
import warnings

from pydantic import validate_arguments, ValidationError
from typing_extensions import Annotated

from pydantic import Field, StrictStr

from typing import Optional

from lakefs_sdk.models.commit import Commit
from lakefs_sdk.models.commit_creation import CommitCreation

from lakefs_sdk.api_client import ApiClient
from lakefs_sdk.api_response import ApiResponse
from lakefs_sdk.exceptions import (  # noqa: F401
    ApiTypeError,
    ApiValueError
)


class CommitsApi(object):
    """NOTE: This class is auto generated by OpenAPI Generator
    Ref: https://openapi-generator.tech

    Do not edit the class manually.
    """

    def __init__(self, api_client=None):
        if api_client is None:
            api_client = ApiClient.get_default()
        self.api_client = api_client

    @validate_arguments
    def commit(self, repository : StrictStr, branch : StrictStr, commit_creation : CommitCreation, source_metarange : Annotated[Optional[StrictStr], Field(description="The source metarange to commit. Branch must not have uncommitted changes.")] = None, **kwargs) -> Commit:  # noqa: E501
        """create commit  # noqa: E501

        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please pass async_req=True

        >>> thread = api.commit(repository, branch, commit_creation, source_metarange, async_req=True)
        >>> result = thread.get()

        :param repository: (required)
        :type repository: str
        :param branch: (required)
        :type branch: str
        :param commit_creation: (required)
        :type commit_creation: CommitCreation
        :param source_metarange: The source metarange to commit. Branch must not have uncommitted changes.
        :type source_metarange: str
        :param async_req: Whether to execute the request asynchronously.
        :type async_req: bool, optional
        :param _request_timeout: timeout setting for this request. If one
                                 number provided, it will be total request
                                 timeout. It can also be a pair (tuple) of
                                 (connection, read) timeouts.
        :return: Returns the result object.
                 If the method is called asynchronously,
                 returns the request thread.
        :rtype: Commit
        """
        kwargs['_return_http_data_only'] = True
        if '_preload_content' in kwargs:
            raise ValueError("Error! Please call the commit_with_http_info method with `_preload_content` instead and obtain raw data from ApiResponse.raw_data")
        return self.commit_with_http_info(repository, branch, commit_creation, source_metarange, **kwargs)  # noqa: E501

    @validate_arguments
    def commit_with_http_info(self, repository : StrictStr, branch : StrictStr, commit_creation : CommitCreation, source_metarange : Annotated[Optional[StrictStr], Field(description="The source metarange to commit. Branch must not have uncommitted changes.")] = None, **kwargs) -> ApiResponse:  # noqa: E501
        """create commit  # noqa: E501

        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please pass async_req=True

        >>> thread = api.commit_with_http_info(repository, branch, commit_creation, source_metarange, async_req=True)
        >>> result = thread.get()

        :param repository: (required)
        :type repository: str
        :param branch: (required)
        :type branch: str
        :param commit_creation: (required)
        :type commit_creation: CommitCreation
        :param source_metarange: The source metarange to commit. Branch must not have uncommitted changes.
        :type source_metarange: str
        :param async_req: Whether to execute the request asynchronously.
        :type async_req: bool, optional
        :param _preload_content: if False, the ApiResponse.data will
                                 be set to none and raw_data will store the 
                                 HTTP response body without reading/decoding.
                                 Default is True.
        :type _preload_content: bool, optional
        :param _return_http_data_only: response data instead of ApiResponse
                                       object with status code, headers, etc
        :type _return_http_data_only: bool, optional
        :param _request_timeout: timeout setting for this request. If one
                                 number provided, it will be total request
                                 timeout. It can also be a pair (tuple) of
                                 (connection, read) timeouts.
        :param _request_auth: set to override the auth_settings for an a single
                              request; this effectively ignores the authentication
                              in the spec for a single request.
        :type _request_auth: dict, optional
        :type _content_type: string, optional: force content-type for the request
        :return: Returns the result object.
                 If the method is called asynchronously,
                 returns the request thread.
        :rtype: tuple(Commit, status_code(int), headers(HTTPHeaderDict))
        """

        _params = locals()

        _all_params = [
            'repository',
            'branch',
            'commit_creation',
            'source_metarange'
        ]
        _all_params.extend(
            [
                'async_req',
                '_return_http_data_only',
                '_preload_content',
                '_request_timeout',
                '_request_auth',
                '_content_type',
                '_headers'
            ]
        )

        # validate the arguments
        for _key, _val in _params['kwargs'].items():
            if _key not in _all_params:
                raise ApiTypeError(
                    "Got an unexpected keyword argument '%s'"
                    " to method commit" % _key
                )
            _params[_key] = _val
        del _params['kwargs']

        _collection_formats = {}

        # process the path parameters
        _path_params = {}
        if _params['repository']:
            _path_params['repository'] = _params['repository']

        if _params['branch']:
            _path_params['branch'] = _params['branch']


        # process the query parameters
        _query_params = []
        if _params.get('source_metarange') is not None:  # noqa: E501
            _query_params.append(('source_metarange', _params['source_metarange']))

        # process the header parameters
        _header_params = dict(_params.get('_headers', {}))
        # process the form parameters
        _form_params = []
        _files = {}
        # process the body parameter
        _body_params = None
        if _params['commit_creation'] is not None:
            _body_params = _params['commit_creation']

        # set the HTTP header `Accept`
        _header_params['Accept'] = self.api_client.select_header_accept(
            ['application/json'])  # noqa: E501

        # set the HTTP header `Content-Type`
        _content_types_list = _params.get('_content_type',
            self.api_client.select_header_content_type(
                ['application/json']))
        if _content_types_list:
                _header_params['Content-Type'] = _content_types_list

        # authentication setting
        _auth_settings = ['basic_auth', 'cookie_auth', 'oidc_auth', 'saml_auth', 'jwt_token']  # noqa: E501

        _response_types_map = {
            '201': "Commit",
            '400': "Error",
            '401': "Error",
            '403': "Error",
            '404': "Error",
            '412': "Error",
            '420': None,
        }

        return self.api_client.call_api(
            '/repositories/{repository}/branches/{branch}/commits', 'POST',
            _path_params,
            _query_params,
            _header_params,
            body=_body_params,
            post_params=_form_params,
            files=_files,
            response_types_map=_response_types_map,
            auth_settings=_auth_settings,
            async_req=_params.get('async_req'),
            _return_http_data_only=_params.get('_return_http_data_only'),  # noqa: E501
            _preload_content=_params.get('_preload_content', True),
            _request_timeout=_params.get('_request_timeout'),
            collection_formats=_collection_formats,
            _request_auth=_params.get('_request_auth'))

    @validate_arguments
    def get_commit(self, repository : StrictStr, commit_id : StrictStr, **kwargs) -> Commit:  # noqa: E501
        """get commit  # noqa: E501

        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please pass async_req=True

        >>> thread = api.get_commit(repository, commit_id, async_req=True)
        >>> result = thread.get()

        :param repository: (required)
        :type repository: str
        :param commit_id: (required)
        :type commit_id: str
        :param async_req: Whether to execute the request asynchronously.
        :type async_req: bool, optional
        :param _request_timeout: timeout setting for this request. If one
                                 number provided, it will be total request
                                 timeout. It can also be a pair (tuple) of
                                 (connection, read) timeouts.
        :return: Returns the result object.
                 If the method is called asynchronously,
                 returns the request thread.
        :rtype: Commit
        """
        kwargs['_return_http_data_only'] = True
        if '_preload_content' in kwargs:
            raise ValueError("Error! Please call the get_commit_with_http_info method with `_preload_content` instead and obtain raw data from ApiResponse.raw_data")
        return self.get_commit_with_http_info(repository, commit_id, **kwargs)  # noqa: E501

    @validate_arguments
    def get_commit_with_http_info(self, repository : StrictStr, commit_id : StrictStr, **kwargs) -> ApiResponse:  # noqa: E501
        """get commit  # noqa: E501

        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please pass async_req=True

        >>> thread = api.get_commit_with_http_info(repository, commit_id, async_req=True)
        >>> result = thread.get()

        :param repository: (required)
        :type repository: str
        :param commit_id: (required)
        :type commit_id: str
        :param async_req: Whether to execute the request asynchronously.
        :type async_req: bool, optional
        :param _preload_content: if False, the ApiResponse.data will
                                 be set to none and raw_data will store the 
                                 HTTP response body without reading/decoding.
                                 Default is True.
        :type _preload_content: bool, optional
        :param _return_http_data_only: response data instead of ApiResponse
                                       object with status code, headers, etc
        :type _return_http_data_only: bool, optional
        :param _request_timeout: timeout setting for this request. If one
                                 number provided, it will be total request
                                 timeout. It can also be a pair (tuple) of
                                 (connection, read) timeouts.
        :param _request_auth: set to override the auth_settings for an a single
                              request; this effectively ignores the authentication
                              in the spec for a single request.
        :type _request_auth: dict, optional
        :type _content_type: string, optional: force content-type for the request
        :return: Returns the result object.
                 If the method is called asynchronously,
                 returns the request thread.
        :rtype: tuple(Commit, status_code(int), headers(HTTPHeaderDict))
        """

        _params = locals()

        _all_params = [
            'repository',
            'commit_id'
        ]
        _all_params.extend(
            [
                'async_req',
                '_return_http_data_only',
                '_preload_content',
                '_request_timeout',
                '_request_auth',
                '_content_type',
                '_headers'
            ]
        )

        # validate the arguments
        for _key, _val in _params['kwargs'].items():
            if _key not in _all_params:
                raise ApiTypeError(
                    "Got an unexpected keyword argument '%s'"
                    " to method get_commit" % _key
                )
            _params[_key] = _val
        del _params['kwargs']

        _collection_formats = {}

        # process the path parameters
        _path_params = {}
        if _params['repository']:
            _path_params['repository'] = _params['repository']

        if _params['commit_id']:
            _path_params['commitId'] = _params['commit_id']


        # process the query parameters
        _query_params = []
        # process the header parameters
        _header_params = dict(_params.get('_headers', {}))
        # process the form parameters
        _form_params = []
        _files = {}
        # process the body parameter
        _body_params = None
        # set the HTTP header `Accept`
        _header_params['Accept'] = self.api_client.select_header_accept(
            ['application/json'])  # noqa: E501

        # authentication setting
        _auth_settings = ['basic_auth', 'cookie_auth', 'oidc_auth', 'saml_auth', 'jwt_token']  # noqa: E501

        _response_types_map = {
            '200': "Commit",
            '401': "Error",
            '404': "Error",
            '420': None,
        }

        return self.api_client.call_api(
            '/repositories/{repository}/commits/{commitId}', 'GET',
            _path_params,
            _query_params,
            _header_params,
            body=_body_params,
            post_params=_form_params,
            files=_files,
            response_types_map=_response_types_map,
            auth_settings=_auth_settings,
            async_req=_params.get('async_req'),
            _return_http_data_only=_params.get('_return_http_data_only'),  # noqa: E501
            _preload_content=_params.get('_preload_content', True),
            _request_timeout=_params.get('_request_timeout'),
            collection_formats=_collection_formats,
            _request_auth=_params.get('_request_auth'))

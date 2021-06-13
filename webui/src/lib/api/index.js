export const API_ENDPOINT = '/api/v1';
export const DEFAULT_LISTING_AMOUNT = 100;

class LocalCache {
    get(key) {
        const value = localStorage.getItem(key)
        if (value !== null)
            return JSON.parse(value)
        return null
    }

    set(key, value) {
        localStorage.setItem(key, JSON.stringify(value))
    }

    delete(key) {
        localStorage.removeItem(key)
    }
}

const cache = new LocalCache();

export const linkToPath = (repoId, branchId, path) => {
    const query = qs({
        path: path,
    });
    return `${API_ENDPOINT}/repositories/${repoId}/refs/${branchId}/objects?${query}`;
};

const json =(data) => {
    return JSON.stringify(data, null, "");
};

const qs = (queryParts) => {
    const parts = Object.keys(queryParts).map(key => [key, queryParts[key]]);
    return new URLSearchParams(parts).toString();
};

export const extractError = async (response) => {
    let body;
    if (response.headers.get('Content-Type') === 'application/json') {
        const jsonBody = await response.json();
        body = jsonBody.message;
    } else {
        body = await response.text();
    }
    return body;
};

export const defaultAPIHeaders = {
    "Accept": "application/json",
    "Content-Type": "application/json",
}

const authenticationError = "error authenticating request"

const apiRequest = async (uri, requestData = {}, additionalHeaders = {}) => {
    const headers = new Headers({
        ...defaultAPIHeaders,
        ...additionalHeaders,
    })
    const response = await fetch(`${API_ENDPOINT}${uri}`, {headers,  ...requestData});

    // check if we're missing credentials
    if (response.status === 401) {
        const errorMessage = await extractError(response);
        if (errorMessage === authenticationError) {
            cache.delete('user')
            throw new AuthenticationError('Authentication Error');
        } else {
            throw new AuthorizationError(errorMessage);
        }
    }

    return response;
}

// helper errors
export class NotFoundError extends Error {
    constructor(message) {
        super(message)
        this.name = "NotFoundError"
    }
}

export class AuthorizationError extends Error {
    constructor(message) {
        super(message);
        this.name = "AuthorizationError"
    }
}

export class AuthenticationError extends Error {
    constructor(message) {
        super(message);
        this.name = "AuthenticationError"
    }
}

export class MergeError extends Error {
    constructor(message, payload) {
        super(message);
        this.name = "MergeError";
        this.payload = payload;
    }
}


// actual actions:
class Auth {

    async login(accessKeyId, secretAccessKey) {
        const response = await fetch(`${API_ENDPOINT}/auth/login`, {
            headers: new Headers(defaultAPIHeaders),
            method: 'POST',
            body: json({access_key_id: accessKeyId, secret_access_key: secretAccessKey})
        });

        if (response.status === 401) {
            throw new AuthenticationError('invalid credentials');
        }
        if (response.status !== 200) {
            throw new AuthenticationError('unknown authentication error');
        }

        // get current user and cache it
        const userResponse = await apiRequest('/user')
        const body = await userResponse.json();
        const user = body.user;

        cache.set('user', {...user, accessKeyId});
        return user;
    }

    async logout() {
        const response = await fetch(`${API_ENDPOINT}/auth/logout`, {
            headers: new Headers(defaultAPIHeaders),
            method: 'POST',
        });
        if (response.status !== 200) {
            throw new Error('unknown authentication error');
        }
        cache.delete('user');
    }

    async getCurrentUser() {
        return cache.get('user');
    }

    async listUsers(prefix = "", after = "", amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({prefix, after, amount});
        const response = await apiRequest(`/auth/users?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list users: ${await extractError(response)}`);
        }
        return response.json();
    }

    async createUser(userId) {
        const response = await apiRequest(`/auth/users`, {method: 'POST', body: json({id: userId})});
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async listGroups(prefix = "", after = "", amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({prefix, after, amount});
        const response = await apiRequest(`/auth/groups?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list groups: ${await extractError(response)}`);
        }
        return response.json();
    }

    async listGroupMembers(groupId, after, amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({after, amount});
        const response = await apiRequest(`/auth/groups/${groupId}/members?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list group members: ${await extractError(response)}`);
        }
        return response.json();
    }

    async addUserToGroup(userId, groupId) {
        const response = await apiRequest(`/auth/groups/${groupId}/members/${userId}`, {method: 'PUT'});
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
    }

    async removeUserFromGroup(userId, groupId) {
        const response = await apiRequest(`/auth/groups/${groupId}/members/${userId}`, {method: 'DELETE'});
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }

    async attachPolicyToUser(userId, policyId) {
        const response = await apiRequest(`/auth/users/${userId}/policies/${policyId}`, {method: 'PUT'});
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
    }

    async detachPolicyFromUser(userId, policyId) {
        const response = await apiRequest(`/auth/users/${userId}/policies/${policyId}`, {method: 'DELETE'});
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }

    async attachPolicyToGroup(groupId, policyId) {
        const response = await apiRequest(`/auth/groups/${groupId}/policies/${policyId}`, {method: 'PUT'});
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
    }

    async detachPolicyFromGroup(groupId, policyId) {
        const response = await apiRequest(`/auth/groups/${groupId}/policies/${policyId}`, {method: 'DELETE'});
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }

    async deleteCredentials(userId, accessKeyId) {
        const response = await apiRequest(`/auth/users/${userId}/credentials/${accessKeyId}`, {method: 'DELETE'});
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }

    async createGroup(groupId) {
        const response = await apiRequest(`/auth/groups`, {method: 'POST',  body: json({id: groupId})});
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async listPolicies(prefix = "", after = "", amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({prefix, after, amount});
        const response = await apiRequest(`/auth/policies?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list policies: ${await extractError(response)}`);
        }
        return response.json();
    }

    async createPolicy(policyId, policyDocument) {
        const policy = {id: policyId, ...JSON.parse(policyDocument)};
        const response = await apiRequest(`/auth/policies`, {
            method: 'POST',
            body: json(policy)
        });
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async editPolicy(policyId, policyDocument) {
        const policy = {id: policyId, ...JSON.parse(policyDocument)};
        const response = await apiRequest(`/auth/policies/${policyId}`, {
            method: 'PUT',
            body: json(policy)
        });
        if (response.status !== 200) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async listCredentials(userId, after, amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({after, amount});
        const response = await apiRequest(`/auth/users/${userId}/credentials?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list credentials: ${await extractError(response)}`);
        }
        return response.json();
    }

    async createCredentials(userId) {
        const response = await apiRequest(`/auth/users/${userId}/credentials`, {
            method: 'POST',
        });
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async listUserGroups(userId, after, amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({after, amount});
        const response = await apiRequest(`/auth/users/${userId}/groups?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list user groups: ${await extractError(response)}`);
        }
        return response.json();
    }

    async listUserPolicies(userId, effective = false, after = "", amount = DEFAULT_LISTING_AMOUNT) {
        let params = {after, amount};
        if (effective) {
            params.effective =  'true'
        }
        const response = await apiRequest(`/auth/users/${userId}/policies?${qs(params)}`);
        if (response.status !== 200) {
            throw new Error(`could not list policies: ${await extractError(response)}`);
        }
        return response.json()
    }

    async getPolicy(policyId) {
        const response = await apiRequest(`/auth/policies/${policyId}`);
        if (response.status !== 200) {
            throw new Error(`could not get policy: ${await extractError(response)}`);
        }
        return response.json();
    }

    async listGroupPolicies(groupId, after, amount = DEFAULT_LISTING_AMOUNT) {
        let params = {after, amount};
        const response = await apiRequest(`/auth/groups/${groupId}/policies?${qs(params)}`);
        if (response.status !== 200) {
            throw new Error(`could not list policies: ${await extractError(response)}`);
        }
        return response.json();
    }

    async deleteUser(userId) {
        const response = await apiRequest(`/auth/users/${userId}`, {method: 'DELETE'});
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }

    async deleteUsers (userIds) {
        for (let i = 0; i < userIds.length; i++) {
            const userId = userIds[i];
            await this.deleteUser(userId);
        }

    }

    async deleteGroup(groupId) {
        const response = await apiRequest(`/auth/groups/${groupId}`, {method: 'DELETE'});
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }

    async deleteGroups (groupIds) {
        for (let i = 0; i < groupIds.length; i++) {
            const groupId = groupIds[i]
            await this.deleteGroup(groupId);
        }
    }

    async deletePolicy(policyId) {
        const response = await apiRequest(`/auth/policies/${policyId}`, {method: 'DELETE'});
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }

    async deletePolicies (policyIds) {
        for (let i = 0; i < policyIds.length; i++) {
            const policyId = policyIds[i];
            await this.deletePolicy(policyId);
        }
    }
}

class Repositories {

    async get(repoId) {
        const response = await apiRequest(`/repositories/${encodeURIComponent(repoId)}`);
        if (response.status === 404) {
            throw new NotFoundError(`could not find repository ${repoId}`);
        } else if (response.status !== 200) {
            throw new Error(`could not get repository: ${await extractError(response)}`);
        }
        return response.json();
    }

    async list(prefix = "", after = "", amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({prefix, after, amount});
        const response = await apiRequest(`/repositories?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list repositories: ${await extractError(response)}`);
        }
        return await response.json();
    }

    async create(repo) {
        const response = await apiRequest('/repositories', {
            method: 'POST',
            body: json(repo),
        });
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async delete(repoId) {
        const response = await apiRequest(`/repositories/${repoId}`, {method: 'DELETE'});
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }
}

class Branches {

    async get(repoId, branchId) {
        const response = await apiRequest(`/repositories/${repoId}/branches/${branchId}`);
        if (response.status === 404) {
            throw new NotFoundError(`could not find branch ${branchId}`);
        } else if (response.status !== 200) {
            throw new Error(`could not get branch: ${await extractError(response)}`);
        }
        return response.json();
    }

    async create(repoId, name, source) {
        const response = await apiRequest(`/repositories/${repoId}/branches`, {
            method: 'POST',
            body: json({name, source}),
        });
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async delete(repoId, name) {
        const response = await apiRequest(`/repositories/${repoId}/branches/${name}`, {
            method: 'DELETE',
        });
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }

    async revert(repoId, branch, options) {
        const response = await apiRequest(`/repositories/${repoId}/branches/${branch}`, {
            method: 'PUT',
            body: json(options),
        });
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }

    async list(repoId, prefix = "", after = "", amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({prefix, after, amount});
        const response = await apiRequest(`/repositories/${repoId}/branches?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list branches: ${await extractError(response)}`);
        }
        return response.json();
    }
}

class Objects {

    async list(repoId, ref, tree, after = "", amount = DEFAULT_LISTING_AMOUNT, readUncommitted = true, delimiter = "/") {
        const query = qs({prefix:tree, amount, after, readUncommitted, delimiter});
        const response = await apiRequest(`/repositories/${repoId}/refs/${ref}/objects/ls?${query}`);
        if (response.status !== 200) {
            throw new Error(await extractError(response));
        }
        return await response.json();
    }

    async upload(repoId, branchId, path, fileObject) {
        const data = new FormData();
        data.append('content', fileObject);
        window.data = data;
        const query = qs({path});
        const response = await apiRequest(`/repositories/${repoId}/branches/${branchId}/objects?${query}`, {
            method: 'POST',
            body: data,
            headers: new Headers({'Accept': 'application/json'})
        });
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async delete(repoId, branchId, path) {
        const query = qs({path});
        const response = await apiRequest(`/repositories/${repoId}/branches/${branchId}/objects?${query}`, {
            method: 'DELETE',
        });
        if (response.status !== 204) {
            throw new Error(await extractError(response));
        }
    }
}

class Commits {
    async log(repoId, refId, after = "", amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({after, amount});
        const response = await apiRequest(`/repositories/${repoId}/refs/${refId}/commits?${query}`);
        if (response.status !== 200) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async get(repoId, commitId) {
        const response = await apiRequest(`/repositories/${repoId}/commits/${commitId}`);
        if (response.status === 404) {
            throw new NotFoundError(`could not find commit ${commitId}`);
        } else if (response.status !== 200) {
            throw new Error(`could not get commit: ${await extractError(response)}`);
        }
        return response.json();
    }

    async commit(repoId, branchId, message, metadata ={}) {
        const response = await apiRequest(`/repositories/${repoId}/branches/${branchId}/commits`, {
            method: 'POST',
            body: json({message, metadata}),
        });
        if (response.status !== 201) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }
}

class Refs {

    async changes(repoId, branchId, after, prefix, delimiter, amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({after, prefix, delimiter, amount});
        const response = await apiRequest(`/repositories/${repoId}/branches/${branchId}/diff?${query}`);
        if (response.status !== 200) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async diff(repoId, leftRef, rightRef, after, prefix = "", delimiter = "", amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({after, amount, delimiter, prefix});
        const response = await apiRequest(`/repositories/${repoId}/refs/${leftRef}/diff/${rightRef}?${query}`);
        if (response.status !== 200) {
            throw new Error(await extractError(response));
        }
        return response.json();
    }

    async merge(repoId, sourceBranch, destinationBranch) {
        const response = await apiRequest(`/repositories/${repoId}/refs/${sourceBranch}/merge/${destinationBranch}`, {
            method: 'POST',
            body: '{}',
        });
        switch (response.status) {
            case 200:
                return response.json();
            case 409:
                const resp = await response.json();
                throw new MergeError(response.statusText, resp.body);
            case 412:
            default:
                throw new Error(await extractError(response));
        }
    }
}

class Actions {

    async listRuns(repoId, branch = "", commit = "", after = "", amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({branch, commit, after, amount});
        const response = await apiRequest(`/repositories/${repoId}/actions/runs?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list actions runs: ${await extractError(response)}`);
        }
        return response.json();
    }

    async getRun(repoId, runId) {
        const response = await apiRequest(`/repositories/${repoId}/actions/runs/${runId}`);
        if (response.status !== 200) {
            throw new Error(`could not get actions run: ${await extractError(response)}`);
        }
        return response.json();
    }

    async listRunHooks(repoId, runId, after = "", amount = DEFAULT_LISTING_AMOUNT) {
        const query = qs({after, amount});
        const response = await apiRequest(`/repositories/${repoId}/actions/runs/${runId}/hooks?${query}`);
        if (response.status !== 200) {
            throw new Error(`could not list actions run hooks: ${await extractError(response)}`)
        }
        return response.json();
    }

    async getRunHookOutput(repoId, runId, hookRunId) {
        const response = await apiRequest(`/repositories/${repoId}/actions/runs/${runId}/hooks/${hookRunId}/output`, {
            headers: {"Content-Type": "application/octet-stream"},
        });
        if (response.status !== 200) {
            throw new Error(`could not get actions run hook output: ${await extractError(response)}`);
        }
        return response.text();
    }

}


class Setup {
    async lakeFS(username) {
        const response = await apiRequest('/setup_lakefs', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({username: username}),
        });
        switch (response.status) {
            case 200:
                return response.json();
            case 409:
                throw new Error('Conflict');
            default:
                throw new Error('Unknown');
        }
    }
}

class Config {
    async getStorageConfig() {
        const response = await apiRequest('/config/storage', {
            method: 'GET',
        });
        switch (response.status) {
            case 200:
                const cfg = await response.json();
                cfg.warnings = []
                if (cfg.blockstore_type === 'local' || cfg.blockstore_type === 'mem') {
                    cfg.warnings.push(`Block adapter ${cfg.blockstore_type} not usable in production`)
                }
                return cfg;
            case 409:
                throw new Error('Conflict');
            default:
                throw new Error('Unknown');
        }
    }
    async getLakeFSVersion() {
        const response = await apiRequest('/config/version', {
            method: 'GET',
        });
        switch (response.status) {
            case 200:
                return await response.json();
            default:
                throw new Error('Unknown');
        }
    }
}

export const repositories = new Repositories();
export const branches = new Branches();
export const objects = new Objects();
export const commits = new Commits();
export const refs = new Refs();
export const setup = new Setup();
export const auth = new Auth();
export const actions = new Actions();
export const config = new Config();

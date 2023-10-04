# lakefs-client
lakeFS HTTP API

This Python package is automatically generated by the [OpenAPI Generator](https://openapi-generator.tech) project:

- API version: 0.1.0
- Package version: 0.1.0-SNAPSHOT
- Build package: org.openapitools.codegen.languages.PythonClientCodegen

## Requirements.

Python >= 3.6

## Installation & Usage
### pip install

If the python package is hosted on a repository, you can install directly using:

```sh
pip install git+https://github.com/treeverse/lakeFS.git
```
(you may need to run `pip` with root permission: `sudo pip install git+https://github.com/treeverse/lakeFS.git`)

Then import the package:
```python
import lakefs_client
```

### Setuptools

Install via [Setuptools](http://pypi.python.org/pypi/setuptools).

```sh
python setup.py install --user
```
(or `sudo python setup.py install` to install the package for all users)

Then import the package:
```python
import lakefs_client
```

## Getting Started

Please follow the [installation procedure](#installation--usage) and then run the following:

```python

import time
import lakefs_client
from pprint import pprint
from lakefs_client.api import actions_api
from lakefs_client.model.action_run import ActionRun
from lakefs_client.model.action_run_list import ActionRunList
from lakefs_client.model.error import Error
from lakefs_client.model.hook_run_list import HookRunList
# Defining the host is optional and defaults to http://localhost/api/v1
# See configuration.py for a list of all supported configuration parameters.
configuration = lakefs_client.Configuration(
    host = "http://localhost/api/v1"
)

# The client must configure the authentication and authorization parameters
# in accordance with the API server security policy.
# Examples for each auth method are provided below, use the example that
# satisfies your auth use case.

# Configure HTTP basic authorization: basic_auth
configuration = lakefs_client.Configuration(
    username = 'YOUR_USERNAME',
    password = 'YOUR_PASSWORD'
)

# Configure API key authorization: cookie_auth
configuration.api_key['cookie_auth'] = 'YOUR_API_KEY'

# Uncomment below to setup prefix (e.g. Bearer) for API key, if needed
# configuration.api_key_prefix['cookie_auth'] = 'Bearer'

# Configure Bearer authorization (JWT): jwt_token
configuration = lakefs_client.Configuration(
    access_token = 'YOUR_BEARER_TOKEN'
)

# Configure API key authorization: oidc_auth
configuration.api_key['oidc_auth'] = 'YOUR_API_KEY'

# Uncomment below to setup prefix (e.g. Bearer) for API key, if needed
# configuration.api_key_prefix['oidc_auth'] = 'Bearer'

# Configure API key authorization: saml_auth
configuration.api_key['saml_auth'] = 'YOUR_API_KEY'

# Uncomment below to setup prefix (e.g. Bearer) for API key, if needed
# configuration.api_key_prefix['saml_auth'] = 'Bearer'


# Enter a context with an instance of the API client
with lakefs_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = actions_api.ActionsApi(api_client)
    repository = "repository_example" # str | 
run_id = "run_id_example" # str | 

    try:
        # get a run
        api_response = api_instance.get_run(repository, run_id)
        pprint(api_response)
    except lakefs_client.ApiException as e:
        print("Exception when calling ActionsApi->get_run: %s\n" % e)
```

## Documentation for API Endpoints

All URIs are relative to *http://localhost/api/v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ActionsApi* | [**get_run**](docs/ActionsApi.md#get_run) | **GET** /repositories/{repository}/actions/runs/{run_id} | get a run
*ActionsApi* | [**get_run_hook_output**](docs/ActionsApi.md#get_run_hook_output) | **GET** /repositories/{repository}/actions/runs/{run_id}/hooks/{hook_run_id}/output | get run hook output
*ActionsApi* | [**list_repository_runs**](docs/ActionsApi.md#list_repository_runs) | **GET** /repositories/{repository}/actions/runs | list runs
*ActionsApi* | [**list_run_hooks**](docs/ActionsApi.md#list_run_hooks) | **GET** /repositories/{repository}/actions/runs/{run_id}/hooks | list run hooks
*AuthApi* | [**add_group_membership**](docs/AuthApi.md#add_group_membership) | **PUT** /auth/groups/{groupId}/members/{userId} | add group membership
*AuthApi* | [**attach_policy_to_group**](docs/AuthApi.md#attach_policy_to_group) | **PUT** /auth/groups/{groupId}/policies/{policyId} | attach policy to group
*AuthApi* | [**attach_policy_to_user**](docs/AuthApi.md#attach_policy_to_user) | **PUT** /auth/users/{userId}/policies/{policyId} | attach policy to user
*AuthApi* | [**create_credentials**](docs/AuthApi.md#create_credentials) | **POST** /auth/users/{userId}/credentials | create credentials
*AuthApi* | [**create_group**](docs/AuthApi.md#create_group) | **POST** /auth/groups | create group
*AuthApi* | [**create_policy**](docs/AuthApi.md#create_policy) | **POST** /auth/policies | create policy
*AuthApi* | [**create_user**](docs/AuthApi.md#create_user) | **POST** /auth/users | create user
*AuthApi* | [**delete_credentials**](docs/AuthApi.md#delete_credentials) | **DELETE** /auth/users/{userId}/credentials/{accessKeyId} | delete credentials
*AuthApi* | [**delete_group**](docs/AuthApi.md#delete_group) | **DELETE** /auth/groups/{groupId} | delete group
*AuthApi* | [**delete_group_membership**](docs/AuthApi.md#delete_group_membership) | **DELETE** /auth/groups/{groupId}/members/{userId} | delete group membership
*AuthApi* | [**delete_policy**](docs/AuthApi.md#delete_policy) | **DELETE** /auth/policies/{policyId} | delete policy
*AuthApi* | [**delete_user**](docs/AuthApi.md#delete_user) | **DELETE** /auth/users/{userId} | delete user
*AuthApi* | [**detach_policy_from_group**](docs/AuthApi.md#detach_policy_from_group) | **DELETE** /auth/groups/{groupId}/policies/{policyId} | detach policy from group
*AuthApi* | [**detach_policy_from_user**](docs/AuthApi.md#detach_policy_from_user) | **DELETE** /auth/users/{userId}/policies/{policyId} | detach policy from user
*AuthApi* | [**get_credentials**](docs/AuthApi.md#get_credentials) | **GET** /auth/users/{userId}/credentials/{accessKeyId} | get credentials
*AuthApi* | [**get_current_user**](docs/AuthApi.md#get_current_user) | **GET** /user | get current user
*AuthApi* | [**get_group**](docs/AuthApi.md#get_group) | **GET** /auth/groups/{groupId} | get group
*AuthApi* | [**get_group_acl**](docs/AuthApi.md#get_group_acl) | **GET** /auth/groups/{groupId}/acl | get ACL of group
*AuthApi* | [**get_policy**](docs/AuthApi.md#get_policy) | **GET** /auth/policies/{policyId} | get policy
*AuthApi* | [**get_user**](docs/AuthApi.md#get_user) | **GET** /auth/users/{userId} | get user
*AuthApi* | [**list_group_members**](docs/AuthApi.md#list_group_members) | **GET** /auth/groups/{groupId}/members | list group members
*AuthApi* | [**list_group_policies**](docs/AuthApi.md#list_group_policies) | **GET** /auth/groups/{groupId}/policies | list group policies
*AuthApi* | [**list_groups**](docs/AuthApi.md#list_groups) | **GET** /auth/groups | list groups
*AuthApi* | [**list_policies**](docs/AuthApi.md#list_policies) | **GET** /auth/policies | list policies
*AuthApi* | [**list_user_credentials**](docs/AuthApi.md#list_user_credentials) | **GET** /auth/users/{userId}/credentials | list user credentials
*AuthApi* | [**list_user_groups**](docs/AuthApi.md#list_user_groups) | **GET** /auth/users/{userId}/groups | list user groups
*AuthApi* | [**list_user_policies**](docs/AuthApi.md#list_user_policies) | **GET** /auth/users/{userId}/policies | list user policies
*AuthApi* | [**list_users**](docs/AuthApi.md#list_users) | **GET** /auth/users | list users
*AuthApi* | [**login**](docs/AuthApi.md#login) | **POST** /auth/login | perform a login
*AuthApi* | [**set_group_acl**](docs/AuthApi.md#set_group_acl) | **POST** /auth/groups/{groupId}/acl | set ACL of group
*AuthApi* | [**update_policy**](docs/AuthApi.md#update_policy) | **PUT** /auth/policies/{policyId} | update policy
*BranchesApi* | [**cherry_pick**](docs/BranchesApi.md#cherry_pick) | **POST** /repositories/{repository}/branches/{branch}/cherry-pick | Replay the changes from the given commit on the branch
*BranchesApi* | [**create_branch**](docs/BranchesApi.md#create_branch) | **POST** /repositories/{repository}/branches | create branch
*BranchesApi* | [**delete_branch**](docs/BranchesApi.md#delete_branch) | **DELETE** /repositories/{repository}/branches/{branch} | delete branch
*BranchesApi* | [**diff_branch**](docs/BranchesApi.md#diff_branch) | **GET** /repositories/{repository}/branches/{branch}/diff | diff branch
*BranchesApi* | [**get_branch**](docs/BranchesApi.md#get_branch) | **GET** /repositories/{repository}/branches/{branch} | get branch
*BranchesApi* | [**list_branches**](docs/BranchesApi.md#list_branches) | **GET** /repositories/{repository}/branches | list branches
*BranchesApi* | [**reset_branch**](docs/BranchesApi.md#reset_branch) | **PUT** /repositories/{repository}/branches/{branch} | reset branch
*BranchesApi* | [**revert_branch**](docs/BranchesApi.md#revert_branch) | **POST** /repositories/{repository}/branches/{branch}/revert | revert
*CommitsApi* | [**commit**](docs/CommitsApi.md#commit) | **POST** /repositories/{repository}/branches/{branch}/commits | create commit
*CommitsApi* | [**get_commit**](docs/CommitsApi.md#get_commit) | **GET** /repositories/{repository}/commits/{commitId} | get commit
*ConfigApi* | [**get_config**](docs/ConfigApi.md#get_config) | **GET** /config | 
*ExperimentalApi* | [**get_otf_diffs**](docs/ExperimentalApi.md#get_otf_diffs) | **GET** /otf/diffs | get the available Open Table Format diffs
*ExperimentalApi* | [**otf_diff**](docs/ExperimentalApi.md#otf_diff) | **GET** /repositories/{repository}/otf/refs/{left_ref}/diff/{right_ref} | perform otf diff
*HealthCheckApi* | [**health_check**](docs/HealthCheckApi.md#health_check) | **GET** /healthcheck | 
*ImportApi* | [**import_cancel**](docs/ImportApi.md#import_cancel) | **DELETE** /repositories/{repository}/branches/{branch}/import | cancel ongoing import
*ImportApi* | [**import_start**](docs/ImportApi.md#import_start) | **POST** /repositories/{repository}/branches/{branch}/import | import data from object store
*ImportApi* | [**import_status**](docs/ImportApi.md#import_status) | **GET** /repositories/{repository}/branches/{branch}/import | get import status
*InternalApi* | [**create_branch_protection_rule_preflight**](docs/InternalApi.md#create_branch_protection_rule_preflight) | **GET** /repositories/{repository}/branch_protection/set_allowed | 
*InternalApi* | [**create_symlink_file**](docs/InternalApi.md#create_symlink_file) | **POST** /repositories/{repository}/refs/{branch}/symlink | creates symlink files corresponding to the given directory
*InternalApi* | [**dump_refs**](docs/InternalApi.md#dump_refs) | **PUT** /repositories/{repository}/refs/dump | Dump repository refs (tags, commits, branches) to object store Deprecated: a new API will introduce long running operations 
*InternalApi* | [**get_auth_capabilities**](docs/InternalApi.md#get_auth_capabilities) | **GET** /auth/capabilities | list authentication capabilities supported
*InternalApi* | [**get_garbage_collection_config**](docs/InternalApi.md#get_garbage_collection_config) | **GET** /config/garbage-collection | 
*InternalApi* | [**get_lake_fs_version**](docs/InternalApi.md#get_lake_fs_version) | **GET** /config/version | 
*InternalApi* | [**get_setup_state**](docs/InternalApi.md#get_setup_state) | **GET** /setup_lakefs | check if the lakeFS installation is already set up
*InternalApi* | [**get_storage_config**](docs/InternalApi.md#get_storage_config) | **GET** /config/storage | 
*InternalApi* | [**internal_create_branch_protection_rule**](docs/InternalApi.md#internal_create_branch_protection_rule) | **POST** /repositories/{repository}/branch_protection | 
*InternalApi* | [**internal_delete_branch_protection_rule**](docs/InternalApi.md#internal_delete_branch_protection_rule) | **DELETE** /repositories/{repository}/branch_protection | 
*InternalApi* | [**internal_delete_garbage_collection_rules**](docs/InternalApi.md#internal_delete_garbage_collection_rules) | **DELETE** /repositories/{repository}/gc/rules | 
*InternalApi* | [**internal_get_branch_protection_rules**](docs/InternalApi.md#internal_get_branch_protection_rules) | **GET** /repositories/{repository}/branch_protection | get branch protection rules
*InternalApi* | [**internal_get_garbage_collection_rules**](docs/InternalApi.md#internal_get_garbage_collection_rules) | **GET** /repositories/{repository}/gc/rules | 
*InternalApi* | [**internal_set_garbage_collection_rules**](docs/InternalApi.md#internal_set_garbage_collection_rules) | **POST** /repositories/{repository}/gc/rules | 
*InternalApi* | [**post_stats_events**](docs/InternalApi.md#post_stats_events) | **POST** /statistics | post stats events, this endpoint is meant for internal use only
*InternalApi* | [**prepare_garbage_collection_commits**](docs/InternalApi.md#prepare_garbage_collection_commits) | **POST** /repositories/{repository}/gc/prepare_commits | save lists of active commits for garbage collection
*InternalApi* | [**prepare_garbage_collection_uncommitted**](docs/InternalApi.md#prepare_garbage_collection_uncommitted) | **POST** /repositories/{repository}/gc/prepare_uncommited | save repository uncommitted metadata for garbage collection
*InternalApi* | [**restore_refs**](docs/InternalApi.md#restore_refs) | **PUT** /repositories/{repository}/refs/restore | Restore repository refs (tags, commits, branches) from object store. Deprecated: a new API will introduce long running operations 
*InternalApi* | [**set_garbage_collection_rules_preflight**](docs/InternalApi.md#set_garbage_collection_rules_preflight) | **GET** /repositories/{repository}/gc/rules/set_allowed | 
*InternalApi* | [**setup**](docs/InternalApi.md#setup) | **POST** /setup_lakefs | setup lakeFS and create a first user
*InternalApi* | [**setup_comm_prefs**](docs/InternalApi.md#setup_comm_prefs) | **POST** /setup_comm_prefs | setup communications preferences
*InternalApi* | [**stage_object**](docs/InternalApi.md#stage_object) | **PUT** /repositories/{repository}/branches/{branch}/objects | stage an object&#39;s metadata for the given branch
*InternalApi* | [**upload_object_preflight**](docs/InternalApi.md#upload_object_preflight) | **GET** /repositories/{repository}/branches/{branch}/objects/stage_allowed | 
*MetadataApi* | [**get_meta_range**](docs/MetadataApi.md#get_meta_range) | **GET** /repositories/{repository}/metadata/meta_range/{meta_range} | return URI to a meta-range file
*MetadataApi* | [**get_range**](docs/MetadataApi.md#get_range) | **GET** /repositories/{repository}/metadata/range/{range} | return URI to a range file
*ObjectsApi* | [**copy_object**](docs/ObjectsApi.md#copy_object) | **POST** /repositories/{repository}/branches/{branch}/objects/copy | create a copy of an object
*ObjectsApi* | [**delete_object**](docs/ObjectsApi.md#delete_object) | **DELETE** /repositories/{repository}/branches/{branch}/objects | delete object. Missing objects will not return a NotFound error.
*ObjectsApi* | [**delete_objects**](docs/ObjectsApi.md#delete_objects) | **POST** /repositories/{repository}/branches/{branch}/objects/delete | delete objects. Missing objects will not return a NotFound error.
*ObjectsApi* | [**get_object**](docs/ObjectsApi.md#get_object) | **GET** /repositories/{repository}/refs/{ref}/objects | get object content
*ObjectsApi* | [**get_underlying_properties**](docs/ObjectsApi.md#get_underlying_properties) | **GET** /repositories/{repository}/refs/{ref}/objects/underlyingProperties | get object properties on underlying storage
*ObjectsApi* | [**head_object**](docs/ObjectsApi.md#head_object) | **HEAD** /repositories/{repository}/refs/{ref}/objects | check if object exists
*ObjectsApi* | [**list_objects**](docs/ObjectsApi.md#list_objects) | **GET** /repositories/{repository}/refs/{ref}/objects/ls | list objects under a given prefix
*ObjectsApi* | [**stat_object**](docs/ObjectsApi.md#stat_object) | **GET** /repositories/{repository}/refs/{ref}/objects/stat | get object metadata
*ObjectsApi* | [**upload_object**](docs/ObjectsApi.md#upload_object) | **POST** /repositories/{repository}/branches/{branch}/objects | 
*RefsApi* | [**diff_refs**](docs/RefsApi.md#diff_refs) | **GET** /repositories/{repository}/refs/{leftRef}/diff/{rightRef} | diff references
*RefsApi* | [**find_merge_base**](docs/RefsApi.md#find_merge_base) | **GET** /repositories/{repository}/refs/{sourceRef}/merge/{destinationBranch} | find the merge base for 2 references
*RefsApi* | [**log_commits**](docs/RefsApi.md#log_commits) | **GET** /repositories/{repository}/refs/{ref}/commits | get commit log from ref. If both objects and prefixes are empty, return all commits.
*RefsApi* | [**merge_into_branch**](docs/RefsApi.md#merge_into_branch) | **POST** /repositories/{repository}/refs/{sourceRef}/merge/{destinationBranch} | merge references
*RepositoriesApi* | [**create_repository**](docs/RepositoriesApi.md#create_repository) | **POST** /repositories | create repository
*RepositoriesApi* | [**delete_gc_rules**](docs/RepositoriesApi.md#delete_gc_rules) | **DELETE** /repositories/{repository}/settings/gc_rules | 
*RepositoriesApi* | [**delete_repository**](docs/RepositoriesApi.md#delete_repository) | **DELETE** /repositories/{repository} | delete repository
*RepositoriesApi* | [**get_branch_protection_rules**](docs/RepositoriesApi.md#get_branch_protection_rules) | **GET** /repositories/{repository}/settings/branch_protection | get branch protection rules
*RepositoriesApi* | [**get_gc_rules**](docs/RepositoriesApi.md#get_gc_rules) | **GET** /repositories/{repository}/settings/gc_rules | get repository GC rules
*RepositoriesApi* | [**get_repository**](docs/RepositoriesApi.md#get_repository) | **GET** /repositories/{repository} | get repository
*RepositoriesApi* | [**get_repository_metadata**](docs/RepositoriesApi.md#get_repository_metadata) | **GET** /repositories/{repository}/metadata | get repository metadata
*RepositoriesApi* | [**list_repositories**](docs/RepositoriesApi.md#list_repositories) | **GET** /repositories | list repositories
*RepositoriesApi* | [**set_branch_protection_rules**](docs/RepositoriesApi.md#set_branch_protection_rules) | **PUT** /repositories/{repository}/settings/branch_protection | 
*RepositoriesApi* | [**set_gc_rules**](docs/RepositoriesApi.md#set_gc_rules) | **PUT** /repositories/{repository}/settings/gc_rules | 
*StagingApi* | [**get_physical_address**](docs/StagingApi.md#get_physical_address) | **GET** /repositories/{repository}/branches/{branch}/staging/backing | get a physical address and a return token to write object to underlying storage
*StagingApi* | [**link_physical_address**](docs/StagingApi.md#link_physical_address) | **PUT** /repositories/{repository}/branches/{branch}/staging/backing | associate staging on this physical address with a path
*TagsApi* | [**create_tag**](docs/TagsApi.md#create_tag) | **POST** /repositories/{repository}/tags | create tag
*TagsApi* | [**delete_tag**](docs/TagsApi.md#delete_tag) | **DELETE** /repositories/{repository}/tags/{tag} | delete tag
*TagsApi* | [**get_tag**](docs/TagsApi.md#get_tag) | **GET** /repositories/{repository}/tags/{tag} | get tag
*TagsApi* | [**list_tags**](docs/TagsApi.md#list_tags) | **GET** /repositories/{repository}/tags | list tags


## Documentation For Models

 - [ACL](docs/ACL.md)
 - [AccessKeyCredentials](docs/AccessKeyCredentials.md)
 - [ActionRun](docs/ActionRun.md)
 - [ActionRunList](docs/ActionRunList.md)
 - [AuthCapabilities](docs/AuthCapabilities.md)
 - [AuthenticationToken](docs/AuthenticationToken.md)
 - [BranchCreation](docs/BranchCreation.md)
 - [BranchProtectionRule](docs/BranchProtectionRule.md)
 - [CherryPickCreation](docs/CherryPickCreation.md)
 - [CommPrefsInput](docs/CommPrefsInput.md)
 - [Commit](docs/Commit.md)
 - [CommitCreation](docs/CommitCreation.md)
 - [CommitList](docs/CommitList.md)
 - [Config](docs/Config.md)
 - [Credentials](docs/Credentials.md)
 - [CredentialsList](docs/CredentialsList.md)
 - [CredentialsWithSecret](docs/CredentialsWithSecret.md)
 - [CurrentUser](docs/CurrentUser.md)
 - [Diff](docs/Diff.md)
 - [DiffList](docs/DiffList.md)
 - [DiffProperties](docs/DiffProperties.md)
 - [Error](docs/Error.md)
 - [ErrorNoACL](docs/ErrorNoACL.md)
 - [FindMergeBaseResult](docs/FindMergeBaseResult.md)
 - [GarbageCollectionConfig](docs/GarbageCollectionConfig.md)
 - [GarbageCollectionPrepareResponse](docs/GarbageCollectionPrepareResponse.md)
 - [GarbageCollectionRule](docs/GarbageCollectionRule.md)
 - [GarbageCollectionRules](docs/GarbageCollectionRules.md)
 - [Group](docs/Group.md)
 - [GroupCreation](docs/GroupCreation.md)
 - [GroupList](docs/GroupList.md)
 - [HookRun](docs/HookRun.md)
 - [HookRunList](docs/HookRunList.md)
 - [ImportCreation](docs/ImportCreation.md)
 - [ImportCreationResponse](docs/ImportCreationResponse.md)
 - [ImportLocation](docs/ImportLocation.md)
 - [ImportStatus](docs/ImportStatus.md)
 - [InlineObject](docs/InlineObject.md)
 - [InlineObject1](docs/InlineObject1.md)
 - [LoginConfig](docs/LoginConfig.md)
 - [LoginInformation](docs/LoginInformation.md)
 - [Merge](docs/Merge.md)
 - [MergeResult](docs/MergeResult.md)
 - [MetaRangeCreation](docs/MetaRangeCreation.md)
 - [MetaRangeCreationResponse](docs/MetaRangeCreationResponse.md)
 - [OTFDiffs](docs/OTFDiffs.md)
 - [ObjectCopyCreation](docs/ObjectCopyCreation.md)
 - [ObjectError](docs/ObjectError.md)
 - [ObjectErrorList](docs/ObjectErrorList.md)
 - [ObjectStageCreation](docs/ObjectStageCreation.md)
 - [ObjectStats](docs/ObjectStats.md)
 - [ObjectStatsList](docs/ObjectStatsList.md)
 - [ObjectUserMetadata](docs/ObjectUserMetadata.md)
 - [OtfDiffEntry](docs/OtfDiffEntry.md)
 - [OtfDiffList](docs/OtfDiffList.md)
 - [Pagination](docs/Pagination.md)
 - [PathList](docs/PathList.md)
 - [Policy](docs/Policy.md)
 - [PolicyList](docs/PolicyList.md)
 - [PrepareGCUncommittedRequest](docs/PrepareGCUncommittedRequest.md)
 - [PrepareGCUncommittedResponse](docs/PrepareGCUncommittedResponse.md)
 - [RangeMetadata](docs/RangeMetadata.md)
 - [Ref](docs/Ref.md)
 - [RefList](docs/RefList.md)
 - [RefsDump](docs/RefsDump.md)
 - [Repository](docs/Repository.md)
 - [RepositoryCreation](docs/RepositoryCreation.md)
 - [RepositoryList](docs/RepositoryList.md)
 - [RepositoryMetadata](docs/RepositoryMetadata.md)
 - [ResetCreation](docs/ResetCreation.md)
 - [RevertCreation](docs/RevertCreation.md)
 - [Setup](docs/Setup.md)
 - [SetupState](docs/SetupState.md)
 - [StagingLocation](docs/StagingLocation.md)
 - [StagingMetadata](docs/StagingMetadata.md)
 - [Statement](docs/Statement.md)
 - [StatsEvent](docs/StatsEvent.md)
 - [StatsEventsList](docs/StatsEventsList.md)
 - [StorageConfig](docs/StorageConfig.md)
 - [StorageURI](docs/StorageURI.md)
 - [TagCreation](docs/TagCreation.md)
 - [UnderlyingObjectProperties](docs/UnderlyingObjectProperties.md)
 - [UpdateToken](docs/UpdateToken.md)
 - [User](docs/User.md)
 - [UserCreation](docs/UserCreation.md)
 - [UserList](docs/UserList.md)
 - [VersionConfig](docs/VersionConfig.md)


## Documentation For Authorization


## basic_auth

- **Type**: HTTP basic authentication


## cookie_auth

- **Type**: API key
- **API key parameter name**: internal_auth_session
- **Location**: 


## jwt_token

- **Type**: Bearer authentication (JWT)


## oidc_auth

- **Type**: API key
- **API key parameter name**: oidc_auth_session
- **Location**: 


## saml_auth

- **Type**: API key
- **API key parameter name**: saml_auth_session
- **Location**: 


## Author

services@treeverse.io


## Notes for Large OpenAPI documents
If the OpenAPI document is large, imports in lakefs_client.apis and lakefs_client.models may fail with a
RecursionError indicating the maximum recursion limit has been exceeded. In that case, there are a couple of solutions:

Solution 1:
Use specific imports for apis and models like:
- `from lakefs_client.api.default_api import DefaultApi`
- `from lakefs_client.model.pet import Pet`

Solution 2:
Before importing the package, adjust the maximum recursion limit as shown below:
```
import sys
sys.setrecursionlimit(1500)
import lakefs_client
from lakefs_client.apis import *
from lakefs_client.models import *
```

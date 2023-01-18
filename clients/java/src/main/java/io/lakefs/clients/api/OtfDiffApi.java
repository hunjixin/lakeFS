/*
 * lakeFS API
 * lakeFS HTTP API
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.lakefs.clients.api;

import io.lakefs.clients.api.ApiCallback;
import io.lakefs.clients.api.ApiClient;
import io.lakefs.clients.api.ApiException;
import io.lakefs.clients.api.ApiResponse;
import io.lakefs.clients.api.Configuration;
import io.lakefs.clients.api.Pair;
import io.lakefs.clients.api.ProgressRequestBody;
import io.lakefs.clients.api.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.lakefs.clients.api.model.Error;
import io.lakefs.clients.api.model.OtfDiffList;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtfDiffApi {
    private ApiClient localVarApiClient;

    public OtfDiffApi() {
        this(Configuration.getDefaultApiClient());
    }

    public OtfDiffApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for otfDiff
     * @param repository  (required)
     * @param leftRef  (required)
     * @param rightRef  (required)
     * @param tablePath a path to the table location under the specified ref. (required)
     * @param type the type of otf (required)
     * @param baseRef base ref to compare a three way diff (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> diff list </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Resource Not Found </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call otfDiffCall(String repository, String leftRef, String rightRef, String tablePath, String type, String baseRef, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/repositories/{repository}/otf/refs/{left_ref}/diff/{right_ref}"
            .replaceAll("\\{" + "repository" + "\\}", localVarApiClient.escapeString(repository.toString()))
            .replaceAll("\\{" + "left_ref" + "\\}", localVarApiClient.escapeString(leftRef.toString()))
            .replaceAll("\\{" + "right_ref" + "\\}", localVarApiClient.escapeString(rightRef.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (tablePath != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("table_path", tablePath));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        if (baseRef != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("base_ref", baseRef));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basic_auth", "cookie_auth", "jwt_token", "oidc_auth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call otfDiffValidateBeforeCall(String repository, String leftRef, String rightRef, String tablePath, String type, String baseRef, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'repository' is set
        if (repository == null) {
            throw new ApiException("Missing the required parameter 'repository' when calling otfDiff(Async)");
        }
        
        // verify the required parameter 'leftRef' is set
        if (leftRef == null) {
            throw new ApiException("Missing the required parameter 'leftRef' when calling otfDiff(Async)");
        }
        
        // verify the required parameter 'rightRef' is set
        if (rightRef == null) {
            throw new ApiException("Missing the required parameter 'rightRef' when calling otfDiff(Async)");
        }
        
        // verify the required parameter 'tablePath' is set
        if (tablePath == null) {
            throw new ApiException("Missing the required parameter 'tablePath' when calling otfDiff(Async)");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException("Missing the required parameter 'type' when calling otfDiff(Async)");
        }
        

        okhttp3.Call localVarCall = otfDiffCall(repository, leftRef, rightRef, tablePath, type, baseRef, _callback);
        return localVarCall;

    }

    /**
     * perform otf diff
     * 
     * @param repository  (required)
     * @param leftRef  (required)
     * @param rightRef  (required)
     * @param tablePath a path to the table location under the specified ref. (required)
     * @param type the type of otf (required)
     * @param baseRef base ref to compare a three way diff (optional)
     * @return OtfDiffList
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> diff list </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Resource Not Found </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public OtfDiffList otfDiff(String repository, String leftRef, String rightRef, String tablePath, String type, String baseRef) throws ApiException {
        ApiResponse<OtfDiffList> localVarResp = otfDiffWithHttpInfo(repository, leftRef, rightRef, tablePath, type, baseRef);
        return localVarResp.getData();
    }

    /**
     * perform otf diff
     * 
     * @param repository  (required)
     * @param leftRef  (required)
     * @param rightRef  (required)
     * @param tablePath a path to the table location under the specified ref. (required)
     * @param type the type of otf (required)
     * @param baseRef base ref to compare a three way diff (optional)
     * @return ApiResponse&lt;OtfDiffList&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> diff list </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Resource Not Found </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<OtfDiffList> otfDiffWithHttpInfo(String repository, String leftRef, String rightRef, String tablePath, String type, String baseRef) throws ApiException {
        okhttp3.Call localVarCall = otfDiffValidateBeforeCall(repository, leftRef, rightRef, tablePath, type, baseRef, null);
        Type localVarReturnType = new TypeToken<OtfDiffList>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * perform otf diff (asynchronously)
     * 
     * @param repository  (required)
     * @param leftRef  (required)
     * @param rightRef  (required)
     * @param tablePath a path to the table location under the specified ref. (required)
     * @param type the type of otf (required)
     * @param baseRef base ref to compare a three way diff (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> diff list </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Resource Not Found </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call otfDiffAsync(String repository, String leftRef, String rightRef, String tablePath, String type, String baseRef, final ApiCallback<OtfDiffList> _callback) throws ApiException {

        okhttp3.Call localVarCall = otfDiffValidateBeforeCall(repository, leftRef, rightRef, tablePath, type, baseRef, _callback);
        Type localVarReturnType = new TypeToken<OtfDiffList>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}

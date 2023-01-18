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

import io.lakefs.clients.api.ApiException;
import io.lakefs.clients.api.model.Error;
import io.lakefs.clients.api.model.OtfDiffList;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ExperimentalApi
 */
@Ignore
public class ExperimentalApiTest {

    private final ExperimentalApi api = new ExperimentalApi();

    
    /**
     * perform otf diff
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void otfDiffTest() throws ApiException {
        String repository = null;
        String leftRef = null;
        String rightRef = null;
        String tablePath = null;
        String type = null;
        String baseRef = null;
                OtfDiffList response = api.otfDiff(repository, leftRef, rightRef, tablePath, type, baseRef);
        // TODO: test validations
    }
    
}

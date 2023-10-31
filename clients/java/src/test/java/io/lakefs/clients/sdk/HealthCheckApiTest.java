/*
 * lakeFS API
 * lakeFS HTTP API
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.lakefs.clients.sdk;

import io.lakefs.clients.sdk.ApiException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for HealthCheckApi
 */
@Disabled
public class HealthCheckApiTest {

    private final HealthCheckApi api = new HealthCheckApi();

    /**
     * check that the API server is up and running
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void healthCheckTest() throws ApiException {
        api.healthCheck()
                .execute();
        // TODO: test validations
    }

}

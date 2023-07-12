package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utils.constants.DataConstant;

public class RequestFactory {
    private String token;

    /**
     * make Request
     *
     * @return A RequestSpecification with authenticated true
     */
    public RequestSpecification makeRequest() {
        return makeRequest(true);
    }

    /**
     * make Request
     *
     * @param apiVersion is the specific number of api version
     * @return A RequestSpecification with authenticated true and the specific number of api version
     */
    public RequestSpecification makeRequest(Float apiVersion) {
        return makeRequest(true, apiVersion);
    }

    /**
     * make Request
     *
     * @param authenticated is if the user need to be logged in the system
     * @return A RequestSpecification with authenticated value and the specific number of api version
     */
    public RequestSpecification makeRequest(Boolean authenticated) {
        return makeRequest(authenticated, 1.0f);
    }

    /**
     * make Request
     *
     * @param authenticated is false when to need be logged Everything else will be true
     * @param apiVersion    is the api version
     * @return A RequestSpecification with authenticated value and the specific number of api version
     */
    public RequestSpecification makeRequest(Boolean authenticated, Float apiVersion) {
        RestAssured.baseURI = DataConstant.BASE_URL;
        RequestSpecification request = RestAssured.given();
        request = request.header("Content-Type", "application/json")
                .header("Cache-Control", "no-cache");
        if (Boolean.TRUE.equals(authenticated)) {
            request = request.header("Authorization", "Bearer " + getToken());
        }
        if (apiVersion > 1) {
            request = request.header("x-api-version", apiVersion);
        }
        return request;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


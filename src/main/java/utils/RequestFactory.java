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
     * @param authenticated is false when to need be logged Everything else will be true
     * @return A RequestSpecification with authenticated value and the specific number of api version
     */
    public RequestSpecification makeRequest(Boolean authenticated) {
        RestAssured.baseURI = DataConstant.BASE_URL;
        RequestSpecification request = RestAssured.given();
        request = request.header("Content-Type", "application/json")
                .header("accept", "*/*");
        if (Boolean.TRUE.equals(authenticated)) {
            request = request.header("Authorization", "Bearer " + getToken());
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


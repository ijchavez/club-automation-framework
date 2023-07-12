package tests.hook;

import cucumber.api.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.ReadProperties;

public class init {

    private static RequestSpecification requestSpecification;

    @Before()
    public void createRequestSpecification() {
        requestSpecification = new RequestSpecBuilder().setBaseUri(ReadProperties.getInstance().getProperty("BASE_URL")).build();
    }
}
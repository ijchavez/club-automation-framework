package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.constants.DataConstantQueries;

import java.time.LocalDate;
import java.util.Date;

public class HelperMethods {
    private static final String localEnvironment = "LOCAL";
    private static final String qaEnvironment = "QA";
    private static final String demoEnvironment = "DEMO";
    private static final String prodEnvironment = "PROD";
    private static final String devEnvironment = "DEV";
    public static String environments;
    protected RequestFactory requestFactory;

    public HelperMethods(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    protected static String testEnvironments() {
        setEnvironments(System.getenv("ENVIRONMENTS"));
        setEnvironments(getEnvironments() != null ? getEnvironments() : "QA");

        switch (getEnvironments().toLowerCase()) {
            case "qa":
                return qaEnvironment;
            case "demo":
                return demoEnvironment;
            case "prod":
                return prodEnvironment;
            default:
                return devEnvironment;
        }
    }

    /**
     * This getPassword method get the map of environment variables defined in the system
     *
     * @param key it's the key of the password
     * @return the value of the password defined on the environment variables
     */
    public static String getPassword(String key) {
        return System.getenv().get(key);
    }

    public static String specificDate(int month, int day) {
        LocalDate localDate = LocalDate.now();
        return (localDate.withDayOfMonth(month).withMonth(day)).toString();
    }

    /**
     * Create a Date
     *
     * @param option it is the part of the Date that you will need
     */
    public static int currentYearDayOrMonthOfDate(String option) {
        LocalDate currentDate = LocalDate.now();
        switch (option) {
            case "year":
                return currentDate.getYear();
            case "day":
                return currentDate.getDayOfMonth();
            case "month":
                return currentDate.getMonthValue();
            default:
                throw new IllegalStateException("This value does not exist: " + option);
        }
    }

    /**
     * Create random String
     *
     * @param value number of digits
     */
    public static String randomString(int value) {
        return RandomStringUtils.randomAlphabetic(value);
    }

    public static String getEnvironments() {
        return environments;
    }

    public static void setEnvironments(String environments) {
        HelperMethods.environments = environments;
    }

    /**
     * Create random alfa Numeric Code
     *
     * @param length is the length of the code to want to arrive.
     */
    public static String randomAlfaNumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }

    /**
     * This function is used to configure the ChromeOptions object that is used to create a ChromeDriver object
     *
     * @return A ChromeOptions object
     */
    public static ChromeOptions chromeOptionsConfig() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        options.addArguments("window-size=1980,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-setuid-sandbox");
        return options;
    }

    /**
     * This getPostResponse method trigger the post request
     *
     * @param payload it's the payload related with the update goal
     * @return
     */
    public Response getPostResponse(Object payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String payloadFormatted = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
            return requestFactory.makeRequest().body("[\n" + payloadFormatted + "\n]")
                    .post(DataConstantQueries.PATH_FOR_MEMBER);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * This getPutResponse method trigger the put request
     *
     * @param payload it's the payload related with the update goal
     * @param id      it's the id necessary to complete the request path
     * @return the response related with the request
     */
    public Response getPutResponse(Object payload, Long id, boolean isPayloadSurroundBrackets) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String payloadFormatted = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
            String payloadSurrounded = isPayloadSurroundBrackets ? "[\n" + payloadFormatted + "\n]" : payloadFormatted;
            return requestFactory.makeRequest().body(payloadSurrounded)
                    .put(DataConstantQueries.PATH_FOR_MEMBER + id.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String getTimeStamp() {
        Date date = new Date();
        return String.valueOf(date.getTime());
    }
}

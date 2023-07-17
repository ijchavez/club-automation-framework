package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.constants.Constant;
import utils.constants.DataConstantQueries;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class HelperMethods {
    protected static String BLOCK_UI_XPATH = "//*[@class='block-ui-wrapper active']";
    WebDriver driver;
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

    public HelperMethods(WebDriver driver) {
        this.driver = driver;
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

    public boolean elementExistWaitLongTime(WebElement element) {
        try {
            waitForElementPresentLong(element);
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public void waitForElementPresentLong(WebElement element) {
        waitForElementPresent(element, Constant.LONGEST_TIMEOUT, this.driver);
    }

    protected void waitForElementPresent(WebElement element, int time, WebDriver optionalDriver) {
        WebDriverWait wait = new WebDriverWait(optionalDriver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for element to be present, clear the field and complete the field with the value
     *
     * @param element to send the value
     * @param value   to sent to the value
     */
    public void sendKeysInElement(WebElement element, String value) {
        waitForElementPresentLong(element);
        element.clear();
        element.sendKeys(Keys.chord(Keys.DELETE));
        element.sendKeys(value);
    }

    public void waitForElements(int value) {
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(value * 500L));
    }

    /**
     * Wait for element to be present and then click the element
     *
     * @param element to click on
     */
    public void clickWithActions(WebElement element) {
        if (environments.equalsIgnoreCase("LOCAL")) {
            waitBlockUIDisappear();
        }
        waitForElementPresentLong(element);
        Actions action = new Actions(driver);
        waitForElementClickableLong(element);
        action.moveToElement(element).click(element).perform();
    }


    public void waitBlockUIDisappear() {
        int waited = 0;
        while (isBlockUIPresent() && waited < 200) {
            waitForElements(1);
            waited++;
        }
    }

    public void waitForElementClickableLong(WebElement element) {
        if (environments.equalsIgnoreCase("LOCAL")) {
            waitBlockUIDisappear();
        }
        waitForElementPresentLong(element);
        waitForElementClickable(element, Constant.LONG_TIMEOUT, driver);
    }


    public boolean isBlockUIPresent() {
        try {
            List<WebElement> blockUIs = driver.findElements(By.xpath(BLOCK_UI_XPATH));
            return blockUIs.size() > 0;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected void waitForElementClickable(WebElement element, int time, WebDriver optionalDriver) {
        if (environments.equalsIgnoreCase("LOCAL")) {
            waitBlockUIDisappear();
        }
        WebDriverWait wait = new WebDriverWait(optionalDriver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isElementEnabled(WebElement element) {
        try {
            waitForElementPresentLong(element);
            return element.isEnabled();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            return false;
        }
    }

    /**
     * taking a screenshot
     *
     * @param name name of screenshot where will be saved in screenshot folder.
     */
    public void takePicture(String name, WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./Screenshots/" + name + ".png"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

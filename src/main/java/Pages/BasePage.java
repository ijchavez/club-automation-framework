package Pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperMethods;
import utils.constants.Constant;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static utils.HelperMethods.environments;

public class BasePage {
    protected static String BLOCK_UI_XPATH = "//*[@class='block-ui-wrapper active']";
    protected WebDriver driver;

    private static final String localEnvironment = "LOCAL";
    private static final String qaEnvironment = "QA";
    private static final String demoEnvironment = "DEMO";
    private static final String prodEnvironment = "PROD";
    private static final String devEnvironment = "DEV";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BasePage() {
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

    public void waitForElementPresentLong(WebElement element) {
        waitForElementPresent(element, Constant.LONGEST_TIMEOUT, this.driver);
    }

    protected void waitForElementPresent(WebElement element, int time, WebDriver optionalDriver) {
        WebDriverWait wait = new WebDriverWait(optionalDriver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Create a JavaScript driver
     *
     * @return driver to execute javascript scripts
     */
    private JavascriptExecutor javaScriptWebDriver() {
        if (this.driver instanceof JavascriptExecutor) {
            return ((JavascriptExecutor) this.driver);
        } else {
            throw new IllegalStateException("This driver does not support JavaScript...!");
        }
    }

    /**
     * If the value is true, then the function will execute a JavaScript command to set the local storage item
     * "disablePendo" to true. If the value is false, then the function will execute a JavaScript command to set the local
     * storage item "disablePendo" to false
     *
     * @param value true or false
     */
    public void handlePendo(boolean value) {
        if (value) {
            javaScriptWebDriver().executeScript("localStorage.setItem(\"disablePendo\", true)");
        } else {
            javaScriptWebDriver().executeScript("localStorage.setItem(\"disablePendo\", false)");
        }
    }

    public boolean elementExistWaitLongTime(WebElement element) {
        try {
            waitForElementPresentLong(element);
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * This function will set the local storage value of the automation key to the correct value for the environment that is being tested
     */
    public void handleCaptcha() {
        String currentEnvironment = HelperMethods.getEnvironments();
        switch (currentEnvironment) {
            case localEnvironment:
                javaScriptWebDriver().executeScript("var localValue = 'Za9-6-ZDQjqwF6ZB_AjyRARZ2WFYWaZqZunKvn-x';localStorage.setItem(\"automationKey\", localValue)");
                break;
            case qaEnvironment:
                javaScriptWebDriver().executeScript("var qaValue = 'mIDKpmpLZ0g05d2ELDE-O1f2QKUGrn0wwpjDbt73';localStorage.setItem(\"automationKey\", qaValue)");
                break;
            case demoEnvironment:
                javaScriptWebDriver().executeScript("var demoValue = 'cMMyh5a9O4c4Mc5D-m5hRe7FcmL-F4RY8hBCku80';localStorage.setItem(\"automationKey\", demoValue)");
                break;
            case prodEnvironment:
                javaScriptWebDriver().executeScript("var prodValue = 'otQoi1i8CubFqGwRylKV5QUffO7N_yFD42VF3hUR';localStorage.setItem(\"automationKey\", prodValue)");
                break;
            case devEnvironment:
                javaScriptWebDriver().executeScript("var devValue = '3OJkJj9xCVhZJTwo3noKp1XYZ2FgQqMQPWzyGJmn';localStorage.setItem(\"automationKey\", devValue)");
                break;
        }
    }

    public void waitForElements(int value) {
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(value * 500L));
    }

    /**
     * Wait for element to be present and then click the element
     *
     * @param element to click on
     */
    protected void clickWithActions(WebElement element) {
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

    public boolean isBlockUIPresent() {
        try {
            List<WebElement> blockUIs = driver.findElements(By.xpath(BLOCK_UI_XPATH));
            return blockUIs.size() > 0;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void waitForElementClickableLong(WebElement element) {
        if (environments.equalsIgnoreCase("LOCAL")) {
            waitBlockUIDisappear();
        }
        waitForElementPresentLong(element);
        waitForElementClickable(element, Constant.LONG_TIMEOUT, driver);
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
     * It waits for the element to be present, then scrolls to it
     *
     * @param element The element to scroll to.
     */
    public void scrollToElement(WebElement element) {
        if (environments.equalsIgnoreCase("LOCAL")) {
            waitBlockUIDisappear();
        }
        waitForElementPresentLong(element);
        javaScriptWebDriver()
                .executeScript("arguments[0].scrollIntoView();", element);
    }
    /**
     * taking a screenshot
     *
     * @param name name of screenshot where will be saved in screenshot folder.
     */
    public void takePicture(String name){
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./Screenshots/" + name + ".png"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

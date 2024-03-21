package Pages;

import cucumber.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected Scenario scenario;
    protected WebDriver driver;

    public BasePage(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
    }

    public BasePage() {
    }
}

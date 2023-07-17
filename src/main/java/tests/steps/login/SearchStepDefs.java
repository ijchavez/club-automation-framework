package tests.steps.login;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DriverFactory;
import utils.HelperMethods;
import utils.ReadProperties;
import utils.constants.Constant;


import java.time.Duration;

import static utils.HelperMethods.chromeOptionsConfig;

public class SearchStepDefs {

    Scenario scenario;
    DriverFactory driverFactory;
    HelperMethods helperMethods;

    public SearchStepDefs(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
        helperMethods = new HelperMethods(this.driverFactory.getDriver());
    }

    @And("^setUp$")
    public void clickOnLoginButton() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptionsConfig());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.SHORT_TIMEOUT));
        driver.get(ReadProperties.getInstance().getProperty("LOGIN_URL"));
        scenario.write(scenario.getName());
        this.driverFactory.setDriver(driver);
        this.driverFactory.InitializePageObject(driver, scenario);
    }

    @Before()
    public void setUp(Scenario sc) {
        this.scenario = sc;
    }

    @After
    public void TearDownTest(Scenario sc) {
        if (sc.isFailed()) {
            helperMethods.takePicture(sc.getId(), driverFactory.getDriver());
        }
        this.driverFactory.getDriver().quit();
        scenario.write(scenario.getName());
    }
}

package tests.steps.login;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DriverFactory;
import utils.HelperMethods;
import utils.ReadProperties;
import utils.constants.Constant;

import java.time.Duration;

import static utils.HelperMethods.chromeOptionsConfig;

public class ScenarioSetUp {

    Scenario scenario;
    DriverFactory driverFactory;
    HelperMethods helperMethods;
    boolean flagDriver = false;

    public ScenarioSetUp(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
        helperMethods = new HelperMethods(this.driverFactory.getDriver());
    }

    @And("^setUp \"([^\"]*)\"$")
    public void scenarioDriverSetUp(boolean upDriver) {
        if (upDriver) {
            flagDriver = true;
            this.driverFactory.setDriver(new ChromeDriver(chromeOptionsConfig()));
            this.driverFactory.getDriver().manage().window().maximize();
            this.driverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.SHORT_TIMEOUT));
            this.driverFactory.getDriver().get(ReadProperties.getInstance().getProperty("LOGIN_URL"));
        }
        scenario.write(scenario.getName());
        this.driverFactory.setDriver(this.driverFactory.getDriver());
        this.driverFactory.InitializePageObject(this.driverFactory.getDriver(), scenario);

    }

    @Before()
    public void setUp(Scenario sc) {
        this.scenario = sc;
    }

    @After
    public void TearDownTest(Scenario sc) {
        if (flagDriver) {
            if (sc.isFailed()) {
                helperMethods.takePicture(sc.getId(), driverFactory.getDriver());
            }
            this.driverFactory.getDriver().quit();
            scenario.write(scenario.getName());
        }
    }
}

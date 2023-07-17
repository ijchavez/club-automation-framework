package Pages.Login;

import Pages.BasePage;
import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.HelperMethods;
import utils.constants.Constant;
import utils.constants.DataConstant;
import utils.enums.UserRole;

public class LoginPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'card-body')]")
    private WebElement LOGIN_PAGE;
    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='email']")
    private WebElement EMAIL_FIELD;
    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='password']")
    private WebElement PASSWORD_FIELD;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'btn-primary')][text()='Autenticar']")
    private WebElement LOGIN_BUTTON;
    WebDriver driver;
    Scenario scenario;
    HelperMethods helperMethods;

    public LoginPage(WebDriver driver, Scenario scenario) {
        super(driver);
        this.driver = driver;
        this.scenario = scenario;
        helperMethods = new HelperMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoginPageDisplayed() {
        return helperMethods.elementExistWaitLongTime(LOGIN_PAGE);
    }

    public void completeEmail(UserRole role) {
        switch (role) {
            case superAdmin:
                helperMethods.sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_SUPER_ADMIN);
                break;
            case normalAccount:
                helperMethods.sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_ACCOUNT_ADMIN);
                break;
        }
    }

    public void completeEmail(String role) {
        helperMethods.sendKeysInElement(EMAIL_FIELD, role);
    }

    public void completePassword(UserRole role) {
        switch (role) {
            case superAdmin:
                helperMethods.sendKeysInElement(PASSWORD_FIELD, DataConstant.PASSWORD_SUPER_ADMIN);
                break;
            case normalAccount:
                helperMethods.sendKeysInElement(PASSWORD_FIELD, DataConstant.PASSWORD);
                break;
        }
    }

    public void completePassword(String password) {
        helperMethods.sendKeysInElement(PASSWORD_FIELD, password);
    }

    public void clickLoginBtn() {
        helperMethods.waitForElements(Constant.SHORT_TIMEOUT);
        helperMethods.waitForElementPresentLong(LOGIN_BUTTON);
        helperMethods.clickWithActions(LOGIN_BUTTON);
    }

    public boolean loginButtonVisibility() {
        helperMethods.waitForElements(Constant.SHORT_TIMEOUT);
        helperMethods.waitForElementPresentLong(LOGIN_BUTTON);
        return helperMethods.isElementEnabled(LOGIN_BUTTON);
    }
}

package Pages.Login;

import Pages.BasePage;
import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.constants.Constant;
import utils.constants.DataConstant;
import utils.enums.UserRole;

public class LoginPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'auth-frame')]")
    private WebElement LOGIN_PAGE;
    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='emailAddress']")
    private WebElement EMAIL_FIELD;
    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='password']")
    private WebElement PASSWORD_FIELD;
    @FindBy(how = How.XPATH, using = "//*[@type='submit']")
    private WebElement LOGIN_BUTTON;
    WebDriver driver;
    Scenario scenario;

    public LoginPage(WebDriver driver, Scenario scenario) {
        super(driver);
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
    }

    public boolean isLoginPageDisplayed() {
        super.handlePendo(true);
        super.handleCaptcha();
        return elementExistWaitLongTime(LOGIN_PAGE);
    }

    public void completeEmail(UserRole role) {
        switch (role) {
            case superAdmin:
                super.sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_SUPER_ADMIN);
                break;
            case FirmAdminEnterprise:
                sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_FIRM_ADMIN_ENTERPRISE);
                break;
            default:
                break;
        }
    }

    public void completePassword(UserRole role) {
        switch (role) {
            case superAdmin:
                super.sendKeysInElement(PASSWORD_FIELD, DataConstant.PASSWORD_SUPER_ADMIN);
                break;
            case FirmAdmin:
            case FirmAdminEnterprise:
            case AdvisorEnterprise:
            case Advisor:
            case Household:
                super.sendKeysInElement(PASSWORD_FIELD, DataConstant.PASSWORD);
                break;
            default:
                break;
        }
    }

    public void clickLoginBtn() {
        handlePendo(true);
        super.waitForElements(Constant.SHORT_TIMEOUT);
        super.waitForElementPresentLong(LOGIN_BUTTON);
        super.clickWithActions(LOGIN_BUTTON);
    }
}

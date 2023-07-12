package Pages;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.constants.Constant;
import utils.enums.UserRole;

import java.util.List;

public class MenuPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'shadow')]")
    private WebElement NAV_BAR;
    @FindBy(how = How.XPATH, using = "//*[@id='Households']//*[contains(text(),'Households')]")
    private WebElement HOUSEHOLD_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Members']")
    private WebElement MEMBER_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Groups']")
    private WebElement GROUPS_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='selectedGroup']")
    private WebElement GROUPS_MENU_OPTION_SELECT;
    @FindBy(how = How.XPATH, using = "//*[@id='Group Households']")
    private WebElement GROUPS_OF_HOUSEHOLD_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Group Members']")
    private WebElement GROUPS_OF_MEMBER_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Account']/following-sibling::*[@id='Settings']")
    private WebElement SETTINGS_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Account']/following-sibling::*[@id='Settings']//*[contains(@class,'children')]/*")
    private List<WebElement> LIST_SETTINGS_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'group-menu-items')]//*[@id='Settings']")
    private WebElement SETTINGS_WITHIN_GROUP_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'group-menu-items')]//*[@id='Settings']//*[contains(@class,'children')]/*")
    private List<WebElement> LIST_SETTINGS_WITHIN_GROUP_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Model Allocations']")
    private WebElement MODEL_ALLOCATIONS_SETTINGS_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Account']")
    private WebElement ACCOUNT_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Firm Information')]")
    private WebElement ACCOUNT_FIRM_INFORMATION_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Billing & Payments')]")
    private WebElement ACCOUNT_BILLING_AND_PAYMENTS_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Capital Mkt Assumptions']")
    private WebElement CAPITAL_MKT_ASSUMPTIONS_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Integrations']")
    private WebElement INTEGRATIONS_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'values')]")
    private WebElement DEFAULT_VALUES_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Asset Class Taxes')]")
    private WebElement ASSET_CLASS_TAXES_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[@id='Report Center']")
    private WebElement REPORT_CENTER_MENU_OPTION;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'icon-logout')]")
    private WebElement LOGOUT_BUTTON;
    @FindBy(how = How.XPATH, using = "//*[@class='page-title'][contains(text(),'Households')]")
    private WebElement DASHBOARD_LABEL;
    Scenario scenario;
    WebDriver driver;

    public MenuPage(WebDriver driver, Scenario scenario) {
        super(driver);
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
    }

    public boolean isMenuPageDisplayed() {
        super.waitBlockUIDisappear();
        super.waitForElementClickableLong(LOGOUT_BUTTON);
        super.waitForElementPresentLong(NAV_BAR);
        return super.elementExistWaitLongTime(NAV_BAR);
    }

    public boolean areRoleOptionsDisplayed(UserRole role) {
        switch (role) {
            case FirmAdminEnterprise:
                return areFirmAdminOptionsDisplayed();
            case AdvisorEnterprise:
                return areAdvOptionsDisplayed();
            default:
                throw new RuntimeException("This role" + role + "doesnt exist.");
        }
    }

    public boolean areAdvOptionsDisplayed() {
        waitForElements(Constant.SHORT_TIMEOUT);
        return elementExistWaitLongTime(HOUSEHOLD_MENU_OPTION)
                && elementExistWaitLongTime(REPORT_CENTER_MENU_OPTION)
                && elementExistWaitLongTime(GROUPS_MENU_OPTION_SELECT)
                && isEnabledDashBoardLabel()
                && areOptionWithinGroupSettingsDisplayed();
    }

    public boolean areFirmAdminMenuOptionsDisplayed() {
        waitForElements(Constant.SHORT_TIMEOUT);
        return elementExistWaitLongTime(HOUSEHOLD_MENU_OPTION)
                && elementExistWaitLongTime(MEMBER_MENU_OPTION)
                && elementExistWaitLongTime(GROUPS_MENU_OPTION);
    }

    public void clickSettings() {
        super.waitForElementPresentLong(SETTINGS_MENU_OPTION);
        super.scrollToElement(SETTINGS_MENU_OPTION);
        super.clickWithActions(SETTINGS_MENU_OPTION);
    }

    public void clickSettingsOfGroup() {
        super.waitForElementPresentLong(SETTINGS_WITHIN_GROUP_MENU_OPTION);
        super.scrollToElement(SETTINGS_WITHIN_GROUP_MENU_OPTION);
        super.clickWithActions(SETTINGS_WITHIN_GROUP_MENU_OPTION);
    }

    public boolean areChildOfSettingsMenuEnterpriseOption() {
        return LIST_SETTINGS_MENU_OPTION != null && LIST_SETTINGS_MENU_OPTION.size() == 4;
    }

    public boolean areChildOfSettingsWithinGroupsMenuEnterpriseOption() {
        return LIST_SETTINGS_WITHIN_GROUP_MENU_OPTION != null && LIST_SETTINGS_WITHIN_GROUP_MENU_OPTION.size() == 3;
    }

    public void clickAccount() {
        super.clickWithActions(ACCOUNT_MENU_OPTION);
    }

    public boolean areFirmAdminInfoOfGroupDisplayed() {
        return (elementExistWaitLongTime(GROUPS_OF_HOUSEHOLD_MENU_OPTION)
                && elementExistWaitLongTime(GROUPS_OF_MEMBER_MENU_OPTION)
                && elementExistWaitLongTime(REPORT_CENTER_MENU_OPTION)
                && LIST_SETTINGS_WITHIN_GROUP_MENU_OPTION.size() == 3);
    }

    public boolean areFirmAdminSettingsDisplayed() {
        clickSettings();
        return (elementExistWaitLongTime(ASSET_CLASS_TAXES_MENU_OPTION)
                && elementExistWaitLongTime(CAPITAL_MKT_ASSUMPTIONS_MENU_OPTION)
                && elementExistWaitLongTime(DEFAULT_VALUES_MENU_OPTION)
                && elementExistWaitLongTime(MODEL_ALLOCATIONS_SETTINGS_MENU_OPTION)
                && areChildOfSettingsMenuEnterpriseOption());
    }

    public boolean areOptionWithinGroupSettingsDisplayed() {
        clickSettingsOfGroup();
        return (elementExistWaitLongTime(CAPITAL_MKT_ASSUMPTIONS_MENU_OPTION)
                && elementExistWaitLongTime(DEFAULT_VALUES_MENU_OPTION)
                && elementExistWaitLongTime(INTEGRATIONS_MENU_OPTION)
                && areChildOfSettingsWithinGroupsMenuEnterpriseOption());
    }

    public boolean areFirmAdminAccountOptionsDisplayed() {
        clickAccount();
        return (elementExistWaitLongTime(ACCOUNT_FIRM_INFORMATION_MENU_OPTION)
                && elementExistWaitLongTime(ACCOUNT_BILLING_AND_PAYMENTS_MENU_OPTION));
    }

    public boolean isLogoutOptionDisplayed() {
        super.waitForElementClickableLong(LOGOUT_BUTTON);
        return elementExistWaitLongTime(LOGOUT_BUTTON);
    }

    public boolean areFirmAdminOptionsDisplayed() {
        return (areFirmAdminMenuOptionsDisplayed()
                && areFirmAdminSettingsDisplayed()
                && areOptionWithinGroupSettingsDisplayed()
                && areFirmAdminAccountOptionsDisplayed()
                && areFirmAdminInfoOfGroupDisplayed());
    }

    public boolean isEnabledDashBoardLabel() {
        super.waitForElements(Constant.MEDIUM_TIMEOUT);
        super.waitForElementPresentLong(DASHBOARD_LABEL);
        return super.isElementEnabled(DASHBOARD_LABEL);
    }
}

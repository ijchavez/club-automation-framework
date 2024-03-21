package Pages;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.HelperMethods;
import utils.constants.Constant;

public class MenuPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@id='sidenav-collapse-main']")
    private WebElement NAV_BAR;
    @FindBy(how = How.XPATH, using = "//*[@class='navbar-nav']//*[contains(text(),'Categorias')]")
    private WebElement CATEGORY_MENU;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'ni-button-power')]")
    private WebElement LOGOUT_BUTTON;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'text-uppercase')][contains(text(),'Total de contribuciones')]")
    private WebElement DASHBOARD_LABEL;
    HelperMethods helperMethods;

    public MenuPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
        helperMethods = new HelperMethods(driver);
    }

    public boolean isMenuPageDisplayed() {
        helperMethods.waitForElementClickableLong(LOGOUT_BUTTON);
        helperMethods.waitForElementPresentLong(NAV_BAR);
        return helperMethods.elementExistWaitLongTime(NAV_BAR);
    }

    public boolean isHomePageDisplayed() {
        helperMethods.waitForElements(Constant.MEDIUM_TIMEOUT);
        helperMethods.waitForElementClickableLong(LOGOUT_BUTTON);
        return isMenuPageDisplayed();
    }

    public boolean isLogoutOptionDisplayed() {
        helperMethods.waitForElementClickableLong(LOGOUT_BUTTON);
        return helperMethods.elementExistWaitLongTime(LOGOUT_BUTTON);
    }

    public boolean isEnabledDashBoardLabel() {
        helperMethods.waitForElements(Constant.MEDIUM_TIMEOUT);
        helperMethods.waitForElementPresentLong(DASHBOARD_LABEL);
        return helperMethods.isElementEnabled(DASHBOARD_LABEL);
    }

    public void clickCategoryOption() {
        helperMethods.waitForElements(Constant.MEDIUM_TIMEOUT);
        helperMethods.waitForElementPresentLong(DASHBOARD_LABEL);
        helperMethods.clickWithActions(CATEGORY_MENU);
    }
}

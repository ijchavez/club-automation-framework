package utils;

import Pages.Login.LoginPage;
import Pages.MenuPage;
import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private WebDriver driver;
    Scenario scenario;
    private LoginPage loginObj;
    private MenuPage menuPage;

    public DriverFactory() {
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void InitializePageObject(WebDriver driver, Scenario scenario) {
        setLoginObj(new LoginPage(driver, scenario));
        setMenuPage(new MenuPage(driver, scenario));
    }

    public LoginPage getLoginObj() {
        return loginObj;
    }

    public void setLoginObj(LoginPage loginObj) {
        this.loginObj = loginObj;
    }

    public MenuPage getMenuPage() {
        return menuPage;
    }

    public void setMenuPage(MenuPage menuPage) {
        this.menuPage = menuPage;
    }
}

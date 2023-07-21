package utils;

import Pages.Category.CategoryPage;
import Pages.Login.LoginPage;
import Pages.MenuPage;
import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private WebDriver driver;
    private LoginPage loginPage;
    private MenuPage menuPage;
    private CategoryPage categoryPage;

    public DriverFactory() {
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void InitializePageObject(WebDriver driver, Scenario scenario) {
        setLoginPage(new LoginPage(driver, scenario));
        setMenuPage(new MenuPage(driver, scenario));
        setCategoryPage(new CategoryPage(driver, scenario));
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public MenuPage getMenuPage() {
        return menuPage;
    }

    public void setMenuPage(MenuPage menuPage) {
        this.menuPage = menuPage;
    }

    public CategoryPage getCategoryPage() {
        return categoryPage;
    }

    public void setCategoryPage(CategoryPage categoryPage) {
        this.categoryPage = categoryPage;
    }
}

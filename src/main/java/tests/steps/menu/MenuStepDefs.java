package tests.steps.menu;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import utils.DriverFactory;
import utils.enums.UserRole;

public class MenuStepDefs {
    DriverFactory driverFactory;

    public MenuStepDefs(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    @Then("^the Home page is displayed$")
    public void validateHomePageIsDisplayed() {
        Assert.assertTrue("Menu page is not displayed", this.driverFactory.getMenuPage().isMenuPageDisplayed());
    }

    @And("^\"([^\"]*)\" options are displayed$")
    public void validateOptionsAreDisplayed(UserRole role) {
        Assert.assertTrue("The: " + role + " options are not displayed", this.driverFactory.getMenuPage().areRoleOptionsDisplayed(role));
        Assert.assertTrue("The logout button is not displayed", this.driverFactory.getMenuPage().isLogoutOptionDisplayed());
    }
}

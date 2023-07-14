package tests.steps.login;

import cucumber.api.java.en.*;
import endpoints.Firm.FirmEndpoint;
import endpoints.login.LoginPageEndpoint;
import org.junit.Assert;
import utils.DriverFactory;
import utils.constants.DataConstant;
import utils.RequestFactory;
import utils.context.ScenarioContextInfoHolder;
import utils.enums.RegisterAccounts;
import utils.enums.UserRole;

import java.util.ArrayList;

public class LoginStepDefs {
    LoginPageEndpoint loginPageEndpoint;
    FirmEndpoint firmEndpoint;
    ScenarioContextInfoHolder context;
    RequestFactory requestFactory;
    DriverFactory driverFactory;

    public LoginStepDefs(ScenarioContextInfoHolder context, RequestFactory requestFactory, DriverFactory driverFactory) {
        this.context = context;
        this.requestFactory = requestFactory;
        this.driverFactory = driverFactory;
        this.loginPageEndpoint = new LoginPageEndpoint(requestFactory);
        this.firmEndpoint = new FirmEndpoint(requestFactory);
    }

    /**
     * This anAuthorizationUser method trigger the login request and store in the context each value necessary
     *
     * @param registerAccounts it's the mail associated with the account
     */
    @Given("^An \"([^\"]*)\" is logged in the system$")
    public void anAuthorizationUser(RegisterAccounts registerAccounts) {
        this.requestFactory.setToken(loginPageEndpoint.anAuthorizedUserLogged(registerAccounts));
    }

    @Then("^A list of members are available")
    public void validateListOfMembers() {
        Assert.assertTrue("The members list is empty", loginPageEndpoint.listOfMembers(this.context.getScenarioContext(DataConstant.FIRM_ID)));
    }

    @When("^User completes Email field with \"([^\"]*)\" credentials$")
    public void completeEmailFieldWithCredentials(UserRole role) {
        this.driverFactory.getLoginObj().completeEmail(role);
    }

    @And("^User completes Password field with \"([^\"]*)\" credentials$")
    public void completePasswordFieldWithRoleCredentials(UserRole role) {
        this.driverFactory.getLoginObj().completePassword(role);
    }

    @Given("^Login page is displayed$")
    public void validateLoginPageIsDisplayed() {
        Assert.assertTrue("Login page is not displayed", this.driverFactory.getLoginObj().isLoginPageDisplayed());
    }

    @And("^User clicks on Login button$")
    public void clickOnLoginButton() {
        this.driverFactory.getLoginObj().clickLoginBtn();
    }
}

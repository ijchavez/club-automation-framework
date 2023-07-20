package tests.steps.login;

import cucumber.api.java.en.*;
import endpoints.login.LoginEndpoint;
import org.junit.Assert;
import utils.DriverFactory;
import utils.RequestFactory;
import utils.context.ScenarioContextInfoHolder;
import utils.enums.RegisterAccounts;
import utils.enums.UserRole;

public class LoginStepDefs {
    LoginEndpoint loginEndpoint;
    ScenarioContextInfoHolder context;
    RequestFactory requestFactory;
    DriverFactory driverFactory;

    public LoginStepDefs(ScenarioContextInfoHolder context, RequestFactory requestFactory, DriverFactory driverFactory) {
        this.context = context;
        this.requestFactory = requestFactory;
        this.driverFactory = driverFactory;
        this.loginEndpoint = new LoginEndpoint(requestFactory);
    }

    /**
     * This anAuthorizationUser method trigger the login request and store in the context each value necessary
     *
     * @param registerAccounts it's the mail associated with the account
     */
    @Given("^An \"([^\"]*)\" is logged in the system$")
    public void anAuthorizationUser(RegisterAccounts registerAccounts) {
        this.requestFactory.setToken(loginEndpoint.anAuthorizedUserLogged(registerAccounts));
    }

    @When("^User completes Email field with \"([^\"]*)\" credentials$")
    public void completeEmailFieldWithCredentials(UserRole role) {
        this.driverFactory.getLoginPage().completeEmail(role);
    }

    @When("^User completes Email field with any credentials \"([^\"]*)\"$")
    public void completeEmailFieldWithAnyCredentials(String user) {
        this.driverFactory.getLoginPage().completeEmail(user);
    }

    @And("^User completes Password field with \"([^\"]*)\" credentials$")
    public void completePasswordFieldWithRoleCredentials(UserRole role) {
        this.driverFactory.getLoginPage().completePassword(role);
    }

    @And("^User completes Password field with any credentials \"([^\"]*)\"$")
    public void completePasswordFieldWithAnyCredentials(String password) {
        this.driverFactory.getLoginPage().completePassword(password);
    }

    @Given("^Login page is displayed$")
    public void validateLoginPageIsDisplayed() {
        Assert.assertTrue("Login page is not displayed", this.driverFactory.getLoginPage().isLoginPageDisplayed());
    }

    @And("^User clicks on Login button$")
    public void clickOnLoginButton() {
        this.driverFactory.getLoginPage().clickLoginBtn();
    }

    @And("^the Login button is \"([^\"]*)\"$")
    public void isLoginButtonDisabled(String statusExpected) {
        boolean status = this.driverFactory.getLoginPage().loginButtonVisibility();
        Assert.assertFalse("The Login button visibility expected was false and the current is: " + status, status);
    }

    @And("^the Toast message is Displayed$")
    public void isToastMessageDisplayed() {
        Assert.assertTrue("The Login Page should display the Toast message", this.driverFactory.getLoginPage().isToastMessageDisplayed());
    }
}

package tests.steps.category;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import endpoints.category.CategoryTypeEndpoint;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.DriverFactory;
import utils.RequestFactory;
import utils.constants.DataConstant;
import utils.context.ScenarioContextInfoHolder;

public class CategoryStepDefs {
    private Response responseDeleteCategory;
    CategoryTypeEndpoint categoryTypeEndpoint;
    ScenarioContextInfoHolder context;
    RequestFactory requestFactory;
    DriverFactory driverFactory;

    public CategoryStepDefs(ScenarioContextInfoHolder context, RequestFactory requestFactory, DriverFactory driverFactory) {
        this.context = context;
        this.requestFactory = requestFactory;
        this.driverFactory = driverFactory;
        this.categoryTypeEndpoint = new CategoryTypeEndpoint(requestFactory);
    }

    @When("^User adds a new \"([^\"]*)\" category$")
    public void userAddsANewCategory(String categoryType) {
        Response categoryResponse = categoryTypeEndpoint.addCategory();
        this.context.setScenarioContext(DataConstant.CURRENT_CATEGORY_ID, JsonPath.from(categoryResponse.asString()).getString("id"));
    }

    @And("^User wipes the categories$")
    public void userWipesTheCategories() {
        categoryTypeEndpoint.wipeOldCategories();
    }

    @And("^User deletes the current category$")
    public void userDeletesTheCurrentCategory() {
        responseDeleteCategory = categoryTypeEndpoint.deleteCategory(this.context.getScenarioContext(DataConstant.CURRENT_CATEGORY_ID));
    }

    @Then("^the category was delete correctly$")
    public void theCategoryWasDeleteCorrectly() {
        Assert.assertEquals("The status code expected was" + DataConstant.STATUS_200, DataConstant.STATUS_200, responseDeleteCategory.getStatusCode());
    }
}

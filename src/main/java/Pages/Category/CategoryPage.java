package Pages.Category;

import Pages.BasePage;
import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.HelperMethods;
import utils.constants.Constant;

import java.util.List;

public class CategoryPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@class='card shadow']//*[contains(text(),'Tipos de categorías')]")
    private WebElement TITLE;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'btn-primary')][contains(text(),'Adicionar')]")
    private WebElement CATEGORY_ADD_BUTTON;
    @FindBy(how = How.XPATH, using = "//*[@class='dropdown']")
    private WebElement CATEGORY_OPTIONS_DOTS;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'dropdown')]//*[text()='Eliminar']")
    private WebElement CATEGORY_OPTIONS_DELETE;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'dropdown')]//*[text()='Modificar']")
    private WebElement CATEGORY_OPTIONS_EDIT;
    @FindBy(how = How.XPATH, using = "//*[contains(@role,'dialog')]//*[contains(text(),'Adicionar tipo de categoría')]")
    private WebElement CATEGORY_ADD_MODAL;
    @FindBy(how = How.XPATH, using = "//*[contains(@role,'dialog')]//*[contains(text(),'Actualizar tipo de categoría')]")
    private WebElement CATEGORY_EDIT_MODAL;
    @FindBy(how = How.XPATH, using = "//*[@id='input-username']")
    private WebElement CATEGORY_NAME_MODAL_FIELD;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'mat-dialog-container')]//*[contains(@class,'btn-primary')]")
    private WebElement CATEGORY_ACCEPT_MODAL_BUTTON;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'mat-dialog-container')]//*[contains(@class,'btn-neutral')]")
    private WebElement CATEGORY_CANCEL_MODAL_BUTTON;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'text-uppercase')][contains(text(),'Total de contribuciones')]")
    private WebElement DASHBOARD_LABEL;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'table-responsive')]//*[@class='ng-star-inserted']/*")
    private List<WebElement> CATEGORY_LIST;
    @FindBy(how = How.XPATH, using = "//*[@role='dialog']//*[contains(text(),'Esta usted seguro que desea eliminar')]")
    private WebElement CATEGORY_DELETE_MODAL;
    @FindBy(how = How.XPATH, using = "//*[@class='mat-button-wrapper'][text()='Sí']")
    private WebElement CATEGORY_DELETE_YES_BUTTON;
    @FindBy(how = How.XPATH, using = "//*[@class='mat-button-wrapper'][text()='No']")
    private WebElement CATEGORY_DELETE_NO_BUTTON;
    String categoryName = "";
    Scenario scenario;
    WebDriver driver;
    HelperMethods helperMethods;

    public CategoryPage(WebDriver driver, Scenario scenario) {
        super(driver);
        this.driver = driver;
        this.scenario = scenario;
        helperMethods = new HelperMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCategoryPageDisplayed() {
        helperMethods.waitForElementPresentLong(TITLE);
        return helperMethods.elementExistWaitLongTime(TITLE);
    }

    public boolean isEnabledDashBoardLabel() {
        helperMethods.waitForElements(Constant.MEDIUM_TIMEOUT);
        helperMethods.waitForElementPresentLong(DASHBOARD_LABEL);
        return helperMethods.isElementEnabled(DASHBOARD_LABEL);
    }

    public void clickCategoryOptions() {
        helperMethods.waitForElements(Constant.MEDIUM_TIMEOUT);
        helperMethods.waitForElementPresentLong(CATEGORY_OPTIONS_DOTS);
        helperMethods.clickWithActions(CATEGORY_OPTIONS_DOTS);
    }

    public void clickCategoryAddButton() {
        helperMethods.waitForElements(Constant.MEDIUM_TIMEOUT);
        helperMethods.waitForElementPresentLong(CATEGORY_ADD_BUTTON);
        helperMethods.clickWithActions(CATEGORY_ADD_BUTTON);
    }

    public boolean isAddCategoryModalDisplayed() {
        helperMethods.waitForElements(Constant.MEDIUM_TIMEOUT);
        return helperMethods.isElementEnabled(CATEGORY_ADD_MODAL);
    }

    public void categoryName() {
        categoryName = "Automation" + HelperMethods.randomString(5);
        helperMethods.sendKeysInElement(CATEGORY_NAME_MODAL_FIELD, categoryName);
    }

    public void clickAcceptModalButtonCategory() {
        helperMethods.waitForElementClickableLong(CATEGORY_ACCEPT_MODAL_BUTTON);
        helperMethods.clickWithActions(CATEGORY_ACCEPT_MODAL_BUTTON);
    }

    public void clickCancelModalButtonCategory() {
        helperMethods.waitForElementClickableLong(CATEGORY_CANCEL_MODAL_BUTTON);
        helperMethods.clickWithActions(CATEGORY_CANCEL_MODAL_BUTTON);
    }

    public void createCategory() {
        categoryName();
        clickAcceptModalButtonCategory();
    }

    public boolean isCategoryDisplayed() {
        for (WebElement element : CATEGORY_LIST) {
            if (element.getText().equalsIgnoreCase(categoryName)) {
                return true;
            }
        }
        return false;
    }

    public void deleteCategory(boolean deleteCategory) {
        helperMethods.waitForElements(Constant.SHORT_TIMEOUT);
        do {
            helperMethods.clickWithActions(CATEGORY_OPTIONS_DOTS);
        }
        while (!helperMethods.isElementPresent(CATEGORY_OPTIONS_DELETE));
        do {
            helperMethods.clickWithActions(CATEGORY_OPTIONS_DELETE);
        }
        while (!helperMethods.isElementPresent(CATEGORY_DELETE_MODAL));
        if (deleteCategory) {
            helperMethods.clickWithActions(CATEGORY_DELETE_YES_BUTTON);
        } else {
            helperMethods.clickWithActions(CATEGORY_DELETE_NO_BUTTON);
        }
    }

    public boolean isListOfCategoryEmpty() {
        return CATEGORY_LIST.isEmpty();
    }
}

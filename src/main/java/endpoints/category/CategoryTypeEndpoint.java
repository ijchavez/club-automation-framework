package endpoints.category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import endpoints.BaseEndpoint;
import entities.category.CategoryTypePayload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CategoryTypeEndpoint extends BaseEndpoint {
    protected RequestFactory requestFactory;

    public CategoryTypeEndpoint(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public Response addCategory(String categoryParentId) {
        CategoryTypePayload categoryPayload = new CategoryTypePayload("Automation Category", categoryParentId);
        return getResponseCategoryType(categoryPayload);
    }

    /**
     * this addCategory method prepare the category payload and name to pass through the
     * getResponseCategoryType method by parameters
     *
     * @return
     */
    public Response addCategory() {
        CategoryTypePayload categoryPayload = new CategoryTypePayload("Automation Category");
        return getResponseCategoryType(categoryPayload);
    }

    /**
     * this getResponseCategoryType send a post request to create a category
     *
     * @param categoryPayload it's the body of the request to create the category
     * @return the response of the request
     */
    private Response getResponseCategoryType(CategoryTypePayload categoryPayload) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return this.requestFactory.makeRequest().body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoryPayload))
                    .post(DataConstantQueries.PATH_CATEGORY_CREATE);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * this deleteCategory method send a delete request to delete a category by id
     *
     * @param categoryTypeId it's the category id
     * @return
     */
    public Response deleteCategory(String categoryTypeId) {
        return this.requestFactory.makeRequest().body("{}")
                .delete(DataConstantQueries.PATH_DELETE_CATEGORY_TYPE + categoryTypeId);
    }

    /**
     * this getAllCategories method send a get request to obtains all categories
     *
     * @return the response of the request
     */
    public Response getAllCategories() {
        return this.requestFactory.makeRequest()
                .get(DataConstantQueries.QUERY_TO_GET_ALL_CATEGORIES);
    }

    public Map<String, ?> getCurrentCategory() {
        List<HashMap<String, ?>> listOfGroupsIdFromSpecificFirm = JsonPath.from(getAllCategories().asString()).get();
        HashMap<String, ?> stringHashMap = listOfGroupsIdFromSpecificFirm.get(listOfGroupsIdFromSpecificFirm.size() - 1);
        return stringHashMap;
    }

    /**
     * this wipeOldCategories rerun the category list and delete the category who contains the automation name
     */
    public void wipeOldCategories() {
        List<LinkedHashMap<String, ?>> listOfGroupsIdFromSpecificFirm = JsonPath.from(getAllCategories().asString()).get();
        for (LinkedHashMap<String, ?> stringHashMap : listOfGroupsIdFromSpecificFirm) {
            if (stringHashMap.get("name").toString().contains("Automation")) {
                deleteCategory((String) stringHashMap.get("id"));
            }
        }
    }

    public Response getCategoryById(String categoryId) {
        return this.requestFactory.makeRequest()
                .get(DataConstantQueries.QUERY_TO_GET_ALL_CATEGORIES + categoryId);
    }
}

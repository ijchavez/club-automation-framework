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

public class CategoryTypeEndpoint extends BaseEndpoint {
    protected RequestFactory requestFactory;

    public CategoryTypeEndpoint(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public Response addCategory(String categoryParentId) {
        CategoryTypePayload categoryPayload = new CategoryTypePayload("Automation Category", categoryParentId);
        return getResponseCategoryType(categoryPayload);
    }

    public Response addCategory() {
        CategoryTypePayload categoryPayload = new CategoryTypePayload("Automation Category");
        return getResponseCategoryType(categoryPayload);
    }

    private Response getResponseCategoryType(CategoryTypePayload categoryPayload) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response response = this.requestFactory.makeRequest().body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoryPayload))
                    .post(DataConstantQueries.PATH_CATEGORY_CREATE);
            return response;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Response deleteCategory(String categoryTypeId) {
        return this.requestFactory.makeRequest().body("{}")
                .delete(DataConstantQueries.PATH_DELETE_CATEGORY_TYPE + categoryTypeId);
    }

    public Response getAllCategories() {
        return this.requestFactory.makeRequest()
                .get(DataConstantQueries.QUERY_TO_GET_ALL_CATEGORIES);
    }

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

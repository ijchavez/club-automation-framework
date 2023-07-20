package endpoints.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import endpoints.BaseEndpoint;
import entities.category.CategoryTypePayload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;

public class MemberEndpoint extends BaseEndpoint {
    protected RequestFactory requestFactory;

    public MemberEndpoint(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public Response addCategory(String categoryParentId) {
        CategoryTypePayload categoryPayload = new CategoryTypePayload("", categoryParentId);
        return getResponseCategoryType(categoryPayload);
    }

    public Response addCategory() {
        CategoryTypePayload categoryPayload = new CategoryTypePayload("");
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

    public int deleteCategory(Long categoryTypeId) {
        return JsonPath.from(this.requestFactory.makeRequest().body("{}")
                .delete(DataConstantQueries.PATH_DELETE_CATEGORY_TYPE + categoryTypeId.toString()).asString()).getBoolean("success") ? 200 : 400;
    }

    public Response getAllCategories() {
        return this.requestFactory.makeRequest()
                .get(DataConstantQueries.QUERY_TO_GET_ALL_CATEGORIES);
    }

    public Response getCategoryById(String categoryId) {
        return this.requestFactory.makeRequest()
                .get(DataConstantQueries.QUERY_TO_GET_ALL_CATEGORIES + categoryId);
    }
}

package endpoints.user;

import endpoints.BasePageEndpoint;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;

import java.util.List;

public class UserEndpoint extends BasePageEndpoint {
    RequestFactory requestFactory;

    public UserEndpoint(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    /**
     * The listCurrentUserPermissions method make a get request to obtain the permissions related with the User logged
     *
     * @return the list of permissions related with the user logged.
     */
    public List<String> listCurrentUserPermissions() {
        Response response = this.requestFactory.makeRequest().get(DataConstantQueries.QUERY_TO_GET_CURRENT_USER_PERMISSIONS);
        return JsonPath.from(response.asString()).getList("Permissions");
    }
}

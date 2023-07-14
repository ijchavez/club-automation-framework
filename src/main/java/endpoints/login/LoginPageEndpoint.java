package endpoints.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.login.LoginPayload;
import io.restassured.path.json.JsonPath;
import endpoints.BasePageEndpoint;
import io.restassured.response.Response;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;
import utils.enums.RegisterAccounts;

import java.util.LinkedHashMap;

public class LoginPageEndpoint extends BasePageEndpoint {
    protected RequestFactory requestFactory;

    public LoginPageEndpoint(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    /**
     * The anAuthorizedUserLogged method makes a request to log in with an account that was passed via parameters
     *
     * @param registerAccounts it's the account to be logged
     * @return the login token.
     */
    public String anAuthorizedUserLogged(RegisterAccounts registerAccounts) {
        LoginPayload loginPayload = new LoginPayload(registerAccounts);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response response = this.requestFactory.makeRequest(false).body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(loginPayload))
                    .post(DataConstantQueries.PATH_TO_LOGIN);
            return JsonPath.from(response.asString()).getString("token");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * The listOfMembers method make a get request to get the member from one specific firm
     *
     * @param firmId is the firm's id to get their members
     * @return true if the status was 200 or the list is not empty, else is false
     */
    public boolean listOfMembers(String firmId) {
        Response response = this.requestFactory.makeRequest().get("/odata/Firms(" + firmId + ")" + DataConstantQueries.QUERY_TO_GET_MEMBERS);
        Integer numberOfMembers = ((LinkedHashMap<String, Integer>) JsonPath.from(response.asString()).get()).get("@odata.count");
        return numberOfMembers > 0 || 200 == response.statusCode();
    }
}

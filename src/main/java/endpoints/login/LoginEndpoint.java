package endpoints.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.login.LoginPayload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;
import utils.enums.RegisterAccounts;

public class LoginEndpoint {
    protected RequestFactory requestFactory;

    public LoginEndpoint(RequestFactory requestFactory) {
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
}

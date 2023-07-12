package endpoints.household.householdInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import endpoints.household.CreateHouseholdEndpoint;
import io.restassured.response.Response;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;

import java.util.List;
import java.util.Map;

public class HouseholdInfoEndpoint extends CreateHouseholdEndpoint {
    protected RequestFactory requestFactory;

    public HouseholdInfoEndpoint(RequestFactory requestFactory) {
        super(requestFactory);
        this.requestFactory = requestFactory;
    }

    /**
     * The  getHouseholdList method make a request to get the list of household
     *
     * @return the household list
     */
    public List<Map<String, Integer>> getHouseholdList() {
        Response response = this.requestFactory.makeRequest().get(DataConstantQueries.PATH_FOR_HH);
        return JsonPath.from(response.asString()).get("result");
    }

    /**
     * The accessToHousehold method make a request trying to get the Household menu
     *
     * @param householdId household id
     * @return the status code number
     */
    public int accessToHousehold(Long householdId) {
        Response response = this.requestFactory.makeRequest().get(DataConstantQueries.PATH_FOR_HH + householdId.toString() + "/menu");
        return response.statusCode();
    }


    /**
     * This requestCreateHousehold method trigger the request to create a HH since legacy,flat or enterprise
     *
     * @param householdPayload it's the payload related with the HH
     * @return the Household's id
     */
    public Long requestCreateHousehold(Object householdPayload) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String payloadFormatted = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(householdPayload);
            Response response = this.requestFactory.makeRequest().body(payloadFormatted)
                    .post(DataConstantQueries.PATH_TO_CREATE_HH);
            String jsonString = response.asString();
            return JsonPath.from(jsonString).getLong("result.id");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * The removeHouseholdFromList method make a delete request to delete the specific Household taken the household id from the params.
     *
     * @param householdId is the household's id to delete
     * @return the status code from the request.
     */
    public int removeHouseholdFromList(Long householdId) {
        Response response = this.requestFactory.makeRequest().body("{}")
                .delete(DataConstantQueries.PATH_FOR_HH + householdId.toString());
        return response.statusCode();
    }

    /**
     * The isHouseholdPlanInfoRemoved method make a request to get the scenario associated with one HH passed through the params
     *
     * @param householdId the household's id
     * @return the status code from the request
     */
    public int isHouseholdPlanInfoRemoved(Long householdId) {
        Response response = this.requestFactory.makeRequest().get(DataConstantQueries.PATH_FOR_SCENARIO + householdId.toString());
        return response.getStatusCode();
    }
}

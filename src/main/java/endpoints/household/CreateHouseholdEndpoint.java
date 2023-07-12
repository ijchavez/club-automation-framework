package endpoints.household;

import endpoints.BasePageEndpoint;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;

import static utils.HelperMethods.specificDate;

public class CreateHouseholdEndpoint extends BasePageEndpoint {
    protected RequestFactory requestFactory;

    public CreateHouseholdEndpoint(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public int isHouseholdPlanInfoAdded(Long householdId) {
        return this.requestFactory.makeRequest().get(DataConstantQueries.PATH_FOR_SCENARIO + householdId.toString()).statusCode();
    }

    /**
     * The isHouseholdPlanAdded method make a get request to get the plans scenarios related with the Household id passed
     * parameters via
     *
     * @param householdId it's the household's id
     * @return return the status code from this request
     */
    public int isHouseholdPlanAdded(Long householdId) {
        return this.requestFactory.makeRequest().get(DataConstantQueries.PATH_FOR_SCENARIO + householdId.toString()).getStatusCode();
    }

    public int setRunMonth(Integer householdId) {
        String path = DataConstantQueries.PATH_FOR_HH + householdId.toString() + "/set-run-month/" + specificDate(1, 1);
        return this.requestFactory.makeRequest().put(path).getStatusCode();
    }

    /**
     * The reassignHousehold method make a post request to reassign one household to another advisor or firm
     *
     * @param userId      it's the User where the household will be moved.
     * @param householdId it's the household's id to be reassigned
     * @return the status code of request
     */
    public int reassignHousehold(Long userId, Long householdId) {
        try {
            return this.requestFactory.makeRequest().body("[\n" + householdId.toString() + "\n]")
                    .post("/api/firm/" + userId.toString() + "/reassign-households").statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

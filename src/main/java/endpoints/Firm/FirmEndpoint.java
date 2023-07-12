package endpoints.Firm;

import endpoints.BasePageEndpoint;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;

import java.util.*;

public class FirmEndpoint extends BasePageEndpoint {
    RequestFactory requestFactory;

    public FirmEndpoint(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    /**
     * The listOfGroups method makes a get request to obtains the firm's groups with (Id,Name,FirmId,ParentGroupId)
     *
     * @return the list with this information
     */
    public List<Map<String, Integer>> listOfGroups() {
        Response response = this.requestFactory.makeRequest().get(DataConstantQueries.QUERY_TO_GET_GROUPS);
        return JsonPath.from(response.asString()).get("value");
    }

    /**
     * The listOfGroups method makes a get request to obtains the firm's groups with (ids)
     *
     * @return the list with this information
     */
    public List<Integer> listOfGroupsIds() {
        Response response = this.requestFactory.makeRequest().get(DataConstantQueries.QUERY_TO_GET_GROUPS);
        return JsonPath.from(response.asString()).getList("value.Id");
    }

    public boolean isTheListOfGroupEmpty() {
        return !listOfGroups().isEmpty();
    }

    /**
     * The getParentIdOfGroup method run into the list of groups to get the ParentGroupId
     * get the first match
     *
     * @return the ParentGroupId
     */
    public Long getParentIdOfGroup() {
        return Long.valueOf(listOfGroups()
                .stream()
                .map(group -> group.get("ParentGroupId"))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(0));
    }

    /**
     * The firmId method make a request to get the firmId from the current user logged
     *
     * @return the firmId
     */
    public Long firmId() {
        Response response = this.requestFactory.makeRequest().get(DataConstantQueries.QUERY_TO_GET_CURRENT_USER_FIRM_ID);
        return JsonPath.from(response.asString()).getLong("FirmId");
    }

    /**
     * The currentUserId method make a request to get the Current user id
     *
     * @return returns the User id from the User who its logged.
     */
    public Long currentUserId() {
        Response response = this.requestFactory.makeRequest().get(DataConstantQueries.QUERY_TO_GET_CURRENT_USER_ID);
        return JsonPath.from(response.asString()).getLong("Id");
    }

    /**
     * The getListOfGroupsIdFromSpecificFirm method make a get request to obtain the
     *
     * @param firmId is the firm's id to create the endpoint
     * @return a List of maps who contains every group from a FirmAdmin
     */
    public List<HashMap<String, ?>> getListOfGroupsIdFromSpecificFirm(Long firmId) {
        Response response = this.requestFactory.makeRequest().get("/odata/Firms(" + firmId.toString() + ")" + DataConstantQueries.QUERY_TO_GET_GROUPS_BELONGED);
        return JsonPath.from(response.asString()).getList("value");
    }

    /**
     * This method run the list of groups until find the goal ID (currentUserId) from the current User(FirmAdmin member)
     * and from this specific Member Map obtain the list of group belong it
     *
     * @param firmId        it's the firm's id
     * @param currentUserId it's the current user id registered
     * @return return the list of groups from a specific user
     */
    public List<HashMap<String, ?>> ListOfGroupsFirmBelong(Long firmId, Long currentUserId) {
        List<HashMap<String, ?>> listOfGroupsIdFromSpecificFirm = getListOfGroupsIdFromSpecificFirm(firmId);
        return listOfGroupsIdFromSpecificFirm
                .parallelStream()
                .filter(group -> Long.valueOf(group.get("Id").toString()).equals(currentUserId))
                .map(map -> (List<HashMap<String, ?>>) map.get("Groups"))
                .findFirst()
                .orElse(listOfGroupsIdFromSpecificFirm);
    }

    /**
     * The isUserPresentInExpectedGroups method compare the list of groups from the logged FirmAdmin against the current list of groups
     * where the current user might stay (Firm Admin) belong it.
     *
     * @param groupId       it's the group's id
     * @param currentUserId it's the current user id registered
     * @return true if the lists has the same size, else return false.
     */
    public boolean isUserPresentInExpectedGroups(Long groupId, Long currentUserId) {
        return listOfGroups().size() - 1 == ListOfGroupsFirmBelong(groupId, currentUserId).size();
    }

    /**
     * The deleteGroups() Method removes all groups belonging to a firm, ignoring the root and private groups.
     */
    public void deleteGroups() {
        listOfGroups().stream()
                .filter(map -> !isPrivateOrRootGroup(map))
                .forEach(map -> this.requestFactory.makeRequest()
                        .body("{replacementGroup : null}")
                        .delete("/api/Groups/" + map.get("Id")));
    }

    /**
     * The isPrivateOrRootGroup method search into the whole map looking for the groups that contains in their names
     * private group or Root.
     *
     * @param map it's the map's groups where will be checked
     * @return true if the group in this part of the map contains the name private or root
     */
    private boolean isPrivateOrRootGroup(Map<String, ?> map) {
        String name = map.get("Name").toString();
        return name.contains("private group") || name.contains("Root");
    }

    /**
     * The isTheGroupDisplayed method search into the list of group looking for one specific group
     *
     * @param currentGroupId it's the group's id when was created.
     * @return true is the group's id passed through parameters is present in the group list, else false
     */
    public boolean isTheGroupDisplayed(Integer currentGroupId) {
        return listOfGroups().stream()
                .anyMatch(map -> Objects.equals(map.get("Id"), currentGroupId));
    }
}

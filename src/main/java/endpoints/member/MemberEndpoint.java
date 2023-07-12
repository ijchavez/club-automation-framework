package endpoints.member;

import endpoints.BasePageEndpoint;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.HelperMethods;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;

public class MemberEndpoint extends BasePageEndpoint {
    protected RequestFactory requestFactory;
    HelperMethods helperMethods;

    public MemberEndpoint(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        helperMethods = new HelperMethods(requestFactory);

    }



    /**
     * The removeMemberFromList method make a delete request to delete one specific member
     *
     * @param memberId it's the id related with the member to be deleted
     * @return the status 200 if the request was success and 400 if not
     */
    public int removeMemberFromList(Long memberId) {
        return JsonPath.from(this.requestFactory.makeRequest(2.0f).body("{}")
                .delete(DataConstantQueries.PATH_FOR_MEMBER + memberId.toString()).asString()).getBoolean("success") ? 200 : 400;
    }

    public Response getMemberInfoFromFirmAdmin(Long firmId, Long memberId) {
        return this.requestFactory.makeRequest()
                .get("/odata/Firms(" + firmId.toString() + ")" + "/Members(" + memberId.toString() + ")" + DataConstantQueries.QUERY_TO_GET_SPECIFIC_MEMBER);
    }
}

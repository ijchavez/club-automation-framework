package utils.constants;

public class DataConstantQueries {
   public static final String QUERY_TO_GET_CURRENT_USER_FIRM_ID = "/odata/CurrentUser?select=firmId";
   public static final String QUERY_TO_GET_CURRENT_USER_INFO = "/odata/CurrentUser";
   public static final String QUERY_TO_GET_CURRENT_USER_ID = "/odata/CurrentUser?select=id";
   public static final String QUERY_TO_GET_GROUPS_BELONGED = "/Members?select=id,name&expand=groups(select=id,name)";
   public static final String QUERY_TO_GET_SPECIFIC_MEMBER = "?select=id,name,surname,EmailAddress&expand=groups(select=id,name)";
   public static final String QUERY_TO_GET_CURRENT_USER_PERMISSIONS = "/odata/CurrentUser?expand=firm,groups";
   public static final String QUERY_TO_GET_GROUPS = "/odata/Groups";
   public static final String QUERY_TO_GET_MEMBERS = "/Members?count=true&top=0";
   public static final String PATH_TO_CREATE_HH = "/api/Authentication/Register/Household";
   public static final String PATH_FOR_HH = "/api/households/";
   public static final  String PATH_FOR_SCENARIO = "/api/planScenarios/";
   public static final  String PATH_TO_LOGIN = "/api/auth/login";
   public static final String PATH_FOR_MEMBER = "/api/Members/";

}

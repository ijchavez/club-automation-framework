package utils.constants;

import utils.ReadProperties;

import static utils.HelperMethods.getPassword;

public class DataConstant {
    public static final String PASSWORD = getPassword("PASSWORD");
    public static final String BASE_URL = ReadProperties.getInstance().getProperty("BASE_URL");
    public static final String PASSWORD_SUPER_ADMIN = getPassword("PASSWORD_SUPERADMIN");
    public static final String EMAIL_SUPER_ADMIN = ReadProperties.getInstance().getProperty("EMAIL_SUPERADMIN");
    public static final String EMAIL_FIRM_ADMIN_ENTERPRISE = "graviel.sosa+firm-enterprise-security@decemberlabs.com";
    public static final String EMAIL_ADVISOR_ENTERPRISE = "graviel.sosa+adv-enterprise-security@decemberlabs.com";
    public static final String FIRM_LEGACY_SECURITY1 = "graviel.sosa+firm-legacy-security@decemberlabs.com";
    public static final String ADV_LEGACY_SECURITY11_FROM_FIRM1 = "graviel.sosa+adv-legacy-security@decemberlabs.com";
    public static final String ADV_LEGACY_SECURITY21_FROM_FIRM1 = "graviel.sosa+adv-legacy-security1@decemberlabs.com";
    public static final String HH_LEGACY_SECURITY121_FROM_ADV21 = "graviel.sosa+hh-legacy-security@decemberlabs.com";
    public static final String HH_LEGACY_SECURITY221_FROM_ADV21 = "graviel.sosa+hh-legacy-security1@decemberlabs.com";
    public static final String FIRM_LEGACY_SECURITY2 = "graviel.sosa+firm-legacy-security1@decemberlabs.com";
    public static final String ADV_LEGACY_SECURITY12_FROM_FIRM2 = "graviel.sosa+adv-legacy-security2@decemberlabs.com";
    public static final String HH_LEGACY_SECURITY112_FROM_ADV12 = "graviel.sosa+hh-legacy-security2@decemberlabs.com";
    public static final String FIRM_FLAT_SECURITY1 = "graviel.sosa+firm-flat-security@decemberlabs.com";
    public static final String ADV_FLAT_SECURITY11_FROM_FIRM1 = "graviel.sosa+adv-flat-securitys@decemberlabs.com";
    public static final String HH_FLAT_SECURITY11_FROM_ADV11 = "graviel.sosa+hh-flat-securitys1@decemberlabs.com";
    public static final String ADV_FLAT_SECURITY21_FROM_FIRM1 = "graviel.sosa+adv-flat-security@decemberlabs.com";
    public static final String HH_FLAT_SECURITY12_FROM_ADV21 = "graviel.sosa+hh-flat-security@decemberlabs.com";
    public static final String HH_FLAT_SECURITY22_FROM_ADV21 = "graviel.sosa+hh-flat-securitys@decemberlabs.com";
    public static final String HH_FLAT_SECURITY31_FROM_FIRM1 = "graviel.sosa+hh-flat-security-firm@decemberlabs.com";
    public static final String FIRM_FLAT_SECURITY2 = "graviel.sosa+firm-flat-security1@decemberlabs.com";
    public static final String ADV_FLAT_SECURITY12_FROM_FIRM2 = "graviel.sosa+adv-flat-security1@decemberlabs.com";
    public static final String HH_FLAT_SECURITY11_FROM_ADV12 = "graviel.sosa+hh-flat-security1@decemberlabs.com";
    public static final String FIRM_LEGACY_LIFE_HUB_PACKAGE = "graviel.sosa+firm-legacy-security-lifehub-package@decemberlabs.com";
    public static final String FIRM_LEGACY_FULL_PACKAGE = "graviel.sosa+firm-legacy-security-full-package@decemberlabs.com";
    public static final String FIRM_ENTERPRISE_SECURITY1 = "graviel.sosa+firm-enterprise-security@decemberlabs.com";
    public static final String ADV_ENTERPRISE_SECURITY11_FROM_FIRM1 = "graviel.sosa+adv-enterprise-security@decemberlabs.com";
    public static final String HH_ENTERPRISE_SECURITY11_FROM_ADV1 = "graviel.sosa+hh-enterprise-security@decemberlabs.com";
    public static final String ADV_ENTERPRISE_SECURITY21_FROM_FIRM1 = "graviel.sosa+adv-enterprise-security2@decemberlabs.com";
    public static final String MEMBER_RESPONSE = "MemberResponse";
    public static final String FIRM_ID = "firmId";
    public static final String CURRENT_GROUP_ID = "currentGroupId";
    public static final String PARENT_GROUP_ID = "ParentGroupId";
    public static final String HOUSEHOLD_ID = "householdId";
    public static final String MEMBER_ID = "memberId";
    public static final String CURRENT_USER_ID = "currentUserId";
    public static final String ARRAY_LIST_OF_GROUP_IDS = "arrayListOfGroupIds";
    public static final String EDIT_MEMBER_RESPONSE = "editMemberResponse";
    public static final long FIRM_ADMIN_ROLE = 2L;
    public static final long MEMBER_ID_GROUP_ONE = 36561L;
    public static final long MEMBER_ID_GROUP_TWO = 36507L;
    public static final long ADVISOR_ROLE = 4L;
    public static final String FIRM_ENTERPRISE_GROUP = "graviel.sosa+firmEnterpriseGroup@decemberlabs.com";
    public static final String ADV_ENTERPRISE_GROUP_ONE = "graviel.sosa+advEnterpriseGroupOne@decemberlabs.com";
    public static final String ADV_ENTERPRISE_GROUP_TWO = "graviel.sosa+advEnterpriseGroupTwo@decemberlabs.com";
}

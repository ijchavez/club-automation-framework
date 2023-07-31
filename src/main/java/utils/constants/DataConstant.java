package utils.constants;

import utils.ReadProperties;

import static utils.HelperMethods.getPassword;

public class DataConstant {
    public static final String PASSWORD = getPassword("PASSWORD");
    public static final String BASE_URL = ReadProperties.getInstance().getProperty("BASE_URL");
    public static final String PASSWORD_SUPER_ADMIN = getPassword("PASSWORD_SUPERADMIN");
    public static final String EMAIL_SUPER_ADMIN = ReadProperties.getInstance().getProperty("EMAIL_SUPERADMIN");
    public static final String EMAIL_ACCOUNT_ADMIN = "graviel.sosa+ort@qubika.com";
    public static final String CURRENT_CATEGORY_ID = "currentCategoryId";
    public static final int STATUS_200 = 200;
}

package entities.login;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import utils.constants.DataConstant;
import utils.enums.RegisterAccounts;

@Generated("net.hexar.json2pojo")
public class LoginPayload {

    @Expose
    private String email;
    @Expose
    private String password;

    public LoginPayload(RegisterAccounts registerAccounts) {
        switch (registerAccounts) {
            case superAdmin:
                this.setEmail(DataConstant.EMAIL_ACCOUNT_ADMIN);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case normalAccount:
                this.setEmail(DataConstant.EMAIL_ACCOUNT_ADMIN);
                this.setPassword(DataConstant.PASSWORD);
                break;
            default:
                break;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

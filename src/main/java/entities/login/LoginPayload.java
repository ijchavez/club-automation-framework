package entities.login;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import utils.constants.DataConstant;
import utils.enums.RegisterAccounts;

@Generated("net.hexar.json2pojo")
public class LoginPayload {

    @Expose
    private String emailAddress;
    @Expose
    private String password;

    public LoginPayload(RegisterAccounts registerAccounts) {
        switch (registerAccounts) {
            case firmLegacySecurity1:
                this.setEmailAddress(DataConstant.FIRM_LEGACY_SECURITY1);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case advLegacySecurity11FromFirm1:
                this.setEmailAddress(DataConstant.ADV_LEGACY_SECURITY11_FROM_FIRM1);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case advLegacySecurity21FromFirm1:
                this.setEmailAddress(DataConstant.ADV_LEGACY_SECURITY21_FROM_FIRM1);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case hhLegacySecurity121FromAdv21:
                this.setEmailAddress(DataConstant.HH_LEGACY_SECURITY121_FROM_ADV21);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case hhLegacySecurity221FromAdv21:
                this.setEmailAddress(DataConstant.HH_LEGACY_SECURITY221_FROM_ADV21);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case firmLegacySecurity2:
                this.setEmailAddress(DataConstant.FIRM_LEGACY_SECURITY2);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case advLegacySecurity12FromFirm2:
                this.setEmailAddress(DataConstant.ADV_LEGACY_SECURITY12_FROM_FIRM2);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case hhLegacySecurity112FromAdv12:
                this.setEmailAddress(DataConstant.HH_LEGACY_SECURITY112_FROM_ADV12);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case firmFlatSecurity1:
                this.setEmailAddress(DataConstant.FIRM_FLAT_SECURITY1);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case advFlatSecurity11FromFirm1:
                this.setEmailAddress(DataConstant.ADV_FLAT_SECURITY11_FROM_FIRM1);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case hhFlatSecurity11FromAdv11:
                this.setEmailAddress(DataConstant.HH_FLAT_SECURITY11_FROM_ADV11);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case advFlatSecurity21FromFirm1:
                this.setEmailAddress(DataConstant.ADV_FLAT_SECURITY21_FROM_FIRM1);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case hhFlatSecurity12FromAdv21:
                this.setEmailAddress(DataConstant.HH_FLAT_SECURITY12_FROM_ADV21);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case hhFlatSecurity22FromAdv21:
                this.setEmailAddress(DataConstant.HH_FLAT_SECURITY22_FROM_ADV21);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case hhFlatSecurity31FromFirm1:
                this.setEmailAddress(DataConstant.HH_FLAT_SECURITY31_FROM_FIRM1);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case firmFlatSecurity2:
                this.setEmailAddress(DataConstant.FIRM_FLAT_SECURITY2);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case advFlatSecurity12FromFirm2:
                this.setEmailAddress(DataConstant.ADV_FLAT_SECURITY12_FROM_FIRM2);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case hhFlatSecurity11FromAdv12:
                this.setEmailAddress(DataConstant.HH_FLAT_SECURITY11_FROM_ADV12);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case firmLegacyLifeHubPackage:
                this.setEmailAddress(DataConstant.FIRM_LEGACY_LIFE_HUB_PACKAGE);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case firmLegacyFullPackage:
                this.setEmailAddress(DataConstant.FIRM_LEGACY_FULL_PACKAGE);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case firmEnterpriseSecurity1:
                this.setEmailAddress(DataConstant.FIRM_ENTERPRISE_SECURITY1);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case advEnterpriseSecurity11FromFirm1:
                this.setEmailAddress(DataConstant.ADV_ENTERPRISE_SECURITY11_FROM_FIRM1);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case firmEnterpriseGroup:
                this.setEmailAddress(DataConstant.FIRM_ENTERPRISE_GROUP);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case advEnterpriseGroupOne:
                this.setEmailAddress(DataConstant.ADV_ENTERPRISE_GROUP_ONE);
                this.setPassword(DataConstant.PASSWORD);
                break;
            case advEnterpriseGroupTwo:
                this.setEmailAddress(DataConstant.ADV_ENTERPRISE_GROUP_TWO);
                this.setPassword(DataConstant.PASSWORD);
                break;
            default:
                break;
        }
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

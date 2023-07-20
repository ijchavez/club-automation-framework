
package entities.member;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

import static utils.HelperMethods.currentDateFull;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MemberPayload {

    @SerializedName("active")
    private Boolean mActive;
    @SerializedName("categoryType")
    private CategoryType mCategoryType;
    @SerializedName("document")
    private String mDocument;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("lastName")
    private String mLastName;
    @SerializedName("startDate")
    private String mStartDate;

    public MemberPayload(CategoryType categoryType, String categoryTypeId, String name) {
        this.setFirstName("automation Member");
        this.setLastName("Sosa");
        this.setDocument("1234567");
        this.setActive(true);
        this.setStartDate(currentDateFull());
        this.setCategoryType(new CategoryType(categoryTypeId, name));
    }

    public Boolean getActive() {
        return mActive;
    }

    public void setActive(Boolean active) {
        mActive = active;
    }

    public CategoryType getCategoryType() {
        return mCategoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        mCategoryType = categoryType;
    }

    public String getDocument() {
        return mDocument;
    }

    public void setDocument(String document) {
        mDocument = document;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

}


package entities.member;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import static utils.HelperMethods.currentDateFull;

@Getter
@Setter
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
        this.setMFirstName("automation Member");
        this.setMLastName("Sosa");
        this.setMDocument("1234567");
        this.setMActive(true);
        this.setMStartDate(currentDateFull());
        this.setMCategoryType(new CategoryType(categoryTypeId, name));
    }
}

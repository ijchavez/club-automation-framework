
package entities.member;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryType {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("parentId")
    private Object mParentId;
    @SerializedName("parentName")
    private Object mParentName;
    @SerializedName("root")
    private Boolean mRoot;

    public CategoryType(String categoryTypeId, String name) {
        this.setMId(categoryTypeId);
        this.setMName(name);
        this.setMRoot(true);
        this.setMParentId(null);
        this.setMParentName(null);
    }
}

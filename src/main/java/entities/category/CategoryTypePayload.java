
package entities.category;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryTypePayload {

    @SerializedName("name")
    private String mName;
    @SerializedName("parentId")
    private String mParentId;
    @SerializedName("root")
    private Boolean mRoot;

    public CategoryTypePayload(String categoryName) {
        this.setMName(categoryName);
        this.setMRoot(true);
    }

    public CategoryTypePayload(String categoryName, String categoryParentId) {
        this.setMName(categoryName);
        this.setMParentId(categoryParentId);
        this.setMRoot(false);
    }
}

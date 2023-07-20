
package entities.category;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CategoryTypePayload {

    @SerializedName("name")
    private String mName;
    @SerializedName("parentId")
    private String mParentId;
    @SerializedName("root")
    private Boolean mRoot;

    public CategoryTypePayload(String categoryName) {
        this.setName(categoryName);
        this.setRoot(true);
    }

    public CategoryTypePayload(String categoryName, String categoryParentId) {
        this.setName(categoryName);
        this.setParentId(categoryParentId);
        this.setRoot(false);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getParentId() {
        return mParentId;
    }

    public void setParentId(String parentId) {
        mParentId = parentId;
    }

    public Boolean getRoot() {
        return mRoot;
    }

    public void setRoot(Boolean root) {
        mRoot = root;
    }

}

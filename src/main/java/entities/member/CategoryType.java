
package entities.member;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
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

    public CategoryType(String categoryTypeId,String name) {
        this.setId(categoryTypeId);
        this.setName(name);
        this.setRoot(true);
        this.setParentId(null);
        this.setParentName(null);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Object getParentId() {
        return mParentId;
    }

    public void setParentId(Object parentId) {
        mParentId = parentId;
    }

    public Object getParentName() {
        return mParentName;
    }

    public void setParentName(Object parentName) {
        mParentName = parentName;
    }

    public Boolean getRoot() {
        return mRoot;
    }

    public void setRoot(Boolean root) {
        mRoot = root;
    }

}

package net.lintasarta.state.model;

/**
 * Created by Joshua
 * Date: Jun 18, 2010
 * Time: 2:56:56 PM
 */
public class UiObject {
    private String objectId;
    private String roleName;
    private String formName;

    public UiObject() {
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}

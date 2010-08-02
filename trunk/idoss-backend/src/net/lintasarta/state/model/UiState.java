package net.lintasarta.state.model;

/**
 * Created by Joshua
 * Date: Jun 18, 2010
 * Time: 4:24:24 PM
 */
public class UiState {
    private String formId;
    private String roleName;
    private String status;

    public UiState() {
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

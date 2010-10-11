package net.lintasarta.pengaduan.model;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 7, 2010
 * Time: 5:46:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class PApplication {
    private Integer p_application_role_id;
    private Integer p_application_user_id;
    private String user_name;
    private String employee_no;
    private String employee_name;
    private String code;

    public PApplication() {
    }

    public Integer getP_application_role_id() {
        return p_application_role_id;
    }

    public void setP_application_role_id(Integer p_application_role_id) {
        this.p_application_role_id = p_application_role_id;
    }

    public Integer getP_application_user_id() {
        return p_application_user_id;
    }

    public void setP_application_user_id(Integer p_application_user_id) {
        this.p_application_user_id = p_application_user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmployee_no() {
        return employee_no;
    }

    public void setEmployee_no(String employee_no) {
        this.employee_no = employee_no;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

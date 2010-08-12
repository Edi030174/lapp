package net.lintasarta.auditlog.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 12, 2010
 * Time: 4:28:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuditLog implements Serializable {
    private String field_name;
    private String action_field;
    private String new_value;
    private String old_value;
    private String created_user;
    private Date created_date;
    private String id_no;

    public AuditLog() {
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getAction_field() {
        return action_field;
    }

    public void setAction_field(String action_field) {
        this.action_field = action_field;
    }

    public String getNew_value() {
        return new_value;
    }

    public void setNew_value(String new_value) {
        this.new_value = new_value;
    }

    public String getOld_value() {
        return old_value;
    }

    public void setOld_value(String old_value) {
        this.old_value = old_value;
    }

    public String getCreated_user() {
        return created_user;
    }

    public void setCreated_user(String created_user) {
        this.created_user = created_user;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }
}

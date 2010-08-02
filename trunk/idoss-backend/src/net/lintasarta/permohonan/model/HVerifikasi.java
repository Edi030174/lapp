package net.lintasarta.permohonan.model;

import java.sql.Blob;
import java.sql.Date;

/**
 * Created by Joshua
 * Date: Jun 21, 2010
 * Time: 8:56:36 AM
 */
public class HVerifikasi {
    private String field_name;
    private String action;
    private Blob new_value;
    private Blob old_value;
    private String created_user;
    private Date created_date;
    private String h_idoss_verifikasi_id;

    public HVerifikasi() {
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Blob getNew_value() {
        return new_value;
    }

    public void setNew_value(Blob new_value) {
        this.new_value = new_value;
    }

    public Blob getOld_value() {
        return old_value;
    }

    public void setOld_value(Blob old_value) {
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

    public String getH_idoss_verifikasi_id() {
        return h_idoss_verifikasi_id;
    }

    public void setH_idoss_verifikasi_id(String h_idoss_verifikasi_id) {
        this.h_idoss_verifikasi_id = h_idoss_verifikasi_id;
    }
}

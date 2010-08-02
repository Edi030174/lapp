package net.lintasarta.pengaduan.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

/**
 * Created by Joshua
 * Date: Jun 18, 2010
 * Time: 5:56:38 PM
 */
public class HPenangananGangguan implements Serializable {
    private String field_name;
    private String action;
    private Blob new_value;
    private Blob old_value;
    private String created_user;
    private Timestamp created_date;
    private String h_idoss_penanganan_gangguan_id;

    public HPenangananGangguan() {
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

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public String getH_idoss_penanganan_gangguan_id() {
        return h_idoss_penanganan_gangguan_id;
    }

    public void setH_idoss_penanganan_gangguan_id(String h_idoss_penanganan_gangguan_id) {
        this.h_idoss_penanganan_gangguan_id = h_idoss_penanganan_gangguan_id;
    }
}

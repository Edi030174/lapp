package net.lintasarta.pengaduan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 8:47:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class PType implements Serializable {

    private int p_idoss_type_id;
    private String type_desc;
    private String active;
    private Timestamp created_date;
    private String created_user;
    private Timestamp updated_date;
    private String updated_user;

    public PType() {
    }

    public int getP_idoss_type_id() {
        return p_idoss_type_id;
    }

    public void setP_idoss_type_id(int p_idoss_type_id) {
        this.p_idoss_type_id = p_idoss_type_id;
    }

    public String getType_desc() {
        return type_desc;
    }

    public void setType_desc(String type_desc) {
        this.type_desc = type_desc;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public String getCreated_user() {
        return created_user;
    }

    public void setCreated_user(String created_user) {
        this.created_user = created_user;
    }

    public Timestamp getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Timestamp updated_date) {
        this.updated_date = updated_date;
    }

    public String getUpdated_user() {
        return updated_user;
    }

    public void setUpdated_user(String updated_user) {
        this.updated_user = updated_user;
    }
}

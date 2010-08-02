package net.lintasarta.permohonan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Joshua
 * Date: Jun 21, 2010
 * Time: 1:27:29 PM
 */
public class TPelaksanaan implements Serializable {
    private String t_idoss_pelaksanaan_id;
    private Timestamp tgl_permohonan;
    private String rfs;
    private Timestamp rfs_date;
    private String catatan_pelaksana;
    private String id_pelaksana;
    private String nama_pelaksana;
    private String status_perubahan;
    private Timestamp created_date;
    private String created_user;
    private Timestamp updated_date;
    private String updated_user;

    public TPelaksanaan() {
    }

    public String getT_idoss_pelaksanaan_id() {
        return t_idoss_pelaksanaan_id;
    }

    public void setT_idoss_pelaksanaan_id(String t_idoss_pelaksanaan_id) {
        this.t_idoss_pelaksanaan_id = t_idoss_pelaksanaan_id;
    }

    public Timestamp getTgl_permohonan() {
        return tgl_permohonan;
    }

    public void setTgl_permohonan(Timestamp tgl_permohonan) {
        this.tgl_permohonan = tgl_permohonan;
    }

    public String getRfs() {
        return rfs;
    }

    public void setRfs(String rfs) {
        this.rfs = rfs;
    }

    public Timestamp getRfs_date() {
        return rfs_date;
    }

    public void setRfs_date(Timestamp rfs_date) {
        this.rfs_date = rfs_date;
    }

    public String getCatatan_pelaksana() {
        return catatan_pelaksana;
    }

    public void setCatatan_pelaksana(String catatan_pelaksana) {
        this.catatan_pelaksana = catatan_pelaksana;
    }

    public String getId_pelaksana() {
        return id_pelaksana;
    }

    public void setId_pelaksana(String id_pelaksana) {
        this.id_pelaksana = id_pelaksana;
    }

    public String getNama_pelaksana() {
        return nama_pelaksana;
    }

    public void setNama_pelaksana(String nama_pelaksana) {
        this.nama_pelaksana = nama_pelaksana;
    }

    public String getStatus_perubahan() {
        return status_perubahan;
    }

    public void setStatus_perubahan(String status_perubahan) {
        this.status_perubahan = status_perubahan;
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

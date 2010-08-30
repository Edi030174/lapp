package net.lintasarta.report.permohonan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 11, 2010
 * Time: 2:01:10 PM
 */
public class ReportPermohonan implements Serializable {
    private String t_idoss_permohonan_id;
    private Timestamp created_date;
    private String nama_pemohon;
    private String type_permohonan;
    private String urgensi;
    private Timestamp tgl_permohonan;
    private String nama_asman;
    private String prioritas;
    private String dampak;
    private String status_permohonanasman;
    private Timestamp updated_asman;
    private String nama_pelaksana;
    private String status_perubahan;
    private Timestamp updated_date;

    public ReportPermohonan() {
    }

    public String getT_idoss_permohonan_id() {
        return t_idoss_permohonan_id;
    }

    public void setT_idoss_permohonan_id(String t_idoss_permohonan_id) {
        this.t_idoss_permohonan_id = t_idoss_permohonan_id;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public String getNama_pemohon() {
        return nama_pemohon;
    }

    public void setNama_pemohon(String nama_pemohon) {
        this.nama_pemohon = nama_pemohon;
    }

    public String getType_permohonan() {
        return type_permohonan;
    }

    public void setType_permohonan(String type_permohonan) {
        this.type_permohonan = type_permohonan;
    }

    public String getUrgensi() {
        return urgensi;
    }

    public void setUrgensi(String urgensi) {
        this.urgensi = urgensi;
    }

    public Timestamp getTgl_permohonan() {
        return tgl_permohonan;
    }

    public void setTgl_permohonan(Timestamp tgl_permohonan) {
        this.tgl_permohonan = tgl_permohonan;
    }

    public String getNama_asman() {
        return nama_asman;
    }

    public void setNama_asman(String nama_asman) {
        this.nama_asman = nama_asman;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    public String getDampak() {
        return dampak;
    }

    public void setDampak(String dampak) {
        this.dampak = dampak;
    }

    public String getStatus_permohonanasman() {
        return status_permohonanasman;
    }

    public void setStatus_permohonanasman(String status_permohonanasman) {
        this.status_permohonanasman = status_permohonanasman;
    }

    public Timestamp getUpdated_asman() {
        return updated_asman;
    }

    public void setUpdated_asman(Timestamp updated_asman) {
        this.updated_asman = updated_asman;
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

    public Timestamp getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Timestamp updated_date) {
        this.updated_date = updated_date;
    }
}

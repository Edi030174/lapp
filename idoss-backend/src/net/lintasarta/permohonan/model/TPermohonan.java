package net.lintasarta.permohonan.model;

import java.sql.Timestamp;
import java.io.*;

/**
 * Created by Joshua
 * Date: Jun 21, 2010
 * Time: 2:11:18 PM
 */
public class TPermohonan implements Serializable{
    private String t_idoss_permohonan_id;
    private Timestamp tgl_permohonan;
    private String dampak;
    private String urgensi;
    private Timestamp target_mulai_digunakan;
    private String detail_permohonan;
    private String lampiran;
    private String status_track_permohonan;
    private String nik_pemohon;
    private String nama_pemohon;
    private String bagian_pemohon;
    private String nik_asman;
    private String nama_asman;
    private String nik_manager;
    private String nama_manager;
    private String nik_gm;
    private String nama_gm;
    private String type_permohonan;
    private String lain_lain;
    private Timestamp updated_pemohon;
    private Timestamp updated_divisi;
    private Timestamp updated_asman;
    private Timestamp updated_manager;
    private Timestamp updated_gm;
    private Timestamp created_date;
    private String created_user;
    private Timestamp updated_date;
    private String updated_user;
    private InputStream uploadStream;
    private int gen_id_col;
    private String mttr;
    private String durasi;
    private String catatan_manager;
    private String catatan_gm;

    public TPermohonan() {
    }

    public String getT_idoss_permohonan_id() {
        return t_idoss_permohonan_id;
    }

    public void setT_idoss_permohonan_id(String t_idoss_permohonan_id) {
        this.t_idoss_permohonan_id = t_idoss_permohonan_id;
    }

    public Timestamp getTgl_permohonan() {
        return tgl_permohonan;
    }

    public void setTgl_permohonan(Timestamp tgl_permohonan) {
        this.tgl_permohonan = tgl_permohonan;
    }

    public String getDampak() {
        return dampak;
    }

    public void setDampak(String dampak) {
        this.dampak = dampak;
    }

    public String getUrgensi() {
        return urgensi;
    }

    public void setUrgensi(String urgensi) {
        this.urgensi = urgensi;
    }

    public Timestamp getTarget_mulai_digunakan() {
        return target_mulai_digunakan;
    }

    public void setTarget_mulai_digunakan(Timestamp target_mulai_digunakan) {
        this.target_mulai_digunakan = target_mulai_digunakan;
    }

    public String getDetail_permohonan() {
        return detail_permohonan;
    }

    public void setDetail_permohonan(String detail_permohonan) {
        this.detail_permohonan = detail_permohonan;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public String getStatus_track_permohonan() {
        return status_track_permohonan;
    }

    public void setStatus_track_permohonan(String status_track_permohonan) {
        this.status_track_permohonan = status_track_permohonan;
    }

    public String getNik_pemohon() {
        return nik_pemohon;
    }

    public void setNik_pemohon(String nik_pemohon) {
        this.nik_pemohon = nik_pemohon;
    }

    public String getNama_pemohon() {
        return nama_pemohon;
    }

    public void setNama_pemohon(String nama_pemohon) {
        this.nama_pemohon = nama_pemohon;
    }

    public String getBagian_pemohon() {
        return bagian_pemohon;
    }

    public void setBagian_pemohon(String bagian_pemohon) {
        this.bagian_pemohon = bagian_pemohon;
    }

    public String getNik_asman() {
        return nik_asman;
    }

    public void setNik_asman(String nik_asman) {
        this.nik_asman = nik_asman;
    }

    public String getNama_asman() {
        return nama_asman;
    }

    public void setNama_asman(String nama_asman) {
        this.nama_asman = nama_asman;
    }

    public String getNik_manager() {
        return nik_manager;
    }

    public void setNik_manager(String nik_manager) {
        this.nik_manager = nik_manager;
    }

    public String getNama_manager() {
        return nama_manager;
    }

    public void setNama_manager(String nama_manager) {
        this.nama_manager = nama_manager;
    }

    public String getNik_gm() {
        return nik_gm;
    }

    public void setNik_gm(String nik_gm) {
        this.nik_gm = nik_gm;
    }

    public String getNama_gm() {
        return nama_gm;
    }

    public void setNama_gm(String nama_gm) {
        this.nama_gm = nama_gm;
    }

    public String getType_permohonan() {
        return type_permohonan;
    }

    public void setType_permohonan(String type_permohonan) {
        this.type_permohonan = type_permohonan;
    }

    public String getLain_lain() {
        return lain_lain;
    }

    public void setLain_lain(String lain_lain) {
        this.lain_lain = lain_lain;
    }

    public Timestamp getUpdated_pemohon() {
        return updated_pemohon;
    }

    public void setUpdated_pemohon(Timestamp updated_pemohon) {
        this.updated_pemohon = updated_pemohon;
    }

    public Timestamp getUpdated_divisi() {
        return updated_divisi;
    }

    public void setUpdated_divisi(Timestamp updated_divisi) {
        this.updated_divisi = updated_divisi;
    }

    public Timestamp getUpdated_asman() {
        return updated_asman;
    }

    public void setUpdated_asman(Timestamp updated_asman) {
        this.updated_asman = updated_asman;
    }

    public Timestamp getUpdated_manager() {
        return updated_manager;
    }

    public void setUpdated_manager(Timestamp updated_manager) {
        this.updated_manager = updated_manager;
    }

    public Timestamp getUpdated_gm() {
        return updated_gm;
    }

    public void setUpdated_gm(Timestamp updated_gm) {
        this.updated_gm = updated_gm;
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

    public InputStream getUploadStream() {
        return uploadStream;
    }

    public void setUploadStream(InputStream uploadStream) {
        this.uploadStream = uploadStream;
    }

    public int getGen_id_col() {
        return gen_id_col;
    }

    public void setGen_id_col(int gen_id_col) {
        this.gen_id_col = gen_id_col;
    }

    public String getCatatan_manager() {
        return catatan_manager;
    }

    public void setCatatan_manager(String catatan_manager) {
        this.catatan_manager = catatan_manager;
    }

    public String getCatatan_gm() {
        return catatan_gm;
    }

    public void setCatatan_gm(String catatan_gm) {
        this.catatan_gm = catatan_gm;
    }

    public String getMttr() {
        return mttr;
    }

    public void setMttr(String mttr) {
        this.mttr = mttr;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }
}
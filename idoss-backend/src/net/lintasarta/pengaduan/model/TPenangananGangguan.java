package net.lintasarta.pengaduan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Joshua
 * Date: Jun 21, 2010
 * Time: 1:10:36 PM
 */
public class TPenangananGangguan implements Serializable {
    private String t_idoss_penanganan_gangguan_id;
    private String nik_pelapor;
    private String nama_pelapor;
    private String bagian_pelapor;
    private String judul;
    private String deskripsi;
    private String dampak;
    private String p_idoss_type_id;
    private String nik_pelaksana;
    private String nama_pelaksana;
    private Integer p_idoss_root_caused_id;
    private String status;
    private String solusi;
    private String mttr;
    private String durasi;
    private Timestamp created_date;
    private String created_user;
    private Timestamp updated_date;
    private String updated_user;
    private String no_hp;
    private String ext;
    private int gen_id_col;
    private Timestamp inserted_root_caused;

    public TPenangananGangguan() {
    }

    public String getT_idoss_penanganan_gangguan_id() {
        return t_idoss_penanganan_gangguan_id;
    }

    public void setT_idoss_penanganan_gangguan_id(String t_idoss_penanganan_gangguan_id) {
        this.t_idoss_penanganan_gangguan_id = t_idoss_penanganan_gangguan_id;
    }

    public String getNik_pelapor() {
        return nik_pelapor;
    }

    public void setNik_pelapor(String nik_pelapor) {
        this.nik_pelapor = nik_pelapor;
    }

    public String getNama_pelapor() {
        return nama_pelapor;
    }

    public void setNama_pelapor(String nama_pelapor) {
        this.nama_pelapor = nama_pelapor;
    }

    public String getBagian_pelapor() {
        return bagian_pelapor;
    }

    public void setBagian_pelapor(String bagian_pelapor) {
        this.bagian_pelapor = bagian_pelapor;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDampak() {
        return dampak;
    }

    public void setDampak(String dampak) {
        this.dampak = dampak;
    }

    public String getP_idoss_type_id() {
        return p_idoss_type_id;
    }

    public void setP_idoss_type_id(String p_idoss_type_id) {
        this.p_idoss_type_id = p_idoss_type_id;
    }

    public String getNik_pelaksana() {
        return nik_pelaksana;
    }

    public void setNik_pelaksana(String nik_pelaksana) {
        this.nik_pelaksana = nik_pelaksana;
    }

    public String getNama_pelaksana() {
        return nama_pelaksana;
    }

    public void setNama_pelaksana(String nama_pelaksana) {
        this.nama_pelaksana = nama_pelaksana;
    }

    public Integer getP_idoss_root_caused_id() {
        return p_idoss_root_caused_id;
    }

    public void setP_idoss_root_caused_id(Integer p_idoss_root_caused_id) {
        this.p_idoss_root_caused_id = p_idoss_root_caused_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
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

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getGen_id_col() {
        return gen_id_col;
    }

    public void setGen_id_col(int gen_id_col) {
        this.gen_id_col = gen_id_col;
    }

    public Timestamp getInserted_root_caused() {
        return inserted_root_caused;
    }

    public void setInserted_root_caused(Timestamp inserted_root_caused) {
        this.inserted_root_caused = inserted_root_caused;
    }
}

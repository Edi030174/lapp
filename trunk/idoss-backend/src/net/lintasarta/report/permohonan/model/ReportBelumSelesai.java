package net.lintasarta.report.permohonan.model;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 23, 2010
 * Time: 12:31:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportBelumSelesai {
    private String nomor;
    private String tanggal;
    private String type_permohonan;
    private String deskripsi;
    private String nama_pemohon;
    private String bagian_pemohon;
    private String nama_ver;
    private String jabatan;
    private String tgl_ver;
    private String status_track_permohonan;

    public ReportBelumSelesai() {
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getType_permohonan() {
        return type_permohonan;
    }

    public void setType_permohonan(String type_permohonan) {
        this.type_permohonan = type_permohonan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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

    public String getNama_ver() {
        return nama_ver;
    }

    public void setNama_ver(String nama_ver) {
        this.nama_ver = nama_ver;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTgl_ver() {
        return tgl_ver;
    }

    public void setTgl_ver(String tgl_ver) {
        this.tgl_ver = tgl_ver;
    }

    public String getStatus_track_permohonan() {
        return status_track_permohonan;
    }

    public void setStatus_track_permohonan(String status_track_permohonan) {
        this.status_track_permohonan = status_track_permohonan;
    }
}

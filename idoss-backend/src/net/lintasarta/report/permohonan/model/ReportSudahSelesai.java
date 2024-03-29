package net.lintasarta.report.permohonan.model;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 23, 2010
 * Time: 12:36:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportSudahSelesai {
    private String nomor;
    private String tanggal;
    private String type_permohonan ;
    private String deskripsi;
    private String durasi_pelaksanaan;
    private String durasi_target;
    private String nama_pemohon;
    private String bagian_pemohon;
    private String nama_pelaksana;
    private String tgl_pelaksana ;
    private String bulan;
    private String tahun;

    public ReportSudahSelesai() {
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

    public String getNama_pelaksana() {
        return nama_pelaksana;
    }

    public void setNama_pelaksana(String nama_pelaksana) {
        this.nama_pelaksana = nama_pelaksana;
    }

    public String getTgl_pelaksana() {
        return tgl_pelaksana;
    }

    public void setTgl_pelaksana(String tgl_pelaksana) {
        this.tgl_pelaksana = tgl_pelaksana;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getDurasi_pelaksanaan() {
        return durasi_pelaksanaan;
    }

    public void setDurasi_pelaksanaan(String durasi_pelaksanaan) {
        this.durasi_pelaksanaan = durasi_pelaksanaan;
    }

    public String getDurasi_target() {
        return durasi_target;
    }

    public void setDurasi_target(String durasi_target) {
        this.durasi_target = durasi_target;
    }
}

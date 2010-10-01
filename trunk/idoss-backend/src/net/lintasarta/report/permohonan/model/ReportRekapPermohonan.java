package net.lintasarta.report.permohonan.model;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 28, 2010
 * Time: 1:34:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportRekapPermohonan {
    private String type_permohonan;
    private String bagian_pemohon;
    private String nama_pelaksana;
    private String n_pelaksana;
    private String bulan;
    private String tahun;

    public ReportRekapPermohonan() {
    }

    public String getType_permohonan() {
        return type_permohonan;
    }

    public void setType_permohonan(String type_permohonan) {
        this.type_permohonan = type_permohonan;
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

    public String getN_pelaksana() {
        return n_pelaksana;
    }

    public void setN_pelaksana(String n_pelaksana) {
        this.n_pelaksana = n_pelaksana;
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
}

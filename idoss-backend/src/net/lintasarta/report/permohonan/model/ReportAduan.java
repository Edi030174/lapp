package net.lintasarta.report.permohonan.model;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 23, 2010
 * Time: 12:03:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportAduan {
    private String awal_ganggguan;
    private String akhir_ganggguan;
    private String durasi;
    private Integer no_gangguan;
    private String asal_aduan;
    private String tipe_gangguan;
    private String severity;
    private String problem;
    private Integer p_idoss_root_caused_id;
    private String solusi;
    private String pj;
    private String mttr;
    private String tercapai;
    private String bulan;
    private String tahun;
    private String jumlah_server;

    public ReportAduan() {
    }

    public String getAwal_ganggguan() {
        return awal_ganggguan;
    }

    public void setAwal_ganggguan(String awal_ganggguan) {
        this.awal_ganggguan = awal_ganggguan;
    }

    public String getAkhir_ganggguan() {
        return akhir_ganggguan;
    }

    public void setAkhir_ganggguan(String akhir_ganggguan) {
        this.akhir_ganggguan = akhir_ganggguan;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public Integer getNo_gangguan() {
        return no_gangguan;
    }

    public void setNo_gangguan(Integer no_gangguan) {
        this.no_gangguan = no_gangguan;
    }

    public String getAsal_aduan() {
        return asal_aduan;
    }

    public void setAsal_aduan(String asal_aduan) {
        this.asal_aduan = asal_aduan;
    }

    public String getTipe_gangguan() {
        return tipe_gangguan;
    }

    public void setTipe_gangguan(String tipe_gangguan) {
        this.tipe_gangguan = tipe_gangguan;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Integer getP_idoss_root_caused_id() {
        return p_idoss_root_caused_id;
    }

    public void setP_idoss_root_caused_id(Integer p_idoss_root_caused_id) {
        this.p_idoss_root_caused_id = p_idoss_root_caused_id;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }

    public String getPj() {
        return pj;
    }

    public void setPj(String pj) {
        this.pj = pj;
    }

    public String getMttr() {
        return mttr;
    }

    public void setMttr(String mttr) {
        this.mttr = mttr;
    }

    public String getTercapai() {
        return tercapai;
    }

    public void setTercapai(String tercapai) {
        this.tercapai = tercapai;
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

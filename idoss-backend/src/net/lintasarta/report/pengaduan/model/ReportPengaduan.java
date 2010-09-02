package net.lintasarta.report.pengaduan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Sep 2, 2010
 * Time: 9:12:24 AM
 */
public class ReportPengaduan implements Serializable {
    private Timestamp awal_gangguan;
    private Timestamp akhir_Gangguan;
    private String durasi;
    private String no_gangguan;
    private String asal_aduan;
    private String type_gangguan;
    private String severity;
    private String problem;
    private String solusi;
    private String pj;
    private String mttr;
    private String tercapai;

    public ReportPengaduan() {
    }

    public Timestamp getAwal_gangguan() {
        return awal_gangguan;
    }

    public void setAwal_gangguan(Timestamp awal_gangguan) {
        this.awal_gangguan = awal_gangguan;
    }

    public Timestamp getAkhir_Gangguan() {
        return akhir_Gangguan;
    }

    public void setAkhir_Gangguan(Timestamp akhir_Gangguan) {
        this.akhir_Gangguan = akhir_Gangguan;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getNo_gangguan() {
        return no_gangguan;
    }

    public void setNo_gangguan(String no_gangguan) {
        this.no_gangguan = no_gangguan;
    }

    public String getAsal_aduan() {
        return asal_aduan;
    }

    public void setAsal_aduan(String asal_aduan) {
        this.asal_aduan = asal_aduan;
    }

    public String getType_gangguan() {
        return type_gangguan;
    }

    public void setType_gangguan(String type_gangguan) {
        this.type_gangguan = type_gangguan;
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
}

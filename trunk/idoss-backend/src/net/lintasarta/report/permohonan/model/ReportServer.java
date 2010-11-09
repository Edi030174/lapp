package net.lintasarta.report.permohonan.model;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Nov 8, 2010
 * Time: 11:58:19 AM
 */
public class ReportServer {
    /*CREATE TABLE SNI.T_IDOSS_PEGANG_REPORT(
    UPDATE_BY VARCHAR2(255), PRIMARY KEY ()*/
    private Integer gangguan_report_id;
    private Integer tahun;
    private String bulan;
    private Integer jumlah;
    private Integer gen_id_col;
    private Timestamp update_date;
    private String update_by;

    public ReportServer() {
    }

    public Integer getGangguan_report_id() {
        return gangguan_report_id;
    }

    public void setGangguan_report_id(Integer gangguan_report_id) {
        this.gangguan_report_id = gangguan_report_id;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getGen_id_col() {
        return gen_id_col;
    }

    public void setGen_id_col(Integer gen_id_col) {
        this.gen_id_col = gen_id_col;
    }

    public Timestamp getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }
}

package net.lintasarta.pengaduan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Oct 13, 2010
 * Time: 1:06:38 PM
 */
public class TDeskripsi implements Serializable {
    private int t_idoss_deskripsi_id;
    private String deskripsi;
    private String solusi;
    private String t_idoss_penanganan_gangguan_id;
    private String updated_by;
    private Timestamp updated_date;
    private int gen_id_col;

    public TDeskripsi() {
    }

    public int getT_idoss_deskripsi_id() {
        return t_idoss_deskripsi_id;
    }

    public void setT_idoss_deskripsi_id(int t_idoss_deskripsi_id) {
        this.t_idoss_deskripsi_id = t_idoss_deskripsi_id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }

    public String getT_idoss_penanganan_gangguan_id() {
        return t_idoss_penanganan_gangguan_id;
    }

    public void setT_idoss_penanganan_gangguan_id(String t_idoss_penanganan_gangguan_id) {
        this.t_idoss_penanganan_gangguan_id = t_idoss_penanganan_gangguan_id;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Timestamp getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Timestamp updated_date) {
        this.updated_date = updated_date;
    }

    public int getGen_id_col() {
        return gen_id_col;
    }

    public void setGen_id_col(int gen_id_col) {
        this.gen_id_col = gen_id_col;
    }
}

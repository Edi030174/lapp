package net.lintasarta.pengaduan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 19, 2010
 * Time: 2:37:37 PM
 */
public class Mttr implements Serializable{
    private int t_idoss_mttr_id;
    private String nomor_tiket;
    private int mttr;
    private int opened;
    private int closed;
    private int inprogress;
    private int pending;
    private int target;
    private String updated_by;
    private Timestamp updated_date;
    private int gen_id_col;

    public Mttr() {
    }

    public int getT_idoss_mttr_id() {
        return t_idoss_mttr_id;
    }

    public void setT_idoss_mttr_id(int t_idoss_mttr_id) {
        this.t_idoss_mttr_id = t_idoss_mttr_id;
    }

    public String getNomor_tiket() {
        return nomor_tiket;
    }

    public void setNomor_tiket(String nomor_tiket) {
        this.nomor_tiket = nomor_tiket;
    }

    public int getMttr() {
        return mttr;
    }

    public void setMttr(int mttr) {
        this.mttr = mttr;
    }

    public int getOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public int getInprogress() {
        return inprogress;
    }

    public void setInprogress(int inprogress) {
        this.inprogress = inprogress;
    }

    public int getPending() {
        return pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
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

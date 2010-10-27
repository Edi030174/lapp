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
    private long t_idoss_mttr_id;
    private String nomor_tiket;
    private long mttr;
    private long opened;
    private long closed;
    private long inprogress;
    private long pending;
    private long target;
    private String updated_by;
    private Timestamp updated_date;
    private long gen_id_col;

    public Mttr() {
    }

    public long getT_idoss_mttr_id() {
        return t_idoss_mttr_id;
    }

    public long getGen_id_col() {
        return gen_id_col;
    }

    public String getNomor_tiket() {
        return nomor_tiket;
    }

    public void setNomor_tiket(String nomor_tiket) {
        this.nomor_tiket = nomor_tiket;
    }

    public long getMttr() {
        return mttr;
    }

    public void setMttr(long mttr) {
        this.mttr = mttr;
    }

    public long getOpened() {
        return opened;
    }

    public void setOpened(long opened) {
        this.opened = opened;
    }

    public long getClosed() {
        return closed;
    }

    public void setClosed(long closed) {
        this.closed = closed;
    }

    public long getInprogress() {
        return inprogress;
    }

    public void setInprogress(long inprogress) {
        this.inprogress = inprogress;
    }

    public long getPending() {
        return pending;
    }

    public void setPending(long pending) {
        this.pending = pending;
    }

    public long getTarget() {
        return target;
    }

    public void setTarget(long target) {
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
}

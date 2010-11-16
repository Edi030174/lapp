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
    private long pending_start;
    private long pending_end;
    private long lama_pending;
    private String updated_by;
    private Timestamp updated_date;
    private long target;
    private long inserted_pelaksana;
    private long gen_id_col;

    public Mttr() {
    }

    public long getT_idoss_mttr_id() {
        return t_idoss_mttr_id;
    }

    public void setT_idoss_mttr_id(long t_idoss_mttr_id) {
        this.t_idoss_mttr_id = t_idoss_mttr_id;
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

    public long getPending_start() {
        return pending_start;
    }

    public void setPending_start(long pending_start) {
        this.pending_start = pending_start;
    }

    public long getPending_end() {
        return pending_end;
    }

    public void setPending_end(long pending_end) {
        this.pending_end = pending_end;
    }

    public long getLama_pending() {
        return lama_pending;
    }

    public void setLama_pending(long lama_pending) {
        this.lama_pending = lama_pending;
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

    public long getTarget() {
        return target;
    }

    public void setTarget(long target) {
        this.target = target;
    }

    public long getInserted_pelaksana() {
        return inserted_pelaksana;
    }

    public void setInserted_pelaksana(long inserted_pelaksana) {
        this.inserted_pelaksana = inserted_pelaksana;
    }

    public long getGen_id_col() {
        return gen_id_col;
    }

    public void setGen_id_col(long gen_id_col) {
        this.gen_id_col = gen_id_col;
    }
}

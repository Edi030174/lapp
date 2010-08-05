package net.lintasarta.permohonan.service.impl;

import net.lintasarta.permohonan.dao.TPelaksanaanDAO;
import net.lintasarta.permohonan.dao.TPermohonanDAO;
import net.lintasarta.permohonan.dao.TVerifikasiDAO;
import net.lintasarta.permohonan.model.Status;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.PermohonanService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 28, 2010
 * Time: 7:37:19 PM
 */
public class PermohonanServiceImpl implements PermohonanService{
    private TPermohonanDAO tPermohonanDAO;
    private TVerifikasiDAO tVerifikasiDAO;
    private TPelaksanaanDAO tPelaksanaanDAO;

    public TPermohonanDAO gettPermohonanDAO() {
        return tPermohonanDAO;
    }

    public void settPermohonanDAO(TPermohonanDAO tPermohonanDAO) {
        this.tPermohonanDAO = tPermohonanDAO;
    }

    public TVerifikasiDAO gettVerifikasiDAO() {
        return tVerifikasiDAO;
    }

    public void settVerifikasiDAO(TVerifikasiDAO tVerifikasiDAO) {
        this.tVerifikasiDAO = tVerifikasiDAO;
    }

    public TPelaksanaanDAO gettPelaksanaanDAO() {
        return tPelaksanaanDAO;
    }

    public void settPelaksanaanDAO(TPelaksanaanDAO tPelaksanaanDAO) {
        this.tPelaksanaanDAO = tPelaksanaanDAO;
    }

    public TPermohonan getNewPermohonan() {
        return new TPermohonan();
    }

    public TPermohonan getTPermohonanByTIdossPermohonanId(String t_idoss_permohonan_id) {
        return gettPermohonanDAO().getTPermohonanByTIdossPermohonanId(t_idoss_permohonan_id);
    }

    public List<TPermohonan> getAllTPermohonan() {
        return gettPermohonanDAO().getAllTPermohonan();
    }

    public void createTPermohonan(TPermohonan tPermohonan) {

        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPermohonan.setCreated_date(ts);
        tPermohonan.setUpdated_date(ts);
        tPermohonan.setStatus_track_permohonan(Status.PERMOHONAN.toString());
        gettPermohonanDAO().createTPermohonan(tPermohonan);

    }

    public void saveOrUpdateTPermohonan(TPermohonan tPermohonan) {
        gettPermohonanDAO().saveOrUpdateTPermohonan(tPermohonan);
    }

    public TVerifikasi getTVerifikasiByTIdossVerifikasiId(String t_idoss_verifikasi_id) {
        return gettVerifikasiDAO().getTVerifikasiByTIdossVerifikasiId(t_idoss_verifikasi_id);
    }

    public TVerifikasi getNewVerifikasi() {
        return new TVerifikasi();
    }

    public TPelaksanaan getTPelaksanaanByTIdossPelaksanaanId(String t_idoss_pelaksanaan_id) {
        return gettPelaksanaanDAO().getTPelaksanaanByTIdossPelaksanaanId(t_idoss_pelaksanaan_id);
    }

    @Override
    public TPelaksanaan getNewPelaksanaan() {
        return new TPelaksanaan();
    }
}
package net.lintasarta.permohonan.service.impl;

import net.lintasarta.permohonan.dao.TPelaksanaanDAO;
import net.lintasarta.permohonan.dao.TPermohonanDAO;
import net.lintasarta.permohonan.dao.TVerifikasiDAO;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.service.PelaksanaanService;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 28, 2010
 * Time: 7:20:23 PM
 */
public class PelaksanaanServiceImpl implements PelaksanaanService{
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


    public TPelaksanaan getNewPelaksanaan() {
        return new TPelaksanaan();
    }

    public int getCountAllTPelaksanaan() {
        return gettPelaksanaanDAO().getCountAllTPelaksanaan();
    }

    public List<TPelaksanaan> getAllTPelaksanaan() {
        return gettPelaksanaanDAO().getAllTPelaksanaan();
    }

    public TPelaksanaan getTPelaksanaanByTIdossPelaksanaanId(String t_idoss_pelaksanaan_id) {
        return gettPelaksanaanDAO().getTPelaksanaanByTIdossPelaksanaanId(t_idoss_pelaksanaan_id);

    }

    public void createTPelaksanaan(TPelaksanaan tPelaksanaan) {
        gettPelaksanaanDAO().createTPelaksanaan(tPelaksanaan);
    }

    public void saveOrUpdateTPelaksanaan(TPelaksanaan tPelaksanaan) {
        gettPelaksanaanDAO().saveOrUpdateTPelaksanaan(tPelaksanaan);
    }
}
package net.lintasarta.permohonan.service.impl;

import net.lintasarta.permohonan.dao.TPelaksanaanDAO;
import net.lintasarta.permohonan.dao.TPermohonanDAO;
import net.lintasarta.permohonan.dao.TVerifikasiDAO;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.VerifikasiService;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 28, 2010
 * Time: 7:43:46 PM
 */
public class VerifikasiServiceImpl implements VerifikasiService {
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

    public TVerifikasi getNewVerifikasi() {
        return new TVerifikasi();
    }

    public int getCountAllTVerifikasi() {
        return gettVerifikasiDAO().getCountAllTVerifikasi();
    }

    public List<TVerifikasi> getAllTVerifikasi() {
        return gettVerifikasiDAO().getAllTVerifikasi();
    }

    public TVerifikasi getTVerifikasiByTIdossVerifikasiId(String t_idoss_verifikasi_id) {
        return getTVerifikasiByTIdossVerifikasiId(t_idoss_verifikasi_id);
    }

    public void createTVerifikasi(TPermohonan tPermohonan, TPelaksanaan tPelaksanaan, TVerifikasi tVerifikasi) {
        gettVerifikasiDAO().createTVerifikasi(tVerifikasi);
    }

    public void saveOrUpdateTVerifikasi(TVerifikasi tVerifikasi) {
        gettVerifikasiDAO().saveOrUpdateTVerifikasi(tVerifikasi);
    }
}

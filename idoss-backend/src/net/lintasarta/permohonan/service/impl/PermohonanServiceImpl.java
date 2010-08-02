package net.lintasarta.permohonan.service.impl;

import net.lintasarta.permohonan.dao.TPelaksanaanDAO;
import net.lintasarta.permohonan.dao.TPermohonanDAO;
import net.lintasarta.permohonan.dao.TVerifikasiDAO;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.service.PermohonanService;

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

    @Override
    public TPermohonan getNewPermohonan() {
        return new TPermohonan();
    }

    @Override
    public TPermohonan getTPermohonanByNomorPermohonanId(String nomorPermohonanId) {
        return getTPermohonanByNomorPermohonanId(nomorPermohonanId);
    }

    @Override
    public List<TPermohonan> getAllTPermohonan() {
        return gettPermohonanDAO().getAllTPermohonan();
    }

    @Override
    public void createTPermohonan(TPermohonan tPermohonan) {
        gettPermohonanDAO().createTPermohonan(tPermohonan);
    }

    @Override
    public void saveOrUpdateTPermohonan(TPermohonan tPermohonan) {
        gettPermohonanDAO().saveOrUpdateTPermohonan(tPermohonan);
    }
}
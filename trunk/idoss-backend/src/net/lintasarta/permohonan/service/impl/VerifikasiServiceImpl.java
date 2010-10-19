package net.lintasarta.permohonan.service.impl;

import net.lintasarta.pengaduan.dao.VHrEmployeePelaksanaDAO;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import net.lintasarta.permohonan.dao.TPelaksanaanDAO;
import net.lintasarta.permohonan.dao.TPermohonanDAO;
import net.lintasarta.permohonan.dao.TVerifikasiDAO;
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
    private VHrEmployeePelaksanaDAO vHrEmployeePelaksanaDAO;

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

    public VHrEmployeePelaksanaDAO getvHrEmployeePelaksanaDAO() {
        return vHrEmployeePelaksanaDAO;
    }

    public void setvHrEmployeePelaksanaDAO(VHrEmployeePelaksanaDAO vHrEmployeePelaksanaDAO) {
        this.vHrEmployeePelaksanaDAO = vHrEmployeePelaksanaDAO;
    }

    public int getCountAllTVerifikasi() {
        return gettVerifikasiDAO().getCountAllTVerifikasi();
    }

    public List<TVerifikasi> getAllTVerifikasi() {
        return gettVerifikasiDAO().getAllTVerifikasi();
    }

    public TVerifikasi getTVerifikasiByTIdossVerifikasiId(String t_idoss_verifikasi_id) {
        return gettVerifikasiDAO().getTVerifikasiByTIdossVerifikasiId(t_idoss_verifikasi_id);
    }

    @Override
    public List<VHrEmployeePelaksana> getEmployeeName() {
        return getvHrEmployeePelaksanaDAO().getAllVHrEmployeePelaksana();
    }

    public void createTVerifikasi(TVerifikasi tVerifikasi) {
        gettVerifikasiDAO().createTVerifikasi(tVerifikasi);
    }

    public void saveOrUpdateTVerifikasi(TVerifikasi tVerifikasi) {
        gettVerifikasiDAO().saveOrUpdateTVerifikasi(tVerifikasi);
    }
}

package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.pengaduan.dao.VHrEmployeePelaksanaDAO;
import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.pengaduan.dao.PRootCausedDAO;
import net.lintasarta.pengaduan.dao.PTypeDAO;
import net.lintasarta.pengaduan.dao.TPenangananGangguanDAO;
import net.lintasarta.pengaduan.model.TPenangananGangguan;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 19, 2010
 * Time: 4:37:20 PM
 */
public class PelaksanaanGangguanServiceImpl implements PelaksanaanGangguanService {
    private TPenangananGangguanDAO tPenangananGangguanDAO;
    private PRootCausedDAO pRootCausedDAO;
    private PTypeDAO pTypeDAO;
    private VHrEmployeePelaksanaDAO vHrEmployeePelaksanaDAO;

    public TPenangananGangguanDAO gettPenangananGangguanDAO() {
        return tPenangananGangguanDAO;
    }

    public void settPenangananGangguanDAO(TPenangananGangguanDAO tPenangananGangguanDAO) {
        this.tPenangananGangguanDAO = tPenangananGangguanDAO;
    }

    public PRootCausedDAO getpRootCausedDAO() {
        return pRootCausedDAO;
    }

    public void setpRootCausedDAO(PRootCausedDAO pRootCausedDAO) {
        this.pRootCausedDAO = pRootCausedDAO;
    }

    public PTypeDAO getpTypeDAO() {
        return pTypeDAO;
    }

    public void setpTypeDAO(PTypeDAO pTypeDAO) {
        this.pTypeDAO = pTypeDAO;
    }

    public VHrEmployeePelaksanaDAO getvHrEmployeePelaksanaDAO() {
        return vHrEmployeePelaksanaDAO;
    }

    public void setvHrEmployeePelaksanaDAO(VHrEmployeePelaksanaDAO vHrEmployeePelaksanaDAO) {
        this.vHrEmployeePelaksanaDAO = vHrEmployeePelaksanaDAO;
    }

    @Override
    public TPenangananGangguan getDetail(String tiketId) {
        return gettPenangananGangguanDAO().getTPenangananGangguanByTiketId(tiketId);
    }

    @Override
    public List<PType> getType() {
        return getpTypeDAO().getAllPType();
    }

    @Override
    public List<PRootCaused> getRootCaused() {
        return getpRootCausedDAO().getAllPRootCaused();
    }

    @Override
    public List<VHrEmployeePelaksana> getEmployeeName() {
        return getvHrEmployeePelaksanaDAO().getAllVHrEmployeePelaksana();
    }

    @Override
    public void saveOrUpdate(TPenangananGangguan tPenangananGangguan) {

        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setUpdated_date(ts);

        gettPenangananGangguanDAO().saveOrUpdate(tPenangananGangguan);
    }

    @Override
    public List<PRootCaused> getRootCausedByPTypeId(int TypeId) {
        List<PRootCaused> pRootCauseds =pRootCausedDAO.getPRootCausedByPTypeID(TypeId);
        return pRootCauseds;
    }
}
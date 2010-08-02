package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.model.PType;
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
    public void createRootCaused(PRootCaused pRootCaused) {
        int i = pRootCausedDAO.getGenerateId();
        pRootCaused.setP_idoss_root_caused_id(i);
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pRootCaused.setActive("T");
        pRootCaused.setCreated_date(ts);
        pRootCaused.setCreated_user("2472898");
        pRootCaused.setUpdated_date(ts);
        pRootCaused.setUpdated_user("2472897");
        getpRootCausedDAO().createPRootCaused(pRootCaused);
    }

    @Override
    public void saveOrUpdate(TPenangananGangguan tPenangananGangguan) {
        gettPenangananGangguanDAO().saveOrUpdate(tPenangananGangguan);
    }
}
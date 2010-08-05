package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.pengaduan.dao.PRootCausedDAO;
import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.service.RootCausedService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 24, 2010
 * Time: 8:32:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class RootCausedServiceImpl implements RootCausedService {

    private PRootCausedDAO pRootCausedDAO;

    public PRootCausedDAO getpRootCausedDAO() {
        return pRootCausedDAO;
    }

    public void setpRootCausedDAO(PRootCausedDAO pRootCausedDAO) {
        this.pRootCausedDAO = pRootCausedDAO;
    }

    public PRootCaused getNewRootCaused() {
        return new PRootCaused();
    }

    public List<PRootCaused> getAllRootCaused() {
        return getpRootCausedDAO().getAllPRootCaused();
    }

    public PRootCaused getPRootCausedByRootCausedId(int rootCausedId) {
        PRootCaused pRootCauseds = pRootCausedDAO.getPRootCausedByRootCausedId(rootCausedId);
        return pRootCauseds;
    }

    public void createRootCaused(PRootCaused pRootCaused) {
        int i = pRootCausedDAO.getGenerateId();
        pRootCaused.setP_idoss_root_caused_id(i);
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pRootCaused.setCreated_date(ts);
        pRootCaused.setUpdated_date(ts);
        getpRootCausedDAO().createPRootCaused(pRootCaused);
    }

    public void saveOrUpdate(PRootCaused pRootCaused) {
        getpRootCausedDAO().saveOrUpdate(pRootCaused);

    }
}

package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.constants.Constants;
import net.lintasarta.pengaduan.dao.TPenangananGangguanDAO;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.pengaduan.service.PenangananGangguanService;
import net.lintasarta.util.TicketIdGenerator;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Asri
 * Date: Jun 28, 2010
 * Time: 3:34:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class PenangananGangguanServiceImpl implements PenangananGangguanService {

    private TPenangananGangguanDAO tPenangananGangguanDAO;

    public TPenangananGangguanDAO gettPenangananGangguanDAO() {
        return tPenangananGangguanDAO;
    }

    public void settPenangananGangguanDAO(TPenangananGangguanDAO tPenangananGangguanDAO) {
        this.tPenangananGangguanDAO = tPenangananGangguanDAO;
    }

    public TPenangananGangguan getNewPenangananGangguan() {
        return new TPenangananGangguan();
    }

    public String getTiketId() {
        int i = tPenangananGangguanDAO.getSeqTiketId();
        String seq = String.valueOf(i);
        TicketIdGenerator tid = new TicketIdGenerator(seq);
        String ticketIdResult = tid.getTicketId();

        return ticketIdResult;
    }

    public List<TPenangananGangguan> getAllPenangananGangguan() {
        return gettPenangananGangguanDAO().getAllTPenangananGangguan();
    }

    public TPenangananGangguan getPenangananGangguanbyTiketId(String tiketId) {
        TPenangananGangguan tPenangananGangguan = tPenangananGangguanDAO.getTPenangananGangguanByTiketId(tiketId);
        return tPenangananGangguan;
    }

    public List<TPenangananGangguan> getAllPenangananGanguanByNikPelapor(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelapor(tPenangananGangguan);
        return tPenangananGangguans;
    }

    public List<TPenangananGangguan> getAllPenangananGangguanByNikPelaksana(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelaksana(tPenangananGangguan);
        return tPenangananGangguans;
    }

    public List<TPenangananGangguan> getAllPenangananGangguanByStatus(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByStatus(tPenangananGangguan);
        return tPenangananGangguans;
    }

    public List<TPenangananGangguan> getAllPenangananGangguanByNikPelaporStatus(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelaporStatus(tPenangananGangguan);
        return tPenangananGangguans;
    }

    public List<TPenangananGangguan> getAllPenangananGangguanByNikPelaksanaStatus(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelaksanaStatus(tPenangananGangguan);
        return tPenangananGangguans;
    }

    public void createPenangananGangguan(TPenangananGangguan tPenangananGangguan) {

        int i = tPenangananGangguanDAO.getSeqTiketId();
        tPenangananGangguan.setGen_id_col(i);
        tPenangananGangguan.setDurasi(1);
        tPenangananGangguan.setNama_pelaksana(Constants.EMPTY_STRING);
        tPenangananGangguan.setMttr(Constants.EMPTY_STRING);
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setCreated_date(ts);
        tPenangananGangguan.setUpdated_date(ts);
        gettPenangananGangguanDAO().createTPenangananGangguan(tPenangananGangguan);
    }

    public void saveOrUpdate(TPenangananGangguan tPenangananGangguan) {
        gettPenangananGangguanDAO().saveOrUpdate(tPenangananGangguan);
    }
}

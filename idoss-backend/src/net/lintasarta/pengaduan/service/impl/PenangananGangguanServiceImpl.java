package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.constants.Constants;
import net.lintasarta.pengaduan.dao.TPenangananGangguanDAO;
import net.lintasarta.pengaduan.model.TDeskripsi;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.pengaduan.model.comparator.TPenangananGangguanComparator;
import net.lintasarta.pengaduan.service.PenangananGangguanService;
import net.lintasarta.pengaduan.service.TDeskripsiService;
import net.lintasarta.security.dao.VHrEmployeeDAO;
import net.lintasarta.security.model.VHrEmployee;
import net.lintasarta.util.TicketIdGenerator;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Asri
 * Date: Jun 28, 2010
 * Time: 3:34:05 PM
 */
public class PenangananGangguanServiceImpl implements PenangananGangguanService {

    private TPenangananGangguanDAO tPenangananGangguanDAO;
    private VHrEmployeeDAO vHrEmployeeDAO;
    private TDeskripsiService tDeskripsiService;

    public TPenangananGangguanDAO gettPenangananGangguanDAO() {
        return tPenangananGangguanDAO;
    }

    public void settPenangananGangguanDAO(TPenangananGangguanDAO tPenangananGangguanDAO) {
        this.tPenangananGangguanDAO = tPenangananGangguanDAO;
    }

    public VHrEmployeeDAO getvHrEmployeeDAO() {
        return vHrEmployeeDAO;
    }

    public void setvHrEmployeeDAO(VHrEmployeeDAO vHrEmployeeDAO) {
        this.vHrEmployeeDAO = vHrEmployeeDAO;
    }

    public TPenangananGangguan getNewPenangananGangguan() {
        return new TPenangananGangguan();
    }

    public void settDeskripsiService(TDeskripsiService tDeskripsiService) {
        this.tDeskripsiService = tDeskripsiService;
    }

    public String getTiketId() {
        int i = tPenangananGangguanDAO.getSeqTiketId();
        String seq = String.valueOf(i);
        TicketIdGenerator tid = new TicketIdGenerator(seq);
        String ticketIdResult = tid.getTicketId();

        return ticketIdResult;
    }

    public List<TPenangananGangguan> getAllPenangananGangguan() {
        List<TPenangananGangguan> tPenangananGangguans = gettPenangananGangguanDAO().getAllTPenangananGangguan();
        java.util.Collections.sort(tPenangananGangguans, new TPenangananGangguanComparator());
        return tPenangananGangguans;
    }

    public List<VHrEmployee> getEmployeeName() {
        List<VHrEmployee> vHrEmployees = getvHrEmployeeDAO().getVHrEmployeeByEmployeeName();
        return vHrEmployees;
    }

    public TPenangananGangguan getPenangananGangguanbyTiketId(String tiketId) {
        TPenangananGangguan tPenangananGangguan = tPenangananGangguanDAO.getTPenangananGangguanByTiketId(tiketId);
        return tPenangananGangguan;
    }

    public List<TPenangananGangguan> getAllPenangananGanguanByNikPelapor(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelapor(tPenangananGangguan);
        return tPenangananGangguans;
    }

    public List<TPenangananGangguan> getAllTPenangananGangguanByNikPelaksana(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelaksana(tPenangananGangguan);
        return tPenangananGangguans;
    }

    public List<TPenangananGangguan> getAllTPenangananGangguanByStatus(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByStatus(tPenangananGangguan);
        return tPenangananGangguans;
    }

    public void createTDeskripsi(TDeskripsi tDeskripsi) {
        if (tDeskripsi.getDeskripsi() != null || tDeskripsi.getSolusi() != null) {
            tDeskripsi.setUpdated_date(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            tDeskripsiService.createTDeskripsi(tDeskripsi);
        }
    }

    public void createPenangananGangguan(TPenangananGangguan tPenangananGangguan, TDeskripsi tDeskripsi) {

        int i = tPenangananGangguanDAO.getSeqTiketId();
        tPenangananGangguan.setGen_id_col(i);
        tPenangananGangguan.setDurasi(Constants.EMPTY_STRING);
        tPenangananGangguan.setNama_pelaksana(Constants.EMPTY_STRING);
        tPenangananGangguan.setMttr(Constants.EMPTY_STRING);
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setCreated_date(ts);
        tPenangananGangguan.setUpdated_date(ts);
        if (tPenangananGangguan.getP_idoss_root_caused_id() != null) {
            tPenangananGangguan.setInserted_root_caused(ts);
        }
        gettPenangananGangguanDAO().createTPenangananGangguan(tPenangananGangguan);

        createTDeskripsi(tDeskripsi);
    }
}
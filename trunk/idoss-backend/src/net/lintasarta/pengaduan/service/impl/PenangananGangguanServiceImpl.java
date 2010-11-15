package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.constants.Constants;
import net.lintasarta.pengaduan.dao.TPenangananGangguanDAO;
import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.pengaduan.model.TDeskripsi;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.pengaduan.model.comparator.TPenangananGangguanComparator;
import net.lintasarta.pengaduan.service.MttrService;
import net.lintasarta.pengaduan.service.PenangananGangguanService;
import net.lintasarta.pengaduan.service.TDeskripsiService;
import net.lintasarta.security.dao.VHrEmployeeDAO;
import net.lintasarta.security.model.VHrEmployee;
import net.lintasarta.util.TicketIdGenerator;

import java.sql.Timestamp;
import java.text.DecimalFormat;
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
    private MttrService mttrService;

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

    public MttrService getMttrService() {
        return mttrService;
    }

    public void setMttrService(MttrService mttrService) {
        this.mttrService = mttrService;
    }

    public String getTiketId() {
        int i = tPenangananGangguanDAO.getSeqTiketId();
        String seq = String.valueOf(i);
        TicketIdGenerator tid = new TicketIdGenerator(seq);
        String ticketIdResult = tid.getTicketId();

        return ticketIdResult;
    }

    public List<TPenangananGangguan> hitungDurasiMttr(List<TPenangananGangguan> tPenangananGangguans) {
        for (TPenangananGangguan tPenangananGangguan : tPenangananGangguans) {
            List<Mttr> mttrs = mttrService.getMttrByNomorTiket(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
            for (Mttr mttr : mttrs) {
                long duration = mttrService.getDurasi(mttr);
                long lama_pending = mttrService.getLamaPending(mttr);
                long mttrLong = duration - lama_pending;

                tPenangananGangguan.setDurasi(getDuration(duration));
                tPenangananGangguan.setMttr(getDuration(mttrLong));
                if (mttrService.isInProgress(mttr)) {
                    tPenangananGangguan.setStatus("In Progress");
                }

            }
        }
        return tPenangananGangguans;
    }

    public List<TPenangananGangguan> getAllPenangananGangguan() {
        List<TPenangananGangguan> tPenangananGangguans = gettPenangananGangguanDAO().getAllTPenangananGangguan();
        hitungDurasiMttr(tPenangananGangguans);
        java.util.Collections.sort(tPenangananGangguans, new TPenangananGangguanComparator());
        return tPenangananGangguans;
    }

    private String getDuration(long duration) {
        final int millisPerSecond = 1000;
        final int millisPerMinute = 1000 * 60;
        final int millisPerHour = 1000 * 60 * 60;
        final int millisPerDay = 1000 * 60 * 60 * 24;
//        int hours = (int) (duration / millisPerHour);
        int days = (int) (duration / millisPerDay);
        int hours = (int) (duration % millisPerDay / millisPerHour);
        int minutes = (int) (duration % millisPerHour / millisPerMinute);
//        int seconds = (int) (duration % millisPerMinute / millisPerSecond);

        DecimalFormat df = new DecimalFormat("##");
        if (days > 0) {
            return df.format(days) + " d, " + df.format(hours) + " h, " + df.format(minutes) + " m";
        } else if ((days < 1) && (hours > 0)) {
            return df.format(hours) + " h, " + df.format(minutes) + " m";
        } else if ((days < 1) && (hours < 1) && (minutes > 0)) {
            return df.format(minutes) + " m";
        }else if((days < 1) && (hours < 1) && (minutes < 1)) {
            return "< 1 m";
        }else {
            return "0";
        }
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
        hitungDurasiMttr(tPenangananGangguans);
        java.util.Collections.sort(tPenangananGangguans, new TPenangananGangguanComparator());
        return tPenangananGangguans;
    }

    public List<TPenangananGangguan> getAllTPenangananGangguanByNikPelaksana(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelaksana(tPenangananGangguan);
        hitungDurasiMttr(tPenangananGangguans);
        java.util.Collections.sort(tPenangananGangguans, new TPenangananGangguanComparator());
        return tPenangananGangguans;
    }

//    public List<TPenangananGangguan> getAllTPenangananGangguanByNikPelaksanaStatus(String nik_pelaksana, String status) {
//        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
//        tPenangananGangguan.setNik_pelaksana(nik_pelaksana);
//        tPenangananGangguan.setStatus(status);
//        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelaksanaStatus(tPenangananGangguan);
//        java.util.Collections.sort(tPenangananGangguans, new TPenangananGangguanComparator());
//        return tPenangananGangguans;
//    }

    public List<TPenangananGangguan> getAllTPenangananGangguanByStatus(TPenangananGangguan tPenangananGangguan) {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByStatus(tPenangananGangguan);
        hitungDurasiMttr(tPenangananGangguans);
        java.util.Collections.sort(tPenangananGangguans, new TPenangananGangguanComparator());
        return tPenangananGangguans;
    }

    public void createTDeskripsi(TDeskripsi tDeskripsi, String nomorTiket) {
        if (tDeskripsi.getDeskripsi() != null || tDeskripsi.getSolusi() != null) {
            tDeskripsi.setUpdated_date(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            tDeskripsiService.createTDeskripsi(tDeskripsi, nomorTiket);
        }
    }

    public void createPenangananGangguan(TPenangananGangguan tPenangananGangguan, TDeskripsi tDeskripsi, Mttr mttr) {

        int i = tPenangananGangguanDAO.getSeqTiketId();
        tPenangananGangguan.setT_idoss_penanganan_gangguan_id(getTiketId());
        tPenangananGangguan.setGen_id_col(i);
        tPenangananGangguan.setDurasi(Constants.EMPTY_STRING);
//        tPenangananGangguan.setNama_pelaksana(Constants.EMPTY_STRING);
        tPenangananGangguan.setMttr(Constants.EMPTY_STRING);
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setCreated_date(ts);
        tPenangananGangguan.setUpdated_date(ts);
        if (tPenangananGangguan.getP_idoss_root_caused_id() != null) {
            tPenangananGangguan.setInserted_root_caused(ts);
        }
        mttr.setNomor_tiket(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        Timestamp created = tPenangananGangguan.getCreated_date();
        long duration = created.getTime();
        mttr.setOpened(duration);
        mttr.setUpdated_by(tPenangananGangguan.getUpdated_user());
        mttr.setUpdated_date(tPenangananGangguan.getUpdated_date());
        gettPenangananGangguanDAO().createTPenangananGangguan(tPenangananGangguan);
        getMttrService().createMttr(mttr);

        createTDeskripsi(tDeskripsi, tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
    }
}
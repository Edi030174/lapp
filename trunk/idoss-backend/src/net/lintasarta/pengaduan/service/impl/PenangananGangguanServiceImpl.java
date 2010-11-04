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
            Mttr mttr = mttrService.getMttrByNomorTiket(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());

            long ts = Calendar.getInstance().getTimeInMillis();
            long duration = ts - mttr.getOpened();
            if (mttr.getClosed() > 0) {
                if (ts >= mttr.getClosed()) {
                    duration = mttr.getClosed() - mttr.getOpened();
                }
            }

            long lama_pending = mttr.getLama_pending();
            if (mttr.getPending_start() > 0) {
                lama_pending = lama_pending + ts - mttr.getPending_start();
                if (mttr.getPending_end() > 0) {
                    if (ts >= mttr.getPending_end()) {
                        lama_pending = lama_pending + mttr.getPending_end() - mttr.getPending_start();
                    }
                }
            }
            long mttrLong = duration - lama_pending;

            tPenangananGangguan.setDurasi(getDuration(duration));
            tPenangananGangguan.setMttr(getDuration(mttrLong));
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
        int hours = (int) (duration / millisPerHour);
        int minutes = (int) (duration % millisPerHour / millisPerMinute);
//        int seconds = (int) (duration % millisPerMinute / millisPerSecond);

        DecimalFormat df = new DecimalFormat("00");
        return df.format(hours) + ":" + df.format(minutes);
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
        tPenangananGangguan.setDampak("Minor");
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
        Timestamp created =  tPenangananGangguan.getCreated_date();
        long duration = created.getTime();
        mttr.setOpened(duration);
        mttr.setUpdated_by(tPenangananGangguan.getUpdated_user());
        mttr.setUpdated_date(tPenangananGangguan.getUpdated_date());
        gettPenangananGangguanDAO().createTPenangananGangguan(tPenangananGangguan);
        getMttrService().createMttr(mttr);

        createTDeskripsi(tDeskripsi, tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
    }
}
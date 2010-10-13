package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.pengaduan.dao.VHrEmployeePelaksanaDAO;
import net.lintasarta.pengaduan.model.*;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.pengaduan.dao.PRootCausedDAO;
import net.lintasarta.pengaduan.dao.PTypeDAO;
import net.lintasarta.pengaduan.dao.TPenangananGangguanDAO;
import net.lintasarta.pengaduan.service.TDeskripsiService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    private TDeskripsiService tDeskripsiService;

    public void settDeskripsiService(TDeskripsiService tDeskripsiService) {
        this.tDeskripsiService = tDeskripsiService;
    }

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

    public void createTDeskripsi(TDeskripsi tDeskripsi) {
        if (tDeskripsi.getDeskripsi() != null || tDeskripsi.getSolusi() != null) {
            tDeskripsi.setUpdated_date(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            tDeskripsiService.createTDeskripsi(tDeskripsi);
        }
    }

    @Override
    public void saveOrUpdate(TPenangananGangguan tPenangananGangguan, TDeskripsi tDeskripsi) throws ParseException {

        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        if (tPenangananGangguan.getInserted_root_caused() == null) {
            tPenangananGangguan.setInserted_root_caused(ts);
        }
//        long awal = tPenangananGangguan.getCreated_date().getTime();
//        long akhir = ts.getTime();
//        double durasi = (double )(akhir - awal)/(1000);
//        DecimalFormat durasiResult = new DecimalFormat("##0.000000");

/*Update Durasi
        String dateAwal = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(tPenangananGangguan.getCreated_date());
        String dateAkhir = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(ts);
        Date awal = getDateTime(dateAwal);
        Date akhir = getDateTime(dateAkhir);
        long diff = (akhir.getTime()- awal.getTime());
        float diffResult =((float)diff/(1000.0f*60.0f*60.0f));
        DecimalFormat durasiResult = new DecimalFormat("##0.000000");
        tPenangananGangguan.setDurasi(durasiResult.format(diffResult));
update MTTR
        String firtUpadate = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(tPenangananGangguan.getInserted_root_caused());
        String lastUpdate = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(ts);
        Date first = getDateTime(firtUpadate);
        Date last = getDateTime(lastUpdate);
        long diffMttr = (last.getTime()- first.getTime());
        float diffResultMttr =((float)diffMttr/(1000.0f*60.0f*60.0f));
        DecimalFormat durasiResultMttr = new DecimalFormat("##0.000000");
        tPenangananGangguan.setMttr(durasiResultMttr.format(diffResultMttr));*/
//
        if (tPenangananGangguan.getCreated_date() != null) {
            if (tPenangananGangguan.getUpdated_date() != null) {
                Timestamp start = tPenangananGangguan.getCreated_date();
                Timestamp end = tPenangananGangguan.getUpdated_date();
                long duration = end.getTime() - start.getTime();
                String dur = getDuration(duration);
                tPenangananGangguan.setDurasi(dur);
            }
        }
        if (tPenangananGangguan.getInserted_root_caused() != null) {
            if (tPenangananGangguan.getUpdated_date() != null) {
                Timestamp start = tPenangananGangguan.getInserted_root_caused();
                Timestamp end = tPenangananGangguan.getUpdated_date();
                long MTTR = end.getTime() - start.getTime();
                String mttr = getDuration(MTTR);
                tPenangananGangguan.setDurasi(mttr);
            }
        }

        gettPenangananGangguanDAO().saveOrUpdate(tPenangananGangguan);
        createTDeskripsi(tDeskripsi);
    }

    private String getDuration(long duration) {
        final int millisPerSecond = 1000;
        final int millisPerMinute = 1000 * 60;
        final int millisPerHour = 1000 * 60 * 60;
//        final int millisPerDay = 1000 * 60 * 60 * 24;
//        int days = (int) (duration / millisPerDay);
//        int hours = (int) (duration % millisPerDay / millisPerHour);
        int hours = (int) (duration / millisPerHour);
        int minutes = (int) (duration % millisPerHour / millisPerMinute);
        int seconds = (int) (duration % millisPerMinute / millisPerSecond);
//        return String.format("%d %02d:%02d:%02d", days, hours, minutes, seconds);

        DecimalFormat df = new DecimalFormat("00");
//        return (days == 0 ? "" : days + " ")+ df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
        return df.format(hours) + ":" + df.format(minutes);
    }

    private static Date getDateTime(String dateTime) throws ParseException {

        DateFormat formatOldDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
        Date date = formatOldDateTime.parse(dateTime);

        return date;
    }

    @Override
    public List<PRootCaused> getRootCausedByPTypeId(String typeId) {
        List<PRootCaused> pRootCauseds = pRootCausedDAO.getPRootCausedByPTypeID(typeId);
        return pRootCauseds;
    }

    @Override
    public VHrEmployeePelaksana getVHrEmployeePelaksanaById(String id) {
        return getvHrEmployeePelaksanaDAO().getVHrEmployeePelaksanaById(id);
    }
}
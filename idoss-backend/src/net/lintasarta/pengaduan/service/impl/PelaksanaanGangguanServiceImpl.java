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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void saveOrUpdate(TPenangananGangguan tPenangananGangguan) throws ParseException {

        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setUpdated_date(ts);
        if(tPenangananGangguan.getInserted_root_caused()==null){
            tPenangananGangguan.setInserted_root_caused(ts);
        }
//        long awal = tPenangananGangguan.getCreated_date().getTime();
//        long akhir = ts.getTime();
//        double durasi = (double )(akhir - awal)/(1000);
//        DecimalFormat durasiResult = new DecimalFormat("##0.000000");

        String dateAwal = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(tPenangananGangguan.getCreated_date());
        String dateAkhir = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(ts);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date awal = getDateTime(dateAwal);
        Date akhir = getDateTime(dateAkhir);
        long diff = (akhir.getTime()- awal.getTime());
        float diffResult =((float)diff/(1000.0f*60.0f*60.0f));
        DecimalFormat durasiResult = new DecimalFormat("##0.000000");

        tPenangananGangguan.setDurasi(durasiResult.format(diffResult));

        gettPenangananGangguanDAO().saveOrUpdate(tPenangananGangguan);
    }

    private static Date getDateTime(String dateTime) throws ParseException {

        DateFormat formatOldDateTime = new SimpleDateFormat( "yyyy-MM-dd hh:mm aa");
        Date date = formatOldDateTime.parse(dateTime);

        return date;
    }
/*
    public static Date getDateTime(String dateTime, Date orig)
            throws ParseException {
        Date akhir = getDateTime(dateTime);

        if (akhir.getYear() == 70) {
            akhir.setYear(orig.getYear());
            akhir.setMonth(orig.getMonth());
            akhir.setDate(orig.getDate());
        }
        return akhir;
    }
*/

    @Override
    public List<PRootCaused> getRootCausedByPTypeId(String typeId) {
        List<PRootCaused> pRootCauseds =pRootCausedDAO.getPRootCausedByPTypeID(typeId);
        return pRootCauseds;
    }

    @Override
    public VHrEmployeePelaksana getVHrEmployeePelaksanaById(String id) {
        return getvHrEmployeePelaksanaDAO().getVHrEmployeePelaksanaById(id);
    }
}
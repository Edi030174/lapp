package net.lintasarta.permohonan.service.impl;

import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.pengaduan.service.MttrService;
import net.lintasarta.permohonan.dao.TPelaksanaanDAO;
import net.lintasarta.permohonan.dao.TPermohonanDAO;
import net.lintasarta.permohonan.dao.TVerifikasiDAO;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.model.VerifikasiPermohonan;
import net.lintasarta.permohonan.model.comparator.TPermohonanComparator;
import net.lintasarta.permohonan.service.PermohonanService;
import net.lintasarta.security.dao.VHrEmployeeDAO;
import net.lintasarta.security.model.VHrEmployee;
import net.lintasarta.util.PermohonanIdGenerator;

import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 28, 2010
 * Time: 7:37:19 PM
 */
public class PermohonanServiceImpl implements PermohonanService {

    private String filePath;
    private TPermohonanDAO tPermohonanDAO;
    private TVerifikasiDAO tVerifikasiDAO;
    private TPelaksanaanDAO tPelaksanaanDAO;
    private VHrEmployeeDAO vHrEmployeeDAO;
    private MttrService mttrService;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

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

    public void setvHrEmployeeDAO(VHrEmployeeDAO vHrEmployeeDAO) {
        this.vHrEmployeeDAO = vHrEmployeeDAO;
    }

    public MttrService getMttrService() {
        return mttrService;
    }

    public void setMttrService(MttrService mttrService) {
        this.mttrService = mttrService;
    }

    public TPermohonan getNewPermohonan() {
        return new TPermohonan();
    }

    public String getPermohonanID() {
        int i = tPermohonanDAO.getGeneratedID();
        String seq = String.valueOf(i);
        PermohonanIdGenerator pid = new PermohonanIdGenerator(seq);
        String permohonanIdResult = pid.getPermohonanId();

        return permohonanIdResult;
    }

    public TPermohonan getTPermohonanByTIdossPermohonanId(String t_idoss_permohonan_id) {
        return gettPermohonanDAO().getTPermohonanByTIdossPermohonanId(t_idoss_permohonan_id);
    }

    public List<TPermohonan> getAllTPermohonan() {
        List<TPermohonan> tPermohonans = gettPermohonanDAO().getAllTPermohonan();
        java.util.Collections.sort(tPermohonans, new TPermohonanComparator());

        return tPermohonans;

//        return gettPermohonanDAO().getAllTPermohonan();
    }

    private void saveFile(String fileName, InputStream fin) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(fin);

            File baseDir = new File(filePath);

            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }

            File file = new File(filePath + fileName);

            OutputStream fout = new FileOutputStream(file);
            out = new BufferedOutputStream(fout);
            byte buffer[] = new byte[1024];
            int ch = in.read(buffer);
            while (ch != -1) {
                out.write(buffer, 0, ch);
                ch = in.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null)
                    out.close();

                if (in != null)
                    in.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void createTPermohonan(String uploadedFileName, TPermohonan tPermohonan, List<Integer> employeeRole) {
        int i = tPermohonanDAO.getGeneratedID();
        tPermohonan.setT_idoss_permohonan_id(getPermohonanID());
        tPermohonan.setGen_id_col(i);
        if (uploadedFileName != null) {
            saveFile(uploadedFileName, tPermohonan.getUploadStream());
            File file = new File(filePath + uploadedFileName);
            tPermohonan.setLampiran(file.getPath());
        }
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPermohonan.setCreated_date(ts);
        tPermohonan.setUpdated_date(ts);
        if (employeeRole.contains(1532)) {
            tPermohonan.setStatus_track_permohonan("Disetujui Asman Dukophar");
        } else if (tPermohonan.getBagian_pemohon().contains("DUKUNGAN OPERASI DAN PEMELIHARAAN")) {
            if (tPermohonan.getNik_pemohon().equals(tPermohonan.getNik_manager())) {
                tPermohonan.setStatus_track_permohonan("Permohonan Baru Manager Dukophar");
            } else if (tPermohonan.getNik_pemohon().equals(tPermohonan.getNik_gm())) {
                tPermohonan.setStatus_track_permohonan("Permohonan Baru GM Dukophar");
//            } else if (tPermohonan.getNik_pemohon().equals(tPermohonan.getNik_asman())) {
//                tPermohonan.setStatus_track_permohonan("Disetujui Asman Dukophar");
            } else {
                tPermohonan.setStatus_track_permohonan("Persetujuan Analyst");
            }
        } else if (tPermohonan.getNik_pemohon().equals(tPermohonan.getNik_manager())) {
            tPermohonan.setStatus_track_permohonan("Disetujui Manager Pemohon");
        } else if (tPermohonan.getNik_pemohon().equals(tPermohonan.getNik_gm())) {
            tPermohonan.setStatus_track_permohonan("Disetujui GM Pemohon");
        } else {
            tPermohonan.setStatus_track_permohonan("Persetujuan Manager");
        }
        gettPermohonanDAO().createTPermohonan(tPermohonan);


    }

    public void simpanAllTPermohonan(String uploadedFileName, TPermohonan tPermohonan, Mttr mttr, List<Integer> employeeRole) {

        createTPermohonan(uploadedFileName, tPermohonan, employeeRole);

        TVerifikasi tVerifikasi = new TVerifikasi();
        tVerifikasi.setT_idoss_verifikasi_id(tPermohonan.getT_idoss_permohonan_id());
        tVerifikasi.setTgl_permohonan(tPermohonan.getTgl_permohonan());
        tVerifikasi.setUrgensi(tPermohonan.getUrgensi());
        tVerifikasi.setDampak(tPermohonan.getDampak());
        tVerifikasi.setType_permohonan(tPermohonan.getType_permohonan());
        tVerifikasi.setStatus_permohonanasman("NEW");
        tVerifikasi.setStatus_permohonanmanager("NEW");
        tVerifikasi.setStatus_permohonan_gm("NEW");
        tVerifikasi.setCreated_date(tPermohonan.getCreated_date());
        tVerifikasi.setCreated_user(tPermohonan.getCreated_user());
        tVerifikasi.setUpdated_date(tPermohonan.getUpdated_date());
        tVerifikasi.setUpdated_user(tPermohonan.getUpdated_user());
        gettVerifikasiDAO().createTVerifikasi(tVerifikasi);

        TPelaksanaan tPelaksanaan = new TPelaksanaan();
        tPelaksanaan.setT_idoss_pelaksanaan_id(tPermohonan.getT_idoss_permohonan_id());
        tPelaksanaan.setTgl_permohonan(tPermohonan.getTgl_permohonan());
        tPelaksanaan.setStatus_perubahan("OPEN");
        tPelaksanaan.setCreated_date(tPermohonan.getCreated_date());
        tPelaksanaan.setCreated_user(tPermohonan.getCreated_user());
        tPelaksanaan.setUpdated_date(tPermohonan.getUpdated_date());
        tPelaksanaan.setUpdated_user(tPermohonan.getUpdated_user());
        gettPelaksanaanDAO().createTPelaksanaan(tPelaksanaan);

        mttr.setNomor_tiket(tPermohonan.getT_idoss_permohonan_id());
        Timestamp created = tPermohonan.getCreated_date();
        long duration = created.getTime();
        mttr.setOpened(duration);
        mttr.setUpdated_by(tPermohonan.getUpdated_user());
        mttr.setUpdated_date(tPermohonan.getUpdated_date());
        getMttrService().createMttr(mttr);
    }

    public void saveOrUpdateTPermohonan(TPermohonan tPermohonan) {
        gettPermohonanDAO().saveOrUpdateTPermohonan(tPermohonan);
        TVerifikasi tVerifikasi = new TVerifikasi();
        tVerifikasi.setDampak(tPermohonan.getDampak());
    }

    public TVerifikasi getTVerifikasiByTIdossVerifikasiId(String t_idoss_verifikasi_id) {
        return gettVerifikasiDAO().getTVerifikasiByTIdossVerifikasiId(t_idoss_verifikasi_id);
    }

    public TVerifikasi getNewVerifikasi() {
        return new TVerifikasi();
    }

    public TPelaksanaan getTPelaksanaanByTIdossPelaksanaanId(String t_idoss_pelaksanaan_id) {
        return gettPelaksanaanDAO().getTPelaksanaanByTIdossPelaksanaanId(t_idoss_pelaksanaan_id);
    }

    @Override
    public TPelaksanaan getNewPelaksanaan() {
        return new TPelaksanaan();
    }

    @Override
    public String getManager(String nikPemohon) {
        return gettPermohonanDAO().getManager(nikPemohon);
    }

    @Override
    public String getManagerReport(String nikPemohon) {
        return gettPermohonanDAO().getManagerReport(nikPemohon);
    }

    public List<TPermohonan> getTPermohonanByStatusAndNikManager(TPermohonan tPermohonan) {
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByStatusAndNikManager(tPermohonan);
        hitungDurasiMttr(tPermohonans);
        java.util.Collections.sort(tPermohonans, new TPermohonanComparator());
        return tPermohonans;
    }

    public List<TPermohonan> getTPermohonanByStatusAndNikGM(TPermohonan tPermohonan) {
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByStatusAndNikGM(tPermohonan);
        hitungDurasiMttr(tPermohonans);
        java.util.Collections.sort(tPermohonans, new TPermohonanComparator());
        return tPermohonans;
    }

    public List<TPermohonan> getTPermohonanByNikPelaksanaStatus(String nik_pelaksana, String status_track_permohonan) {
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikPelaksanaStatus(nik_pelaksana, status_track_permohonan);
        java.util.Collections.sort(tPermohonans, new TPermohonanComparator());
        return tPermohonans;
    }

    public List<TPermohonan> getTPermohonanByStatusTrackPermohonan(TPermohonan tPermohonan) {
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByStatusTrackPermohonan(tPermohonan);
        hitungDurasiMttr(tPermohonans);
        java.util.Collections.sort(tPermohonans, new TPermohonanComparator());
        return tPermohonans;
    }

    public List<TPermohonan> getTPermohonanByStatusTrackPermohonanAndDampak(TPermohonan tPermohonan) {
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByStatusTrackPermohonanAndDampak(tPermohonan);
        hitungDurasiMttr(tPermohonans);
        java.util.Collections.sort(tPermohonans, new TPermohonanComparator());
        return tPermohonans;
    }

    public List<TPermohonan> getTPermohonanByNikPemohon(TPermohonan tPermohonan) {
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikPemohon(tPermohonan);
        hitungDurasiMttr(tPermohonans);
        java.util.Collections.sort(tPermohonans, new TPermohonanComparator());
        return tPermohonans;
    }

    public List<VHrEmployee> getVHrEmployeeByEmployeeNo(String employeeNo) {
        return vHrEmployeeDAO.getVHrEmployeeByEmployeeNo(employeeNo);
    }

    public List<TPermohonan> getTPermohonanByNikPelaksana(VerifikasiPermohonan verifikasiPermohonan) {
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikPelaksana(verifikasiPermohonan);
        hitungDurasiMttr(tPermohonans);
        java.util.Collections.sort(tPermohonans, new TPermohonanComparator());
        return tPermohonans;
    }

    public List<TPermohonan> hitungDurasiMttr(List<TPermohonan> tPermohonans) {
        for (TPermohonan tPermohonan : tPermohonans) {
            List<Mttr> mttrs = mttrService.getMttrByNomorTiket(tPermohonan.getT_idoss_permohonan_id());
            for (Mttr mttr : mttrs) {
                long durasi = mttrService.getDurasi(mttr);
                long lama_pending = mttrService.getLamaPending(mttr);
                long durasiPelaksanaan = mttrService.getDurasiPelaksanaan(mttr);
                long mttrLong = durasiPelaksanaan - lama_pending;
//                long durasiTarget = mttrService.getDurasiTarget(mttr);
                String durasiTarget = mttrService.getDurasiTarget(mttr);
                long ts = Calendar.getInstance().getTimeInMillis();
                tPermohonan.setDurasi(getDuration(durasi));

                if (mttrLong > 0) {
                    tPermohonan.setMttr(getDuration(mttrLong));
                } else {
                    tPermohonan.setMttr("");
                }

                if (durasiTarget != null) {
                    tPermohonan.setTarget(durasiTarget + " hari");
                } else {
                    tPermohonan.setTarget("");
                }

                if (mttrService.isInProgress(mttr)) {
                    tPermohonan.setStatus_track_permohonan("INPROGRESS");
                }
            }
        }
        return tPermohonans;
    }

    public String getDuration(long duration) {
        final int millisPerSecond = 1000;
        final int millisPerMinute = 1000 * 60;
        final int millisPerHour = 1000 * 60 * 60;
        final int millisPerDay = 1000 * 60 * 60 * 24;

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
        } else if ((days < 1) && (hours < 1) && (minutes < 1)) {
            return "< 1 m";
        } else {
            return "0";
        }
    }
}
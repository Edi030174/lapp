package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.pengaduan.service.MttrService;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.PelaksanaanService;
import net.lintasarta.permohonan.service.PermohonanService;
import net.lintasarta.permohonan.service.VerifikasiService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Joshua
 * Date: Aug 3, 2010
 * Time: 11:49:01 AM
 */
public class PelaksanaanCtrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(PelaksanaanCtrl.class);

    protected Window window_Permohonan;
    protected Window window_Pelaksanaan;
    protected Groupbox groupboxPelaksanaan;
    protected Combobox combobox_Status;
    protected Datebox datebox_pending;
    protected Textbox textbox_pelaksana;
    protected Listbox listbox_DaftarPermohonan;
    protected Button btnSimpan_Pelaksanaan;
    protected Button btnBatal;
    protected PelaksanaanCtrl pelaksanaanCtrl;

    private transient TPermohonan tPermohonan;
    private transient TPelaksanaan tPelaksanaan;
    private transient TVerifikasi tVerifikasi;
    private transient Mttr mttr;
    private transient PermohonanService permohonanService;
    private transient PelaksanaanService pelaksanaanService;
    private transient VerifikasiService verifikasiService;
    private transient MttrService mttrService;

    public PelaksanaanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Pelaksanaan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Map<String, Object> args = getCreationArgsMap(event);
        if (args.containsKey("tVerifikasi")) {
            TVerifikasi tVerifikasi = (TVerifikasi) args.get("tVerifikasi");
            settVerifikasi(tVerifikasi);
        } else {
            settVerifikasi(null);
        }
        if (args.containsKey("tPermohonan")) {
            TPermohonan tPermohonan = (TPermohonan) args.get("tPermohonan");
            settPermohonan(tPermohonan);
        } else {
            settPermohonan(null);
        }
        if (args.containsKey("tPelaksanaan")) {
            TPelaksanaan tPelaksanaan = (TPelaksanaan) args.get("tPelaksanaan");
            settPelaksanaan(tPelaksanaan);
        } else {
            settPelaksanaan(null);
        }
//        if (args.containsKey("pelaksanaanCtrl")) {
//            pelaksanaanCtrl = (PelaksanaanCtrl) args.get("pelaksanaanCtrl");
//        } else {
//            pelaksanaanCtrl = null;
//        }
//        if (args.containsKey("listbox_DaftarPermohonan")) {
//            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
//        } else {
//            listbox_DaftarPermohonan = null;
//        }
//        doCheckRights(gettPermohonan(), gettVerifikasi());
        textbox_pelaksana.setReadonly(true);
        List<Mttr> mttrs = getMttrService().getMttrByNomorTiket(gettPermohonan().getT_idoss_permohonan_id());
        for (Mttr mttr1 : mttrs) {
            setMttr(mttr1);
        }
        doShowDialog(gettPelaksanaan(), gettPermohonan(), gettVerifikasi());
    }

    private void doCheckRights(TPermohonan tPermohonan, TVerifikasi tVerifikasi) {
        UserWorkspace workspace = getUserWorkspace();
        boolean pl = (tPermohonan.getStatus_track_permohonan().contains("Disetujui Manager Dukophar")) && (tVerifikasi.getDampak().equals("MINOR"));
        boolean pk = (tPermohonan.getStatus_track_permohonan().contains("INPROGRESS")) && (tVerifikasi.getDampak().equals("MAJOR"));
        boolean pm = pl ^ pk;
        btnSimpan_Pelaksanaan.setVisible(pm);
    }

    private void doShowDialog(TPelaksanaan tPelaksanaan, TPermohonan tPermohonan, TVerifikasi tVerifikasi) throws InterruptedException {
        try {
            doWriteBeanToComponents(tPelaksanaan);
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponents(TPelaksanaan tPelaksanaan) throws Exception {
        if (tPelaksanaan.getStatus_perubahan().equals("INPROGRESS")) {
            combobox_Status.setValue("In Progress");
        } else if (tPelaksanaan.getStatus_perubahan().equals("PENDING")) {
            combobox_Status.setValue("Pending");
        } else if (tPelaksanaan.getStatus_perubahan().equals("CLOSED")) {
            combobox_Status.setValue("Selesai");
        }
        if (tPelaksanaan.getStatus_perubahan().equalsIgnoreCase("PENDING")) {
            datebox_pending.setVisible(true);
        } else {
            datebox_pending.setVisible(false);
        }
        if (tPelaksanaan.getStatus_perubahan().equals("CLOSED")) {
            textbox_pelaksana.setReadonly(false);
        }
        textbox_pelaksana.setValue(tPelaksanaan.getCatatan_pelaksana());
        if (mttr.getPending_end() == 0) {
            datebox_pending.setValue(tPelaksanaan.getCreated_date());
        } else {
            Timestamp ts = new Timestamp(mttr.getPending_end());
            datebox_pending.setValue(ts);
        }
    }

    private int getPending(long duration) {
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
        return hours;
    }

    public void onClick$btnBatal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        window_Permohonan.onClose();
    }

    public void onClick$btnSimpan_Pelaksanaan(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        if (isValidatedFlow()) {
            doSimpan();
            window_Permohonan.onClose();
        }
    }

    public void onChange$combobox_Status() {
        if (combobox_Status.getValue().equals("Open")) {
            textbox_pelaksana.setReadonly(true);
            textbox_pelaksana.setValue(tPelaksanaan.getCatatan_pelaksana());
            datebox_pending.setVisible(false);
        } else if (combobox_Status.getValue().equals("In Progress")) {
            textbox_pelaksana.setReadonly(true);
            textbox_pelaksana.setValue(tPelaksanaan.getCatatan_pelaksana());
            datebox_pending.setVisible(false);
        } else if (combobox_Status.getValue().equals("Pending")) {
            textbox_pelaksana.setReadonly(true);
            textbox_pelaksana.setValue(tPelaksanaan.getCatatan_pelaksana());
            datebox_pending.setVisible(true);
        } else if (combobox_Status.getValue().equals("Selesai")) {
            textbox_pelaksana.setReadonly(false);
            datebox_pending.setVisible(false);
        }
    }

    private void doSimpan() throws Exception {
        TPermohonan tPermohonan = gettPermohonan();
        TPelaksanaan tPelaksanaan = gettPelaksanaan();
        Mttr mttr = getMttr();
        doWriteComponentsToBean(tPelaksanaan, tPermohonan, mttr);
        mttr.setNomor_tiket(tPelaksanaan.getT_idoss_pelaksanaan_id());

        try {
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
            getPelaksanaanService().saveOrUpdateTPelaksanaan(tPelaksanaan);
            getMttrService().saveOrUpdateMttr(mttr);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    private void doWriteComponentsToBean(TPelaksanaan tPelaksanaan, TPermohonan tPermohonan, Mttr mttr) {
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        if (combobox_Status.getSelectedIndex() == 0) {
            tPelaksanaan.setStatus_perubahan("INPROGRESS");
            tPermohonan.setStatus_track_permohonan("INPROGRESS");
            long tsLong = ts.getTime();
            if (mttr.getInprogress() > 0) {
                mttr.setPending_end(tsLong);
            }
            mttr.setInprogress(tsLong);
        } else if (combobox_Status.getSelectedIndex() == 1) {
            tPelaksanaan.setStatus_perubahan("PENDING");
            tPermohonan.setStatus_track_permohonan("PENDING");
            if (mttr.getPending_end() > 0) {
                long lama_pending = mttr.getLama_pending();
                if (ts.getTime() < mttr.getPending_end()) {
                    mttr.setLama_pending(lama_pending + ts.getTime() - mttr.getPending_start());
                } else {
                    mttr.setLama_pending(lama_pending + mttr.getPending_end() - mttr.getPending_start());
                }
            }
            long pending_start = ts.getTime();
            mttr.setPending_start(pending_start);
            Timestamp tspending_end = new Timestamp(datebox_pending.getValue().getTime());
            long pending_end = tspending_end.getTime();
            mttr.setPending_end(pending_end);
        } else if (combobox_Status.getSelectedIndex() == 2) {
            tPelaksanaan.setStatus_perubahan("CLOSED");
            tPermohonan.setStatus_track_permohonan("CLOSED");
            mttr.setClosed(ts.getTime());
        }
        tPelaksanaan.setCatatan_pelaksana(textbox_pelaksana.getValue());
        tPelaksanaan.setNama_pelaksana(getUserWorkspace().getUserSession().getEmployeeName());
        tPelaksanaan.setId_pelaksana(getUserWorkspace().getUserSession().getEmployeeNo());

        tPelaksanaan.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        tPelaksanaan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());

        tPelaksanaan.setUpdated_date(ts);
        tPelaksanaan.setCreated_date(ts);
    }

    public void onChange$datebox_pending(Event event) throws Exception {
        if (datebox_pending.getValue() != null) {
            if (datebox_pending.getValue().before(new Date())) {
                alert("End Date must be equal or bigger than this date");
                datebox_pending.setValue(new Date());
            }
        }
    }

    private boolean isValidatedFlow() throws InterruptedException {
        if (combobox_Status.getValue().equalsIgnoreCase("Pending")) {
            if (datebox_pending.getValue() != null) {
                if (datebox_pending.getValue().before(new Date())) {
                    alert("End Date must be equal or bigger than this date");
                    datebox_pending.setValue(new Date());
                    return false;
                }
            }
        }
        return true;
    }

    public TPelaksanaan gettPelaksanaan() {
        return tPelaksanaan;
    }

    public void settPelaksanaan(TPelaksanaan tPelaksanaan) {
        this.tPelaksanaan = tPelaksanaan;
    }

    public PelaksanaanService getPelaksanaanService() {
        return pelaksanaanService;
    }

    public void setPelaksanaanService(PelaksanaanService pelaksanaanService) {
        this.pelaksanaanService = pelaksanaanService;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public TPermohonan gettPermohonan() {
        return tPermohonan;
    }

    public void settPermohonan(TPermohonan tPermohonan) {
        this.tPermohonan = tPermohonan;
    }

    public TVerifikasi gettVerifikasi() {
        return tVerifikasi;
    }

    public void settVerifikasi(TVerifikasi tVerifikasi) {
        this.tVerifikasi = tVerifikasi;
    }

    public VerifikasiService getVerifikasiService() {
        return verifikasiService;
    }

    public void setVerifikasiService(VerifikasiService verifikasiService) {
        this.verifikasiService = verifikasiService;
    }

    public Mttr getMttr() {
        return mttr;
    }

    public void setMttr(Mttr mttr) {
        this.mttr = mttr;
    }

    public MttrService getMttrService() {
        return mttrService;
    }

    public void setMttrService(MttrService mttrService) {
        this.mttrService = mttrService;
    }
}
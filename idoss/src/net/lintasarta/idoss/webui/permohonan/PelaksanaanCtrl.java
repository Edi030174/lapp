package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
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
import java.util.Calendar;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 3, 2010
 * Time: 11:49:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class PelaksanaanCtrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(PelaksanaanCtrl.class);

    protected Window window_Permohonan;
    protected Window window_Pelaksanaan;

    protected Radiogroup radiogroup_StatusPerubahan;
    protected Radio open;
    protected Radio inprogress;
    protected Radio closed;
    protected Radio pending;
    protected Datebox datebox_Pending;
    protected Textbox textbox_pelaksana;

    private transient String oldVar_selesai;
    private transient String oldVar_tunda;
    private transient String oldVar_mulai;
    private transient String oldVar_dateboxTglPermohonan;
    private transient String oldVar_dateboxPending;

    protected Listbox listbox_DaftarPermohonan;
    protected Button btnSimpan_Pelaksanaan;
    protected PelaksanaanCtrl pelaksanaanCtrl;

    private transient TPermohonan tPermohonan;
    private transient TPelaksanaan tPelaksanaan;
    private transient TVerifikasi tVerifikasi;
    private transient PermohonanService permohonanService;
    private transient PelaksanaanService pelaksanaanService;
    private transient VerifikasiService verifikasiService;

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
        doShowDialog(gettPelaksanaan(), gettPermohonan(), gettVerifikasi());
    }

    private void doCheckRights(TPermohonan tPermohonan, TVerifikasi tVerifikasi) {
        UserWorkspace workspace = getUserWorkspace();
        boolean pl = (tPermohonan.getStatus_track_permohonan().contains("Disetujui Manager Dukophar")) && (tVerifikasi.getDampak().equals("MINOR"));
        boolean pk = (tPermohonan.getStatus_track_permohonan().contains("Disetujui GM Dukophar")) && (tVerifikasi.getDampak().equals("MAJOR"));
        boolean pm = pl^pk;
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
//        if (tPelaksanaan.getStatus_perubahan().equals("OPEN")) {
//            radiogroup_StatusPerubahan.setSelectedItem(open);
//        } else if (tPelaksanaan.getStatus_perubahan().equals("INPROGRESS")) {
//            radiogroup_StatusPerubahan.setSelectedItem(inprogress);
//        } else if (tPelaksanaan.getStatus_perubahan().equals("CLOSED")) {
//            radiogroup_StatusPerubahan.setSelectedItem(closed);
//        } else if (tPelaksanaan.getStatus_perubahan().equals("PENDING")) {
//            radiogroup_StatusPerubahan.setSelectedItem(pending);
//        }
//        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
//        datebox_TglPermohonan.setValue(ts);
//        textbox_pelaksana.setValue(tPelaksanaan.getCatatan_pelaksana());
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
        doSimpan();
        window_Permohonan.onClose();
    }

    private void doSimpan() throws Exception {
        TPermohonan tPermohonan = gettPermohonan();
        TPelaksanaan tPelaksanaan = gettPelaksanaan();
        doWriteComponentsToBean(tPelaksanaan, tPermohonan);

        try {
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
            getPelaksanaanService().saveOrUpdateTPelaksanaan(tPelaksanaan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    private void doWriteComponentsToBean(TPelaksanaan tPelaksanaan, TPermohonan tPermohonan) {
        Radio status = radiogroup_StatusPerubahan.getSelectedItem();
        tPelaksanaan.setStatus_perubahan(status.getValue());
        tPermohonan.setStatus_track_permohonan(status.getValue());
        if (radiogroup_StatusPerubahan.getSelectedItem().equals(pending)) {
            tPelaksanaan.setTgl_pending(new Timestamp(datebox_Pending.getValue().getTime()));
        }
//        tPelaksanaan.setTgl_permohonan(new Timestamp(datebox_TglPermohonan.getValue().getTime()));
        tPelaksanaan.setCatatan_pelaksana(textbox_pelaksana.getValue());
        tPelaksanaan.setNama_pelaksana(getUserWorkspace().getUserSession().getEmployeeName());
        tPelaksanaan.setId_pelaksana(getUserWorkspace().getUserSession().getEmployeeNo());

        tPelaksanaan.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        tPelaksanaan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPelaksanaan.setUpdated_date(now);
        tPelaksanaan.setCreated_date(now);
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
}
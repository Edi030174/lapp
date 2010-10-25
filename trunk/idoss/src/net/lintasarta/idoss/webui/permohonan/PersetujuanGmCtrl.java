package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaListModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.PermohonanService;
import net.lintasarta.permohonan.service.VerifikasiService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 20, 2010
 * Time: 5:53:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersetujuanGmCtrl extends GFCBaseCtrl implements Serializable {
    private transient static final Logger logger = Logger.getLogger(PersetujuanGmCtrl.class);

    protected Window window_Permohonan;
    protected Window window_PersetujuanGm;

    protected Button btn_SimpanPersetujuanAsman;
    protected Button btn_SimpanPersetujuanManager;
    protected Button btn_SimpanPersetujuanGm;
    protected Button btn_Batal;

    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Datebox datebox_Tanggal;
    protected Textbox textbox_NikPemohon;

    protected Listbox listbox_NamaPelaksana;

    protected Radiogroup radiogroup_Prioritas;
    protected Radio high;
    protected Radio normal;
    protected Radiogroup radiogroup_Dampak;
    protected Radio major;
    protected Radio minor;
    protected Groupbox groupbox_AM;
    protected Groupbox groupbox_Manager;
    protected Groupbox groupbox_Gm;

    protected FCKeditor fck_DetailPermohonan;

    protected Radiogroup radiogroup_StatusPermohonanAsman;
    protected Radio radio_DisetujuiAM;
    protected Radio radio_DitolakAM;
    protected FCKeditor fck_CatatanAsman;

    protected Radiogroup radiogroup_StatusPermohonanManager;
    protected Radio radio_DisetujuiM;
    protected Radio radio_DitolakM;
    protected FCKeditor fck_CatatanManager;

    protected Radiogroup radiogroup_StatusPermohonanGm;
    protected Radio radio_DisetujuiGM;
    protected Radio radio_DitolakGM;
    protected FCKeditor fck_CatatanGm;

    private transient String oldVar_textbox_TIdossPermohonanId;
    private transient String oldVar_textbox_NamaPemohon;
    private transient String oldVar_datebox_Tanggal;
    private transient String oldVar_textbox_NikPemohon;
    private transient String oldVar_fck_DetailPermohonan;
    private transient String oldVar_fck_CatatanAsman;
    private transient String oldVar_fck_CatatanManager;
    private transient boolean oldVar_radio_DisetujuiGM;
    private transient boolean oldVar_radio_DitolakGM;
    private transient String oldVar_fck_CatatanGm;

    protected Listbox listbox_DaftarPermohonan;
    protected PersetujuanGmCtrl persetujuanGmCtrl;

    private transient TPermohonan tPermohonan;
    private transient TVerifikasi tVerifikasi;
    private transient TPelaksanaan tPelaksanaan;
    private transient VerifikasiService verifikasiService;
    private transient PermohonanService permohonanService;

    public PersetujuanGmCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_PersetujuanGm(Event event) throws Exception {

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
        if (args.containsKey("persetujuanGmCtrl")) {
            persetujuanGmCtrl = (PersetujuanGmCtrl) args.get("persetujuanGmCtrl");
        } else {
            persetujuanGmCtrl = null;
        }
        if (args.containsKey("listbox_DaftarPermohonan")) {
            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
        } else {
            listbox_DaftarPermohonan = null;
        }
        ListModelList lmlNamaPelaksana = new ListModelList(getVerifikasiService().getEmployeeName());
        VHrEmployeePelaksana pelaksana = new VHrEmployeePelaksana();
        pelaksana.setEmployee_name("Silakan pilih");
        pelaksana.setEmployee_no("555");
        lmlNamaPelaksana.add(0, pelaksana);
        listbox_NamaPelaksana.setModel(lmlNamaPelaksana);
        listbox_NamaPelaksana.setItemRenderer(new PelaksanaListModelItemRenderer());

        doCheckRights(gettVerifikasi(), gettPermohonan());
        doShowDialog(gettVerifikasi(), gettPermohonan());
    }

    private void doCheckRights(TVerifikasi tVerifikasi, TPermohonan tPermohonan) {
        UserWorkspace workspace = getUserWorkspace();
        groupbox_AM.setVisible(workspace.isAllowed("groupbox_AMDukophar"));
        groupbox_Manager.setVisible(workspace.isAllowed("groupbox_ManagerDukophar"));
        groupbox_Gm.setVisible(workspace.isAllowed("groupbox_GmDukophar"));
//        boolean np = (workspace.isAllowed("btn_SimpanPersetujuanAsman")) && ((tPermohonan.getStatus_track_permohonan().contains("Disetujui Manager Dukophar")) || (tPermohonan.getStatus_track_permohonan().contains("Disetujui GM Dukophar")));
//        listbox_NamaPelaksana.setVisible(np);
        boolean b = (workspace.isAllowed("btn_SimpanPersetujuanAsman")) && (tPermohonan.getStatus_track_permohonan().contains("Disetujui GM Pemohon"));
        btn_SimpanPersetujuanAsman.setVisible(b);
        boolean bb = (workspace.isAllowed("btn_SimpanPersetujuanManager")) && (tPermohonan.getStatus_track_permohonan().contains("Disetujui Asman Dukophar"));
        btn_SimpanPersetujuanManager.setVisible(bb);
        boolean bbb = (workspace.isAllowed("btn_SimpanPersetujuanGm")) && (tPermohonan.getStatus_track_permohonan().contains("Disetujui Manager Dukophar")) && (tVerifikasi.getDampak().equals("MAJOR"));
        btn_SimpanPersetujuanGm.setVisible(bbb);
    }

    private void doShowDialog(TVerifikasi tVerifikasi, TPermohonan tPermohonan) throws InterruptedException {
        try {
            doWriteBeanToComponents(tVerifikasi, tPermohonan);
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponents(TVerifikasi tVerifikasi, TPermohonan tPermohonan) throws Exception {
        textbox_TIdossPermohonanId.setValue(tVerifikasi.getT_idoss_verifikasi_id());
        textbox_NamaPemohon.setValue(tPermohonan.getNama_pemohon());
        datebox_Tanggal.setValue(tVerifikasi.getTgl_permohonan());
        textbox_NikPemohon.setValue(tPermohonan.getNik_pemohon());
        int indexPlks = 0;
        ListModel listPlks = listbox_NamaPelaksana.getModel();
        for (int i = 0; i < listPlks.getSize(); i++) {
            VHrEmployeePelaksana np = (VHrEmployeePelaksana) listPlks.getElementAt(i);
            if (np.getEmployee_no().equals(tVerifikasi.getNik_pelaksana())) indexPlks = i;
        }
        listbox_NamaPelaksana.setSelectedIndex(indexPlks);
        fck_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());
        if (tVerifikasi.getDampak().equals("MAJOR")) {
            radiogroup_Dampak.setSelectedItem(major);
        }
        if (tVerifikasi.getStatus_permohonanasman().equals("Ditolak Asman Dukophar")) {
            radiogroup_StatusPermohonanAsman.setSelectedItem(radio_DitolakAM);
        }
        fck_CatatanAsman.setValue(tVerifikasi.getCatatan_asman());
        if (tVerifikasi.getStatus_permohonanmanager().equals("Ditolak Manager Dukophar")) {
            radiogroup_StatusPermohonanManager.setSelectedItem(radio_DitolakM);
        }
        fck_CatatanManager.setValue(tVerifikasi.getCatatan_manager());
        if (tVerifikasi.getStatus_permohonan_gm().equals("Ditolak GM Dukophar")) {
            radiogroup_StatusPermohonanGm.setSelectedItem(radio_DitolakGM);
        }
        fck_CatatanGm.setValue(tVerifikasi.getCatatan_gm());
    }

    public void onClick$btn_Batal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        window_Permohonan.onClose();
    }

    public void onClick$btn_SimpanPersetujuanAsman(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        if (isValidatedFlow()) {
            doSimpanAsmanDukophar();
            window_Permohonan.onClose();
        }
    }

    private boolean isValidatedFlow() throws InterruptedException {
        if (listbox_NamaPelaksana.getSelectedItem().getLabel().equalsIgnoreCase("Silakan pilih")) {
            Messagebox.show("Silakan pilih nama pelaksana");
            return false;
        }
        if (listbox_NamaPelaksana.getSelectedItem() == null) {
            Messagebox.show("Silakan pilih nama pelaksana");
            return false;
        }
        return true;
    }

    private void doSimpanAsmanDukophar() throws Exception {
        TPermohonan tPermohonan = gettPermohonan();
        TVerifikasi tVerifikasi = gettVerifikasi();
        TPelaksanaan tPelaksanaan = gettPelaksanaan();
        doWriteComponentsToBean1(tPermohonan, tVerifikasi, tPelaksanaan);

        try {
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
            getVerifikasiService().saveOrUpdateTVerifikasi(tVerifikasi);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
        doStoreInitValues();
    }

    private void doWriteComponentsToBean1(TPermohonan tPermohonan, TVerifikasi tVerifikasi, TPelaksanaan tPelaksanaan) {
        Radio dampak = radiogroup_Dampak.getSelectedItem();
        tVerifikasi.setDampak(dampak.getValue());
        Listitem itempelaksana = listbox_NamaPelaksana.getSelectedItem();
        ListModelList lml3 = (ListModelList) listbox_NamaPelaksana.getListModel();
        VHrEmployeePelaksana vHrEmployeePelaksana = (VHrEmployeePelaksana) lml3.get(itempelaksana.getIndex());
//        if (!vHrEmployeePelaksana.getEmployee_name().equalsIgnoreCase("Silakan pilih")) {
//            tPelaksanaan.setNama_pelaksana(vHrEmployeePelaksana.getEmployee_name());
//        }
//        if (!vHrEmployeePelaksana.getEmployee_no().equalsIgnoreCase("555")) {
//            tVerifikasi.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());
//        }

        Radio statusAM = radiogroup_StatusPermohonanAsman.getSelectedItem();
        tVerifikasi.setStatus_permohonanasman(statusAM.getValue());
        tPermohonan.setStatus_track_permohonan(statusAM.getValue());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tVerifikasi.setUpdated_asman(ts);
        tVerifikasi.setCatatan_asman(fck_CatatanAsman.getValue());
    }

    public void onClick$btn_SimpanPersetujuanManager(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpanManagerDukophar();
        window_Permohonan.onClose();
    }

    private void doSimpanManagerDukophar() throws Exception {
        TPermohonan tPermohonan = gettPermohonan();
        TVerifikasi tVerifikasi = gettVerifikasi();
        doWriteComponentsToBean2(tPermohonan, tVerifikasi);

        try {
            getVerifikasiService().saveOrUpdateTVerifikasi(tVerifikasi);
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
        doStoreInitValues();
    }

    private void doWriteComponentsToBean2(TPermohonan tPermohonan, TVerifikasi tVerifikasi) {
        Radio statusM = radiogroup_StatusPermohonanManager.getSelectedItem();
        tVerifikasi.setStatus_permohonanmanager(statusM.getValue());
        tPermohonan.setStatus_track_permohonan(statusM.getValue());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tVerifikasi.setUpdated_manager(ts);
        tVerifikasi.setCatatan_manager(fck_CatatanManager.getValue());
    }

    public void onClick$btn_SimpanPersetujuanGm(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpanGMDukophar();
        window_Permohonan.onClose();
    }

    private void doSimpanGMDukophar() throws Exception {
        TPermohonan tPermohonan = gettPermohonan();
        TVerifikasi tVerifikasi = gettVerifikasi();
        doWriteComponentsToBean3(tPermohonan, tVerifikasi);

        try {
            getVerifikasiService().saveOrUpdateTVerifikasi(tVerifikasi);
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
        doStoreInitValues();
    }

    private void doWriteComponentsToBean3(TPermohonan tPermohonan, TVerifikasi tVerifikasi) {
        Radio statusGM = radiogroup_StatusPermohonanGm.getSelectedItem();
        tVerifikasi.setStatus_permohonan_gm(statusGM.getValue());
        tPermohonan.setStatus_track_permohonan(statusGM.getValue());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tVerifikasi.setUpdated_gm(ts);
        tVerifikasi.setCatatan_gm(fck_CatatanGm.getValue());
    }

    private void doStoreInitValues() {
//        oldVar_radio_DisetujuiGM = radio_DisetujuiGM.getValue();
        oldVar_textbox_TIdossPermohonanId = textbox_TIdossPermohonanId.getValue();

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

    public TPelaksanaan gettPelaksanaan() {
        return tPelaksanaan;
    }

    public void settPelaksanaan(TPelaksanaan tPelaksanaan) {
        this.tPelaksanaan = tPelaksanaan;
    }

    public VerifikasiService getVerifikasiService() {
        return verifikasiService;
    }

    public void setVerifikasiService(VerifikasiService verifikasiService) {
        this.verifikasiService = verifikasiService;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }
}
package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaListModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.PermohonanService;
import net.lintasarta.permohonan.service.VerifikasiService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: JosH
 * Date: Jul 23, 2010
 * Time: 12:48:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersetujuanCtrl extends GFCBaseCtrl implements Serializable {

    private transient static final Logger logger = Logger.getLogger(PersetujuanCtrl.class);
    protected Window window_Permohonan;
    protected Window window_Persetujuan;

    protected Groupbox groupbox_ManagerPemohon;
    protected Groupbox groupbox_GmPemohon;
    protected Groupbox groupbox_AM;
    protected Groupbox groupbox_Manager;
    protected Groupbox groupbox_Gm;

    protected Button btn_SimpanPersetujuanManagerPemohon;
    protected Button btn_SimpanPersetujuanGmPemohon;
    protected Button btn_SimpanPersetujuanAsman;
    protected Button btn_SimpanPersetujuanManager;
    protected Button btn_SimpanPersetujuanGm;
    protected Button btn_Batal;
    protected Checkbox checkbox_Cepat;
    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Textbox textbox_NikPemohon;
    protected Textbox textbox_DetailPermohonan;
    protected Textbox textbox_muser;
    protected Textbox textbox_gmuser;
    protected Textbox textbox_amdukophar;
    protected Textbox textbox_mdukophar;
    protected Textbox textbox_gmdukophar;
    protected Listbox listbox_NamaPelaksana;
    protected Datebox datebox_Tanggal;

    protected Radiogroup radiogroup_Prioritas;
    protected Radio radio_high;
    protected Radio radio_normal;
    protected Radiogroup radiogroup_Dampak;
    protected Radio radio_major;
    protected Radio radio_minor;
    protected Radiogroup radiogroup_StatusPermohonanManagerPemohon;
    protected Radio radio_DisetujuiMPemohon;
    protected Radio radio_DitolakMPemohon;
    protected Radiogroup radiogroup_StatusPermohonanGmPemohon;
    protected Radio radio_DisetujuiGmPemohon;
    protected Radio radio_DitolakGmPemohon;
    protected Radiogroup radiogroup_StatusPermohonanAsman;
    protected Radio radio_DisetujuiAM;
    protected Radio radio_DitolakAM;
    protected Radiogroup radiogroup_StatusPermohonanManager;
    protected Radio radio_DisetujuiM;
    protected Radio radio_DitolakM;
    protected Radiogroup radiogroup_StatusPermohonanGm;
    protected Radio radio_DisetujuiGM;
    protected Radio radio_DitolakGM;

    private transient String oldVar_dateboxTanggal1;
    private transient String oldVar_comboboxNikPelaksana;
    private transient boolean oldVar_checkbox1;
    private transient boolean oldVar_radioDisetujui;
    private transient boolean oldVar_radioDitolak;
    private transient String oldVar_fckCatatanAsman;
    private transient Window window_DaftarPermohonan;
    private transient Vbox vboxPrioritas;

    private transient String oldVar_checkbox2;
    private transient boolean oldVar_radioDisetujui2;
    private transient boolean oldVar_radioDitolak2;
    private transient String oldVar_fckCatatanManager;

    protected Listbox listbox_DaftarPermohonan;
    protected Button btnSimpan_Persetujuan;
    protected Button btnBatal;
    protected PersetujuanCtrl persetujuanCtrl;

    private transient TPermohonan tPermohonan;
    private transient TVerifikasi tVerifikasi;
    private transient TPelaksanaan tPelaksanaan;
    private transient PermohonanService permohonanService;
    private transient VerifikasiService verifikasiService;
    private transient PelaksanaanGangguanService pelaksanaanGangguanService;

    public PersetujuanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Persetujuan(Event event) throws Exception {

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

        if (args.containsKey("window_DaftarPermohonan")) {
            window_DaftarPermohonan = (Window) args.get("window_DaftarPermohonan");
        } else {
            window_DaftarPermohonan = null;
        }

        if (args.containsKey("listbox_DaftarPermohonan")) {
            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
        } else {
            listbox_DaftarPermohonan = null;
        }
        doCheckRights(gettVerifikasi(), gettPermohonan());
        ListModelList lmlNamaPelaksana = new ListModelList(getPelaksanaanGangguanService().getEmployeeName());
        VHrEmployeePelaksana pelaksana = new VHrEmployeePelaksana();
        pelaksana.setEmployee_name("Silakan pilih");
        pelaksana.setEmployee_no("555");
        lmlNamaPelaksana.add(0, pelaksana);
        listbox_NamaPelaksana.setModel(lmlNamaPelaksana);
        listbox_NamaPelaksana.setItemRenderer(new PelaksanaListModelItemRenderer());
        doShowDialog(gettVerifikasi(), gettPermohonan());
    }

    private void doCheckRights(TVerifikasi tVerifikasi, TPermohonan tPermohonan) {
        UserWorkspace workspace = getUserWorkspace();

        groupbox_ManagerPemohon.setVisible(workspace.isAllowed("groupbox_ManagerPemohon"));
        groupbox_GmPemohon.setVisible(workspace.isAllowed("groupbox_GmPemohon"));
        groupbox_AM.setVisible(workspace.isAllowed("groupbox_AMDukophar"));
        groupbox_Manager.setVisible(workspace.isAllowed("groupbox_ManagerDukophar"));
        groupbox_Gm.setVisible(workspace.isAllowed("groupbox_GmDukophar"));

        boolean save_muser = (workspace.isAllowed("btn_SimpanPersetujuanManagerPemohon")) && (tPermohonan.getStatus_track_permohonan().contains("Permohonan Baru"));
        btn_SimpanPersetujuanManagerPemohon.setVisible(save_muser);
        boolean save_gmuser = (workspace.isAllowed("btn_SimpanPersetujuanGmPemohon")) && (tPermohonan.getStatus_track_permohonan().contains("Disetujui Manager Pemohon"));
        btn_SimpanPersetujuanGmPemohon.setVisible(save_gmuser);
        boolean save_amdukophar = (workspace.isAllowed("btn_SimpanPersetujuanAsman")) && (tPermohonan.getStatus_track_permohonan().contains("Disetujui GM Pemohon"));
        btn_SimpanPersetujuanAsman.setVisible(save_amdukophar);
//        if(btn_SimpanPersetujuanAsman.setVisible(save_amdukophar)){
//            vboxPrioritas.setVisible(true);
//        }else{
//            vboxPrioritas.setVisible(false);
//        }
        boolean save_mdukophar = (workspace.isAllowed("btn_SimpanPersetujuanManager")) && (tPermohonan.getStatus_track_permohonan().contains("Disetujui Asman Dukophar"));
        btn_SimpanPersetujuanManager.setVisible(save_mdukophar);
        boolean save_gmdukophar = (workspace.isAllowed("btn_SimpanPersetujuanGm")) && (tPermohonan.getStatus_track_permohonan().contains("Disetujui Manager Dukophar")) && (tVerifikasi.getDampak().equals("MAJOR"));
        btn_SimpanPersetujuanGm.setVisible(save_gmdukophar);

        if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("muser")) {
            radio_high.setDisabled(true);
            radio_normal.setDisabled(true);
            radio_major.setDisabled(true);
            radio_minor.setDisabled(true);
            textbox_DetailPermohonan.setReadonly(true);
        } else if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("gmuser")) {
            radio_high.setDisabled(true);
            radio_normal.setDisabled(true);
            radio_major.setDisabled(true);
            radio_minor.setDisabled(true);
            textbox_DetailPermohonan.setReadonly(true);
        } else if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("amduk")) {
            radio_high.setDisabled(false);
            radio_normal.setDisabled(false);
            radio_major.setDisabled(false);
            radio_minor.setDisabled(false);
            textbox_DetailPermohonan.setReadonly(true);
        } else if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("mduk")) {
            radio_high.setDisabled(false);
            radio_normal.setDisabled(false);
            radio_major.setDisabled(false);
            radio_minor.setDisabled(false);
            vboxPrioritas.setVisible(true);
            textbox_DetailPermohonan.setReadonly(true);
        } else if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("gmduk")) {
            radio_high.setDisabled(false);
            radio_normal.setDisabled(false);
            radio_major.setDisabled(false);
            radio_minor.setDisabled(false);
            textbox_DetailPermohonan.setReadonly(true);
        }
//        boolean np = (workspace.isAllowed("btn_SimpanPersetujuanAsman")) && ((tPermohonan.getStatus_track_permohonan().contains("Disetujui Manager Dukophar")) || (tPermohonan.getStatus_track_permohonan().contains("Disetujui GM Dukophar")));
//        listbox_NamaPelaksana.setVisible(np);
    }

    private void doShowDialog(TVerifikasi tVerifikasi, TPermohonan tPermohonan) throws InterruptedException {
        try {
            doWriteBeanToComponents(tVerifikasi, tPermohonan);
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponents(TVerifikasi tVerifikasi, TPermohonan tPermohonan) {
        textbox_TIdossPermohonanId.setValue(tVerifikasi.getT_idoss_verifikasi_id());
//        textbox_TIdossPermohonanId.setValue(tPermohonan.getT_idoss_permohonan_id());
        textbox_NamaPemohon.setValue(tPermohonan.getNama_pemohon());
//        datebox_Tanggal.setValue(tPermohonan.getTgl_permohonan());
        datebox_Tanggal.setValue(tVerifikasi.getTgl_permohonan());

        textbox_NikPemohon.setValue(tPermohonan.getNik_pemohon());
        if (tPermohonan.getUrgensi().equals("H")) {
            radiogroup_Prioritas.setSelectedItem(radio_high);
        } else {
            radiogroup_Prioritas.setSelectedItem(radio_normal);
        }
        if (tPermohonan.getDampak().equals("MAJOR")) {
            radiogroup_Dampak.setSelectedItem(radio_major);
        }

        int indexPlks = 0;
        ListModel listPlks = listbox_NamaPelaksana.getModel();
        for (int i = 0; i < listPlks.getSize(); i++) {
            VHrEmployeePelaksana np = (VHrEmployeePelaksana) listPlks.getElementAt(i);
            if (np.getEmployee_no().equals(tVerifikasi.getNik_pelaksana())) indexPlks = i;
        }
        listbox_NamaPelaksana.setSelectedIndex(indexPlks);

        textbox_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());

        if (tPermohonan.getStatus_track_permohonan().equals("Ditolak Manager Pemohon")) {
            radiogroup_StatusPermohonanManagerPemohon.setSelectedItem(radio_DitolakMPemohon);
        }
        textbox_muser.setValue(tPermohonan.getCatatan_manager());

        if (tPermohonan.getStatus_track_permohonan().equals("Ditolak GM Pemohon")) {
            radiogroup_StatusPermohonanGmPemohon.setSelectedItem(radio_DitolakGmPemohon);
        }
        textbox_gmuser.setValue(tPermohonan.getCatatan_gm());

        if (tVerifikasi.getStatus_permohonanasman().equals("Ditolak Asman Dukophar")) {
            radiogroup_StatusPermohonanAsman.setSelectedItem(radio_DitolakAM);
        }
        textbox_amdukophar.setValue(tVerifikasi.getCatatan_asman());

        if (tVerifikasi.getStatus_permohonanmanager().equals("Ditolak Manager Dukophar")) {
            radiogroup_StatusPermohonanManager.setSelectedItem(radio_DitolakM);
        }
        textbox_mdukophar.setValue(tVerifikasi.getCatatan_manager());

        if (tVerifikasi.getStatus_permohonan_gm().equals("Ditolak GM Dukophar")) {
            radiogroup_StatusPermohonanGm.setSelectedItem(radio_DitolakGM);
        }
        textbox_gmdukophar.setValue(tVerifikasi.getCatatan_gm());
    }

    public void onClick$btn_Batal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        window_Permohonan.onClose();
    }

    public void onClick$btn_SimpanPersetujuanManagerPemohon(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSave();
        window_Permohonan.onClose();
        Events.postEvent("onCreate", window_DaftarPermohonan, event);
        window_DaftarPermohonan.invalidate();
    }

    private void doSave() throws Exception {
        TPermohonan tPermohonan = gettPermohonan();
        doWriteComponentsToBean1(tPermohonan);

        try {
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }


    }

    private void doWriteComponentsToBean1(TPermohonan tPermohonan) {
        String statusM = radiogroup_StatusPermohonanManagerPemohon.getSelectedItem().getValue();
        tPermohonan.setStatus_track_permohonan(statusM);

        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tPermohonan.setUpdated_manager(ts);
        tPermohonan.setCatatan_manager(textbox_muser.getValue());
        if (checkbox_Cepat.isChecked()) {
            tPermohonan.setUrgensi("H");
        } else {
            tPermohonan.setUrgensi("N");
        }
    }

    public void onClick$btn_SimpanPersetujuanGmPemohon(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
        window_Permohonan.onClose();
    }

    private void doSimpan() throws Exception {
        TPermohonan tPermohonan = gettPermohonan();
        doWriteComponentsToBean2(tPermohonan);

        try {
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
//        doStoreInitValues();
    }

    private void doWriteComponentsToBean2(TPermohonan tPermohonan) {
        Radio statusGM = radiogroup_StatusPermohonanGmPemohon.getSelectedItem();
        tPermohonan.setStatus_track_permohonan(statusGM.getValue());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tPermohonan.setUpdated_gm(ts);
        tPermohonan.setCatatan_gm(textbox_gmuser.getValue());
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
        doWriteComponentsToBean3(tPermohonan, tVerifikasi, tPelaksanaan);

        try {
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
            tVerifikasi.setNik_asman(getUserWorkspace().getUserSession().getEmployeeNo());
            tVerifikasi.setNama_asman(getUserWorkspace().getUserSession().getEmployeeName());
            getVerifikasiService().saveOrUpdateTVerifikasi(tVerifikasi);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
//        doStoreInitValues();
    }

    private void doWriteComponentsToBean3(TPermohonan tPermohonan, TVerifikasi tVerifikasi, TPelaksanaan tPelaksanaan) {
        Radio dampak = radiogroup_Dampak.getSelectedItem();
        tVerifikasi.setDampak(dampak.getValue());

        Listitem itempelaksana = listbox_NamaPelaksana.getSelectedItem();
        ListModelList lml = (ListModelList) listbox_NamaPelaksana.getListModel();
        VHrEmployeePelaksana vHrEmployeePelaksana = (VHrEmployeePelaksana) lml.get(itempelaksana.getIndex());
        if (!vHrEmployeePelaksana.getEmployee_name().equalsIgnoreCase("Silakan pilih")) {
            tVerifikasi.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());
        }
        if (!vHrEmployeePelaksana.getEmployee_no().equalsIgnoreCase("555")) {
            tVerifikasi.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());
        }

        ListModelList lml3 = (ListModelList) listbox_NamaPelaksana.getListModel();
//        VHrEmployeePelaksana vHrEmployeePelaksana = (VHrEmployeePelaksana) lml3.get(itempelaksana.getIndex());
        if (!vHrEmployeePelaksana.getEmployee_name().equalsIgnoreCase("Silakan pilih")) {
//            tVerifikasi.setNama_pelaksana(vHrEmployeePelaksana.getEmployee_name());
        }
        if (!vHrEmployeePelaksana.getEmployee_no().equalsIgnoreCase("555")) {
            tVerifikasi.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());
        }

        Radio statusAM = radiogroup_StatusPermohonanAsman.getSelectedItem();
        tVerifikasi.setStatus_permohonanasman(statusAM.getValue());
        tPermohonan.setStatus_track_permohonan(statusAM.getValue());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tVerifikasi.setUpdated_asman(ts);
        tVerifikasi.setCatatan_asman(textbox_amdukophar.getValue());
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
        doWriteComponentsToBean4(tPermohonan, tVerifikasi);

        try {
            tVerifikasi.setNik_manager(getUserWorkspace().getUserSession().getEmployeeNo());
            tVerifikasi.setNama_manager(getUserWorkspace().getUserSession().getEmployeeName());
            getVerifikasiService().saveOrUpdateTVerifikasi(tVerifikasi);
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
//        doStoreInitValues();
    }

    private void doWriteComponentsToBean4(TPermohonan tPermohonan, TVerifikasi tVerifikasi) {
        Radio dampak = radiogroup_Dampak.getSelectedItem();
        tVerifikasi.setDampak(dampak.getValue());
        Radio statusM = radiogroup_StatusPermohonanManager.getSelectedItem();
        tVerifikasi.setStatus_permohonanmanager(statusM.getValue());
        tPermohonan.setStatus_track_permohonan(statusM.getValue());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tVerifikasi.setUpdated_manager(ts);
        tVerifikasi.setCatatan_manager(textbox_mdukophar.getValue());
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
        doWriteComponentsToBean5(tPermohonan, tVerifikasi);

        try {
            tVerifikasi.setNik_gm(getUserWorkspace().getUserSession().getEmployeeNo());
            tVerifikasi.setNama_gm(getUserWorkspace().getUserSession().getEmployeeName());
            getVerifikasiService().saveOrUpdateTVerifikasi(tVerifikasi);
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
//        doStoreInitValues();
    }

    private void doWriteComponentsToBean5(TPermohonan tPermohonan, TVerifikasi tVerifikasi) {
        Radio statusGM = radiogroup_StatusPermohonanGm.getSelectedItem();
        tVerifikasi.setStatus_permohonan_gm(statusGM.getValue());
        tPermohonan.setStatus_track_permohonan(statusGM.getValue());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tVerifikasi.setUpdated_gm(ts);
        tVerifikasi.setCatatan_gm(textbox_gmdukophar.getValue());
    }


    public TPelaksanaan gettPelaksanaan() {
        return tPelaksanaan;
    }

    public void settPelaksanaan(TPelaksanaan tPelaksanaan) {
        this.tPelaksanaan = tPelaksanaan;
    }

    public TVerifikasi gettVerifikasi() {
        return tVerifikasi;
    }

    public void settVerifikasi(TVerifikasi tVerifikasi) {
        this.tVerifikasi = tVerifikasi;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public VerifikasiService getVerifikasiService() {
        return verifikasiService;
    }

    public void setVerifikasiService(VerifikasiService verifikasiService) {
        this.verifikasiService = verifikasiService;
    }

    public PelaksanaanGangguanService getPelaksanaanGangguanService() {
        return pelaksanaanGangguanService;
    }

    public void setPelaksanaanGangguanService(PelaksanaanGangguanService pelaksanaanGangguanService) {
        this.pelaksanaanGangguanService = pelaksanaanGangguanService;
    }

    public TPermohonan gettPermohonan() {
        return tPermohonan;
    }

    public void settPermohonan(TPermohonan tPermohonan) {
        this.tPermohonan = tPermohonan;
    }
}
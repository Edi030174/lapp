package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.PermohonanService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joshua
 * Date: Jul 8, 2010
 * Time: 2:36:13 PM
 */
public class PermohonanCtrl extends GFCBaseCtrl implements Serializable {
    private Media uploadMedia;

    private transient final static Logger logger = Logger.getLogger(PermohonanCtrl.class);

    protected Window window_Permohonan;

    protected Tab tab_Permohonan;
    protected Tabpanel tabPanel_Permohonan;

    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Textbox textbox_BagianPemohon;
    protected Textbox textbox_NamaManager;
    protected Textbox textbox_NamaGm;

    protected Datebox datebox_Tanggal;
    protected Textbox textbox_NikPemohon;
    protected Textbox textbox_NikManager;
    protected Textbox textbox_NikGm;

    protected Radiogroup radiogroupType_permohonan;
    protected Radio radio_readonly;
    protected Radio radio_readwrite;
    protected Radio radio_aplikasi;
    protected Radio radio_lainlain;
    protected Radiogroup radiogroup_Dampak;
    protected Radio major;
    protected Radio minor;

    protected Textbox textbox_Lainlain;
    protected Checkbox checkbox_Cepat;
    protected Toolbarbutton button_Download;
    protected Label filename;
    //protected FCKeditor fck_DetailPermohonan;
    protected Textbox fck_DetailPermohonan;

    protected Tab tab_Persetujuan;
    protected Tabpanel tabPanel_Persetujuan;

    protected Tab tab_PersetujuanPemohon;
    protected Tabpanel tabPanel_PersetujuanPemohon;

    protected Tab tab_PersetujuanDukophar;
    protected Tabpanel tabPanel_PersetujuanDukophar;

    protected Tab tab_Pelaksanaan;
    protected Tabpanel tabPanel_Pelaksanaan;

    private transient boolean validationOn;
    private transient Listbox listbox_DaftarPermohonan;

    protected Button btnSimpan_Permohonan;
    protected Button btnBatal;

    protected PermohonanCtrl permohonanCtrl;

    private transient String oldVar_textboxTIdossPermohonanId;
    private transient Window window_DaftarPermohonan;
    private transient Window window_DaftarPermohonanPelaksana;
    private transient String oldVar_textboxNamaPemohon;
    private transient String oldVar_textboxBagianPemohon;
    private transient String oldVar_textboxNamaManager;
    private transient String oldVar_textboxNamaGm;
    private transient Date oldVar_tanggal;
    private transient String oldVar_textboxNikPemohon;
    private transient String oldVar_textboxNikManager;
    private transient String oldVar_textboxNikGm;

    private transient String oldVar_readonly;
    private transient String oldVar_readwrite;
    private transient String oldVar_aplikasi;
    private transient String oldVar_lainlain;
    private transient String oldVar_textboxLainlain;
    private transient String oldVar_cepat;
    private transient String oldVar_buttonLampiran;
    private transient String oldVar_fckDetailPermohonan;

    private transient TPermohonan tPermohonan;
    private transient TVerifikasi tVerifikasi;
    private transient TPelaksanaan tPelaksanaan;
    private transient PermohonanService permohonanService;

    public PermohonanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Permohonan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doCheckRights();
//        tab_Permohonan.setClosable(false);

//        tab_PersetujuanPemohon.setVisible(true);
//        tabPanel_PersetujuanPemohon.setVisible(true);
//
//        tab_PersetujuanDukophar.setVisible(true);
//        tabPanel_PersetujuanDukophar.setVisible(true);
//
//        tab_Pelaksanaan.setVisible(true);
//        tabPanel_Pelaksanaan.setVisible(true);

        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("tPermohonan")) {
            TPermohonan tPermohonan = (TPermohonan) args.get("tPermohonan");
            settPermohonan(tPermohonan);
        } else {
            settPermohonan(null);
        }

        if (args.containsKey("permohonanCtrl")) {
            permohonanCtrl = (PermohonanCtrl) args.get("permohonanCtrl");
        } else {
            permohonanCtrl = null;
        }

        if (args.containsKey("listbox_DaftarPermohonan")) {
            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
        } else {
            listbox_DaftarPermohonan = null;
        }

        if (args.containsKey("window_DaftarPermohonan")) {
            window_DaftarPermohonan = (Window) args.get("window_DaftarPermohonan");
        } else {
            window_DaftarPermohonan = null;
        }

        if (args.containsKey("window_DaftarPermohonanPelaksana")) {
            window_DaftarPermohonanPelaksana = (Window) args.get("window_DaftarPermohonanPelaksana");
        } else {
            window_DaftarPermohonanPelaksana = null;
        }
        Tabpanel orderTab = (Tabpanel) Path.getComponent("/window_Permohonan/tabPanel_PersetujuanPemohon");
//        orderTab.getChildren().clear();

        Panel panel = new Panel();
        Panelchildren pChildren = new Panelchildren();

        panel.appendChild(pChildren);
//        orderTab.appendChild(panel);
        doShowDialog(gettPermohonan());

//        Events.postEvent("onSelect$tab_Persetujuan", window_Permohonan, event);
//        window_Permohonan.invalidate();
        Events.postEvent("onSelect", tab_Persetujuan, event);
    }

    private void doCheckRights() {
        UserWorkspace workspace = getUserWorkspace();
        TPermohonan tPermohonan = gettPermohonan();
//        window_Permohonan.setVisible(workspace.isAllowed("window_Permohonan"));
//        tab_Permohonan.setVisible(workspace.isAllowed("tab_Permohonan"));
//        tabPanel_Permohonan.setVisible(workspace.isAllowed("tab_Permohonan"));

//        tab_Persetujuan.setVisible(workspace.isAllowed("tab_Persetujuan"));
//        tabPanel_Persetujuan.setVisible(workspace.isAllowed("tabPanel_Persetujuan"));
//        tab_PersetujuanPemohon.setVisible(workspace.isAllowed("tab_PersetujuanPemohon"));
//        tabPanel_PersetujuanPemohon.setVisible(workspace.isAllowed("tab_PersetujuanPemohon"));
//        tab_PersetujuanDukophar.setVisible(workspace.isAllowed("tab_PersetujuanDukophar"));
//        tabPanel_PersetujuanDukophar.setVisible(workspace.isAllowed("tab_PersetujuanDukophar"));

        if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("muser")) {
            tab_Persetujuan.setLabel("Persetujuan Manager");
        } else if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("gmuser")) {
            tab_Persetujuan.setLabel("Persetujuan GM");
        } else if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("amduk")) {
            tab_Persetujuan.setLabel("Persetujuan Asisten Manager Dukophar");
        } else if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("mduk")) {
            tab_Persetujuan.setLabel("Persetujuan Manager Dukophar");
        } else if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase("gmduk")) {
            tab_Persetujuan.setLabel("Persetujuan GM Dukophar");
        }
    }

    private void doShowDialog(TPermohonan tPermohonan) throws InterruptedException {
        /*if (tPermohonan.getStatus_track_permohonan().equalsIgnoreCase("Disetujui GM Dukophar")) {
            tab_Pelaksanaan.setVisible(true);
            tabPanel_Pelaksanaan.setVisible(true);
        }*/
        TVerifikasi tVerifikasi = getPermohonanService().getTVerifikasiByTIdossVerifikasiId(tPermohonan.getT_idoss_permohonan_id());
        if (((tPermohonan.getStatus_track_permohonan().contains("Disetujui Manager Dukophar")) && (tVerifikasi.getDampak().equals("MINOR"))) ||
                ((tPermohonan.getStatus_track_permohonan().contains("INPROGRESS")) && (tVerifikasi.getDampak().equals("MAJOR"))) ||
                (tPermohonan.getStatus_track_permohonan().equals("OPEN")) ||
                (tPermohonan.getStatus_track_permohonan().equals("INPROGRESS")) ||
                (tPermohonan.getStatus_track_permohonan().equals("PENDING"))) {
            tab_Pelaksanaan.setVisible(true);
            tabPanel_Pelaksanaan.setVisible(true);
        } else {
            tab_Pelaksanaan.setVisible(false);
            tabPanel_Pelaksanaan.setVisible(false);
        }
        if (tPermohonan.getLampiran() != null) {
            button_Download.setVisible(true);
            String x = tPermohonan.getLampiran();
//            String y = x.substring(0, x.length() - 1);
//            int dot = fullPath.lastIndexOf(extensionSeparator);
            int sep = x.lastIndexOf("\\");
            String y = x.substring(sep + 1);
            button_Download.setLabel(y);
//            filename.setValue(y);
        } else {
            button_Download.setVisible(false);
        }
        if (tPermohonan == null) {
            tPermohonan = getPermohonanService().getNewPermohonan();
            settPermohonan(tPermohonan);

        } else {
            settPermohonan(tPermohonan);
        }

        try {
            doWriteBeanToComponents(tPermohonan);
            window_Permohonan.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
        textbox_TIdossPermohonanId.setReadonly(true);

        textbox_NamaPemohon.setReadonly(true);
        textbox_BagianPemohon.setReadonly(true);
        textbox_NikPemohon.setReadonly(true);
        datebox_Tanggal.setReadonly(true);
        textbox_Lainlain.setReadonly(true);
        textbox_NamaManager.setReadonly(true);
        textbox_NamaGm.setReadonly(true);
        textbox_NikManager.setReadonly(true);
        textbox_NikGm.setReadonly(true);
        radio_aplikasi.setDisabled(true);
        radio_lainlain.setDisabled(true);
        radio_readonly.setDisabled(true);
        radio_readwrite.setDisabled(true);
        major.setDisabled(true);
        minor.setDisabled(true);
        checkbox_Cepat.setDisabled(true);
    }

    private void doWriteBeanToComponents(TPermohonan tPermohonan) {
        if (tPermohonan.getT_idoss_permohonan_id() != null) {
            textbox_TIdossPermohonanId.setValue(tPermohonan.getT_idoss_permohonan_id());
        } else {
            textbox_TIdossPermohonanId.setValue(getPermohonanService().getPermohonanID());
        }

        if (tPermohonan.getNama_pemohon() != null) {
            textbox_NamaPemohon.setValue(tPermohonan.getNama_pemohon());
        } else {
            textbox_NamaPemohon.setValue(getUserWorkspace().getUserSession().getEmployeeName());
        }
        if (tPermohonan.getBagian_pemohon() != null) {
            textbox_BagianPemohon.setValue(tPermohonan.getBagian_pemohon());
        } else {
            textbox_BagianPemohon.setValue(getUserWorkspace().getUserSession().getDepartment());
        }
        if (tPermohonan.getNik_pemohon() != null) {
            textbox_NikPemohon.setValue(tPermohonan.getNik_pemohon());
        } else {
            textbox_NikPemohon.setValue(getUserWorkspace().getUserSession().getEmployeeNo());
        }
        if (tPermohonan.getUrgensi().equals("H")) {
            checkbox_Cepat.setChecked(true);
        } else {
            checkbox_Cepat.setChecked(false);
        }
        textbox_NamaManager.setValue(tPermohonan.getNama_manager());
        textbox_NamaGm.setValue(tPermohonan.getNama_gm());
        textbox_NikManager.setValue(tPermohonan.getNik_manager());
        textbox_NikGm.setValue(tPermohonan.getNik_gm());
        datebox_Tanggal.setValue(tPermohonan.getTgl_permohonan());

        if (tPermohonan.getType_permohonan().equals("User RO")) {
            radiogroupType_permohonan.setSelectedItem(radio_readonly);
        } else if (tPermohonan.getType_permohonan().equals("User RW")) {
            radiogroupType_permohonan.setSelectedItem(radio_readwrite);
        } else if (tPermohonan.getType_permohonan().equals("Aplikasi")) {
            radiogroupType_permohonan.setSelectedItem(radio_aplikasi);
        } else if (tPermohonan.getType_permohonan().equals("Lain-lain")) {
            radiogroupType_permohonan.setSelectedItem(radio_lainlain);
        }
        if (tPermohonan.getDampak().equals("MAJOR")) {
            radiogroup_Dampak.setSelectedItem(major);
        }

        fck_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());
        textbox_Lainlain.setValue(tPermohonan.getLain_lain());
    }

    public void onSelect$tab_Persetujuan(Event event) {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        TVerifikasi tVerifikasi = getPermohonanService().getTVerifikasiByTIdossVerifikasiId(gettPermohonan().getT_idoss_permohonan_id());
//        if (gettPermohonan().getT_idoss_permohonan_id() != null) {
//            tVerifikasi = getPermohonanService().getTVerifikasiByTIdossVerifikasiId(gettPermohonan().getT_idoss_permohonan_id());
//        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (tVerifikasi != null) {
            map.put("tVerifikasi", tVerifikasi);
        } else {
            map.put("tVerifikasi", getPermohonanService().getNewVerifikasi());
        }
        map.put("tPermohonan", tPermohonan);
        map.put("permohonanCtrl", this);
        map.put("window_DaftarPermohonan", window_DaftarPermohonan);

        Tabpanel orderTab = (Tabpanel) Path.getComponent("/window_Permohonan/tabPanel_Persetujuan");
        orderTab.getChildren().clear();

        Panel panel = new Panel();
        Panelchildren pChildren = new Panelchildren();

        panel.appendChild(pChildren);
        orderTab.appendChild(panel);

        Executions.createComponents("/WEB-INF/pages/permohonan/persetujuan.zul", pChildren, map);
    }

    public void onSelect$tab_PersetujuanPemohon(Event event) {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
//        TPermohonan tPermohonan = null;
//        if (gettPermohonan().getT_idoss_permohonan_id() != null) {
//            tVerifikasi = getPermohonanService().getTVerifikasiByTIdossVerifikasiId(gettPermohonan().getT_idoss_permohonan_id());
//        }
        HashMap<String, Object> map = new HashMap<String, Object>();
//        if (tPermohonan != null) {
//            map.put("tPermohonan", tPermohonan);
//        } else {
//            map.put("tVerifikasi", getPermohonanService().getNewVerifikasi());
//        }
        map.put("tPermohonan", tPermohonan);

        map.put("permohonanCtrl", this);
        map.put("window_DaftarPermohonan", window_DaftarPermohonan);

        Tabpanel orderTab = (Tabpanel) Path.getComponent("/window_Permohonan/tabPanel_PersetujuanPemohon");
        orderTab.getChildren().clear();

        Panel panel = new Panel();
        Panelchildren pChildren = new Panelchildren();

        panel.appendChild(pChildren);
        orderTab.appendChild(panel);

        Executions.createComponents("/WEB-INF/pages/permohonan/persetujuangmpemohon.zul", pChildren, map);
    }

    public void onSelect$tab_PersetujuanDukophar(Event event) {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        TVerifikasi tVerifikasi = null;
        if (gettPermohonan().getT_idoss_permohonan_id() != null) {
            tVerifikasi = getPermohonanService().getTVerifikasiByTIdossVerifikasiId(gettPermohonan().getT_idoss_permohonan_id());
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (tVerifikasi != null) {
            map.put("tVerifikasi", tVerifikasi);
        } else {
            map.put("tVerifikasi", getPermohonanService().getNewVerifikasi());
        }
        map.put("tPermohonan", tPermohonan);

        map.put("permohonanCtrl", this);


        Tabpanel orderTab = (Tabpanel) Path.getComponent("/window_Permohonan/tabPanel_PersetujuanDukophar");
        orderTab.getChildren().clear();

        Panel panel = new Panel();
        Panelchildren pChildren = new Panelchildren();

        panel.appendChild(pChildren);
        orderTab.appendChild(panel);

        Executions.createComponents("/WEB-INF/pages/permohonan/persetujuangm.zul", pChildren, map);
    }

    public void onSelect$tab_Pelaksanaan(Event event) {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        TPelaksanaan tPelaksanaan = null;
        if (gettPermohonan().getT_idoss_permohonan_id() != null) {
            tPelaksanaan = getPermohonanService().getTPelaksanaanByTIdossPelaksanaanId(gettPermohonan().getT_idoss_permohonan_id());
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        if (tPelaksanaan != null) {
            map.put("tPelaksanaan", tPelaksanaan);
        } else {
            map.put("tPelaksanaan", getPermohonanService().getNewPelaksanaan());
        }
        if (tPermohonan != null) {
            map.put("tPermohonan", tPermohonan);
        }
        TVerifikasi tVerifikasi = null;
        if (gettPermohonan().getT_idoss_permohonan_id() != null) {
            tVerifikasi = getPermohonanService().getTVerifikasiByTIdossVerifikasiId(gettPermohonan().getT_idoss_permohonan_id());
        }
        if (tVerifikasi != null) {
            map.put("tVerifikasi", tVerifikasi);
        }
        map.put("permohonanCtrl", this);
        map.put("window_DaftarPermohonanPelaksana", window_DaftarPermohonanPelaksana);

        Tabpanel orderTab = (Tabpanel) Path.getComponent("/window_Permohonan/tabPanel_Pelaksanaan");
        orderTab.getChildren().clear();

        Panel panel = new Panel();
        Panelchildren pChildren = new Panelchildren();

        panel.appendChild(pChildren);
        orderTab.appendChild(panel);

        Executions.createComponents("/WEB-INF/pages/permohonan/pelaksanaan.zul", pChildren, map);

    }

    public void onClose$window_Permohonan(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doClose();
    }

    public void onClick$btnSimpan_Permohonan(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
        window_Permohonan.onClose();
    }

    public void onClick$btnBatal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        window_Permohonan.onClose();
    }

    public void onClick$button_Download(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        String lam = tPermohonan.getLampiran();
        try {
            Filedownload.save(new File(lam), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doClose() throws Exception {
        window_Permohonan.onClose();
    }

    private void doSimpan() throws InterruptedException {

        TPermohonan tPermohonan = gettPermohonan();

        doWriteComponentsToBean(tPermohonan);

        try {
            String uploadeFileName = null;
            if (getUploadMedia() != null) {
                uploadeFileName = getUploadMedia().getName();
            }
//            getPermohonanService().simpanAllTPermohonan(uploadeFileName, tPermohonan, mttr);

        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
        ListModelList lml = (ListModelList) listbox_DaftarPermohonan.getListModel();

        // Check if the object is new or updated
        // -1 means that the obj is not in the list, so it's new.
        if (lml.indexOf(tPermohonan) == -1) {
            lml.add(tPermohonan);
        } else {
            lml.set(lml.indexOf(tPermohonan), tPermohonan);
        }
        doStoreInitValues();
    }

    private void doStoreInitValues() {

        oldVar_textboxTIdossPermohonanId = textbox_TIdossPermohonanId.getValue();
        oldVar_textboxNamaPemohon = textbox_NamaPemohon.getValue();
        oldVar_textboxBagianPemohon = textbox_BagianPemohon.getValue();
        oldVar_textboxNamaManager = textbox_NamaManager.getValue();
        oldVar_textboxNamaGm = textbox_NamaGm.getValue();
        oldVar_tanggal = datebox_Tanggal.getValue();
        oldVar_textboxNikPemohon = textbox_NikPemohon.getValue();
        oldVar_textboxNikManager = textbox_NikManager.getValue();
        oldVar_textboxNikGm = textbox_NikGm.getValue();

        oldVar_readonly = radio_readonly.getValue();
        oldVar_readwrite = radio_readwrite.getValue();
        oldVar_aplikasi = radio_aplikasi.getValue();
        oldVar_lainlain = radio_lainlain.getValue();
        oldVar_textboxLainlain = textbox_Lainlain.getValue();
//        oldVar_cepat = cepat.getValue();
        oldVar_fckDetailPermohonan = fck_DetailPermohonan.getValue();


    }

    private void onCheck$radio_lainlain(TPermohonan tPermohonan) {
        textbox_Lainlain.setDisabled(false);
    }

    private void doWriteComponentsToBean(TPermohonan permohonan) {

        permohonan.setT_idoss_permohonan_id(textbox_TIdossPermohonanId.getValue());
        permohonan.setNama_pemohon(textbox_NamaPemohon.getValue());
        permohonan.setBagian_pemohon(textbox_BagianPemohon.getValue());
        permohonan.setTgl_permohonan(new Timestamp(datebox_Tanggal.getValue().getTime()));
        permohonan.setNik_pemohon(textbox_NikPemohon.getValue());
        permohonan.setNama_manager(textbox_NamaManager.getValue());
        permohonan.setNik_manager(textbox_NikManager.getValue());
        permohonan.setNama_gm(textbox_NamaGm.getValue());
        permohonan.setNik_gm(textbox_NikGm.getValue());
        permohonan.setDetail_permohonan(fck_DetailPermohonan.getValue());
        if (getUploadMedia() != null) {
            permohonan.setUploadStream(getUploadMedia().getStreamData());
        }
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        permohonan.setTarget_mulai_digunakan(ts);
        Radio type = radiogroupType_permohonan.getSelectedItem();
        permohonan.setType_permohonan(type.getValue());
        permohonan.setLain_lain(textbox_Lainlain.getValue());
        permohonan.setUpdated_asman(ts);
        permohonan.setUpdated_divisi(ts);
        permohonan.setUpdated_gm(ts);
        permohonan.setUpdated_manager(ts);
        permohonan.setUpdated_pemohon(ts);
        if (checkbox_Cepat.isChecked()) {
            permohonan.setUrgensi("H");
        } else if (checkbox_Cepat.isDisabled()) {
            permohonan.setUrgensi("L");
        }

        permohonan.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        permohonan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());

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

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public Media getUploadMedia() {
        return uploadMedia;
    }

    public void setUploadMedia(Media uploadMedia) {
        this.uploadMedia = uploadMedia;
    }
}
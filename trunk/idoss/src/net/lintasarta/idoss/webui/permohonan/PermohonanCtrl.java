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
import org.zkforge.fckez.FCKeditor;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.*;

import java.io.File;
import java.io.IOException;
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

    protected Textbox textbox_Lainlain;
    protected Checkbox checkbox_Cepat;
    protected Button button_Lampiran;
    protected Button button_Download;
    protected FCKeditor fck_DetailPermohonan;

    protected Tab tab_Verifikasi;
    protected Tabpanel tabPanel_Verifikasi;

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
        tab_Permohonan.setClosable(false);

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
        doShowDialog(gettPermohonan());
    }

    private void doCheckRights() {
        UserWorkspace workspace = getUserWorkspace();
        window_Permohonan.setVisible(workspace.isAllowed("window_Permohonan"));
        tab_Permohonan.setVisible(workspace.isAllowed("tab_Permohonan"));
        tabPanel_Permohonan.setVisible(workspace.isAllowed("tab_Permohonan"));
        tab_PersetujuanPemohon.setVisible(workspace.isAllowed("tab_PersetujuanPemohon"));
        tabPanel_PersetujuanPemohon.setVisible(workspace.isAllowed("tab_PersetujuanPemohon"));
        tab_PersetujuanDukophar.setVisible(workspace.isAllowed("tab_PersetujuanDukophar"));
        tabPanel_PersetujuanDukophar.setVisible(workspace.isAllowed("tab_PersetujuanDukophar"));
        tab_Pelaksanaan.setVisible(workspace.isAllowed("tab_Pelaksanaan"));
        tabPanel_Pelaksanaan.setVisible(workspace.isAllowed("tab_Pelaksanaan"));






    }

    private void doShowDialog(TPermohonan tPermohonan) throws InterruptedException {
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
        textbox_Lainlain.setDisabled(true);
        textbox_NamaManager.setReadonly(true);
        textbox_NamaGm.setReadonly(true);
        textbox_NikManager.setReadonly(true);
        textbox_NikGm.setReadonly(true);
        radio_aplikasi.setDisabled(true);
        radio_lainlain.setDisabled(true);
        radio_readonly.setDisabled(true);
        radio_readwrite.setDisabled(true);
        checkbox_Cepat.setDisabled(true);
        button_Lampiran.setDisabled(true);

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

        fck_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());
        textbox_Lainlain.setValue(tPermohonan.getLain_lain());
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
        map.put("permohonanCtrl", this);

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
        if (logger.isDebugEnabled()) {
            logger.debug("--> DataIsChanged :" + isDataChanged());
        }
        if (isDataChanged()) {

            // Show a confirm box
            String message = Labels.getLabel("message_Data_Modified_Save_Data_YesNo");
            String title = Labels.getLabel("message_Information");

            MultiLineMessageBox.doSetTemplate();
            if (MultiLineMessageBox.show(message, title, MultiLineMessageBox.YES | MultiLineMessageBox.NO, MultiLineMessageBox.QUESTION, true, new EventListener() {
                public void onEvent(Event evt) {
                    switch (((Integer) evt.getData()).intValue()) {
                        case MultiLineMessageBox.YES:
                            try {
                                doSimpan();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        case MultiLineMessageBox.NO:
                            break; //
                    }
                }
            }
            ) == MultiLineMessageBox.YES) {
            }
        }
        window_Permohonan.onClose();
    }

    private boolean isDataChanged() throws Exception {
        boolean change = false;

        if (oldVar_textboxTIdossPermohonanId != textbox_TIdossPermohonanId.getValue()) {
            change = true;
        }
        if (oldVar_textboxNamaPemohon != textbox_NamaPemohon.getValue()) {
            change = true;
        }
        if (oldVar_textboxBagianPemohon != textbox_BagianPemohon.getValue()) {
            change = true;
        }
        if (oldVar_textboxNamaManager != textbox_NamaManager.getValue()) {
            change = true;
        }
        if (oldVar_textboxNamaGm != textbox_NamaGm.getValue()) {
            change = true;
        }
        if (oldVar_tanggal != datebox_Tanggal.getValue()) {
            change = true;
        }
        if (oldVar_textboxNikPemohon != textbox_NikPemohon.getValue()) {
            change = true;
        }
        if (oldVar_textboxNikManager != textbox_NikManager.getValue()) {
            change = true;
        }
        if (oldVar_textboxNikGm != textbox_NikGm.getValue()) {
            change = true;
        }
        if (oldVar_readonly != radio_readonly.getValue()) {
            change = true;
        }
        if (oldVar_readwrite != radio_readwrite.getValue()) {
            change = true;
        }
        if (oldVar_aplikasi != radio_aplikasi.getValue()) {
            change = true;
        }
        if (oldVar_lainlain != radio_lainlain.getValue()) {
            change = true;
        }
        if (oldVar_textboxLainlain != textbox_Lainlain.getValue()) {
            change = true;
        }
        if (oldVar_fckDetailPermohonan != fck_DetailPermohonan.getValue()) {
            change = true;
        }
        return change;
    }

    public void onUpload$button_Lampiran(UploadEvent event) throws IOException {
        Media media = event.getMedia();
        setUploadMedia(media);
    }

    private void doSimpan() throws InterruptedException {

        TPermohonan tPermohonan = gettPermohonan();
        doWriteComponentsToBean(tPermohonan);

        try {
            String uploadeFileName = null;
            if (getUploadMedia() != null) {
                uploadeFileName = getUploadMedia().getName();
            }
            getPermohonanService().simpanAllTPermohonan(uploadeFileName, tPermohonan);

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
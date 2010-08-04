package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.PermohonanService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

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

    private transient final static Logger logger = Logger.getLogger(PermohonanCtrl.class);

    protected Window window_Permohonan;
    protected Tab tab_Permohonan;
    protected Tabpanel tabpanel_Permohonan;
    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Textbox textbox_BagianPemohon;
    protected Textbox textbox_NamaAsman;
    protected Textbox textbox_NamaManager;
    protected Textbox textbox_NamaGm;

    protected Datebox datebox_Tanggal;
    protected Textbox textbox_NikPemohon;
    protected Textbox textbox_NikAsman;
    protected Textbox textbox_NikManager;
    protected Textbox textbox_NikGm;

    protected Radio readonly;
    protected Radio readwrite;
    protected Radio aplikasi;
    protected Radio lainlain;
    protected Textbox textbox_Lainlain;
    protected Checkbox cepat;
    protected Button button_Lampiran;
    protected FCKeditor fck_DetailPermohonan;

    protected Tab tab_Verifikasi;
    protected Tabpanel tabPanel_Verifikasi;

    protected Tab tab_Pelaksanaan;
    protected Tabpanel tabPanel_Pelaksanaan;


    private transient boolean validationOn;
    private transient Listbox listbox_DaftarPermohonan;

    protected Button btnSimpan_Permohonan;

    protected PermohonanCtrl permohonanCtrl;

    private transient String oldVar_textboxTIdossPermohonanId;
    private transient String oldVar_textboxNamaPemohon;
    private transient String oldVar_textboxBagianPemohon;
    private transient String oldVar_textboxNamaAsman;
    private transient String oldVar_textboxNamaManager;
    private transient String oldVar_textboxNamaGm;
    private transient Date oldVar_tanggal;
    private transient String oldVar_textboxNikPemohon;
    private transient String oldVar_textboxNikAsman;
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

        tab_Verifikasi.setVisible(true);
        tabPanel_Verifikasi.setVisible(true);
        tab_Pelaksanaan.setVisible(true);
        tabPanel_Pelaksanaan.setVisible(true);

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
//        textbox_NamaPemohon.setValue(getUserWorkspace().getUserSession().getEmployeeName());
//        textbox_BagianPemohon.setValue(getUserWorkspace().getUserSession().getDepartment());
//        textbox_NikPemohon.setValue(getUserWorkspace().getUserSession().getEmployeeNo());
    }

    private void doShowDialog(TPermohonan tPermohonan) throws InterruptedException {

        if (tPermohonan == null) {
            tPermohonan = getPermohonanService().getNewPermohonan();
            settPermohonan(tPermohonan);

        }else {
            settPermohonan(tPermohonan);
        }

        try {
            doWriteBeanToComponents(tPermohonan);
            window_Permohonan.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponents(TPermohonan tPermohonan) {

        textbox_TIdossPermohonanId.setValue(tPermohonan.getT_idoss_permohonan_id());
        textbox_NamaPemohon.setValue(tPermohonan.getNama_pemohon());
        textbox_BagianPemohon.setValue(tPermohonan.getBagian_pemohon());
        textbox_NamaAsman.setValue(tPermohonan.getNama_asman());
        textbox_NamaManager.setValue(tPermohonan.getNama_manager());
        textbox_NamaGm.setValue(tPermohonan.getNama_gm());
        textbox_NikPemohon.setValue(tPermohonan.getNik_pemohon());
        textbox_NikAsman.setValue(tPermohonan.getNik_asman());
        textbox_NikManager.setValue(tPermohonan.getNik_manager());
        textbox_NikGm.setValue(tPermohonan.getNik_gm());
        datebox_Tanggal.setValue(tPermohonan.getTgl_permohonan());
        fck_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());

    }

    public void onSelect$tab_Verifikasi(Event event) {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        TVerifikasi tVerifikasi = getPermohonanService().getTVerifikasiByTIdossVerifikasiId(gettPermohonan().getT_idoss_permohonan_id());

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tVerifikasi", tVerifikasi);
        map.put("permohonanCtrl", this);

        Tabpanel orderTab = (Tabpanel) Path.getComponent("/window_Permohonan/tabPanel_Verifikasi");
        orderTab.getChildren().clear();

        Panel panel = new Panel();
        Panelchildren pChildren = new Panelchildren();

        panel.appendChild(pChildren);
        orderTab.appendChild(panel);

        Executions.createComponents("/WEB-INF/pages/permohonan/verifikasi.zul", pChildren, map);
    }

    public void onSelect$tab_Pelaksanaan(Event event) {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        TPelaksanaan tPelaksanaan = getPermohonanService().getTPelaksanaanByTIdossPelaksanaanId(gettPermohonan().getT_idoss_permohonan_id());

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tPelaksanaan", tPelaksanaan);
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

    }

    public void onClick$btnBatal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        window_Permohonan.onClose();
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
        if (oldVar_textboxNamaAsman != textbox_NamaAsman.getValue()) {
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
        if (oldVar_textboxNikAsman != textbox_NikAsman.getValue()) {
            change = true;
        }
        if (oldVar_textboxNikManager != textbox_NikManager.getValue()) {
            change = true;
        }
        if (oldVar_textboxNikGm != textbox_NikGm.getValue()) {
            change = true;
        }
        if (oldVar_readonly != readonly.getValue()) {
            change = true;
        }
        if (oldVar_readwrite != readwrite.getValue()) {
            change = true;
        }
        if (oldVar_aplikasi != aplikasi.getValue()) {
            change = true;
        }
        if (oldVar_lainlain != lainlain.getValue()) {
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

    private void doSimpan() throws InterruptedException {

        TPermohonan tPermohonan = gettPermohonan();

        doWriteComponentsToBean(tPermohonan);

        try {
            getPermohonanService().createTPermohonan(tPermohonan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
        doStoreInitValues();
    }

    private void doStoreInitValues() {

        oldVar_textboxTIdossPermohonanId = textbox_TIdossPermohonanId.getValue();
        oldVar_textboxNamaPemohon = textbox_NamaPemohon.getValue();
        oldVar_textboxBagianPemohon = textbox_BagianPemohon.getValue();
        oldVar_textboxNamaAsman = textbox_NamaAsman.getValue();
        oldVar_textboxNamaManager = textbox_NamaManager.getValue();
        oldVar_textboxNamaGm = textbox_NamaGm.getValue();
        oldVar_tanggal = datebox_Tanggal.getValue();
        oldVar_textboxNikPemohon = textbox_NikPemohon.getValue();
        oldVar_textboxNikAsman = textbox_NikAsman.getValue();
        oldVar_textboxNikManager = textbox_NikManager.getValue();
        oldVar_textboxNikGm = textbox_NikGm.getValue();

        oldVar_readonly = readonly.getValue();
        oldVar_readwrite = readwrite.getValue();
        oldVar_aplikasi = aplikasi.getValue();
        oldVar_lainlain = lainlain.getValue();
        oldVar_textboxLainlain = textbox_Lainlain.getValue();
//        oldVar_cepat = cepat.getValue();
        oldVar_fckDetailPermohonan = fck_DetailPermohonan.getValue();


    }

    private void doWriteComponentsToBean(TPermohonan permohonan) {

        permohonan.setT_idoss_permohonan_id(textbox_TIdossPermohonanId.getValue());
        permohonan.setNama_pemohon(textbox_NamaPemohon.getValue());
        permohonan.setBagian_pemohon(textbox_BagianPemohon.getValue());
        permohonan.setNik_pemohon(textbox_NikPemohon.getValue());
        permohonan.setNama_asman(textbox_NamaAsman.getValue());
        permohonan.setNik_asman(textbox_NikAsman.getValue());
        permohonan.setNama_manager(textbox_NamaManager.getValue());
        permohonan.setNik_manager(textbox_NikManager.getValue());
        permohonan.setNama_gm(textbox_NamaGm.getValue());
        permohonan.setNik_gm(textbox_NikGm.getValue());
        permohonan.setDetail_permohonan(fck_DetailPermohonan.getValue());
        permohonan.setDampak("m");
        permohonan.setLain_lain("lain");
        permohonan.setStatus_track_permohonan("ga jelas");

        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        permohonan.setTarget_mulai_digunakan(ts);
        permohonan.setType_permohonan("ga jelas");
        permohonan.setUpdated_asman(ts);
        permohonan.setUpdated_divisi(ts);
        permohonan.setUpdated_gm(ts);
        permohonan.setUpdated_manager(ts);
        permohonan.setUpdated_pemohon(ts);
        permohonan.setUrgensi("darurat");

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
}
package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.VerifikasiService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 20, 2010
 * Time: 1:16:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersetujuanManagerPemohonCtrl extends GFCBaseCtrl implements Serializable {
    private transient static final Logger logger = Logger.getLogger(PersetujuanManagerPemohonCtrl.class);

    protected Window window_Permohonan;
    protected Window window_PersetujuanManagerPemohon;

    protected Radiogroup radiogroup_Prioritas;
    protected Radio high;
    protected Radio normal;
    protected Radiogroup radiogroup_Dampak;
    protected Radio major;
    protected Radio minor;
    protected Radiogroup radiogroup_StatusPermohonanManagerPemohon;
    protected Radio radio_DisetujuiManagerPemohon;
    protected Radio radio_DitolakManagerPemohon;
    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Textbox textbox_NikPelaksana;
    protected Datebox datebox_Tanggal;
    protected Datebox datebox_Tanggal2;
    protected Textbox textbox_NikPemohon;
    protected Textbox textbox_DetailPermohonan;
    protected FCKeditor fck_CatatanManagerPemohon;

    private transient String oldVar_high;
    private transient String oldVar_normal;
    private transient String oldVar_major;
    private transient String oldVar_minor;
    private boolean oldVar_radio_DisetujuiManagerPemohon;
    private boolean oldVar_radio_DitolakManagerPemohon;
    private transient String oldVar_datebox_Tanggal;
    private transient String oldVar_datebox_Tanggal2;
    private transient String oldVar_textbox_TIdossPermohonanId;
    private transient String oldVar_textbox_NamaPemohon;
    private transient String oldVar_textbox_NikPelaksana;
    private transient String oldVar_textbox_NikPemohon;
    private transient String oldVar_textbox_DetailPermohonan;
    private transient String oldVar_fck_CatatanManagerPemohon;

    protected Listbox listbox_DaftarPermohonan;
    protected Button btn_SimpanPersetujuanManagerPemohon;
    protected Button btn_Batal;
    protected PersetujuanManagerPemohonCtrl persetujuanManagerPemohonCtrl;

    private transient TVerifikasi tVerifikasi;
    private transient VerifikasiService verifikasiService;
    private transient TPermohonan tPermohonan;

    public PersetujuanManagerPemohonCtrl(){
        super();

        if (logger.isDebugEnabled()) {
			logger.debug("--> super()");
        }
    }

    public void onCreate$window_PersetujuanManagerPemohon(Event event) throws Exception {
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
        if (args.containsKey("PersetujuanManagerPemohonCtrl")) {
            persetujuanManagerPemohonCtrl = (PersetujuanManagerPemohonCtrl) args.get("persetujuanManagerPemohonCtrl");
        } else {
            persetujuanManagerPemohonCtrl = null;
        }
        if (args.containsKey("listbox_DaftarPermohonan")) {
            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
        } else {
            listbox_DaftarPermohonan = null;
        }
        doShowDialog(gettVerifikasi());
    }

    private void doShowDialog(TVerifikasi tVerifikasi) throws InterruptedException {
        try {
            doWriteBeanToComponents(tVerifikasi);
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponents(TVerifikasi tVerifikasi) throws Exception{
        textbox_TIdossPermohonanId.setValue(tVerifikasi.getT_idoss_verifikasi_id());
        textbox_NamaPemohon.setValue(tPermohonan.getNama_pemohon());
        datebox_Tanggal.setValue(tPermohonan.getTgl_permohonan());
        textbox_NikPemohon.setValue(tPermohonan.getNik_pemohon());
        textbox_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());
    }

    public void onClick$btn_SimpanPersetujuanManagerPemohon(Event event) throws Exception{
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
    }

    public void onClick$btn_Batal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        window_Permohonan.onClose();
    }

    private void doSimpan() throws Exception {
        TVerifikasi tVerifikasi = gettVerifikasi();
        doWriteComponentsToBean(tVerifikasi);

        try {
            getVerifikasiService().saveOrUpdateTVerifikasi(tVerifikasi);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
        doStoreInitValues();
    }

    private void doStoreInitValues() {
        oldVar_textbox_TIdossPermohonanId = textbox_TIdossPermohonanId.getValue();
        oldVar_textbox_NamaPemohon = textbox_NamaPemohon.getValue();
        oldVar_textbox_NikPemohon = textbox_NikPemohon.getValue();
        oldVar_textbox_DetailPermohonan = textbox_DetailPermohonan.getValue();
        
    }

    private void doWriteComponentsToBean(TVerifikasi tVerifikasi) {
        Radio prioritas = radiogroup_Prioritas.getSelectedItem();
        tVerifikasi.setUrgensi(prioritas.getValue());
        Radio dampak = radiogroup_Dampak.getSelectedItem();
        tVerifikasi.setDampak(dampak.getValue());
        Radio status = radiogroup_StatusPermohonanManagerPemohon.getSelectedItem();
        tVerifikasi.setStatus_permohonan_asman(status.getValue());
        tVerifikasi.setCatatan_asman(fck_CatatanManagerPemohon.getValue());
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

    public TPermohonan gettPermohonan() {
        return tPermohonan;
    }

    public void settPermohonan(TPermohonan tPermohonan) {
        this.tPermohonan = tPermohonan;
    }
}
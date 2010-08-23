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
import org.zkoss.zul.Window;

import java.awt.Button;
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

    protected Button btn_SimpanPersetujuanGm;
    protected Button btn_Batal;

    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Datebox datebox_Tanggal;
    protected Textbox textbox_NikPemohon;
    protected Textbox textbox_DetailPermohonan;
    protected Textbox textbox_CatatanAsman;
    protected Textbox textbox_CatatanManager;
    protected Radiogroup radiogroup_StatusPermohonanGm;
    protected Radio radio_DisetujuiGM;
    protected Radio radio_DitolakGM;
    protected Datebox dateboxTanggal2;
    protected FCKeditor fck_CatatanGm;

    private transient String oldVar_textbox_TIdossPermohonanId;
    private transient String oldVar_textbox_NamaPemohon;
    private transient String oldVar_datebox_Tanggal;
    private transient String oldVar_textbox_NikPemohon;
    private transient String oldVar_textbox_DetailPermohonan;
    private transient String oldVar_textbox_CatatanAsman;
    private transient String oldVar_textbox_CatatanManager;
    private transient boolean oldVar_radio_DisetujuiGM;
    private transient boolean oldVar_radio_DitolakGM;
    private transient String oldVar_dateboxTanggal2;
    private transient String oldVar_fck_CatatanGm;

    protected Listbox listbox_DaftarPermohonan;
    protected PersetujuanGmCtrl persetujuanGmCtrl;

    private transient TPermohonan tPermohonan;
    private transient TVerifikasi tVerifikasi;
    private transient VerifikasiService verifikasiService;

    public PersetujuanGmCtrl(){
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

        doShowDialog(gettVerifikasi());
    }

    private void doShowDialog(TVerifikasi tVerifikasi) throws InterruptedException {
        try {
            doWriteBeanToComponent(tVerifikasi);
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponent(TVerifikasi tVerifikasi) throws Exception{
        textbox_TIdossPermohonanId.setValue(tVerifikasi.getT_idoss_verifikasi_id());
        textbox_NamaPemohon.setValue(tPermohonan.getNama_pemohon());
        datebox_Tanggal.setValue(tPermohonan.getTgl_permohonan());
        textbox_NikPemohon.setValue(tPermohonan.getNik_pemohon());
        textbox_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());
        textbox_CatatanAsman.setValue(tVerifikasi.getCatatan_asman());
        textbox_CatatanManager.setValue(tVerifikasi.getCatatan_manager());
    }

    public void onClick$btn_SimpanPersetujuanGm(Event event) throws Exception{
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

    private void doSimpan() throws Exception{
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

    private void doWriteComponentsToBean(TVerifikasi tVerifikasi) {
        Radio status = radiogroup_StatusPermohonanGm.getSelectedItem();
        tVerifikasi.setStatus_permohonan_manager(status.getValue());
        tVerifikasi.setUpdated_manager(new Timestamp(dateboxTanggal2.getValue().getTime()));
        tVerifikasi.setCatatan_manager(fck_CatatanGm.getValue());
    }

    private void doStoreInitValues() {
//        oldVar_radio_DisetujuiGM = radio_DisetujuiGM.getValue();

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
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
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 20, 2010
 * Time: 3:58:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersetujuanGmPemohonCtrl extends GFCBaseCtrl implements Serializable{

    private transient final static Logger logger = Logger.getLogger(PersetujuanGmPemohonCtrl.class);

    protected Window window_Permohonan;
    protected Window window_PersetujuanGmPemohon;

    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Textbox textbox_NikPemohon;
    protected Textbox textbox_DetailPermohonan;
    protected Textbox textbox_CatatanManager;
    protected Datebox datebox_Tanggal;
    protected Datebox datebox_Tanggal2;
    protected FCKeditor fck_CatatanGmPemohon;
    protected Button btn_SimpanPersetujuanGmPemohon;
    protected Button btn_Batal;
    protected Radiogroup radiogroup_StatusPermohonanGmPemohon;
    protected Radio radio_DisetujuiGmPemohon;
    protected Radio radio_DitolakGmPemohon;

    protected Listbox listbox_DaftarPermohonan;

    protected PersetujuanGmPemohonCtrl persetujuanGmPemohonCtrl;
    private transient TVerifikasi tVerifikasi;
    private transient VerifikasiService verifikasiService;
    private transient TPermohonan tPermohonan;

    private transient String oldVar_textbox_TIdossPermohonanId;
    private transient String oldVar_textbox_NamaPemohon;
    private transient String oldVar_textbox_NikPemohon;
    private transient String oldVar_textbox_DetailPermohonan;
    private transient String oldVar_textbox_CatatanManager;
    private transient String oldVar_datebox_Tanggal;
    private transient String oldVar_datebox_Tanggal2;
    private transient String oldVar_fck_CatatanGmPemohon;
    private transient boolean oldVar_radio_DisetujuiGmPemohon;
    private transient boolean oldVar_radio_DitolakGmPemohon;
//    private transient String oldVar_

    public PersetujuanGmPemohonCtrl(){
        super();

        if (logger.isDebugEnabled()) {
			logger.debug("--> super()");
        }
    }

    public void onCreate$window_Verifikasi(Event event) throws Exception {

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

        if (args.containsKey("persetujuanGmPemohonCtrl")) {
            persetujuanGmPemohonCtrl = (PersetujuanGmPemohonCtrl) args.get("persetujuanGmPemohonCtrl");
        } else {
            persetujuanGmPemohonCtrl = null;
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

    private void doWriteBeanToComponent(TVerifikasi tVerifikasi) {
        textbox_TIdossPermohonanId.setValue(tVerifikasi.getT_idoss_verifikasi_id());
        textbox_NamaPemohon.setValue(tPermohonan.getNama_pemohon());
        datebox_Tanggal.setValue(tVerifikasi.getTgl_permohonan());
        textbox_NikPemohon.setValue(tPermohonan.getNik_pemohon());
        textbox_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());
        textbox_CatatanManager.setValue(tVerifikasi.getCatatan_asman());
    }

    public void onClick$btn_SimpanPersetujuanGmPemohon(Event event) throws Exception{

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
        Radio status = radiogroup_StatusPermohonanGmPemohon.getSelectedItem();
        tVerifikasi.setStatus_permohonan_manager(status.getValue());
        tVerifikasi.setUpdated_manager(new Timestamp(datebox_Tanggal2.getValue().getTime()));
        tVerifikasi.setCatatan_manager(fck_CatatanGmPemohon.getValue());
    }

    private void doStoreInitValues() {

    }

    public TPermohonan gettPermohonan() {
        return tPermohonan;
    }

    public void settPermohonan(TPermohonan tPermohonan) {
        this.tPermohonan = tPermohonan;
    }

    public VerifikasiService getVerifikasiService() {
        return verifikasiService;
    }

    public void setVerifikasiService(VerifikasiService verifikasiService) {
        this.verifikasiService = verifikasiService;
    }

    public TVerifikasi gettVerifikasi() {
        return tVerifikasi;
    }

    public void settVerifikasi(TVerifikasi tVerifikasi) {
        this.tVerifikasi = tVerifikasi;
    }
}

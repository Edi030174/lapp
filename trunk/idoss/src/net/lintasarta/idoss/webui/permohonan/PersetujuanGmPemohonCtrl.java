package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.service.PermohonanService;
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

    protected Button btn_SimpanPersetujuanGmPemohon;
    protected Button btn_Batal;

    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Textbox textbox_NikPemohon;
    protected FCKeditor fck_DetailPermohonan;
    protected FCKeditor fck_CatatanManager;
    protected Datebox datebox_Tanggal;
    protected Datebox datebox_Tanggal2;
    protected FCKeditor fck_CatatanGmPemohon;
    protected Radiogroup radiogroup_StatusPermohonanGmPemohon;
    protected Radio radio_DisetujuiGmPemohon;
    protected Radio radio_DitolakGmPemohon;

    private transient String oldVar_textbox_TIdossPermohonanId;
    private transient String oldVar_textbox_NamaPemohon;
    private transient String oldVar_textbox_NikPemohon;
    private transient String oldVar_fck_DetailPermohonan;
    private transient String oldVar_fck_CatatanManager;
    private transient String oldVar_datebox_Tanggal;
    private transient String oldVar_datebox_Tanggal2;
    private transient String oldVar_fck_CatatanGmPemohon;
    private transient boolean oldVar_radio_DisetujuiGmPemohon;
    private transient boolean oldVar_radio_DitolakGmPemohon;
//    private transient String oldVar_

    protected Listbox listbox_DaftarPermohonan;
    protected PersetujuanGmPemohonCtrl persetujuanGmPemohonCtrl;

    private transient PermohonanService permohonanService;
    private transient TPermohonan tPermohonan;

    public PersetujuanGmPemohonCtrl(){
        super();

        if (logger.isDebugEnabled()) {
			logger.debug("--> super()");
        }
    }

    public void onCreate$window_PersetujuanGmPemohon(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("tPermohonan")) {
            TPermohonan tPermohonan = (TPermohonan) args.get("tPermohonan");
            settPermohonan(tPermohonan);
        } else {
            settPermohonan(null);
        }

        if (args.containsKey("PersetujuanGmPemohonCtrl")) {
            persetujuanGmPemohonCtrl = (PersetujuanGmPemohonCtrl) args.get("PersetujuanGmPemohonCtrl");
        } else {
            persetujuanGmPemohonCtrl = null;
        }

        if (args.containsKey("listbox_DaftarPermohonan")) {
            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
        } else {
            listbox_DaftarPermohonan = null;
        }

        doShowDialog(gettPermohonan());
    }

    private void doShowDialog(TPermohonan tPermohonan) throws InterruptedException {

        settPermohonan(tPermohonan);
        try {
            doWriteBeanToComponents(tPermohonan);
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponents(TPermohonan tPermohonan) throws Exception{
        textbox_TIdossPermohonanId.setValue(tPermohonan.getT_idoss_permohonan_id());
        textbox_NamaPemohon.setValue(tPermohonan.getNama_pemohon());
        datebox_Tanggal.setValue(tPermohonan.getTgl_permohonan());
        textbox_NikPemohon.setValue(tPermohonan.getNik_pemohon());
        fck_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());
        fck_CatatanManager.setValue(tPermohonan.getCatatan_manager());
        datebox_Tanggal2.setValue(tPermohonan.getUpdated_gm());
        fck_CatatanGmPemohon.setValue(tPermohonan.getCatatan_gm());
    }

    public void onClick$btn_SimpanPersetujuanGmPemohon(Event event) throws Exception{

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
        window_Permohonan.onClose();
    }

    public void onClick$btn_Batal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        window_Permohonan.onClose();
    }

    private void doSimpan() throws Exception{
        TPermohonan tPermohonan = gettPermohonan();
        doWriteComponentsToBean(tPermohonan);

        try {
            getPermohonanService().saveOrUpdateTPermohonan(tPermohonan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
        doStoreInitValues();
    }

    private void doWriteComponentsToBean(TPermohonan tPermohonan) {
        Radio status = radiogroup_StatusPermohonanGmPemohon.getSelectedItem();
//        tPermohonan.set(status.getValue());
        tPermohonan.setUpdated_gm(new Timestamp(datebox_Tanggal2.getValue().getTime()));
        tPermohonan.setCatatan_gm(fck_CatatanGmPemohon.getValue());
        tPermohonan.setUpdated_gm(new Timestamp(datebox_Tanggal.getValue().getTime()));
    }

    private void doStoreInitValues() {

    }

    public TPermohonan gettPermohonan() {
        return tPermohonan;
    }

    public void settPermohonan(TPermohonan tPermohonan) {
        this.tPermohonan = tPermohonan;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }
}
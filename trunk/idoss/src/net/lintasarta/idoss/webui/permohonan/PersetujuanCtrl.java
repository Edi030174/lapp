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
 * User: JosH
 * Date: Jul 23, 2010
 * Time: 12:48:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersetujuanCtrl extends GFCBaseCtrl implements Serializable {

    private transient static final Logger logger = Logger.getLogger(PersetujuanCtrl.class);

    protected Window window_Persetujuan;

    protected Datebox dateboxTanggal1;
    protected Textbox textbox_NikPelaksana;
    protected Checkbox checkbox1;
    protected Radiogroup radiogroup_Statuspermohonanasman;
    protected Radio radioDisetujui;
    protected Radio radioDitolak;
    protected FCKeditor fckCatatan_asman;

    protected Datebox dateboxTanggal2;
    protected Checkbox checkbox2;
    protected Radiogroup radiogroup_Statuspermohonanmanager;
    protected Radio radioDisetujui2;
    protected Radio radioDitolak2;
    protected FCKeditor fckCatatan_manager;

    private transient String oldVar_dateboxTanggal1;
    private transient String oldVar_comboboxNikPelaksana;
    private transient boolean oldVar_checkbox1;
    private transient boolean oldVar_radioDisetujui;
    private transient boolean oldVar_radioDitolak;
    private transient String oldVar_fckCatatanAsman;

    private transient String oldVar_dateboxTanggal2;
    private transient String oldVar_checkbox2;
    private transient boolean oldVar_radioDisetujui2;
    private transient boolean oldVar_radioDitolak2;
    private transient String oldVar_fckCatatanManager;

    protected Listbox listbox_DaftarPermohonan;
    protected Button btnSimpan_Persetujuan;
    protected Button btnBatal;
    protected PersetujuanCtrl persetujuanCtrl;

    private transient TVerifikasi tVerifikasi;
    private transient VerifikasiService verifikasiService;
    private transient TPermohonan tPermohonan;

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

        if (args.containsKey("persetujuanCtrl")) {
            persetujuanCtrl = (PersetujuanCtrl) args.get("persetujuanCtrl");
        } else {
            persetujuanCtrl = null;
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

        dateboxTanggal1.setValue(tVerifikasi.getTgl_permohonan());

        if(tVerifikasi.getNik_pelaksana() == null){
           textbox_NikPelaksana.setValue(getUserWorkspace().getUserSession().getEmployeeNo());
        }else {
            textbox_NikPelaksana.setValue(tVerifikasi.getNik_pelaksana());
        }

        fckCatatan_asman.setValue(tVerifikasi.getCatatan_asman());
        dateboxTanggal2.setValue(tVerifikasi.getTgl_permohonan());
        fckCatatan_manager.setValue(tVerifikasi.getCatatan_manager());
    }

    public void onClick$btnSimpan_Persetujuan(Event event) throws Exception{

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
    }

    public void onClick$btnBatal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        window_Persetujuan.onClose();
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

    }

    private void doWriteComponentsToBean(TVerifikasi tVerifikasi) {

//        Permohonan permohonan = getCreationArgsMap().get(permohonanCtrl);
//        tVerifikasi.setT_idoss_verifikasi_id(
//        Radio prioritas = radiogroup_Prioritas.getSelectedItem();
//        tVerifikasi.setUrgensi(prioritas.getValue());
//        Radio dampak = radiogroup_Dampak.getSelectedItem();
//        tVerifikasi.setDampak(dampak.getValue());
        Radio status = radiogroup_Statuspermohonanasman.getSelectedItem();
        tVerifikasi.setStatus_permohonanasman(status.getValue());
        tVerifikasi.setTgl_permohonan(new Timestamp(dateboxTanggal1.getValue().getTime()));
        tVerifikasi.setNik_pelaksana(textbox_NikPelaksana.getValue());
        tVerifikasi.setCatatan_asman(fckCatatan_asman.getValue());
        Radio statusMan = radiogroup_Statuspermohonanmanager.getSelectedItem();
        tVerifikasi.setStatus_permohonanmanager(statusMan.getValue());
        tVerifikasi.setUpdated_asman(new Timestamp(dateboxTanggal2.getValue().getTime()));
        tVerifikasi.setCatatan_manager(fckCatatan_manager.getValue());
        tVerifikasi.setRfs("rfs");
        tVerifikasi.setType_permohonan("T");
        tVerifikasi.setCatatan_per_type("n");
        tVerifikasi.setUpdated_manager(new Timestamp(dateboxTanggal2.getValue().getTime()));
        tVerifikasi.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        tVerifikasi.setUpdated_user(getUserWorkspace().getUserSession().getUserName());
        tVerifikasi.setCreated_date(new Timestamp(dateboxTanggal2.getValue().getTime()));
        tVerifikasi.setUpdated_date(new Timestamp(dateboxTanggal2.getValue().getTime()));

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
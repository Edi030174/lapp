package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.pengaduan.service.MttrService;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.PelaksanaanService;
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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 20, 2010
 * Time: 3:58:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersetujuanGmPemohonCtrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(PersetujuanGmPemohonCtrl.class);
    protected Window window_Permohonan;
    protected Window window_PersetujuanGmPemohon;

    protected Button btn_SimpanPersetujuanGmPemohon;
    protected Button btn_Batal;
    protected Checkbox checkbox_Cepat;
    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Textbox textbox_NikPemohon;
    protected Textbox textbox_DetailPermohonan;
    protected Textbox textbox_muser;
    protected Textbox textbox_gmuser;

//    protected Listbox listbox_NamaPelaksana;
    protected Datebox datebox_Tanggal;
    protected Intbox intbox_target;
    protected Label label_tgl1;
    protected Label label_tgl2;

    protected Label label_by1;
    protected Label label_by2;

    protected Label sp1;
    protected Label sp2;

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

    private transient Window window_DaftarPermohonan;


    protected Listbox listbox_DaftarPermohonan;
    protected Button btnSimpan_Persetujuan;
    protected Button btnBatal;
    protected PersetujuanCtrl persetujuanCtrl;

    private transient TPermohonan tPermohonan;
    private transient TVerifikasi tVerifikasi;
    private transient TPelaksanaan tPelaksanaan;
    private transient Mttr mttr;
    private transient PermohonanService permohonanService;
    private transient VerifikasiService verifikasiService;
    private transient PelaksanaanGangguanService pelaksanaanGangguanService;
    private transient PelaksanaanService pelaksanaanService;
    private transient MttrService mttrService;

    public PersetujuanGmPemohonCtrl() {
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

//        if (args.containsKey("listbox_DaftarPermohonan")) {
//            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
//        } else {
//            listbox_DaftarPermohonan = null;
//        }
        doCheckRights(gettVerifikasi(), gettPermohonan());
        List<Mttr> mttrs = getMttrService().getMttrByNomorTiket(gettPermohonan().getT_idoss_permohonan_id());
        for (Mttr mttr1 : mttrs) {
            setMttr(mttr1);
        }
        doShowDialog(gettVerifikasi(), gettPermohonan(), getMttr());
    }

    private void doCheckRights(TVerifikasi tVerifikasi, TPermohonan tPermohonan) {
        boolean save_gmuser = (tPermohonan.getStatus_track_permohonan().contains("Disetujui Manager Pemohon"));
        btn_SimpanPersetujuanGmPemohon.setVisible(save_gmuser);

        if (tPermohonan.getUpdated_manager() != null) {
            Timestamp ts = tPermohonan.getUpdated_manager();
            String tgl = new SimpleDateFormat("dd-MM-yyyy").format(ts);
            label_tgl1.setValue("Tanggal persetujuan: " + tgl);
        }
        if (tPermohonan.getNama_manager() != null) {
            label_by1.setValue("Oleh: " + tPermohonan.getNama_manager());
        }
        if (tPermohonan.getUpdated_gm() != null) {
            Timestamp ts = tPermohonan.getUpdated_gm();
            String tgl = new SimpleDateFormat("dd-MM-yyyy").format(ts);
            label_tgl2.setValue("Tanggal persetujuan: " + tgl);
        }
        if (tPermohonan.getNama_gm() != null) {
            label_by2.setValue("Oleh: " + tPermohonan.getNama_gm());
        }

        if (tPermohonan.getStatus_track_permohonan().equals("Disetujui Manager Pemohon")) {
            sp2.setVisible(true);
            radiogroup_StatusPermohonanGmPemohon.setVisible(true);
        } else {
            sp2.setVisible(false);
            radiogroup_StatusPermohonanGmPemohon.setVisible(false);
        }
        sp1.setVisible(false);
        radiogroup_StatusPermohonanManagerPemohon.setVisible(false);
        textbox_DetailPermohonan.setReadonly(true);
        textbox_muser.setReadonly(true);
        textbox_gmuser.setReadonly(false);
    }

    private void doShowDialog(TVerifikasi tVerifikasi, TPermohonan tPermohonan, Mttr mttr) throws InterruptedException {
        try {
            doWriteBeanToComponents(tVerifikasi, tPermohonan, mttr);
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponents(TVerifikasi tVerifikasi, TPermohonan tPermohonan, Mttr mttr) {
        textbox_TIdossPermohonanId.setValue(tVerifikasi.getT_idoss_verifikasi_id());
//        textbox_TIdossPermohonanId.setValue(tPermohonan.getT_idoss_permohonan_id());
        textbox_NamaPemohon.setValue(tPermohonan.getNama_pemohon());
        datebox_Tanggal.setValue(tPermohonan.getTgl_permohonan());
//        datebox_Tanggal.setValue(tVerifikasi.getUpdated_date());

        textbox_NikPemohon.setValue(tPermohonan.getNik_pemohon());
        if (tPermohonan.getUrgensi().equals("H")) {
            radiogroup_Prioritas.setSelectedItem(radio_high);
        } else {
            radiogroup_Prioritas.setSelectedItem(radio_normal);
        }
        if (tPermohonan.getDampak().equals("MAJOR")) {
            radiogroup_Dampak.setSelectedItem(radio_major);
        }

        /*int indexPlks = 0;
        ListModel listPlks = listbox_NamaPelaksana.getModel();
        for (int i = 0; i < listPlks.getSize(); i++) {
            VHrEmployeePelaksana np = (VHrEmployeePelaksana) listPlks.getElementAt(i);
            if (np.getEmployee_no().equals(tVerifikasi.getNik_pelaksana())) indexPlks = i;
        }
        listbox_NamaPelaksana.setSelectedIndex(indexPlks);*/

        textbox_DetailPermohonan.setValue(tPermohonan.getDetail_permohonan());

        if (tPermohonan.getStatus_track_permohonan().equals("Ditolak Manager Pemohon")) {
            radiogroup_StatusPermohonanManagerPemohon.setSelectedItem(radio_DitolakMPemohon);
        }
        textbox_muser.setValue(tPermohonan.getCatatan_manager());

        if (tPermohonan.getStatus_track_permohonan().equals("Ditolak GM Pemohon")) {
            radiogroup_StatusPermohonanGmPemohon.setSelectedItem(radio_DitolakGmPemohon);
        }
        textbox_gmuser.setValue(tPermohonan.getCatatan_gm());

        /*if (tVerifikasi.getStatus_permohonanasman().equals("Ditolak Asman Dukophar")) {
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
        if (mttr.getTarget2() != null) {
            intbox_target.setValue(Integer.parseInt(mttr.getTarget2()));
        }*/
    }

    public void onClick$btn_Batal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        window_Permohonan.onClose();
    }

    public void onClick$btn_SimpanPersetujuanGmPemohon(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        if (ValidateGMUser()) {
            doSimpan();
            window_Permohonan.onClose();
            Events.postEvent("onCreate", window_DaftarPermohonan, event);
            window_DaftarPermohonan.invalidate();
        }
    }

    private boolean ValidateGMUser() throws InterruptedException {
        if (textbox_gmuser.getValue().length() < 1) {
            Messagebox.show("Silakan isi Catatan GM...");
            return false;
        }
        return true;
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

    public PelaksanaanService getPelaksanaanService() {
        return pelaksanaanService;
    }

    public void setPelaksanaanService(PelaksanaanService pelaksanaanService) {
        this.pelaksanaanService = pelaksanaanService;
    }

    public MttrService getMttrService() {
        return mttrService;
    }

    public void setMttrService(MttrService mttrService) {
        this.mttrService = mttrService;
    }

    public TPermohonan gettPermohonan() {
        return tPermohonan;
    }

    public void settPermohonan(TPermohonan tPermohonan) {
        this.tPermohonan = tPermohonan;
    }

    public Mttr getMttr() {
        return mttr;
    }

    public void setMttr(Mttr mttr) {
        this.mttr = mttr;
    }
}
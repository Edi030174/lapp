package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaListModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 19, 2010
 * Time: 9:10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersetujuanAsmanCtrl extends GFCBaseCtrl implements Serializable {

    private transient final static Logger logger = Logger.getLogger(PersetujuanAsmanCtrl.class);
    protected Window window_Permohonan;
    protected Window window_Persetujuan;

    protected Groupbox groupbox_ManagerPemohon;
    protected Groupbox groupbox_GmPemohon;
    protected Groupbox groupbox_AM;
    protected Groupbox groupbox_Manager;
    protected Groupbox groupbox_Gm;

    protected Button btn_SimpanPersetujuanAsman;

    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Textbox textbox_NikPemohon;
    protected Textbox textbox_DetailPermohonan;
    protected Textbox textbox_muser;
    protected Textbox textbox_gmuser;
    protected Textbox textbox_amdukophar;
    protected Listbox listbox_NamaPelaksana;
    protected Datebox datebox_Tanggal;
    protected Intbox intbox_target;
    protected Label label_tgl1;
    protected Label label_tgl2;
    protected Label label_tgl3;
    protected Label label_by1;
    protected Label label_by2;
    protected Label label_by3;
    protected Label sp1;
    protected Label sp2;
    protected Label sp3;
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
    private transient Window window_DaftarPermohonan;
    protected Listbox listbox_DaftarPermohonan;
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

    public PersetujuanAsmanCtrl() {
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

//        if (args.containsKey("listbox_DaftarPermohonan")) {
//            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
//        } else {
//            listbox_DaftarPermohonan = null;
//        }
        doCheckRights(gettVerifikasi(), gettPermohonan());
        if (getPelaksanaanGangguanService().getEmployeeName() != null) {
            ListModelList lmlNamaPelaksana = new ListModelList(getPelaksanaanGangguanService().getEmployeeName());
            VHrEmployeePelaksana pelaksana = new VHrEmployeePelaksana();
            pelaksana.setEmployee_name("Silakan pilih");
            pelaksana.setEmployee_no("555");
            lmlNamaPelaksana.add(0, pelaksana);
            listbox_NamaPelaksana.setModel(lmlNamaPelaksana);
            listbox_NamaPelaksana.setItemRenderer(new PelaksanaListModelItemRenderer());
        }
        List<Mttr> mttrs = getMttrService().getMttrByNomorTiket(gettPermohonan().getT_idoss_permohonan_id());
        for (Mttr mttr1 : mttrs) {
            setMttr(mttr1);
        }
        doShowDialog(gettVerifikasi(), gettPermohonan(), getMttr());
    }

    private void doCheckRights(TVerifikasi tVerifikasi, TPermohonan tPermohonan) {
        boolean save_amdukophar = ((tPermohonan.getStatus_track_permohonan().contains("Disetujui GM Pemohon"))
                || (tPermohonan.getStatus_track_permohonan().contains("Persetujuan Asman Dukophar"))
                || (tPermohonan.getStatus_track_permohonan().contains("Permohonan Baru Manager Dukophar"))
                || (tPermohonan.getStatus_track_permohonan().contains("Permohonan Baru GM Dukophar")));
        btn_SimpanPersetujuanAsman.setVisible(save_amdukophar);
        if (save_amdukophar) {
            radio_high.setDisabled(false);
            radio_normal.setDisabled(false);
            radio_major.setDisabled(false);
            radio_minor.setDisabled(false);
            listbox_NamaPelaksana.setDisabled(false);
        } else {
            radio_high.setDisabled(true);
            radio_normal.setDisabled(true);
            radio_major.setDisabled(true);
            radio_minor.setDisabled(true);
            listbox_NamaPelaksana.setDisabled(true);
        }

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

        if (tVerifikasi.getUpdated_asman() != null) {
            Timestamp ts = tVerifikasi.getUpdated_asman();
            String tgl = new SimpleDateFormat("dd-MM-yyyy").format(ts);
            label_tgl3.setValue("Tanggal persetujuan: " + tgl);
        }
        if (tVerifikasi.getNama_asman() != null) {
            label_by3.setValue("Oleh: " + tVerifikasi.getNama_asman());
        }
        radiogroup_StatusPermohonanManagerPemohon.setVisible(false);
        radiogroup_StatusPermohonanGmPemohon.setVisible(false);
        radiogroup_StatusPermohonanAsman.setVisible(true);
        textbox_DetailPermohonan.setReadonly(true);
        textbox_muser.setReadonly(true);
        textbox_gmuser.setReadonly(true);
        textbox_amdukophar.setReadonly(false);

        sp1.setVisible(false);
        sp2.setVisible(false);
        sp3.setVisible(true);

    }

    private void doShowDialog(TVerifikasi tVerifikasi, TPermohonan tPermohonan, Mttr mttr) throws InterruptedException {
        try {
            doWriteBeanToComponents(tVerifikasi, tPermohonan, mttr);
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponents(TVerifikasi tVerifikasi, TPermohonan tPermohonan, Mttr mttr) {
/*        textbox_TIdossPermohonanId.setValue(tVerifikasi.getT_idoss_verifikasi_id());
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

    public void onClick$btn_SimpanPersetujuanManagerPemohon(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        if (ValidateMUser()) {
            doSave();
            window_Permohonan.onClose();
            Events.postEvent("onCreate", window_DaftarPermohonan, event);
            window_DaftarPermohonan.invalidate();
        }
    }

    private boolean ValidateMUser() throws InterruptedException {
        if (textbox_muser.getValue().length() < 1) {
            Messagebox.show("Silakan isi Catatan Manager...");
            return false;
        }
        return true;
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

    public void onClick$btn_SimpanPersetujuanAsman(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        if (isValidatedFlow()) {
            doSimpanAsmanDukophar();
            window_Permohonan.onClose();
            Events.postEvent("onCreate", window_DaftarPermohonan, event);
            window_DaftarPermohonan.invalidate();
        }
    }

    private boolean isValidatedFlow() throws InterruptedException {
        if (radiogroup_StatusPermohonanAsman.getSelectedItem().equals(radio_DisetujuiAM)) {
            if (textbox_amdukophar.getValue().length() < 1) {
                Messagebox.show("Silakan isi Catatan Assisten Manager...");
                return false;
            }
            if (listbox_NamaPelaksana.getSelectedItem().getLabel().equalsIgnoreCase("Silakan pilih")) {
                Messagebox.show("Silakan pilih nama pelaksana");
                return false;
            }
            if (listbox_NamaPelaksana.getSelectedItem() == null) {
                Messagebox.show("Silakan pilih nama pelaksana");
                return false;
            }
            if (intbox_target.getValue() == null) {
                Messagebox.show("Silakan isikan target selesai");
                return false;
            }
        }
        return true;
    }

    private void doSimpanAsmanDukophar() throws Exception {
        TPermohonan tPermohonan = gettPermohonan();
        TVerifikasi tVerifikasi = gettVerifikasi();
        TPelaksanaan tPelaksanaan = gettPelaksanaan();
        Mttr mttr = getMttr();
        doWriteComponentsToBean3(tPermohonan, tVerifikasi, tPelaksanaan, mttr);

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

    private void doWriteComponentsToBean3(TPermohonan tPermohonan, TVerifikasi tVerifikasi, TPelaksanaan tPelaksanaan, Mttr mttr) {
        Radio dampak = radiogroup_Dampak.getSelectedItem();
        tPermohonan.setDampak(dampak.getValue());
        Radio prioritas = radiogroup_Prioritas.getSelectedItem();
        tPermohonan.setUrgensi(prioritas.getValue());

        Listitem itempelaksana = listbox_NamaPelaksana.getSelectedItem();
        ListModelList lml = (ListModelList) listbox_NamaPelaksana.getListModel();
        VHrEmployeePelaksana vHrEmployeePelaksana = (VHrEmployeePelaksana) lml.get(itempelaksana.getIndex());
        if (!vHrEmployeePelaksana.getEmployee_name().equalsIgnoreCase("Silakan pilih")) {
            tVerifikasi.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());
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

        int target = intbox_target.getValue();
        long too = setTarget(target);
        Timestamp tanggalnya = new Timestamp(too);
        mttr.setTarget(too);
        mttr.setTarget2(Integer.toString(target));
    }

    private long setTarget(int berapaHari) {
        long lengthOfInterval = berapaHari * 86400000;
        long currentDate = new Date().getTime();
        long newDate = currentDate + lengthOfInterval;
        return new Date(newDate).getTime();
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
package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaListModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.model.comparator.TPermohonanComparator;
import net.lintasarta.permohonan.service.PermohonanService;
import net.lintasarta.permohonan.service.VerifikasiService;
import net.lintasarta.security.model.VHrEmployee;
import net.lintasarta.security.util.LoginConstants;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Joshua
 * Date: Jul 8, 2010
 * Time: 2:36:13 PM
 */
public class PermohonanBaruCtrl extends GFCBaseCtrl implements Serializable {
    private Media uploadMedia;

    private transient final static Logger logger = Logger.getLogger(PermohonanBaruCtrl.class);

    protected Window window_Permohonan;
    protected Tab tab_Permohonan;
    protected Tabpanel tabpanel_Permohonan;
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
    protected Radiogroup radiogroup_Prioritas;
    protected Listbox listbox_NamaPelaksana;

    protected Textbox textbox_Lainlain;
    protected Button button_Lampiran;
    protected Button button_Download;
    protected Textbox textbox_DetailPermohonan;
    protected Label label_viewAttachment;
    protected Label label_prioritas;
    protected Label label_dampak;
    protected Label label_pelaksana;
    protected Label label_target;
    protected Label label_hari;
    protected Intbox intbox_target;


    protected Tab tab_Verifikasi;
    protected Tabpanel tabPanel_Verifikasi;

    protected Tab tab_Pelaksanaan;
    protected Tabpanel tabPanel_Pelaksanaan;
    private transient boolean validationOn;
    private transient Listbox listbox_DaftarPermohonan;

    protected Button btnSimpan_Permohonan;
    protected Button btnBatal;

    protected PermohonanBaruCtrl permohonanBaruCtrl;

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
    private transient String oldVar_textbox_DetailPermohonan;

    private transient TPermohonan tPermohonan;
    private transient TVerifikasi tVerifikasi;
    private transient TPelaksanaan tPelaksanaan;
    private transient PermohonanService permohonanService;
    private transient VerifikasiService verifikasiService;
    private transient PelaksanaanGangguanService pelaksanaanGangguanService;

    public PermohonanBaruCtrl() {
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

        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("tPermohonan")) {
            TPermohonan tPermohonan = (TPermohonan) args.get("tPermohonan");
            settPermohonan(tPermohonan);
        } else {
            settPermohonan(null);
        }

        if (args.containsKey("permohonanBaruCtrl")) {
            permohonanBaruCtrl = (PermohonanBaruCtrl) args.get("permohonanBaruCtrl");
        } else {
            permohonanBaruCtrl = null;
        }

        if (args.containsKey("listbox_DaftarPermohonan")) {
            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
        } else {
            listbox_DaftarPermohonan = null;
        }
        if (getPelaksanaanGangguanService().getEmployeeName() != null) {
            ListModelList lmlNamaPelaksana = new ListModelList(getPelaksanaanGangguanService().getEmployeeName());
            VHrEmployeePelaksana pelaksana = new VHrEmployeePelaksana();
            pelaksana.setEmployee_name("Silakan pilih");
            pelaksana.setEmployee_no("555");
            lmlNamaPelaksana.add(0, pelaksana);
            listbox_NamaPelaksana.setModel(lmlNamaPelaksana);
            listbox_NamaPelaksana.setItemRenderer(new PelaksanaListModelItemRenderer());
        }
        doShowDialog(gettPermohonan(), gettVerifikasi());

    }

    private void doCheckRights() {
        UserWorkspace workspace = getUserWorkspace();
        String role = getUserWorkspace().getUserSession().getEmployeeRole();
        if (role.equalsIgnoreCase(LoginConstants.AMDUK)) {
            label_prioritas.setVisible(true);
            radiogroup_Prioritas.setVisible(true);
            label_dampak.setVisible(true);
            radiogroup_Dampak.setVisible(true);
            label_pelaksana.setVisible(true);
            listbox_NamaPelaksana.setVisible(true);
            label_target.setVisible(true);
            intbox_target.setVisible(true);
            label_hari.setVisible(true);
        } else {
            label_prioritas.setVisible(false);
            radiogroup_Prioritas.setVisible(false);
            label_dampak.setVisible(false);
            radiogroup_Dampak.setVisible(false);
            label_pelaksana.setVisible(false);
            listbox_NamaPelaksana.setVisible(false);
            label_target.setVisible(false);
            intbox_target.setVisible(false);
            label_hari.setVisible(false);
        }
    }

    private void doShowDialog(TPermohonan tPermohonan, TVerifikasi tVerifikasi) throws InterruptedException {
        if (tPermohonan == null) {
            tPermohonan = getPermohonanService().getNewPermohonan();
            settPermohonan(tPermohonan);

        }
        try {
            doWriteBeanToComponents(tPermohonan, tVerifikasi);
            window_Permohonan.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private VHrEmployee getNamaVHrEmployee(String vHrEmployeeNo) {
        List<VHrEmployee> vHrEmployees = getPermohonanService().getVHrEmployeeByEmployeeNo(vHrEmployeeNo);
        for (VHrEmployee vHrEmployee : vHrEmployees) {
            return vHrEmployee;
        }
        return null;
    }

    private TPermohonan setNikNama(TPermohonan tPermohonan, String employeeNo) {
        VHrEmployee parentEmployee = getNamaVHrEmployee(employeeNo);
        if (parentEmployee.getJob_position_code().equals("Assistant Manager") || parentEmployee.getJob_position_code().equals("Analyst")) {
            tPermohonan.setNik_asman(employeeNo);
            tPermohonan.setNama_asman(parentEmployee.getEmployee_name());
        } else if (parentEmployee.getJob_position_code().equals("Manager") || parentEmployee.getJob_position_code().equals("POH Manager")) {
            tPermohonan.setNik_manager(employeeNo);
            tPermohonan.setNama_manager(parentEmployee.getEmployee_name());
        } else if (parentEmployee.getJob_position_code().equals("General Manager") || parentEmployee.getJob_position_code().equals("POH General Manager")) {
            tPermohonan.setNik_gm(employeeNo);
            tPermohonan.setNama_gm(parentEmployee.getEmployee_name());
        }
        return tPermohonan;
    }

    private TPermohonan setBoss(TPermohonan tPermohonan, String employeeNo) {
        tPermohonan = setNikNama(tPermohonan, employeeNo);

        String parentEmployeeNo = getPermohonanService().getManager(employeeNo);
        tPermohonan = setNikNama(tPermohonan, parentEmployeeNo);

        String grandParentEmployeeNo = getPermohonanService().getManager(parentEmployeeNo);
        tPermohonan = setNikNama(tPermohonan, grandParentEmployeeNo);

        return tPermohonan;

//        String parentEmployeeNo = getPermohonanService().getManager(employeeNo);
//        if (!parentEmployeeNo.equals(employeeNo)) {
//            String grandParentEmployeeNo = getPermohonanService().getManager(parentEmployeeNo);
//            if (!grandParentEmployeeNo.equals(parentEmployeeNo)) {
//                tPermohonan.setNik_manager(parentEmployeeNo);
//                tPermohonan.setNama_manager(getNamaVHrEmployee(parentEmployeeNo).getEmployee_name());
//
//                tPermohonan.setNik_gm(grandParentEmployeeNo);
//                tPermohonan.setNama_gm(getNamaVHrEmployee(grandParentEmployeeNo).getEmployee_name());
//            } else {
//                tPermohonan.setNik_manager(employeeNo);
//                tPermohonan.setNama_manager(getNamaVHrEmployee(employeeNo).getEmployee_name());
//
//                tPermohonan.setNik_gm(parentEmployeeNo);
//                tPermohonan.setNama_gm(getNamaVHrEmployee(parentEmployeeNo).getEmployee_name());
//            }
//        } else {
//            tPermohonan.setNik_gm(employeeNo);
//            tPermohonan.setNama_gm(getNamaVHrEmployee(employeeNo).getEmployee_name());
//        }
//        return tPermohonan;
    }

    private void doWriteBeanToComponents(TPermohonan tPermohonan, TVerifikasi tVerifikasi) {
        textbox_TIdossPermohonanId.setValue(getPermohonanService().getPermohonanID());
        textbox_NamaPemohon.setValue(getUserWorkspace().getUserSession().getEmployeeName());
        textbox_BagianPemohon.setValue(getUserWorkspace().getUserSession().getDepartment());
        tPermohonan = setBoss(tPermohonan, getUserWorkspace().getUserSession().getEmployeeNo());
        textbox_NamaManager.setValue(tPermohonan.getNama_manager());
        textbox_NamaGm.setValue(tPermohonan.getNama_gm());
        textbox_NikPemohon.setValue(getUserWorkspace().getUserSession().getEmployeeNo());
        textbox_NikManager.setValue(tPermohonan.getNik_manager());
        textbox_NikGm.setValue(tPermohonan.getNik_gm());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        datebox_Tanggal.setValue(ts);

//        int indexPlks = 0;
//        ListModel listPlks = listbox_NamaPelaksana.getModel();
//        for (int i = 0; i < listPlks.getSize(); i++) {
//            VHrEmployeePelaksana np = (VHrEmployeePelaksana) listPlks.getElementAt(i);
//            if (np.getEmployee_no().equals(tVerifikasi.getNik_pelaksana())) indexPlks = i;
//        }
//        listbox_NamaPelaksana.setSelectedIndex(indexPlks);
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
        if (isValidatedFlow()) {
            doSimpan();
            window_Permohonan.onClose();
        }
    }

    private boolean isValidatedFlow() throws InterruptedException {
        if (textbox_DetailPermohonan.getValue().length() < 1) {
            Messagebox.show("Silakan isi deskripsi");
            return false;
        }
        String role = getUserWorkspace().getUserSession().getEmployeeRole();
        if (role.equalsIgnoreCase(LoginConstants.AMDUK)) {
            if (listbox_NamaPelaksana.getSelectedItem() == null) {
                Messagebox.show("Silakan pilih nama pelaksana");
                return false;
            }
            if (listbox_NamaPelaksana.getSelectedItem().getLabel().equalsIgnoreCase("Silakan pilih")) {
                Messagebox.show("Silakan pilih nama pelaksana");
                return false;
            }
            if (intbox_target.getValue() < 1) {
                Messagebox.show("Silakan isikan target selesai");
                return false;
            }
        }
        if (radio_readonly.isSelected()) {
            if (getUploadMedia() == null) {
                Messagebox.show("Type permohonan Read Only -> silakan lampirkan file");
                return false;
            }
        } else if (radio_readwrite.isSelected()) {
            if (getUploadMedia() == null) {
                Messagebox.show("Type permohonan Read Write -> silakan lampirkan file");
                return false;
            }
        } else if (radio_aplikasi.isSelected()) {
            return true;
        } else if (radio_lainlain.isSelected()) {
            return true;
        }
        return true;
    }

    public void onClick$btnBatal(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        window_Permohonan.onClose();
    }

    private void doClose() throws Exception {

        window_Permohonan.onClose();
    }

    public void onUpload$button_Lampiran(UploadEvent event) throws IOException {
        Media media = event.getMedia();
        //allowed_types : zip/ rar/ word/ excel/ pdf/ txt.
        String file = media.getName();
        int zip = file.indexOf("zip");
        int rar = file.indexOf("rar");
        int doc = file.indexOf("doc");
        int docx = file.indexOf("docx");
        int xls = file.indexOf("xls");
        int xlsx = file.indexOf("xlsx");
        int pdf = file.indexOf("pdf");

        if (zip != -1 || rar != -1 || doc != -1 || docx != -1 || pdf != -1 || xls != -1 || xlsx != -1) {
            setUploadMedia(media);
            label_viewAttachment.setValue(file);
        } else {
            alert("Silahkan gunakan format : zip/ rar/ word/ excel/ pdf");
        }
    }

    private void doSimpan() throws InterruptedException {

        TPermohonan tPermohonan = gettPermohonan();
        Mttr mttr = new Mttr();
        doWriteComponentsToBean(tPermohonan);

        try {
            String uploadeFileName = null;
            if (getUploadMedia() != null) {
                uploadeFileName = getUploadMedia().getName();
            }
            getPermohonanService().simpanAllTPermohonan(uploadeFileName, tPermohonan, mttr);
            TVerifikasi tVerifikasi = getPermohonanService().getTVerifikasiByTIdossVerifikasiId(gettPermohonan().getT_idoss_permohonan_id());
            doWriteComponentsToBeanVer(tVerifikasi);
            getVerifikasiService().saveOrUpdateTVerifikasi(tVerifikasi);

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
        lml.sort(new TPermohonanComparator(), true);
    }

    private void doStoreInitValues() {

        oldVar_textboxTIdossPermohonanId = textbox_TIdossPermohonanId.getValue();
        oldVar_textboxNamaPemohon = textbox_NamaPemohon.getValue();
        oldVar_textboxBagianPemohon = textbox_BagianPemohon.getValue();
        oldVar_textboxNamaManager = textbox_NamaManager.getValue();
        oldVar_textboxNamaGm = textbox_NamaGm.getValue();
        oldVar_textboxNikPemohon = textbox_NikPemohon.getValue();
        oldVar_textboxNikManager = textbox_NikManager.getValue();
        oldVar_textboxNikGm = textbox_NikGm.getValue();

        oldVar_readonly = radio_readonly.getValue();
        oldVar_readwrite = radio_readwrite.getValue();
        oldVar_aplikasi = radio_aplikasi.getValue();
        oldVar_lainlain = radio_lainlain.getValue();
        oldVar_textboxLainlain = textbox_Lainlain.getValue();
//        oldVar_cepat = cepat.getValue();
        oldVar_textbox_DetailPermohonan = textbox_DetailPermohonan.getValue();


    }

    private void doWriteComponentsToBean(TPermohonan tPermohonan) {

//        tPermohonan.setT_idoss_permohonan_id(textbox_TIdossPermohonanId.getValue());
        tPermohonan.setNama_pemohon(textbox_NamaPemohon.getValue());
        tPermohonan.setBagian_pemohon(textbox_BagianPemohon.getValue());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tPermohonan.setTgl_permohonan(ts);
        tPermohonan.setNik_pemohon(textbox_NikPemohon.getValue());
        tPermohonan.setNama_manager(textbox_NamaManager.getValue());
        tPermohonan.setNik_manager(textbox_NikManager.getValue());
        tPermohonan.setNama_gm(textbox_NamaGm.getValue());
        tPermohonan.setNik_gm(textbox_NikGm.getValue());
        tPermohonan.setDetail_permohonan(textbox_DetailPermohonan.getValue());
        if (getUploadMedia() != null) {
            tPermohonan.setUploadStream(getUploadMedia().getStreamData());
        }
        tPermohonan.setTarget_mulai_digunakan(ts);
        String lain = textbox_Lainlain.getValue();
        tPermohonan.setLain_lain(lain);
//        radio_lainlain.setValue(lain);
        Radio type = radiogroupType_permohonan.getSelectedItem();
        tPermohonan.setType_permohonan(type.getValue());
        tPermohonan.setLain_lain(textbox_Lainlain.getValue());
        tPermohonan.setUpdated_divisi(ts);
        tPermohonan.setUpdated_gm(ts);
        tPermohonan.setUpdated_manager(ts);
        tPermohonan.setUpdated_pemohon(ts);
        Radio dampak = radiogroup_Dampak.getSelectedItem();
        tPermohonan.setDampak(dampak.getValue());
        Radio prioritas = radiogroup_Prioritas.getSelectedItem();
        tPermohonan.setUrgensi(prioritas.getValue());
        tPermohonan.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        tPermohonan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());

    }

    private void doWriteComponentsToBeanVer(TVerifikasi tVerifikasi) {
        if (listbox_NamaPelaksana.getSelectedItem() != null) {
            Listitem itempelaksana = listbox_NamaPelaksana.getSelectedItem();
            ListModelList lml = (ListModelList) listbox_NamaPelaksana.getListModel();
            VHrEmployeePelaksana vHrEmployeePelaksana = (VHrEmployeePelaksana) lml.get(itempelaksana.getIndex());
            if (!vHrEmployeePelaksana.getEmployee_name().equalsIgnoreCase("Silakan pilih")) {
                tVerifikasi.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());
            }
            if (!vHrEmployeePelaksana.getEmployee_no().equalsIgnoreCase("555")) {
                tVerifikasi.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());
            }
        }
    }

    public void onClick$button_Download(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        String lam = permohonanService.getFilePath() + "lampiran.xlsx";
        try {
            Filedownload.save(new File(lam), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public Media getUploadMedia() {
        return uploadMedia;
    }

    public void setUploadMedia(Media uploadMedia) {
        this.uploadMedia = uploadMedia;
    }
}
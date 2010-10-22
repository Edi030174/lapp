package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.idoss.webui.util.NoEmptyStringsConstraint;
import net.lintasarta.pengaduan.model.TDeskripsi;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.pengaduan.model.comparator.TPenangananGangguanComparator;
import net.lintasarta.pengaduan.service.PenangananGangguanService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Asri
 * Date: Jul 6, 2010
 * Time: 2:30:38 PM
 */
public class PengaduanCtrl extends GFCBaseCtrl implements Serializable {

    private transient static final Logger logger = Logger.getLogger(PengaduanCtrl.class);

    protected Window window_Pengaduan;
    protected Textbox textbox_NomorTiket;
    protected Textbox textbox_NamaPelapor;
    protected Textbox textbox_NikPelapor;
    protected Textbox textbox_Bagian;
    protected Textbox textbox_NomorHP;
    protected Textbox textbox_Ext;
    protected Textbox textbox_Judul;
    protected Listbox listbox_DaftarTiket;

    protected FCKeditor fckeditor_Des;

//    protected Button btnSimpan_pengaduan;
//    protected Button btnBatal_pengaduan;
//
    private transient String oldVar_textboxNomorTiket;
    private transient String oldVar_textboxNamaPelapor;
    private transient String oldVar_textboxNikPelapor;
    private transient String oldVar_textboxNomorHP;
    private transient String oldVar_textboxExt;
    private transient String oldVar_textboxJudul;
    private transient String oldVar_fckeditorDes;

    private transient boolean validationOn;

    private transient TPenangananGangguan tPenangananGangguan;
    private transient PenangananGangguanService penangananGangguanService;

    public PengaduanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()*/");
        }
    }

    public void onCreate$window_Pengaduan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("tPenangananGangguan")) {
            TPenangananGangguan tPenangananGangguan = (TPenangananGangguan) args.get("tPenangananGangguan");
            settPenangananGangguan(tPenangananGangguan);
        } else {
            settPenangananGangguan(null);
        }

        if (args.containsKey("listbox_DaftarTiket")) {
            listbox_DaftarTiket = (Listbox) args.get("listbox_DaftarTiket");
        } else {
            listbox_DaftarTiket = null;
        }

        doShowDialog(gettPenangananGangguan());
    }

    private void doShowDialog(TPenangananGangguan tPenangananGangguan) throws InterruptedException {

        if (tPenangananGangguan == null) {

            tPenangananGangguan = getPenangananGangguanService().getNewPenangananGangguan();
        }
        try {
            doWriteBeanToComponent(tPenangananGangguan);
            window_Pengaduan.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponent(TPenangananGangguan tPenangananGangguan) {
        textbox_NomorTiket.setValue(getPenangananGangguanService().getTiketId());
        textbox_NamaPelapor.setValue(getUserWorkspace().getUserSession().getEmployeeName());
        textbox_NikPelapor.setValue(getUserWorkspace().getUserSession().getEmployeeNo());
        textbox_Bagian.setValue(getUserWorkspace().getUserSession().getDepartment());
    }

    public void onClose$window_Pengaduan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        doClose();
    }

    public void onClick$btnSimpan_pengaduan(Event event) throws InterruptedException {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
        window_Pengaduan.onClose();
    }

    public void onClick$btnBatal_pengaduan(Event event) throws InterruptedException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        doBatal();
    }

    private void doClose() throws Exception {
        if (textbox_Judul.getConstraint() != null) {
            textbox_Judul = new Textbox();
        }
        if (textbox_NomorHP.getConstraint() != null) {
            textbox_NomorHP = new Textbox();
        }
        if (textbox_Ext.getConstraint() != null) {
            textbox_Ext = new Textbox();
        }

        window_Pengaduan.onClose();
    }

    private void doSimpan() throws InterruptedException {

        TPenangananGangguan tPenangananGangguan = gettPenangananGangguan();

        if (!isValidationOn()) {
            doSetValidation();
        }

        doWriteComponentsToBean(tPenangananGangguan);

        TDeskripsi tDeskripsi = new TDeskripsi();
        tDeskripsi.setT_idoss_penanganan_gangguan_id(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());

        if (fckeditor_Des.getValue() != null)
            tDeskripsi.setDeskripsi(fckeditor_Des.getValue());

        tDeskripsi.setUpdated_by(getUserWorkspace().getUserSession().getUserName());

        try {
            getPenangananGangguanService().createPenangananGangguan(tPenangananGangguan, tDeskripsi);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }

        // now synchronize the listBox
        ListModelList lml = (ListModelList) listbox_DaftarTiket.getListModel();

        // Check if the object is new or updated
        // -1 means that the obj is not in the list, so it's new.
        if (lml.indexOf(tPenangananGangguan) == -1) {
            lml.add(tPenangananGangguan);
        } else {
            lml.set(lml.indexOf(tPenangananGangguan), tPenangananGangguan);
        }
        lml.sort(new TPenangananGangguanComparator(), true);

        doStoreInitValues();
    }

    private void doStoreInitValues() {

        oldVar_textboxNomorTiket = textbox_NomorTiket.getValue();
        oldVar_textboxNamaPelapor = textbox_NamaPelapor.getValue();
        oldVar_textboxNikPelapor = textbox_NikPelapor.getValue();
        oldVar_textboxNomorHP = textbox_NomorHP.getValue();
        oldVar_textboxExt = textbox_Ext.getValue();
        oldVar_textboxJudul = textbox_Judul.getValue();
        oldVar_fckeditorDes = fckeditor_Des.getValue();

    }

    private void doWriteComponentsToBean(TPenangananGangguan tPenangananGangguan) {

        tPenangananGangguan.setT_idoss_penanganan_gangguan_id(textbox_NomorTiket.getValue());
        tPenangananGangguan.setNama_pelapor(textbox_NamaPelapor.getValue());
        tPenangananGangguan.setNik_pelapor(textbox_NikPelapor.getValue());
        tPenangananGangguan.setBagian_pelapor(textbox_Bagian.getValue());
        tPenangananGangguan.setNo_hp(textbox_NomorHP.getValue());
        tPenangananGangguan.setExt(textbox_Ext.getValue());
        tPenangananGangguan.setJudul(textbox_Judul.getValue());
        tPenangananGangguan.setDeskripsi(fckeditor_Des.getValue());
        tPenangananGangguan.setStatus("Open");
        tPenangananGangguan.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        tPenangananGangguan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());

    }

    private void doBatal() throws InterruptedException {

        window_Pengaduan.onClose();
    }

    private void doSetValidation() {

        setValidationOn(true);

        textbox_NomorHP.setConstraint(new SimpleConstraint("[0-9-/() ]*", Labels.getLabel("message.error.PhoneNumber")));
        textbox_Judul.setConstraint(new NoEmptyStringsConstraint());
        textbox_Ext.setConstraint(new NoEmptyStringsConstraint());
//        textbox_Ext.setConstraint( new SimpleConstraint("[0-9-/() ]*", Labels.getLabel("message.error.PhoneNumber")));
    }

    public boolean isValidationOn() {
        return validationOn;
    }

    public void setValidationOn(boolean validationOn) {
        this.validationOn = validationOn;
    }

    public TPenangananGangguan gettPenangananGangguan() {
        return tPenangananGangguan;
    }

    public void settPenangananGangguan(TPenangananGangguan tPenangananGangguan) {
        this.tPenangananGangguan = tPenangananGangguan;
    }

    public PenangananGangguanService getPenangananGangguanService() {
        return penangananGangguanService;
    }

    public void setPenangananGangguanService(PenangananGangguanService penangananGangguanService) {
        this.penangananGangguanService = penangananGangguanService;
    }
}
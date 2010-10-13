package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaListModelItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.RootCausedListModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.idoss.webui.util.NoEmptyStringsConstraint;
import net.lintasarta.pengaduan.model.*;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.pengaduan.service.RootCausedService;
import net.lintasarta.pengaduan.service.TypeService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Asri
 * Date: Jul 9, 2010
 * Time: 9:32:52 AM
 */
public class PelaksanaanGangguanCtrl extends GFCBaseCtrl implements Serializable {

    private transient static final Logger logger = Logger.getLogger(PelaksanaanGangguanCtrl.class);

    protected Window window_PelaksanaanGangguan;
    protected Textbox textbox_NomorTiket;
    protected Textbox texbox_Pelapor;
    protected Textbox texbox_Bagian;
    protected Textbox texbox_Judul;
    protected Textbox textbox_Type;
    protected FCKeditor fckeditor_Deskripsi;
    protected FCKeditor fckeditor_Solusi;//pelaksana hanya status & solusi yg enable
    protected Radiogroup radiogroup_Dampak;
//    protected Radio radio_minor;
//    protected Radio radio_mayor;
//    protected Listbox listbox_Type;
    protected Listbox listbox_RootCaused;
    protected Listbox listbox_NamaPelaksana;
    protected Combobox combobox_Status;//pelaksana hanya status & solusi yg enable
    protected Button btn_TambahRootCaused;
//    protected Button btnSimpan_RootCaused;
//    protected Button btnBatal_RootCaused;
    protected Button btnSimpan_PelaksanaanGangguan;//monitoring disable all

    protected Button btn_historyDeskripsi;
    protected Button btn_historySolusi;


    private transient boolean validationOn;
    protected PelaksanaanGangguanCtrl pelaksanaaGangguanCtrl;
    private transient Listbox listbox_DaftarTiket;
    private transient String oldVar_textbox_Pelaksana;
    private transient String oldVar_textbox_NikPelaksana;
    private transient String oldVar_fckeditor_Solusi;
    private transient String oldVar_radiogroup_Dampak;
    private transient String oldVar_combobox_Type;
    private transient String oldVar_combobox_RootCaused;
    private transient String oldVar_combobox_Status;
    private transient TPenangananGangguan tPenangananGangguan;
    private transient PRootCaused pRootCaused;
    private transient PType pType;
    private transient PelaksanaanGangguanService pelaksanaanGangguanService;
    private transient TypeService typeService;
    private transient RootCausedService rootCausedService;

    public PelaksanaanGangguanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("-->" + super.toString());
        }
    }

    public void onCreate$window_PelaksanaanGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doCheckRights();

        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("tPenangananGangguan")) {
            TPenangananGangguan tPenangananGangguan = (TPenangananGangguan) args.get("tPenangananGangguan");
            settPenangananGangguan(tPenangananGangguan);

        } else {
            settPenangananGangguan(null);
        }

        if (args.containsKey("pelaksanaanGangguanCtrl")) {
            pelaksanaaGangguanCtrl = (PelaksanaanGangguanCtrl) args.get("pelaksanaanGangguanCtrl");
        } else {
            pelaksanaaGangguanCtrl = null;
        }

        if (args.containsKey("listbox_DaftarTiket")) {
            listbox_DaftarTiket = (Listbox) args.get("listbox_DaftarTiket");
        } else {
            listbox_DaftarTiket = null;
        }
//        listbox_Type.setModel(new ListModelList(getPelaksanaanGangguanService().getType()));
//        listbox_Type.setItemRenderer(new TypeListModelItemRenderer());
//        listbox_RootCaused.setModel(new ListModelList(getPelaksanaanGangguanService().getRootCaused()));
//        listbox_RootCaused.setItemRenderer(new RootCausedListModelItemRenderer());

        listbox_NamaPelaksana.setModel(new ListModelList(getPelaksanaanGangguanService().getEmployeeName()));
        listbox_NamaPelaksana.setItemRenderer(new PelaksanaListModelItemRenderer());

        doShowDialog(gettPenangananGangguan());
    }

    private void doCheckRights() {
        UserWorkspace workspace = getUserWorkspace();
        btnSimpan_PelaksanaanGangguan.setVisible(workspace.isAllowed("btnSimpan_PelaksanaanGangguan"));


    }

    public void onClose$window_PelaksanaanGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        doClose();
    }

    public void onClick$btnSimpan_PelaksanaanGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
        window_PelaksanaanGangguan.onClose();
    }

    public void onClick$btnBatal_PelaksanaanGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        window_PelaksanaanGangguan.onClose();
    }

    public void onClick$btn_historyDeskripsi(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("t_idoss_penanganan_gangguan_id", gettPenangananGangguan().getT_idoss_penanganan_gangguan_id());
        try {
            Executions.createComponents("/WEB-INF/pages/pengaduan/hisDeskripsi.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    public void onClick$btn_historySolusi(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            Executions.createComponents("/WEB-INF/pages/pengaduan/hisSolusi.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    private void doSimpan() throws Exception {

        TPenangananGangguan tPenangananGangguan = gettPenangananGangguan();

        if (!isValidationOn()) {
            doSetValidation();
        }

        doWriteComponentsToBean(tPenangananGangguan);

        TDeskripsi tDeskripsi = new TDeskripsi();
        tDeskripsi.setT_idoss_penanganan_gangguan_id(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());

        if (fckeditor_Deskripsi.getValue() != null)
            tDeskripsi.setDeskripsi(fckeditor_Deskripsi.getValue());
        if (fckeditor_Solusi.getValue() != null)
            tDeskripsi.setSolusi(fckeditor_Solusi.getValue());

        tDeskripsi.setUpdated_by(getUserWorkspace().getUserSession().getUserName());

        try {
            getPelaksanaanGangguanService().saveOrUpdate(tPenangananGangguan,tDeskripsi);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }

        ListModelList lml = (ListModelList) listbox_DaftarTiket.getListModel();
        if (lml.indexOf(tPenangananGangguan) == -1) {
            lml.add(tPenangananGangguan);
        } else {
            lml.set(lml.indexOf(tPenangananGangguan), tPenangananGangguan);
        }
    }

    private void doShowDialog(TPenangananGangguan tPenangananGangguan) throws InterruptedException {

        try {
            doWriteBeanToComponent(tPenangananGangguan);
            window_PelaksanaanGangguan.doOverlapped();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponent(TPenangananGangguan tPenangananGangguan) throws Exception {
        textbox_NomorTiket.setValue(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        texbox_Pelapor.setValue(tPenangananGangguan.getNama_pelapor());
        texbox_Bagian.setValue(tPenangananGangguan.getBagian_pelapor());
        texbox_Judul.setValue(tPenangananGangguan.getJudul());
        combobox_Status.setValue(tPenangananGangguan.getStatus());

//        ListModelList lml = (ListModelList) listbox_NamaPelaksana.getModel();
        VHrEmployeePelaksana vHrEmployeePelaksana = getPelaksanaanGangguanService().getVHrEmployeePelaksanaById(tPenangananGangguan.getNik_pelaksana());
//        if (tPenangananGangguan.getNama_pelaksana() != null) {
//            listbox_NamaPelaksana.setSelectedIndex(lml.indexOf(vHrEmployeePelaksana));
//        } else {
//            listbox_NamaPelaksana.setSelectedIndex(-1);
//        }
//        listbox_NamaPelaksana.setModel(new ListModelList());

        int indexPlks = 0;
        ListModel listPlks = listbox_NamaPelaksana.getModel();
        for (int i = 0; i < listPlks.getSize(); i++) {
            VHrEmployeePelaksana np = (VHrEmployeePelaksana) listPlks.getElementAt(i);
            if (np.getEmployee_no().equals(tPenangananGangguan.getNik_pelaksana())) indexPlks = i;
        }
        listbox_NamaPelaksana.setSelectedIndex(indexPlks);

        if (tPenangananGangguan.getP_idoss_type_id() != null) {
            PType pType = getTypeService().getTypeByTypeID(tPenangananGangguan.getP_idoss_type_id());

            textbox_Type.setValue(pType.getType_desc());

            listbox_RootCaused.setModel(new ListModelList(getPelaksanaanGangguanService().getRootCausedByPTypeId(pType.getP_idoss_type_id())));

            listbox_RootCaused.setItemRenderer(new RootCausedListModelItemRenderer());
            int indexRoot = 0;
            ListModel listRoot = listbox_RootCaused.getModel();
            for (int i = 0; i < listRoot.getSize(); i++) {
                PRootCaused rc = (PRootCaused) listRoot.getElementAt(i);
                if (rc.getP_idoss_root_caused_id() == tPenangananGangguan.getP_idoss_root_caused_id()) indexRoot = i;
            }
            listbox_RootCaused.setSelectedIndex(indexRoot);
        }
    }

    private void doWriteComponentsToBean(TPenangananGangguan tPenangananGangguan) throws Exception {
        tPenangananGangguan.setDeskripsi(fckeditor_Deskripsi.getValue());
        if (fckeditor_Solusi.getValue() != null) {
            tPenangananGangguan.setSolusi(fckeditor_Solusi.getValue());
        }
        Radio dampak = radiogroup_Dampak.getSelectedItem();
        tPenangananGangguan.setDampak(dampak.getValue());
        Listitem item = listbox_RootCaused.getSelectedItem();
        if (item == null) {
            try {
                Messagebox.show("Silakan pilih Root Caused!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        ListModelList lml1 = (ListModelList) listbox_RootCaused.getListModel();
        PRootCaused rootCaused = (PRootCaused) lml1.get(item.getIndex());
        PTypeRootCaused pTypeRootCaused = getTypeService().getPTypeRootCausedByRootCausedId(rootCaused.getP_idoss_root_caused_id());
        tPenangananGangguan.setP_idoss_type_id(pTypeRootCaused.getP_idoss_type_id());
        tPenangananGangguan.setP_idoss_root_caused_id(rootCaused.getP_idoss_root_caused_id());
        Listitem itempelaksana = listbox_NamaPelaksana.getSelectedItem();
        if (itempelaksana == null) {
            try {
                Messagebox.show("Silakan pilih Pelaksana!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        ListModelList lml3 = (ListModelList) listbox_NamaPelaksana.getListModel();
        VHrEmployeePelaksana vHrEmployeePelaksana = (VHrEmployeePelaksana) lml3.get(itempelaksana.getIndex());
        tPenangananGangguan.setNama_pelaksana(vHrEmployeePelaksana.getEmployee_name());
        tPenangananGangguan.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());

        tPenangananGangguan.setStatus(combobox_Status.getValue());
        if (combobox_Status.getValue().equals("Closed")) {
            Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
            tPenangananGangguan.setUpdated_date(ts);
        }
        tPenangananGangguan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());
    }

    private void doClose() throws Exception {
        if (texbox_Pelapor.getConstraint() != null) {
            texbox_Pelapor = new Textbox();
        }
        if (texbox_Bagian.getConstraint() != null) {
            texbox_Bagian = new Textbox();
        }

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
                    switch ((Integer) evt.getData()) {
                        case MultiLineMessageBox.YES:
                            try {
                                doSimpan();
                            } catch (Exception e) {
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
        window_PelaksanaanGangguan.onClose();
    }

    private boolean isDataChanged() throws Exception {
        boolean change = false;

//        if (oldVar_textbox_Pelaksana !=(textbox_Pelaksana.getValue())) {
//            change = true;
//        }
//        if (oldVar_textbox_NikPelaksana !=(textbox_NikPelaksana.getValue())) {
//            change = true;
//        }
        if (oldVar_fckeditor_Solusi != (fckeditor_Solusi.getValue())) {
            change = true;
        }

        return change;
    }

    public void onClick$btn_TambahRootCaused(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pRootCaused", pRootCaused);
        map.put("listbox_RootCaused", listbox_RootCaused);
        map.put("textbox_Type", textbox_Type);

        try {
            Executions.createComponents("/WEB-INF/pages/pengaduan/tambahRootCaused.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    public void onClick$btn_Type(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("textbox_Type", textbox_Type);
        map.put("pRootCaused", pRootCaused);
        //pType sudah di-put:
        map.put("pType", pType);
        map.put("listbox_RootCaused", listbox_RootCaused);

        try {
            Executions.createComponents("/WEB-INF/pages/pengaduan/type.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    private void doSetValidation() {

        setValidationOn(true);

        textbox_NomorTiket.setConstraint(new NoEmptyStringsConstraint());
        texbox_Pelapor.setConstraint(new NoEmptyStringsConstraint());
        texbox_Bagian.setConstraint(new NoEmptyStringsConstraint());
        texbox_Judul.setConstraint(new NoEmptyStringsConstraint());
        textbox_Type.setConstraint(new NoEmptyStringsConstraint());
        combobox_Status.setConstraint(new NoEmptyStringsConstraint());

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

    public PRootCaused getpRootCaused() {
        return pRootCaused;
    }

    public void setpRootCaused(PRootCaused pRootCaused) {
        this.pRootCaused = pRootCaused;
    }

    public PType getpType() {
        return pType;
    }

    public void setpType(PType pType) {
        this.pType = pType;
    }

    public PelaksanaanGangguanService getPelaksanaanGangguanService() {
        return pelaksanaanGangguanService;
    }

    public void setPelaksanaanGangguanService(PelaksanaanGangguanService pelaksanaanGangguanService) {
        this.pelaksanaanGangguanService = pelaksanaanGangguanService;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public RootCausedService getRootCausedService() {
        return rootCausedService;
    }

    public void setRootCausedService(RootCausedService rootCausedService) {
        this.rootCausedService = rootCausedService;
    }
}

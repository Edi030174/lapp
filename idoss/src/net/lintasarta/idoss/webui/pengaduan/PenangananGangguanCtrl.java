package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaComboBoxItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaListModelItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.RootCausedListModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.idoss.webui.util.NoEmptyStringsConstraint;
import net.lintasarta.pengaduan.model.*;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.pengaduan.service.PenangananGangguanService;
import net.lintasarta.pengaduan.service.RootCausedService;
import net.lintasarta.pengaduan.service.TypeService;
import net.lintasarta.security.model.VHrEmployee;
import net.lintasarta.security.util.LoginConstants;
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
import java.util.List;
import java.util.Map;

public class PenangananGangguanCtrl extends GFCBaseCtrl implements Serializable {

    private transient static final Logger logger = Logger.getLogger(PenangananGangguanCtrl.class);

    protected Window window_PenangananGangguan;
    protected Textbox textbox_NomorTiket;
    protected Combobox combobox_NamaPelapor;
    protected Textbox texbox_Bagian;
    protected Textbox texbox_Judul;
    protected Textbox textbox_Type;
    protected FCKeditor fckeditor_Deskripsi;
    protected FCKeditor fckeditor_Solusi;
    protected Radiogroup radiogroup_Dampak;
    protected Listbox listbox_RootCaused;
    protected Listbox listbox_NamaPelaksana;
    protected Combobox combobox_Status;
    protected PenangananGangguanCtrl pelaksanaaGangguanCtrl;
    private transient Listbox listbox_DaftarTiket;
    private transient String oldVar_fckeditor_Solusi;

    private transient boolean validationOn;

    private transient TPenangananGangguan tPenangananGangguan;
    private transient PRootCaused pRootCaused;
    private transient PType pType;

    private transient PelaksanaanGangguanService pelaksanaanGangguanService;
    private transient PenangananGangguanService penangananGangguanService;
    private transient TypeService typeService;
    private transient RootCausedService rootCausedService;
    private transient VHrEmployee employee;

    public PenangananGangguanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("-->" + super.toString());
        }
    }

    public void onCreate$window_PenangananGangguan(Event event) throws Exception {
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

        if (args.containsKey("penangananGangguanCtrl")) {
            pelaksanaaGangguanCtrl = (PenangananGangguanCtrl) args.get("penangananGangguanCtrl");
        } else {
            pelaksanaaGangguanCtrl = null;
        }

        if (args.containsKey("listbox_DaftarTiket")) {
            listbox_DaftarTiket = (Listbox) args.get("listbox_DaftarTiket");
        } else {
            listbox_DaftarTiket = null;
        }
        listbox_NamaPelaksana.setModel(new ListModelList(getPelaksanaanGangguanService().getEmployeeName()));
        listbox_NamaPelaksana.setItemRenderer(new PelaksanaListModelItemRenderer());

        doShowDialog(gettPenangananGangguan());
        doDisplayNama();
    }

    private void doDisplayNama() {
        List<VHrEmployee> nm = getPenangananGangguanService().getEmployeeName();
        ListModelList model = new ListModelList(nm);
        combobox_NamaPelapor.setModel(model);
        combobox_NamaPelapor.setItemRenderer(new PelaksanaComboBoxItemRenderer());
    }

    public void onChange$combobox_NamaPelapor() {
        Comboitem item = combobox_NamaPelapor.getSelectedItem();
        setEmployee((VHrEmployee) item.getValue());
        texbox_Bagian.setValue(getEmployee().getOrganization_code());
    }

    public void onChange$listbox_RootCaused() {
        listbox_RootCaused.getSelectedItem();
    }

    private void doShowDialog(TPenangananGangguan tPenangananGangguan) throws InterruptedException {

        if (tPenangananGangguan == null) {

            tPenangananGangguan = getPenangananGangguanService().getNewPenangananGangguan();
        }
        try {
            doWriteBeanToComponent(tPenangananGangguan);
            window_PenangananGangguan.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponent(TPenangananGangguan tPenangananGangguan) throws Exception {
        textbox_NomorTiket.setValue(getPenangananGangguanService().getTiketId());

        int indexPlks = 0;
        ListModel listPlks = listbox_NamaPelaksana.getModel();
        for (int i = 0; i < listPlks.getSize(); i++) {
            VHrEmployeePelaksana np = (VHrEmployeePelaksana) listPlks.getElementAt(i);
            if (np.getEmployee_no().equals(tPenangananGangguan.getNik_pelaksana())) indexPlks = i;
        }
        listbox_NamaPelaksana.setSelectedIndex(indexPlks);

        if (tPenangananGangguan.getP_idoss_type_id() != null) {
            pType = getTypeService().getTypeByTypeID(tPenangananGangguan.getP_idoss_type_id());

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

    public void onClose$window_PenangananGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        doClose();
    }

    public void onClick$btnSimpan_PenangananGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
        window_PenangananGangguan.onClose();
    }

    public void onClick$btnBatal_PenangananGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        window_PenangananGangguan.onClose();
    }

    private void doSimpan() throws Exception {

        TPenangananGangguan tPenangananGangguan = gettPenangananGangguan();

        if (!isValidationOn()) {
            doSetValidation();
        }

        doWriteComponentsToBean(tPenangananGangguan);

        tPenangananGangguan = gettPenangananGangguan();
        try {
            if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase(LoginConstants.IDOSS_HELPDESK_ADUAN)) {
                tPenangananGangguan.setNik_pelapor(getEmployee().getEmployee_no());
            }
            getPenangananGangguanService().createPenangananGangguan(tPenangananGangguan);
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

    private void doSetValidation() {

        setValidationOn(true);

        texbox_Judul.setConstraint(new NoEmptyStringsConstraint());

//        textbox_Ext.setConstraint( new SimpleConstraint("[0-9-/() ]*", Labels.getLabel("message.error.PhoneNumber")));
    }

    private void doWriteComponentsToBean(TPenangananGangguan tPenangananGangguan) throws Exception {
        tPenangananGangguan.setT_idoss_penanganan_gangguan_id(textbox_NomorTiket.getValue());
        tPenangananGangguan.setNama_pelapor(combobox_NamaPelapor.getValue());
        tPenangananGangguan.setBagian_pelapor(texbox_Bagian.getValue());
        tPenangananGangguan.setNik_pelapor(getEmployee().getEmployee_no());
        tPenangananGangguan.setJudul(texbox_Judul.getValue());
        tPenangananGangguan.setDeskripsi(fckeditor_Deskripsi.getValue());
        tPenangananGangguan.setSolusi(fckeditor_Solusi.getValue());
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
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setCreated_date(ts);
        tPenangananGangguan.setStatus(combobox_Status.getValue());
        if (combobox_Status.getValue().equals("Closed")) {
            tPenangananGangguan.setUpdated_date(ts);
        }
        tPenangananGangguan.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        tPenangananGangguan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());
        settPenangananGangguan(tPenangananGangguan);
    }

    private void doClose() throws Exception {

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
        window_PenangananGangguan.onClose();
    }

    private boolean isDataChanged() throws Exception {
        boolean change = false;

        if (!oldVar_fckeditor_Solusi.equals(fckeditor_Solusi.getValue())) {
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

    public PenangananGangguanService getPenangananGangguanService() {
        return penangananGangguanService;
    }

    public void setPenangananGangguanService(PenangananGangguanService penangananGangguanService) {
        this.penangananGangguanService = penangananGangguanService;
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

    public boolean isValidationOn() {
        return validationOn;
    }

    public void setValidationOn(boolean validationOn) {
        this.validationOn = validationOn;
    }

    public VHrEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(VHrEmployee employee) {
        this.employee = employee;
    }
}

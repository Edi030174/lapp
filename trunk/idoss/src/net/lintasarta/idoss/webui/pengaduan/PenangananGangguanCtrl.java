package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaComboBoxItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaListModelItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.RootCausedListModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.idoss.webui.util.NoEmptyStringsConstraint;
import net.lintasarta.pengaduan.model.*;
import net.lintasarta.pengaduan.service.*;
import net.lintasarta.security.model.VHrEmployee;
import net.lintasarta.security.util.LoginConstants;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
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
    protected Textbox textbox_deskripsi;
    protected Textbox textbox_solusi;
    protected Radiogroup radiogroup_Dampak;
    protected Radio radio_minor;
    protected Radio radio_major;
    protected Listbox listbox_RootCaused;
    protected Listbox listbox_NamaPelaksana;
    protected Combobox combobox_Status;
    protected Datebox datebox_pending;
    protected PenangananGangguanCtrl penangananGangguanCtrl;

    protected Button btn_historyDeskripsi;
    protected Button btn_TambahRootCaused;

    private transient Listbox listbox_DaftarTiket;
    private transient String oldVar_textbox_solusi;
    private transient Window window_Helpdesk;

    private transient boolean validationOn;

    private transient TPenangananGangguan tPenangananGangguan;
    private transient PRootCaused pRootCaused;
    private transient PType pType;

    private transient PelaksanaanGangguanService pelaksanaanGangguanService;
    private transient PenangananGangguanService penangananGangguanService;
    private transient MttrService mttrService;
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
            penangananGangguanCtrl = (PenangananGangguanCtrl) args.get("penangananGangguanCtrl");
        } else {
            penangananGangguanCtrl = null;
        }

        if (args.containsKey("window_Helpdesk")) {
            window_Helpdesk = (Window) args.get("window_Helpdesk");
        } else {
            window_Helpdesk = null;
        }

        if (args.containsKey("listbox_DaftarTiket")) {
            listbox_DaftarTiket = (Listbox) args.get("listbox_DaftarTiket");
        } else {
            listbox_DaftarTiket = null;
        }
        ListModelList lmlNamaPelaksana = new ListModelList(getPelaksanaanGangguanService().getEmployeeName());
        VHrEmployeePelaksana pelaksana = new VHrEmployeePelaksana();
        pelaksana.setEmployee_name("Silakan pilih");
        pelaksana.setEmployee_no("555");
        lmlNamaPelaksana.add(0, pelaksana);
        listbox_NamaPelaksana.setModel(lmlNamaPelaksana);
        listbox_NamaPelaksana.setItemRenderer(new PelaksanaListModelItemRenderer());
        textbox_solusi.setReadonly(true);
        datebox_pending.setVisible(false);
        doShowDialog(gettPenangananGangguan());
        doDisplayNama();
    }

    public void onClick$btnSimpan_PenangananGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        pType = (PType) textbox_Type.getAttribute("pType");

        if (isValidatedFlow()) {
            doSimpan();
            window_PenangananGangguan.onClose();
            Events.postEvent("onCreate", window_Helpdesk, event);
            window_Helpdesk.invalidate();
        }
    }

    public void onClick$btnBatal_PenangananGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        window_PenangananGangguan.onClose();
    }

    public void onClick$btn_historyDeskripsi(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
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
        map.put("btn_TambahRootCaused", btn_TambahRootCaused);

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

    public void onChange$combobox_NamaPelapor() {
        Comboitem item = combobox_NamaPelapor.getSelectedItem();
        setEmployee((VHrEmployee) item.getValue());
        if (getEmployee().getOrganization_code() != null) {
            texbox_Bagian.setValue(getEmployee().getOrganization_code());
        } else {
            combobox_NamaPelapor.setFocus(true);
        }
    }

    public void onChange$combobox_Status() {
        if (combobox_Status.getValue().equals("Open")) {
            textbox_solusi.setReadonly(true);
            textbox_solusi.setValue(tPenangananGangguan.getSolusi());
            datebox_pending.setVisible(false);
        } else if (combobox_Status.getValue().equals("In Progress")) {
            textbox_solusi.setReadonly(true);
            textbox_solusi.setValue(tPenangananGangguan.getSolusi());
            datebox_pending.setVisible(false);
        } else if (combobox_Status.getValue().equals("Pending")) {
            textbox_solusi.setReadonly(true);
            textbox_solusi.setValue(tPenangananGangguan.getSolusi());
            datebox_pending.setVisible(true);
        } else if (combobox_Status.getValue().equals("Selesai")) {
            textbox_solusi.setReadonly(false);
            datebox_pending.setVisible(false);
        }
    }

    public void onChange$listbox_RootCaused() {
        listbox_RootCaused.getSelectedItem();
    }

    public void onSelect$listbox_NamaPelaksana() {
        if (listbox_NamaPelaksana.getSelectedItem().getLabel().equalsIgnoreCase("Silakan pilih")) {
            combobox_Status.setSelectedIndex(0);
        } else {
            if (combobox_Status.getSelectedIndex() != 3) {
                combobox_Status.setSelectedIndex(1);
            }
        }
    }

    public void onClose$window_PenangananGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        doClose();
    }

    private void doDisplayNama() {
        List<VHrEmployee> nm = getPenangananGangguanService().getEmployeeName();
        ListModelList model = new ListModelList(nm);
        combobox_NamaPelapor.setModel(model);
        combobox_NamaPelapor.setItemRenderer(new PelaksanaComboBoxItemRenderer());
    }

    private void doSimpan() throws Exception {

        TPenangananGangguan tPenangananGangguan = gettPenangananGangguan();

        if (!isValidationOn()) {
            doSetValidation();
        }

        Mttr mttr = new Mttr();
        doWriteComponentsToBean(tPenangananGangguan, mttr);

        TDeskripsi tDeskripsi = new TDeskripsi();

        tDeskripsi.setT_idoss_penanganan_gangguan_id(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        if (textbox_deskripsi.getValue() != null)
            tDeskripsi.setDeskripsi(textbox_deskripsi.getValue());
        if (textbox_solusi.getValue() != null)
            tDeskripsi.setSolusi(textbox_solusi.getValue());
        tDeskripsi.setUpdated_by(getUserWorkspace().getUserSession().getUserName());

        try {
            if (getUserWorkspace().getUserSession().getEmployeeRole().equalsIgnoreCase(LoginConstants.IDOSS_HELPDESK_ADUAN)) {
                tPenangananGangguan.setNik_pelapor(getEmployee().getEmployee_no());
            }
            getPenangananGangguanService().createPenangananGangguan(tPenangananGangguan, tDeskripsi, mttr);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
        if (tPenangananGangguan.getStatus().equals("Open")) {
            ListModelList lml = (ListModelList) listbox_DaftarTiket.getListModel();
            if (lml.indexOf(tPenangananGangguan) == -1) {
                lml.add(tPenangananGangguan);
            } else {
                lml.set(lml.indexOf(tPenangananGangguan), tPenangananGangguan);
            }
        }
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
        if (textbox_Type.getValue().length() < 1) {
            btn_TambahRootCaused.setVisible(false);
        }
    }

    private void doWriteBeanToComponent(TPenangananGangguan tPenangananGangguan) throws Exception {
//        textbox_NomorTiket.setValue(getPenangananGangguanService().getTiketId());

        int indexPlks = 0;
        ListModel listPlks = listbox_NamaPelaksana.getModel();
        for (int i = 0; i < listPlks.getSize(); i++) {
            VHrEmployeePelaksana np = (VHrEmployeePelaksana) listPlks.getElementAt(i);
            if (np.getEmployee_no().equals(tPenangananGangguan.getNik_pelaksana())) indexPlks = i;
        }
        listbox_NamaPelaksana.setSelectedIndex(indexPlks);
        combobox_Status.setSelectedIndex(0);
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

    private void doWriteComponentsToBean(TPenangananGangguan tPenangananGangguan, Mttr mttr) throws Exception {
//        tPenangananGangguan.setT_idoss_penanganan_gangguan_id(textbox_NomorTiket.getValue());
        tPenangananGangguan.setNama_pelapor(combobox_NamaPelapor.getValue());
        tPenangananGangguan.setBagian_pelapor(texbox_Bagian.getValue());
        tPenangananGangguan.setNik_pelapor(getEmployee().getEmployee_no());
        tPenangananGangguan.setJudul(texbox_Judul.getValue());
        tPenangananGangguan.setDeskripsi(textbox_deskripsi.getValue());
        tPenangananGangguan.setSolusi(textbox_solusi.getValue());
        Radio dampak = radiogroup_Dampak.getSelectedItem();
        tPenangananGangguan.setDampak(dampak.getValue());
        if (pType != null) {
            tPenangananGangguan.setP_idoss_type_id(pType.getP_idoss_type_id());
        }
        Listitem item = listbox_RootCaused.getSelectedItem();
        if (item != null) {
            ListModelList lml1 = (ListModelList) listbox_RootCaused.getListModel();
            PRootCaused rootCaused = (PRootCaused) lml1.get(item.getIndex());
            tPenangananGangguan.setP_idoss_root_caused_id(rootCaused.getP_idoss_root_caused_id());
        }
        Listitem itempelaksana = listbox_NamaPelaksana.getSelectedItem();
        ListModelList lml3 = (ListModelList) listbox_NamaPelaksana.getListModel();
        VHrEmployeePelaksana vHrEmployeePelaksana = (VHrEmployeePelaksana) lml3.get(itempelaksana.getIndex());
        if (!vHrEmployeePelaksana.getEmployee_name().equalsIgnoreCase("Silakan pilih")) {
            tPenangananGangguan.setNama_pelaksana(vHrEmployeePelaksana.getEmployee_name());
        }
        if (!vHrEmployeePelaksana.getEmployee_no().equalsIgnoreCase("555")) {
            tPenangananGangguan.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());
        }
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setCreated_date(ts);

        tPenangananGangguan.setStatus(combobox_Status.getValue());

        tPenangananGangguan.setUpdated_date(ts);

        tPenangananGangguan.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        tPenangananGangguan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());
        if (combobox_Status.getSelectedIndex() == 1) {
            mttr.setInprogress(ts.getTime());
        } else if (combobox_Status.getSelectedIndex() == 2) {
            long pending_start = ts.getTime();
            mttr.setPending_start(pending_start);
            Timestamp tspending_end = new Timestamp(datebox_pending.getValue().getTime());
            long pending_end = tspending_end.getTime();
            mttr.setPending_end(pending_end);
        } else if (combobox_Status.getSelectedIndex() == 3) {
            mttr.setClosed(ts.getTime());
        }

        settPenangananGangguan(tPenangananGangguan);
    }

    private void doSetValidation() {

        setValidationOn(true);

        combobox_NamaPelapor.setConstraint(new NoEmptyStringsConstraint());
        texbox_Bagian.setConstraint(new NoEmptyStringsConstraint());

        texbox_Judul.setConstraint(new NoEmptyStringsConstraint());
        textbox_Type.setConstraint(new NoEmptyStringsConstraint());
        combobox_Status.setConstraint(new NoEmptyStringsConstraint());


//        textbox_Ext.setConstraint( new SimpleConstraint("[0-9-/() ]*", Labels.getLabel("message.error.PhoneNumber")));
    }

    private void doClose() throws Exception {
        if (combobox_NamaPelapor.getConstraint() != null) {
            combobox_NamaPelapor = new Combobox();
        }
        if (texbox_Bagian.getConstraint() != null) {
            texbox_Bagian = new Textbox();
        }
        if (texbox_Judul.getConstraint() != null) {
            texbox_Judul = new Textbox();
        }
        if (textbox_Type.getConstraint() != null) {
            textbox_Type = new Textbox();
        }
        if (combobox_Status.getConstraint() != null) {
            combobox_Status = new Combobox();
        }

        window_PenangananGangguan.onClose();
    }

    private boolean isValidatedFlow() throws InterruptedException {
        if (combobox_Status.getValue().equalsIgnoreCase("Open")) {
            /* Tidak boleh kosong:
                Nomor Tiket
                Nama Pelapor
                Bagian
                Judul
            */
        } else if (combobox_Status.getValue().equalsIgnoreCase("In Progress")) {
            /* Tidak boleh kosong:
                Nomor Tiket
                Nama Pelapor
                Bagian
                Judul
                Pelaksana
            */
            if (textbox_solusi.getValue().length() > 0) {
                Messagebox.show("Status: In Progress ->solusi tidak boleh diisi. solusi akan dihapus");
                textbox_solusi.setValue("");
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
            if (textbox_solusi.getValue().length() > 0) {
                Messagebox.show("solusi tidak boleh diisi karena Status: In Progress");
                textbox_solusi.setValue("");
                return false;

            }
        } else if (combobox_Status.getValue().equalsIgnoreCase("Selesai")) {
            /* Tidak boleh kosong:
                Nomor Tiket
                Nama Pelapor
                Bagian
                Judul
                Tipe
                Root Caused
                Solusi
            */
            gettPenangananGangguan().setNik_pelaksana(getUserWorkspace().getUserSession().getEmployeeNo());
            gettPenangananGangguan().setNama_pelaksana(getUserWorkspace().getUserSession().getEmployeeName());
            if (textbox_Type.getValue().length() < 1) {
                Messagebox.show("Silakan pilih tipe");
                return false;
            }
            if (listbox_RootCaused.getSelectedItem() == null) {
                Messagebox.show("Silakan pilih root caused");
                return false;
            }
            if (textbox_solusi.getValue().length() < 1) {
                Messagebox.show("Silakan isikan solusi");
                return false;
            }
        }
        return true;
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

    public MttrService getMttrService() {
        return mttrService;
    }

    public void setMttrService(MttrService mttrService) {
        this.mttrService = mttrService;
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
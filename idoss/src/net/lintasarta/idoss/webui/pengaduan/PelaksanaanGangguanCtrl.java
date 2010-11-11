package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaListModelItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.RootCausedListModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.idoss.webui.util.NoEmptyStringsConstraint;
import net.lintasarta.pengaduan.model.*;
import net.lintasarta.pengaduan.service.MttrService;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.pengaduan.service.RootCausedService;
import net.lintasarta.pengaduan.service.TypeService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    protected Textbox textbox_deskripsi;
    protected Textbox textbox_deskripsibaru;
    protected Textbox textbox_solusi;//pelaksana hanya status & solusi yg enable
    protected Label label_Target;
    protected Radiogroup radiogroup_Dampak;
    protected Radio radio_minor;
    protected Radio radio_major;
    protected Listbox listbox_RootCaused;
    protected Listbox listbox_NamaPelaksana;
    protected Combobox combobox_Status;//pelaksana hanya status & solusi yg enable
    protected Datebox datebox_pending;
    protected Button btn_TambahRootCaused;
    protected Button btnSimpan_PelaksanaanGangguan;//monitoring disable all
    protected Button btn_historyDeskripsi;
    protected PelaksanaanGangguanCtrl pelaksanaanGangguanCtrl;
    private transient boolean validationOn;
    private transient Window window_Helpdesk;
    private transient Listbox listbox_DaftarTiket;
    private transient String oldVar_textbox_Pelaksana;
    private transient String oldVar_textbox_NikPelaksana;
    private transient String oldVar_textbox_solusi;
    private transient String oldVar_radiogroup_Dampak;
    private transient String oldVar_combobox_Type;
    private transient String oldVar_combobox_RootCaused;
    private transient String oldVar_combobox_Status;
    private transient TPenangananGangguan tPenangananGangguan;
    private transient Mttr mttr;
    private transient PRootCaused pRootCaused;
    private transient PType pType;
    private transient PelaksanaanGangguanService pelaksanaanGangguanService;
    private transient TypeService typeService;
    private transient RootCausedService rootCausedService;
    private transient MttrService mttrService;

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
            pelaksanaanGangguanCtrl = (PelaksanaanGangguanCtrl) args.get("pelaksanaanGangguanCtrl");
        } else {
            pelaksanaanGangguanCtrl = null;
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
        List<Mttr> mttrs = mttrService.getMttrByNomorTiket(gettPenangananGangguan().getT_idoss_penanganan_gangguan_id());
        for (Mttr mttr1 : mttrs) {
            setMttr(mttr1);
        }
        doShowDialog(gettPenangananGangguan());
    }

    private void doCheckRights() {
        UserWorkspace workspace = getUserWorkspace();
        btnSimpan_PelaksanaanGangguan.setVisible(workspace.isAllowed("btnSimpan_PelaksanaanGangguan"));


    }

    public void onClick$btnSimpan_PelaksanaanGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        pType = (PType) textbox_Type.getAttribute("pType");

        if (isValidatedFlow()) {
            doSimpan();
            window_PelaksanaanGangguan.onClose();
            Events.postEvent("onCreate", window_Helpdesk, event);
            window_Helpdesk.invalidate();
        }
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
        map.put("pType", pType);
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

    public void onChange$listbox_RootCaused() {
        listbox_RootCaused.getSelectedItem();
    }

    public void onChange$combobox_Status() {
        if (combobox_Status.getValue().equals("Open")) {
            textbox_solusi.setReadonly(true);
            textbox_solusi.setValue(tPenangananGangguan.getSolusi());
            datebox_pending.setVisible(false);
            label_Target.setVisible(false);
        } else if (combobox_Status.getValue().equals("In Progress")) {
            textbox_solusi.setReadonly(true);
            textbox_solusi.setValue(tPenangananGangguan.getSolusi());
            datebox_pending.setVisible(false);
            label_Target.setVisible(false);
        } else if (combobox_Status.getValue().equals("Pending")) {
            textbox_solusi.setReadonly(true);
            textbox_solusi.setValue(tPenangananGangguan.getSolusi());
            datebox_pending.setVisible(true);
            label_Target.setVisible(true);
        } else if (combobox_Status.getValue().equals("Selesai")) {
            textbox_solusi.setReadonly(false);
            datebox_pending.setVisible(false);
            label_Target.setVisible(false);
        }
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

    public void onClose$window_PelaksanaanGangguan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        doClose();
    }

    private void doSimpan() throws Exception {

        TPenangananGangguan tPenangananGangguan = gettPenangananGangguan();
        Mttr mttr = getMttr();
        if (!isValidationOn()) {
            doSetValidation();
        }

        doWriteComponentsToBean(tPenangananGangguan, mttr);

        TDeskripsi tDeskripsi = new TDeskripsi();
        tDeskripsi.setT_idoss_penanganan_gangguan_id(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        if (textbox_deskripsibaru.getValue() != null)
            tDeskripsi.setDeskripsi(textbox_deskripsibaru.getValue());
        if (textbox_solusi.getValue() != null)
            tDeskripsi.setSolusi(textbox_solusi.getValue());
        tDeskripsi.setUpdated_by(getUserWorkspace().getUserSession().getUserName());
        try {
            TPenangananGangguan tp = getPelaksanaanGangguanService().getTPenangananGangguanByTiketId(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
            if (!tp.getStatus().equalsIgnoreCase("Selesai")) {
                getPelaksanaanGangguanService().saveOrUpdate(tPenangananGangguan, tDeskripsi);
            } else {
                alert("Status sudah Selesai!");
            }
            mttr.setNomor_tiket(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
            getMttrService().saveOrUpdateMttr(mttr);
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
        if (textbox_Type.getValue().length() < 1) {
            btn_TambahRootCaused.setVisible(false);
        }
    }

    private void doWriteBeanToComponent(TPenangananGangguan tPenangananGangguan) throws Exception {
        textbox_NomorTiket.setValue(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        texbox_Pelapor.setValue(tPenangananGangguan.getNama_pelapor());
        texbox_Bagian.setValue(tPenangananGangguan.getBagian_pelapor());
        texbox_Judul.setValue(tPenangananGangguan.getJudul());
        combobox_Status.setValue(tPenangananGangguan.getStatus());
        if (tPenangananGangguan.getStatus().equalsIgnoreCase("Pending")) {
            datebox_pending.setVisible(true);
            label_Target.setVisible(true);
        } else {
            datebox_pending.setVisible(false);
            label_Target.setVisible(false);
        }
        if (tPenangananGangguan.getStatus().equals("Selesai")) {
            textbox_solusi.setReadonly(false);
        }
        if (tPenangananGangguan.getDampak().equals("Mayor")) {
            radiogroup_Dampak.setSelectedItem(radio_major);
        } else {
            radiogroup_Dampak.setSelectedItem(radio_minor);
        }
        textbox_deskripsi.setValue(tPenangananGangguan.getDeskripsi());
        textbox_solusi.setValue(tPenangananGangguan.getSolusi());
        if (mttr.getPending_end() == 0) {
            datebox_pending.setValue(tPenangananGangguan.getCreated_date());
        } else {
            Timestamp ts = new Timestamp(mttr.getPending_end());
            datebox_pending.setValue(ts);
        }
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
            String x = tPenangananGangguan.getP_idoss_type_id();    //4011
            String y = x.substring(0, x.length() - 1);              //401
            String z = y.substring(0, y.length() - 2);              //4
            PType pType1 = getTypeService().getTypeByTypeID(x);
            PType pType2 = getTypeService().getTypeByTypeID(y);
            PType pType3 = getTypeService().getTypeByTypeID(z);
            String xx = pType1.getType_desc();
            String yy = pType2.getType_desc();
            String zz = pType3.getType_desc();

            textbox_Type.setValue(zz + " - " + yy + " - " + xx);
//            textbox_Type.setValue(tPenangananGangguan.getP_idoss_type_id());
//            textbox_Type.setValue(pType.getType_desc());

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
        if (textbox_deskripsibaru.getValue().length() > 1) {
            tPenangananGangguan.setDeskripsi(textbox_deskripsibaru.getValue());
        }
        if (textbox_solusi.getValue() != null) {
            tPenangananGangguan.setSolusi(textbox_solusi.getValue());
        }
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
        tPenangananGangguan.setStatus(combobox_Status.getValue());
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setUpdated_date(ts);
        tPenangananGangguan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());

        if (combobox_Status.getSelectedIndex() == 1) {
            long tsLong = ts.getTime();
            if (mttr.getInprogress() > 0) {
                mttr.setPending_end(tsLong);
            }
            mttr.setInprogress(tsLong);
        } else if (combobox_Status.getSelectedIndex() == 2) {
            if (mttr.getPending_end() > 0) {
                long lama_pending = mttr.getLama_pending();
                if (ts.getTime() < mttr.getPending_end()) {
                    mttr.setLama_pending(lama_pending + ts.getTime() - mttr.getPending_start());
                } else {
                    mttr.setLama_pending(lama_pending + mttr.getPending_end() - mttr.getPending_start());
                }
            }
            long pending_start = ts.getTime();
            mttr.setPending_start(pending_start);
            Timestamp tspending_end = new Timestamp(datebox_pending.getValue().getTime());
            long pending_end = tspending_end.getTime();
            mttr.setPending_end(pending_end);
        } else if (combobox_Status.getSelectedIndex() == 3) {
            mttr.setClosed(ts.getTime());
        }
    }

    private void doClose() throws Exception {
        if (texbox_Pelapor.getConstraint() != null) {
            texbox_Pelapor = new Textbox();
        }
        if (texbox_Bagian.getConstraint() != null) {
            texbox_Bagian = new Textbox();
        }

        window_PelaksanaanGangguan.onClose();
    }

    private boolean isValidatedFlow() throws InterruptedException {
        if (combobox_Status.getValue().equalsIgnoreCase("Open")) {
            /* Tidak boleh kosong:
                Nomor Tiket
                Nama Pelapor
                Bagian
                Judul
            */
            return true;
        } else if (combobox_Status.getValue().equalsIgnoreCase("In Progress")) {
            /* Tidak boleh kosong:
                Nomor Tiket
                Nama Pelapor
                Bagian
                Judul
                Pelaksana
            */
            if (listbox_NamaPelaksana.getSelectedItem().getLabel().equalsIgnoreCase("Silakan pilih")) {
                Messagebox.show("Silakan pilih nama pelaksana");
                return false;
            }
            if (listbox_NamaPelaksana.getSelectedItem() == null) {
                Messagebox.show("Silakan pilih nama pelaksana");
                return false;
            }
        } else if (combobox_Status.getValue().equalsIgnoreCase("Pending")) {
            if (datebox_pending.getValue() != null) {
                if (datebox_pending.getValue().before(new Date())) {
                    alert("End Date must be equal or bigger than this date");
                    datebox_pending.setValue(new Date());
                    return false;
                }
            }
        } else if (combobox_Status.getValue().equalsIgnoreCase("Selesai")) {
            TPenangananGangguan tPenangananGangguan = gettPenangananGangguan();
            if (tPenangananGangguan.getStatus().equalsIgnoreCase("Pending")) {
                Messagebox.show("Status Pending tidak boleh langsung Selesai, harus melalui In Progress");
                return false;
            }
//            if (listbox_NamaPelaksana.getSelectedItem().getLabel().equalsIgnoreCase("Silakan pilih")) {
//                Messagebox.show("Silakan pilih nama pelaksana");
//                return false;
//            }
//            if (listbox_NamaPelaksana.getSelectedItem() == null) {
//                Messagebox.show("Silakan pilih nama pelaksana");
//                return false;
//            }
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

    public Mttr getMttr() {
        return mttr;
    }

    public void setMttr(Mttr mttr) {
        this.mttr = mttr;
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

    public MttrService getMttrService() {
        return mttrService;
    }

    public void setMttrService(MttrService mttrService) {
        this.mttrService = mttrService;
    }
}
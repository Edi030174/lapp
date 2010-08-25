package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.pengaduan.model.PelaksanaListModelItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.RootCausedListModelItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.TypeListModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import java.io.Serializable;
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
//    protected Textbox textbox_Pelaksana;
//    protected Textbox textbox_NikPelaksana;
    protected Textbox textbox_Type;
    protected FCKeditor fckeditor_Deskripsi;
    protected FCKeditor fckeditor_Solusi;
    protected Radiogroup radiogroup_Dampak;
//    protected Radio radio_minor;
//    protected Radio radio_mayor;
//    protected Listbox listbox_Type;
    protected Listbox listbox_RootCaused;
    protected Listbox listbox_NamaPelaksana;
    protected Combobox combobox_Status;
    protected Button btn_TambahRootCaused;
//    protected Button btnSimpan_RootCaused;
//    protected Button btnBatal_RootCaused;
//    protected Button btnSimpan_PelaksanaanGangguan;
//    protected Button btnBatal_PelaksanaanGangguan;

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

        listbox_RootCaused.setModel(new ListModelList(getPelaksanaanGangguanService().getRootCaused()));
        listbox_RootCaused.setItemRenderer(new RootCausedListModelItemRenderer());

        listbox_NamaPelaksana.setModel(new ListModelList(getPelaksanaanGangguanService().getEmployeeName()));
        listbox_NamaPelaksana.setItemRenderer(new PelaksanaListModelItemRenderer());

        ListModelList lml = (ListModelList)listbox_NamaPelaksana.getModel();
        String vHrEmployeePelaksana = tPenangananGangguan.getNama_pelaksana();
        listbox_NamaPelaksana.setSelectedIndex(lml.indexOf(vHrEmployeePelaksana));

        doShowDialog(gettPenangananGangguan());
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

    private void doSimpan() throws Exception {

        TPenangananGangguan tPenangananGangguan = gettPenangananGangguan();

        doWriteComponentsToBean(tPenangananGangguan);

        try {
            getPelaksanaanGangguanService().saveOrUpdate(tPenangananGangguan);
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
            window_PelaksanaanGangguan.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private void doWriteBeanToComponent(TPenangananGangguan tPenangananGangguan) throws Exception {

        textbox_NomorTiket.setValue(tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        texbox_Pelapor.setValue(tPenangananGangguan.getNama_pelapor());
        texbox_Bagian.setValue(tPenangananGangguan.getBagian_pelapor());
        texbox_Judul.setValue(tPenangananGangguan.getJudul());
        textbox_Type.setValue("401");
        fckeditor_Deskripsi.setValue(tPenangananGangguan.getDeskripsi());
        fckeditor_Solusi.setValue(tPenangananGangguan.getSolusi());
        combobox_Status.setValue(tPenangananGangguan.getStatus());

    }

    private void doWriteComponentsToBean(TPenangananGangguan tPenangananGangguan) throws Exception {
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
            return ;
        }
        ListModelList lml1 = (ListModelList)listbox_RootCaused.getListModel();
        PRootCaused rootCaused = (PRootCaused)lml1.get(item.getIndex());
        tPenangananGangguan.setRoot_cause_id(rootCaused.getP_idoss_root_caused_id());

//        Listitem item1 = listbox_Type.getSelectedItem();
//        if (item == null) {
//            try {
//                Messagebox.show("Silakan pilih Type!");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return ;
//        }
//        ListModelList lml2 = (ListModelList)listbox_Type.getListModel();
//        PType type = (PType)lml2.get(item1.getIndex());
//        tPenangananGangguan.setType_id(type.getP_idoss_type_id());

        Listitem itempelaksana = listbox_NamaPelaksana.getSelectedItem();
        if (itempelaksana == null) {
            try {
                Messagebox.show("Silakan pilih Pelaksana!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ;
        }
        ListModelList lml3 = (ListModelList)listbox_NamaPelaksana.getListModel();
        VHrEmployeePelaksana vHrEmployeePelaksana = (VHrEmployeePelaksana)lml3.get(itempelaksana.getIndex());
        tPenangananGangguan.setNama_pelaksana(vHrEmployeePelaksana.getEmployee_name());
        tPenangananGangguan.setNik_pelaksana(vHrEmployeePelaksana.getEmployee_no());

        tPenangananGangguan.setStatus(combobox_Status.getValue());
        tPenangananGangguan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());

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

    public void onClick$btn_Type(Event event)throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        HashMap<String, Object> map = new HashMap<String, Object>();

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

    public void onChange$textbox_Type(Event event) throws Exception{
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        textbox_Type.getValue();

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

    public PelaksanaanGangguanService getPelaksanaanGangguanService() {
        return pelaksanaanGangguanService;
    }

    public void setPelaksanaanGangguanService(PelaksanaanGangguanService pelaksanaanGangguanService) {
        this.pelaksanaanGangguanService = pelaksanaanGangguanService;
    }
}

package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.permohonan.model.DaftarPermohonanModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseListCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.predicate.IdTPermohonan;
import net.lintasarta.permohonan.model.predicate.StatusPermohonan;
import net.lintasarta.permohonan.model.predicate.TanggalPermohonan;
import net.lintasarta.permohonan.model.predicate.TipePermohonan;
import net.lintasarta.permohonan.service.PermohonanService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.component.Component;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.support.PagedListHolder;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: JosH
 * Date: Jul 14, 2010
 * Time: 2:49:58 PM
 */
public class DaftarPermohonanCtrl extends GFCBaseListCtrl<TPermohonan> implements Serializable {
    private transient static final Logger logger = Logger.getLogger(DaftarPermohonanCtrl.class);

    protected Window window_DaftarPermohonan;
    protected Window window_PersetujuanGmPemohon;
    protected Paging paging_DaftarPermohonan;
    protected Listbox listbox_DaftarPermohonan;
    protected Listheader listheader_Nomor;
    protected Listheader listheader_Tanggal;
    protected Listheader listheader_Tipe;
    protected Listheader listheader_StatusPersetujuan;
    protected Button btnBuatBaru_DaftarPermohonan;
    protected Hbox idHboxTanggal;
    protected Datebox datebox_TanggalAwal;
    protected Datebox datebox_TanggalAkhir;
    protected Checkbox checkbox_all;
    protected Checkbox checkbox_readonly;
    protected Checkbox checkbox_readwrite;
    protected Checkbox checkbox_aplikasi;

    protected Textbox textbox_cariPermohonanId;

//    protected Listheader listheader_Pimbag;
//    protected Listheader listheader_Pimdiv;
    protected Listheader listheader_AssMgr;
    protected Listheader listheader_Mgr;
    protected Listheader listheader_GM;

    protected Listbox listbox_Cari;
    protected Listitem listitem_Nomor;
    protected Listitem listitem_Tanggal;
    protected Listitem listitem_Tipe;
    protected Listitem listitem_Status;


    protected Button btnCari;
    protected Borderlayout borderlayout_daftarPermohonan;
    protected Panel panel_daftarPermohonan;

    private int countRows;

    private transient PermohonanService permohonanService;

    public DaftarPermohonanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_DaftarPermohonan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doCheckRights();
        doHideTanggal();

        int panelHeight = 25;
        /*TODO put the logic for working with panel in the ApplicationWorkspace*/
        boolean withPanel = false;
        if (withPanel == false) {
            panel_daftarPermohonan.setVisible(false);
        } else {
            panel_daftarPermohonan.setVisible(true);
            panelHeight = 0;
        }

        int height = ((Intbox) Path.getComponent("/outerIndexWindow/currentDesktopHeight")).getValue();
        height = height + panelHeight;
        int maxListBoxHeight = (height - 170);
        setCountRows(Math.round(maxListBoxHeight / 17));
        borderlayout_daftarPermohonan.setHeight(String.valueOf(maxListBoxHeight) + "px");

        paging_DaftarPermohonan.setPageSize(getCountRows());
        paging_DaftarPermohonan.setDetailed(true);

        checkbox_all.setChecked(true);
        listheader_Nomor.setSortDescending(new FieldComparator("t_idoss_permohonan_id", true));
        listheader_Nomor.setSortAscending(new FieldComparator("t_idoss_permohonan_id", true));
        listheader_Tanggal.setSortDescending(new FieldComparator("tgl_permohonan", true));
        listheader_Tanggal.setSortAscending(new FieldComparator("tgl_permohonan", true));
        listheader_Tipe.setSortDescending(new FieldComparator("type_permohonan", true));
        listheader_Tipe.setSortAscending(new FieldComparator("type_permohonan", true));
        listheader_StatusPersetujuan.setSortDescending(new FieldComparator("status_track_permohonan", true));
        listheader_StatusPersetujuan.setSortAscending(new FieldComparator("status_track_permohonan", true));
//        listheader_Pimbag.setSortAscending(new FieldComparator("", true));
//        listheader_Pimbag.setSortDescending(new FieldComparator("", true));
//        listheader_Pimdiv.setSortAscending(new FieldComparator("nama_divisi", true));
//        listheader_Pimdiv.setSortDescending(new FieldComparator("nama_divisi", true));
        listheader_AssMgr.setSortDescending(new FieldComparator("nama_asman", true));
        listheader_AssMgr.setSortAscending(new FieldComparator("nama_asman", true));
        listheader_Mgr.setSortDescending(new FieldComparator("nama_manager", true));
        listheader_Mgr.setSortAscending(new FieldComparator("nama_manager", true));
        listheader_GM.setSortDescending(new FieldComparator("nama_gm", true));
        listheader_GM.setSortAscending(new FieldComparator("nama_gm", true));

        List<TPermohonan> tPermohonans = getPermohonanService().getAllTPermohonan();
        PagedListHolder<TPermohonan> pagedListHolder = new PagedListHolder<TPermohonan>(tPermohonans);
        pagedListHolder.setPageSize(getCountRows());

        paging_DaftarPermohonan.setPageSize(getCountRows());
        paging_DaftarPermohonan.setDetailed(true);

        getPagedListWrapper().init(pagedListHolder, listbox_DaftarPermohonan, paging_DaftarPermohonan);
        listbox_DaftarPermohonan.setItemRenderer(new DaftarPermohonanModelItemRenderer());
    }

    private void doCheckRights() {
        UserWorkspace workspace = getUserWorkspace();
        btnBuatBaru_DaftarPermohonan.setVisible(workspace.isAllowed("btnBuatBaru_DaftarPermohonan"));


    }

    public void onPermohonanItemDoubleClicked(Event event) throws Exception {

        Listitem item = listbox_DaftarPermohonan.getSelectedItem();

        if (item != null) {
            TPermohonan tPermohonan = (TPermohonan) item.getAttribute("data");

            if (logger.isDebugEnabled()) {
                logger.debug("--> " + tPermohonan.getT_idoss_permohonan_id());
            }
            showDetailView(tPermohonan);
        }
    }

    public void onClick$btnBuatBaru_DaftarPermohonan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        TPermohonan tPermohonan = getPermohonanService().getNewPermohonan();
        showDetailViewPermohonanBaru(tPermohonan);
    }

    private void showDetailViewPermohonanBaru(TPermohonan tPermohonan) throws InterruptedException {
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("tPermohonan", tPermohonan);

//        map.put("DaftarPermohonanCtrl", this);

        map.put("listbox_DaftarPermohonan", listbox_DaftarPermohonan);

        try {
            Executions.createComponents("/WEB-INF/pages/permohonan/permohonanBaru.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);

        }
    }

    public void onSelect$listbox_Cari(Event event)throws Exception{
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        int hierarchy = Integer.parseInt(listbox_Cari.getSelectedItem().getValue().toString());
        doShowBerdasarkan(hierarchy);
    }

    private void doShowBerdasarkan(int hierarchy){
        switch(hierarchy){
            case 1 :{

               doHideTanggal();
                break;
            }
            case 2 :{
               doHideTanggal();
                break;
            }
            case 3 :{
               doHideTanggal();
                break;
            }
            case 4 :{
               doViewTanggal();
                break;
            }
        }
    }

    public void onClick$btnReport(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        try {
            doPrintReport();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doPrintReport() throws InterruptedException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> begin with printing");
        }

        try {
            Executions.createComponents("/WEB-INF/pages/permohonan/permohonanBaru.zul", null, null);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    public void onClick$btnRefresh(Event event) throws InterruptedException {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Events.postEvent("onCreate", window_DaftarPermohonan, event);
        window_DaftarPermohonan.invalidate();
    }

    public void onClick$btnCari(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        // if not empty
        List searchResult = getPagedListWrapper().getPagedListHolder().getSource();
        if (!textbox_cariPermohonanId.getValue().isEmpty()) {
            if (listbox_Cari.getSelectedItem() == listitem_Nomor) {
                CollectionUtils.filter(searchResult, new IdTPermohonan(textbox_cariPermohonanId.getValue()));
            } else if (listbox_Cari.getSelectedItem() == listitem_Tipe) {
                CollectionUtils.filter(searchResult, new TipePermohonan(textbox_cariPermohonanId.getValue()));
            } else if (listbox_Cari.getSelectedItem() == listitem_Status) {
                CollectionUtils.filter(searchResult, new StatusPermohonan(textbox_cariPermohonanId.getValue()));
            }
        } else {
            if (datebox_TanggalAkhir.getValue() != null) {
                if (datebox_TanggalAwal.getValue() != null) {
                    CollectionUtils.filter(searchResult, new TanggalPermohonan(datebox_TanggalAwal.getValue(), datebox_TanggalAkhir.getValue()));
                }
            }
        }
        PagedListHolder<TPermohonan> pagedListHolder = new PagedListHolder<TPermohonan>(searchResult);
        pagedListHolder.setPageSize(getCountRows());

        getPagedListWrapper().init(pagedListHolder, listbox_DaftarPermohonan, paging_DaftarPermohonan);
        checkbox_all.setChecked(false); // unCheck
    }

    public void onOK$textbox_cariPermohonanId(Event event) {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        List searchResult = getPagedListWrapper().getPagedListHolder().getSource();
        if (!textbox_cariPermohonanId.getValue().isEmpty()) {
            if (listbox_Cari.getSelectedItem() == listitem_Nomor) {
                CollectionUtils.filter(searchResult, new IdTPermohonan(textbox_cariPermohonanId.getValue()));
            } else if (listbox_Cari.getSelectedItem() == listitem_Tipe) {
                CollectionUtils.filter(searchResult, new TipePermohonan(textbox_cariPermohonanId.getValue()));
            } else if (listbox_Cari.getSelectedItem() == listitem_Status) {
                CollectionUtils.filter(searchResult, new StatusPermohonan(textbox_cariPermohonanId.getValue()));
            }
        } else {
            if (datebox_TanggalAkhir.getValue() != null) {
                if (datebox_TanggalAwal.getValue() != null) {
                    CollectionUtils.filter(searchResult, new TanggalPermohonan(datebox_TanggalAwal.getValue(), datebox_TanggalAkhir.getValue()));
                }
            }
        }
        PagedListHolder<TPermohonan> pagedListHolder = new PagedListHolder<TPermohonan>(searchResult);
        pagedListHolder.setPageSize(getCountRows());

        getPagedListWrapper().init(pagedListHolder, listbox_DaftarPermohonan, paging_DaftarPermohonan);
        checkbox_all.setChecked(false);

    }

    public void onTimer$timer(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Events.postEvent("onCreate", window_DaftarPermohonan, event);
        window_DaftarPermohonan.invalidate();
    }

    public void onCheck$checkbox_all(Event event) {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        textbox_cariPermohonanId.setValue("");

        List<TPermohonan> tPermohonans = getPermohonanService().getAllTPermohonan();
        PagedListHolder<TPermohonan> pagedListHolder = new PagedListHolder<TPermohonan>(tPermohonans);
        pagedListHolder.setPageSize(getCountRows());

        getPagedListWrapper().init(pagedListHolder, listbox_DaftarPermohonan, paging_DaftarPermohonan);

    }

    public void showDetailView(TPermohonan tPermohonan) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("tPermohonan", tPermohonan);

        map.put("DaftarPermohonanCtrl", this);

        map.put("listbox_DaftarPermohonan", listbox_DaftarPermohonan);

        map.put("window_DaftarPermohonan", window_DaftarPermohonan);

        map.put("window_PersetujuanGmPemohon", window_PersetujuanGmPemohon);

        try {

            Executions.createComponents("/WEB-INF/pages/permohonan/permohonan.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);

        }
    }

    private void doViewTanggal(){
        idHboxTanggal.setVisible(true);
    }

    private void doHideTanggal(){
        idHboxTanggal.setVisible(false);
    }

    public int getCountRows() {
        return countRows;
    }

    public void setCountRows(int countRows) {
        this.countRows = countRows;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }
}
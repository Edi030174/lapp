package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.webui.permohonan.model.DaftarPermohonanModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseListCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.model.VerifikasiPermohonan;
import net.lintasarta.permohonan.model.predicate.*;
import net.lintasarta.permohonan.service.PermohonanService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.support.PagedListHolder;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: JosH
 * Date: Jul 14, 2010
 * Time: 2:49:58 PM
 */
public class DaftarPermohonanPelaksanaCtrl extends GFCBaseListCtrl<TPermohonan> implements Serializable {
    private transient static final Logger logger = Logger.getLogger(DaftarPermohonanPelaksanaCtrl.class);

    protected Window window_DaftarPermohonanPelaksana;
    protected Window window_PersetujuanGmPemohon;
    protected Paging paging_DaftarPermohonanPelaksana;
    protected Listbox listbox_DaftarPermohonanPelaksana;
    protected Listheader listheader_Nomor;
    protected Listheader listheader_Tanggal;
    protected Listheader listheader_Dampak;
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
    protected Listheader listheader_Durasi;
    protected Listheader listheader_MTTR;
    protected Listheader listheader_TglStatus;
    protected Listheader listheader_Mgr;
    protected Listheader listheader_GM;

    protected Combobox listbox_Cari;


    protected Button btnCari;
    protected Borderlayout borderlayout_daftarPermohonan;
    protected Panel panel_daftarPermohonanPelaksana;

    private int countRows;

    private transient PermohonanService permohonanService;
    private transient PelaksanaanGangguanService pelaksanaanGangguanService;

    public DaftarPermohonanPelaksanaCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_DaftarPermohonanPelaksana(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doCheckRights();

        int panelHeight = 25;
        /*TODO put the logic for working with panel in the ApplicationWorkspace*/
        boolean withPanel = false;
        if (withPanel == false) {
            panel_daftarPermohonanPelaksana.setVisible(false);
        } else {
            panel_daftarPermohonanPelaksana.setVisible(true);
            panelHeight = 0;
        }

        int height = ((Intbox) Path.getComponent("/outerIndexWindow/currentDesktopHeight")).getValue();
        height = height + panelHeight;
        int maxListBoxHeight = (height - 170);
        setCountRows(Math.round(maxListBoxHeight / 34));
        borderlayout_daftarPermohonan.setHeight(String.valueOf(maxListBoxHeight) + "px");

        paging_DaftarPermohonanPelaksana.setPageSize(getCountRows());
        paging_DaftarPermohonanPelaksana.setDetailed(true);

        checkbox_all.setChecked(true);
        listheader_Nomor.setSortDescending(new FieldComparator("t_idoss_permohonan_id", true));
        listheader_Nomor.setSortAscending(new FieldComparator("t_idoss_permohonan_id", true));
        listheader_Tanggal.setSortDescending(new FieldComparator("tgl_permohonan", true));
        listheader_Tanggal.setSortAscending(new FieldComparator("tgl_permohonan", true));
        listheader_Dampak.setSortDescending(new FieldComparator("dampak", true));
        listheader_Dampak.setSortAscending(new FieldComparator("dampak", true));
        listheader_Tipe.setSortDescending(new FieldComparator("type_permohonan", true));
        listheader_Tipe.setSortAscending(new FieldComparator("type_permohonan", true));
        listheader_StatusPersetujuan.setSortDescending(new FieldComparator("status_track_permohonan", true));
        listheader_StatusPersetujuan.setSortAscending(new FieldComparator("status_track_permohonan", true));
        listheader_TglStatus.setSortDescending(new FieldComparator("updated_date", true));
        listheader_TglStatus.setSortAscending(new FieldComparator("updated_date", true));
        listheader_Durasi.setSortAscending(new FieldComparator("durasi", true));
        listheader_Durasi.setSortDescending(new FieldComparator("durasi", false));
        listheader_MTTR.setSortAscending(new FieldComparator("mttr", true));
        listheader_MTTR.setSortDescending(new FieldComparator("mttr", false));
        listheader_Mgr.setSortDescending(new FieldComparator("nama_manager", true));
        listheader_Mgr.setSortAscending(new FieldComparator("nama_manager", true));
        listheader_GM.setSortDescending(new FieldComparator("nama_gm", true));
        listheader_GM.setSortAscending(new FieldComparator("nama_gm", true));

        List<TPermohonan> tPermohonans;

        TPermohonan tPermohonan = new TPermohonan();
        TVerifikasi tVerifikasi = new TVerifikasi();

        VerifikasiPermohonan verifikasiPermohonan = new VerifikasiPermohonan();

        String employeeNo = getUserWorkspace().getUserSession().getEmployeeNo();
        verifikasiPermohonan.setNik_pelaksana(employeeNo);
        verifikasiPermohonan.setStatus_track_permohonan("INPROGRESS");

        tPermohonans = getPermohonanService().getTPermohonanByNikPelaksana(verifikasiPermohonan);
        
        verifikasiPermohonan.setNik_pelaksana(employeeNo);
        verifikasiPermohonan.setStatus_track_permohonan("PENDING");
        tPermohonans.addAll(getPermohonanService().getTPermohonanByNikPelaksana(verifikasiPermohonan));

        PagedListHolder<TPermohonan> pagedListHolder = new PagedListHolder<TPermohonan>(tPermohonans);
        pagedListHolder.setPageSize(getCountRows());

        paging_DaftarPermohonanPelaksana.setPageSize(getCountRows());
        paging_DaftarPermohonanPelaksana.setDetailed(true);

        getPagedListWrapper().init(pagedListHolder, listbox_DaftarPermohonanPelaksana, paging_DaftarPermohonanPelaksana);
        listbox_DaftarPermohonanPelaksana.setItemRenderer(new DaftarPermohonanModelItemRenderer());
    }

    private void doCheckRights() {
        UserWorkspace workspace = getUserWorkspace();
        btnBuatBaru_DaftarPermohonan.setVisible(false);


    }

    public void onPermohonanItemDoubleClicked(Event event) throws Exception {

        Listitem item = listbox_DaftarPermohonanPelaksana.getSelectedItem();

        if (item != null) {
            TPermohonan tPermohonan = (TPermohonan) item.getAttribute("data");

            if (logger.isDebugEnabled()) {
                logger.debug("--> " + tPermohonan.getT_idoss_permohonan_id());
            }
            showDetailView(tPermohonan);
        }
    }

    private void showDetailViewPermohonanBaru(TPermohonan tPermohonan) throws InterruptedException {
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("tPermohonan", tPermohonan);

//        map.put("DaftarPermohonanCtrl", this);

        map.put("listbox_DaftarPermohonanPelaksana", listbox_DaftarPermohonanPelaksana);

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

    public void onClick$btnRefresh(Event event) throws InterruptedException {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Events.postEvent("onCreate", window_DaftarPermohonanPelaksana, event);
        window_DaftarPermohonanPelaksana.invalidate();
    }

    public void onClick$btnCari(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        // if not empty
        List searchResult = getPagedListWrapper().getPagedListHolder().getSource();
        PagedListHolder<TPermohonan> pagedListHolder;

        if (datebox_TanggalAkhir.getValue() != null) {
            if (datebox_TanggalAwal.getValue() != null) {
                CollectionUtils.filter(searchResult, new TanggalPermohonan(datebox_TanggalAwal.getValue(), datebox_TanggalAkhir.getValue()));
            }
        }

        if (!textbox_cariPermohonanId.getValue().isEmpty()) {
            if (listbox_Cari.getValue().equalsIgnoreCase("All")) {

                Set searchAllResult = new HashSet();
                CollectionUtils.select(searchResult, new IdTPermohonan(textbox_cariPermohonanId.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new TipePermohonan(textbox_cariPermohonanId.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new TipePermohonan(textbox_cariPermohonanId.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new StatusPermohonan(textbox_cariPermohonanId.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new DampakPermohonan(textbox_cariPermohonanId.getValue()), searchAllResult);
                searchResult = new ArrayList<TPermohonan>(searchAllResult);

            } else if (listbox_Cari.getValue().equalsIgnoreCase("Nomor")) {
                CollectionUtils.filter(searchResult, new IdTPermohonan(textbox_cariPermohonanId.getValue()));

            } else if (listbox_Cari.getValue().equalsIgnoreCase("Tipe")) {
                CollectionUtils.filter(searchResult, new TipePermohonan(textbox_cariPermohonanId.getValue()));

            } else if (listbox_Cari.getValue().equalsIgnoreCase("Status")) {
                CollectionUtils.filter(searchResult, new StatusPermohonan(textbox_cariPermohonanId.getValue()));

            } else if (listbox_Cari.getValue().equalsIgnoreCase("Dampak")) {
                CollectionUtils.filter(searchResult, new DampakPermohonan(textbox_cariPermohonanId.getValue()));
            }

            pagedListHolder = new PagedListHolder<TPermohonan>(searchResult);
            pagedListHolder.setPageSize(getCountRows());
            getPagedListWrapper().init(pagedListHolder, listbox_DaftarPermohonanPelaksana, paging_DaftarPermohonanPelaksana);
            checkbox_all.setChecked(false);
            datebox_TanggalAwal.setValue(null);
            datebox_TanggalAkhir.setValue(null);
        }
    }

    public void onOK$textbox_cariPermohonanId(Event event) {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        List searchResult = getPagedListWrapper().getPagedListHolder().getSource();
        PagedListHolder<TPermohonan> pagedListHolder = new PagedListHolder<TPermohonan>();

        if (!textbox_cariPermohonanId.getValue().isEmpty()) {
            if (listbox_Cari.getValue().equalsIgnoreCase("All")) {
                Set<TPermohonan> searchAllResult = new HashSet<TPermohonan>();
                CollectionUtils.select(searchResult, new IdTPermohonan(textbox_cariPermohonanId.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new TipePermohonan(textbox_cariPermohonanId.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new StatusPermohonan(textbox_cariPermohonanId.getValue()), searchAllResult);
                if (datebox_TanggalAkhir.getValue() != null) {
                    if (datebox_TanggalAwal.getValue() != null) {
                        CollectionUtils.select(searchResult, new TanggalPermohonan(datebox_TanggalAwal.getValue(), datebox_TanggalAkhir.getValue()), searchAllResult);
                    }
                }
                pagedListHolder = new PagedListHolder<TPermohonan>(new ArrayList<TPermohonan>(searchAllResult));
            } else if (listbox_Cari.getValue().equalsIgnoreCase("Nomor")) {
                CollectionUtils.filter(searchResult, new IdTPermohonan(textbox_cariPermohonanId.getValue()));
                pagedListHolder = new PagedListHolder<TPermohonan>(searchResult);
            } else if (listbox_Cari.getValue().equalsIgnoreCase("Tipe")) {
                CollectionUtils.filter(searchResult, new TipePermohonan(textbox_cariPermohonanId.getValue()));
                pagedListHolder = new PagedListHolder<TPermohonan>(searchResult);
            } else if (listbox_Cari.getValue().equalsIgnoreCase("Status")) {
                CollectionUtils.filter(searchResult, new StatusPermohonan(textbox_cariPermohonanId.getValue()));
                pagedListHolder = new PagedListHolder<TPermohonan>(searchResult);
            } else {
                if (datebox_TanggalAkhir.getValue() != null) {
                    if (datebox_TanggalAwal.getValue() != null) {
                        CollectionUtils.filter(searchResult, new TanggalPermohonan(datebox_TanggalAwal.getValue(), datebox_TanggalAkhir.getValue()));
                        pagedListHolder = new PagedListHolder<TPermohonan>(searchResult);
                    }
                }
            }
            pagedListHolder.setPageSize(getCountRows());
            getPagedListWrapper().init(pagedListHolder, listbox_DaftarPermohonanPelaksana, paging_DaftarPermohonanPelaksana);
            checkbox_all.setChecked(false);
        }
    }

    public void onTimer$timer(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Events.postEvent("onCreate", window_DaftarPermohonanPelaksana, event);
        window_DaftarPermohonanPelaksana.invalidate();
    }

    public void onCheck$checkbox_all(Event event) {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        textbox_cariPermohonanId.setValue("");
        Events.postEvent("onCreate", window_DaftarPermohonanPelaksana, event);
        window_DaftarPermohonanPelaksana.invalidate();

    }

    public void showDetailView(TPermohonan tPermohonan) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("tPermohonan", tPermohonan);

        map.put("DaftarPermohonanCtrl", this);

        map.put("listbox_DaftarPermohonanPelaksana", listbox_DaftarPermohonanPelaksana);

        map.put("window_DaftarPermohonanPelaksana", window_DaftarPermohonanPelaksana);

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

    public void setPelaksanaanGangguanService(PelaksanaanGangguanService pelaksanaanGangguanService) {
        this.pelaksanaanGangguanService = pelaksanaanGangguanService;
    }
}
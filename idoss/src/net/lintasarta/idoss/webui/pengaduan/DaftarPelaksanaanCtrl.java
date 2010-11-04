package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.pengaduan.model.DaftarTiketModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseListCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.pengaduan.model.comparator.TPenangananGangguanComparator;
import net.lintasarta.pengaduan.model.predicate.*;
import net.lintasarta.pengaduan.service.PenangananGangguanService;
import net.lintasarta.security.model.UserSession;
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
 * User: Joshua
 * Date: Sept 15, 2010
 * Time: 1:55:56 PM
 */
public class DaftarPelaksanaanCtrl extends GFCBaseListCtrl<TPenangananGangguan> implements Serializable {

    private transient static final Logger logger = Logger.getLogger(DaftarPelaksanaanCtrl.class);

    protected Window window_Pelaksanaan;
    protected Textbox textbox_Cari;
    protected Hbox idHboxTanggal;
    protected Combobox combobox_Cari;
    protected Datebox datebox_TanggalAwal;
    protected Datebox datebox_TanggalAkhir;
    protected Paging paging_DaftarTiket;
    protected Listbox listbox_DaftarTiket;
    protected Listheader listheader_NomorTiket;
    protected Listheader listheader_Judul;
    protected Listheader listheader_Status;
    protected Listheader listheader_Pelapor;
    protected Listheader listheader_PenanggungJawab;
    protected Listheader listheader_Durasi;
    protected Listheader listheader_MTTR;
    protected Listheader listheader_TglCreate;
    protected Listheader listheader_TglUpdate;
    protected Checkbox checkbox_All;
    protected Listbox listbox_Cari;
    protected Borderlayout borderlayout_daftarTroubleTiket;
    protected Panel panel_daftarTroubleTiket;
    private int countRows;
    private transient PenangananGangguanService penangananGangguanService;

    public DaftarPelaksanaanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Pelaksanaan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        int panelHeight = 25;
        // TODO put the logic for working with panel in the ApplicationWorkspace
        boolean withPanel = false;
        if (withPanel == false) {
            panel_daftarTroubleTiket.setVisible(false);
        } else {
            panel_daftarTroubleTiket.setVisible(true);
            panelHeight = 0;
        }

        int height = ((Intbox) Path.getComponent("/outerIndexWindow/currentDesktopHeight")).getValue();
        height = height + panelHeight;
        int maxListBoxHeight = (height - 170);
        setCountRows(Math.round(maxListBoxHeight / 34));
        borderlayout_daftarTroubleTiket.setHeight(String.valueOf(maxListBoxHeight) + "px");
        paging_DaftarTiket.setPageSize(getCountRows());
        paging_DaftarTiket.setDetailed(true);

        listheader_NomorTiket.setSortAscending(new FieldComparator("t_idoss_penanganan_gangguan_id", true));
        listheader_NomorTiket.setSortDescending(new FieldComparator("t_idoss_penanganan_gangguan_id", false));
        listheader_Judul.setSortAscending(new FieldComparator("judul", true));
        listheader_Judul.setSortDescending(new FieldComparator("judul", false));
        listheader_Status.setSortAscending(new FieldComparator("status", true));
        listheader_Status.setSortDescending(new FieldComparator("status", false));
        listheader_Pelapor.setSortAscending(new FieldComparator("nama_pelapor", true));
        listheader_Pelapor.setSortDescending(new FieldComparator("nama_pelapor", false));
        listheader_PenanggungJawab.setSortAscending(new FieldComparator("nama_pelaksana", true));
        listheader_PenanggungJawab.setSortDescending(new FieldComparator("nama_pelaksana", false));
        listheader_Durasi.setSortAscending(new FieldComparator("durasi", true));
        listheader_Durasi.setSortDescending(new FieldComparator("durasi", false));
        listheader_MTTR.setSortAscending(new FieldComparator("mttr", true));
        listheader_MTTR.setSortDescending(new FieldComparator("mttr", false));
        listheader_TglCreate.setSortAscending(new FieldComparator("created_date", true));
        listheader_TglCreate.setSortDescending(new FieldComparator("created_date", false));
        listheader_TglUpdate.setSortAscending(new FieldComparator("updated_date", true));
        listheader_TglUpdate.setSortDescending(new FieldComparator("updated_date", false));

        setDaftarTiket();
    }

    private void setDaftarTiket() {
        String nikPelaksana = getUserWorkspace().getUserSession().getEmployeeNo();
        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelaksana(nikPelaksana);
        List<TPenangananGangguan> tPenangananGangguans = getPenangananGangguanService().getAllTPenangananGangguanByNikPelaksana(tPenangananGangguan);

        PagedListHolder<TPenangananGangguan> pagedListHolder = new PagedListHolder<TPenangananGangguan>(tPenangananGangguans);
        pagedListHolder.setPageSize(getCountRows());

        paging_DaftarTiket.setPageSize(getCountRows());
        paging_DaftarTiket.setDetailed(true);

        getPagedListWrapper().init(pagedListHolder, listbox_DaftarTiket, paging_DaftarTiket);

        //
        List searchResult = getPagedListWrapper().getPagedListHolder().getSource();
        PagedListHolder<TPenangananGangguan> pagedListHolder2;
        Set searchAllResult = new HashSet();
        CollectionUtils.select(searchResult, new StatusTPenangananGangguan("In Progress"), searchAllResult);
        CollectionUtils.select(searchResult, new StatusTPenangananGangguan("Pending"), searchAllResult);
        searchResult = new ArrayList<TPenangananGangguan>(searchAllResult);
        pagedListHolder2 = new PagedListHolder<TPenangananGangguan>(searchResult);
        pagedListHolder2.setPageSize(getCountRows());
        getPagedListWrapper().init(pagedListHolder2, listbox_DaftarTiket, paging_DaftarTiket);

        listbox_DaftarTiket.setItemRenderer(new DaftarTiketModelItemRenderer());

        ListModelList lml = (ListModelList) listbox_DaftarTiket.getListModel();
        lml.sort(new TPenangananGangguanComparator(), true);
    }

    public void onDoubleClickedTiketItem(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Listitem item = listbox_DaftarTiket.getSelectedItem();

        if (item != null) {
            TPenangananGangguan tPenangananGangguan = (TPenangananGangguan) item.getAttribute("data");

            if (logger.isDebugEnabled()) {
                logger.debug("--> " + tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
            }

            showDetailView(tPenangananGangguan);
        }
    }

    public void onClick$btnCari_DaftarTiket(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        List searchResult = getPagedListWrapper().getPagedListHolder().getSource();
        PagedListHolder<TPenangananGangguan> pagedListHolder;

        if (datebox_TanggalAkhir.getValue() != null) {
            if (datebox_TanggalAwal.getValue() != null) {
                CollectionUtils.filter(searchResult, new TanggalTPenangananGangguan(datebox_TanggalAwal.getValue(), datebox_TanggalAkhir.getValue()));
            }
        }

        if (!textbox_Cari.getValue().isEmpty()) {
            if (combobox_Cari.getValue().equalsIgnoreCase("All")) {
                Set searchAllResult = new HashSet();
                CollectionUtils.select(searchResult, new JudulTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new NomorTiketTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new StatusTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new PelaporTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new PelaksanaTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
//                if (datebox_TanggalAkhir.getValue() != null) {
//                    if (datebox_TanggalAwal.getValue() != null) {
//                        CollectionUtils.select(searchResult, new TanggalTPenangananGangguan(datebox_TanggalAwal.getValue(), datebox_TanggalAkhir.getValue()), searchAllResult);
//                    }
//                }
                searchResult = new ArrayList<TPenangananGangguan>(searchAllResult);
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Judul")) {
                CollectionUtils.filter(searchResult, new JudulTPenangananGangguan(textbox_Cari.getValue()));
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Nomor Tiket")) {
                CollectionUtils.filter(searchResult, new NomorTiketTPenangananGangguan(textbox_Cari.getValue()));
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Status")) {
                CollectionUtils.filter(searchResult, new StatusTPenangananGangguan(textbox_Cari.getValue()));
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Pelapor")) {
                CollectionUtils.filter(searchResult, new PelaporTPenangananGangguan(textbox_Cari.getValue()));
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Pelaksana")) {
                CollectionUtils.filter(searchResult, new PelaksanaTPenangananGangguan(textbox_Cari.getValue()));
            }
         } else {
            if (combobox_Cari.getValue().equalsIgnoreCase("Pelaksana")) {
                CollectionUtils.filter(searchResult, new PelaksanaNullTPenangananGangguan());
            }
        }
        pagedListHolder = new PagedListHolder<TPenangananGangguan>(searchResult);
        pagedListHolder.setPageSize(getCountRows());
        getPagedListWrapper().init(pagedListHolder, listbox_DaftarTiket, paging_DaftarTiket);
        checkbox_All.setChecked(false);
        datebox_TanggalAwal.setValue(null);
        datebox_TanggalAkhir.setValue(null);
    }

    public void onOK$textbox_Cari(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        List searchResult = getPagedListWrapper().getPagedListHolder().getSource();
        PagedListHolder<TPenangananGangguan> pagedListHolder;

        if (datebox_TanggalAkhir.getValue() != null) {
            if (datebox_TanggalAwal.getValue() != null) {
                CollectionUtils.filter(searchResult, new TanggalTPenangananGangguan(datebox_TanggalAwal.getValue(), datebox_TanggalAkhir.getValue()));
            }
        }

        if (!textbox_Cari.getValue().isEmpty()) {
            if (combobox_Cari.getValue().equalsIgnoreCase("All")) {
                Set searchAllResult = new HashSet();
                CollectionUtils.select(searchResult, new JudulTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new NomorTiketTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new StatusTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new PelaporTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
                CollectionUtils.select(searchResult, new PelaksanaTPenangananGangguan(textbox_Cari.getValue()), searchAllResult);
//                if (datebox_TanggalAkhir.getValue() != null) {
//                    if (datebox_TanggalAwal.getValue() != null) {
//                        CollectionUtils.select(searchResult, new TanggalTPenangananGangguan(datebox_TanggalAwal.getValue(), datebox_TanggalAkhir.getValue()), searchAllResult);
//                    }
//                }
                searchResult = new ArrayList<TPenangananGangguan>(searchAllResult);
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Judul")) {
                CollectionUtils.filter(searchResult, new JudulTPenangananGangguan(textbox_Cari.getValue()));
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Nomor Tiket")) {
                CollectionUtils.filter(searchResult, new NomorTiketTPenangananGangguan(textbox_Cari.getValue()));
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Status")) {
                CollectionUtils.filter(searchResult, new StatusTPenangananGangguan(textbox_Cari.getValue()));
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Pelapor")) {
                CollectionUtils.filter(searchResult, new PelaporTPenangananGangguan(textbox_Cari.getValue()));
            } else if (combobox_Cari.getValue().equalsIgnoreCase("Pelaksana")) {
                CollectionUtils.filter(searchResult, new PelaksanaTPenangananGangguan(textbox_Cari.getValue()));
            }
        } else {
            if (combobox_Cari.getValue().equalsIgnoreCase("Pelaksana")) {
                CollectionUtils.filter(searchResult, new PelaksanaNullTPenangananGangguan());
            }
        }
        pagedListHolder = new PagedListHolder<TPenangananGangguan>(searchResult);
        pagedListHolder.setPageSize(getCountRows());
        getPagedListWrapper().init(pagedListHolder, listbox_DaftarTiket, paging_DaftarTiket);
        checkbox_All.setChecked(false);
        datebox_TanggalAwal.setValue(null);
        datebox_TanggalAkhir.setValue(null);
    }

    public void onClick$btnRefresh_DaftarTiket(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Events.postEvent("onCreate", window_Pelaksanaan, event);
        window_Pelaksanaan.invalidate();
    }

    public void onClick$btnBuatBaru_DaftarTiket(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        TPenangananGangguan tPenangananGangguan = getPenangananGangguanService().getNewPenangananGangguan();

        showDetailViewPengaduan(tPenangananGangguan);
    }

    public void onClick$btnBuatBaru_DaftarTiketHelpdesk(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        TPenangananGangguan tPenangananGangguan = getPenangananGangguanService().getNewPenangananGangguan();

        showDetailViewPengaduanHelpdesk(tPenangananGangguan);
    }

    private void showDetailViewPengaduan(TPenangananGangguan tPenangananGangguan) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("tPenangananGangguan", tPenangananGangguan);


        map.put("listbox_DaftarTiket", listbox_DaftarTiket);

        try {
            Executions.createComponents("/WEB-INF/pages/pengaduan/pengaduan.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    private void showDetailViewPengaduanHelpdesk(TPenangananGangguan tPenangananGangguan) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("tPenangananGangguan", tPenangananGangguan);
        map.put("window_Pelaksanaan",window_Pelaksanaan);
        map.put("listbox_DaftarTiket", listbox_DaftarTiket);

        try {
            Executions.createComponents("/WEB-INF/pages/pengaduan/penangananGangguan.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    public void onCheck$checkbox_All(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        textbox_Cari.setValue("");
        datebox_TanggalAwal.setValue(null);
        datebox_TanggalAkhir.setValue(null);
        setDaftarTiket();
    }

    public void onClick$btn_report(Event event) throws InterruptedException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("listbox_DaftarTiket", listbox_DaftarTiket);

        try {
            Executions.createComponents("/WEB-INF/report/pengaduan/reportPengaduan.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    public void onClick$btnKeluar_DaftarTiket(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        window_Pelaksanaan.onClose();
    }

    public void onTimer$timer(Event event) {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Events.postEvent("onCreate", window_Pelaksanaan, event);
        window_Pelaksanaan.invalidate();
    }

    public void showDetailView(TPenangananGangguan tPenangananGangguan) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("tPenangananGangguan", tPenangananGangguan);
        map.put("window_Pelaksanaan", window_Pelaksanaan);
        map.put("listbox_DaftarTiket", listbox_DaftarTiket);

        try {
            Executions.createComponents("/WEB-INF/pages/pengaduan/pelaksanaanGangguanR.zul", null, map);
        } catch (Exception e) {
            logger.error("onOpenWindow:: error opening window / " + e.getMessage());

            // Show a error box
            String msg = e.getMessage();
            String title = Labels.getLabel("message_Error");

            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    private void doViewTanggal() {
        idHboxTanggal.setVisible(true);
    }

    private void doHideTanggal() {
        idHboxTanggal.setVisible(false);
    }

    public int getCountRows() {
        return countRows;
    }

    public void setCountRows(int countRows) {
        this.countRows = countRows;
    }

    public PenangananGangguanService getPenangananGangguanService() {
        return penangananGangguanService;
    }

    public void setPenangananGangguanService(PenangananGangguanService penangananGangguanService) {
        this.penangananGangguanService = penangananGangguanService;
    }
}
package net.lintasarta.idoss.webui.pengaduan;


import net.lintasarta.idoss.webui.pengaduan.model.DaftarTiketModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseListCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.pengaduan.model.predicate.JudulTPenangananGangguan;
import net.lintasarta.pengaduan.service.PenangananGangguanService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Asri
 * Date: Jul 6, 2010
 * Time: 1:55:56 PM
 */
public class DaftarTroubleTiketCtrl extends GFCBaseListCtrl<TPenangananGangguan> implements Serializable {

    private transient static final Logger logger = Logger.getLogger(DaftarTroubleTiketCtrl.class);

    protected Window window_DaftarTroubleTiket;
    protected Textbox textbox_Cari;

    protected Paging paging_DaftaTiket;
    protected Listbox listbox_DaftarTiket;

    protected Listheader listheader_NomorTiket;
    protected Listheader listheader_Judul;
    protected Listheader listheader_Status;
    protected Listheader listheader_Pelapor;

    protected Listheader listheader_PenanggungJawab;
    protected Listheader listheader_Durasi;
    protected Listheader listheader_MTTR;
    protected Listheader listheader_TglUpdate;

//    protected Button btnCari_DaftarTiket;
//    protected Button btnBuatBaru_DaftarTiket;
//    protected Button btnKeluar_DaftarTiket;

    protected Borderlayout borderlayout_daftarTroubleTiket;
    protected Panel panel_daftarTroubleTiket;

    private int countRows;

    private transient PenangananGangguanService penangananGangguanService;

    public DaftarTroubleTiketCtrl() {
        super();

        if (logger.isDebugEnabled()) {
//            logger.debug("--> super()");
        }
    }

    public void onCreate$window_DaftarTroubleTiket(Event event) throws Exception {

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
        setCountRows(Math.round(maxListBoxHeight / 17));

        borderlayout_daftarTroubleTiket.setHeight(String.valueOf(maxListBoxHeight) + "px");

        paging_DaftaTiket.setPageSize(getCountRows());
        paging_DaftaTiket.setDetailed(true);

        listheader_NomorTiket.setSortAscending(new FieldComparator("t_idoss_penanganan_gangguan_id", true));
        listheader_Judul.setSortAscending(new FieldComparator("judul", true));
        listheader_Status.setSortAscending(new FieldComparator("status", true));
        listheader_Pelapor.setSortAscending(new FieldComparator("nama_pelapor", true));
        listheader_PenanggungJawab.setSortAscending(new FieldComparator("nama_pelaksana", true));
        listheader_Durasi.setSortAscending(new FieldComparator("durasi", true));
        listheader_MTTR.setSortAscending(new FieldComparator("mttr", true));
        listheader_TglUpdate.setSortAscending(new FieldComparator("updated_date", true));

        List<TPenangananGangguan> tPenangananGangguans = getPenangananGangguanService().getAllPenangananGangguan();
        PagedListHolder<TPenangananGangguan> pagedListHolder = new PagedListHolder<TPenangananGangguan>(tPenangananGangguans);
        pagedListHolder.setPageSize(getCountRows());

        paging_DaftaTiket.setPageSize(getCountRows());
        paging_DaftaTiket.setDetailed(true);

        getPagedListWrapper().init(pagedListHolder, listbox_DaftarTiket, paging_DaftaTiket);
        listbox_DaftarTiket.setItemRenderer(new DaftarTiketModelItemRenderer());
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

        if (!textbox_Cari.getValue().isEmpty()) {

            // ++ create the searchObject and init sorting ++//

            ListModelList lml = (ListModelList) listbox_DaftarTiket.getListModel();
            List searchResult = new ArrayList(lml);

            CollectionUtils.filter(searchResult, new JudulTPenangananGangguan(textbox_Cari.getValue()));

            PagedListHolder<TPenangananGangguan> pagedListHolder = new PagedListHolder<TPenangananGangguan>(searchResult);
            pagedListHolder.setPageSize(getCountRows());
            
            // Set the ListModel.
            getPagedListWrapper().init(pagedListHolder, listbox_DaftarTiket, paging_DaftaTiket);
        }

    }

    public void onClick$btnRefresh_DaftarTiket(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Events.postEvent("onCreate", window_DaftarTroubleTiket, event);
        window_DaftarTroubleTiket.invalidate();
    }

    public void onClick$btnBuatBaru_DaftarTiket(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        TPenangananGangguan tPenangananGangguan = getPenangananGangguanService().getNewPenangananGangguan();

        showDetailViewPengaduan(tPenangananGangguan);

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

    public void onClick$btnKeluar_DaftarTiket(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        window_DaftarTroubleTiket.onClose();
    }

    public void showDetailView(TPenangananGangguan tPenangananGangguan) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("tPenangananGangguan", tPenangananGangguan);

        map.put("listbox_DaftarTiket", listbox_DaftarTiket);

        try {
            Executions.createComponents("/WEB-INF/pages/pengaduan/pelaksanaanGangguan.zul", null, map);
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

    public PenangananGangguanService getPenangananGangguanService() {
        return penangananGangguanService;
    }

    public void setPenangananGangguanService(PenangananGangguanService penangananGangguanService) {
        this.penangananGangguanService = penangananGangguanService;
    }
}
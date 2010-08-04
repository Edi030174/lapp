package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.permohonan.model.DaftarPermohonanModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseListCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.service.PermohonanService;
import org.apache.log4j.Logger;
import org.springframework.beans.support.PagedListHolder;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.io.Serializable;
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
    protected Paging paging_DaftarPermohonan;
    protected Listbox listbox_DaftarPermohonan;
    protected Listheader listheader_Nomor;
    protected Listheader listheader_Tanggal;
    protected Listheader listheader_Tipe;
    protected Listheader listheader_StatusPersetujuan;
//    protected Listheader listheader_Pimbag;
//    protected Listheader listheader_Pimdiv;
    protected Listheader listheader_AssMgr;
    protected Listheader listheader_Mgr;
    protected Listheader listheader_GM;

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

        int panelHeight = 25;
        // TODO put the logic for working with panel in the ApplicationWorkspace
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

        paging_DaftarPermohonan.setPageSize(getCountRows());
        paging_DaftarPermohonan.setDetailed(true);

        listheader_Nomor.setSortAscending(new FieldComparator("t_idoss_permohonan_id", true));
        listheader_Nomor.setSortDescending(new FieldComparator("t_idoss_permohonan_id", true));
        listheader_Tanggal.setSortAscending(new FieldComparator("tgl_permohonan", true));
        listheader_Tanggal.setSortDescending(new FieldComparator("tgl_permohonan", true));
        listheader_Tipe.setSortAscending(new FieldComparator("type_permohonan", true));
        listheader_Tipe.setSortDescending(new FieldComparator("type_permohonan", true));
        listheader_StatusPersetujuan.setSortAscending(new FieldComparator("status_track_permohonan", true));
        listheader_StatusPersetujuan.setSortDescending(new FieldComparator("status_track_permohonan", true));
//        listheader_Pimbag.setSortAscending(new FieldComparator("", true));
//        listheader_Pimbag.setSortDescending(new FieldComparator("", true));
//        listheader_Pimdiv.setSortAscending(new FieldComparator("nama_divisi", true));
//        listheader_Pimdiv.setSortDescending(new FieldComparator("nama_divisi", true));
        listheader_AssMgr.setSortAscending(new FieldComparator("nama_asman", true));
        listheader_AssMgr.setSortDescending(new FieldComparator("nama_asman", true));
        listheader_Mgr.setSortAscending(new FieldComparator("nama_manager", true));
        listheader_Mgr.setSortDescending(new FieldComparator("nama_manager", true));
        listheader_GM.setSortAscending(new FieldComparator("nama_gm", true));
        listheader_GM.setSortDescending(new FieldComparator("nama_gm", true));

        List<TPermohonan> tPermohonans = getPermohonanService().getAllTPermohonan();
        PagedListHolder<TPermohonan> pagedListHolder = new PagedListHolder<TPermohonan>(tPermohonans);
        pagedListHolder.setPageSize(getCountRows());

        paging_DaftarPermohonan.setPageSize(getCountRows());
        paging_DaftarPermohonan.setDetailed(true);

        getPagedListWrapper().init(pagedListHolder, listbox_DaftarPermohonan, paging_DaftarPermohonan);
        listbox_DaftarPermohonan.setItemRenderer(new DaftarPermohonanModelItemRenderer());
    }

    public void onPermohonanItemDoubleClicked(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

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

        showDetailView(tPermohonan);
    }

    public void onClick$btnRefresh(Event event) throws InterruptedException {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Events.postEvent("onCreate", window_DaftarPermohonan, event);
        window_DaftarPermohonan.invalidate();
    }

    public void showDetailView(TPermohonan tPermohonan) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("tPermohonan", tPermohonan);

        map.put("DaftarPermohonanCtrl", this);

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
}
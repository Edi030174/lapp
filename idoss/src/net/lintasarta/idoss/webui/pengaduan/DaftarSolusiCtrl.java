package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.pengaduan.model.DaftarSolusiModelItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseListCtrl;
import net.lintasarta.pengaduan.model.TDeskripsi;
import net.lintasarta.pengaduan.service.TDeskripsiService;
import org.apache.log4j.Logger;
import org.springframework.beans.support.PagedListHolder;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 13, 2010
 * Time: 4:46:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class DaftarSolusiCtrl extends GFCBaseListCtrl<TDeskripsi> implements Serializable {
    private transient static final Logger logger = Logger.getLogger(DaftarSolusiCtrl.class);
    protected Window window_Solusi;
    protected Paging paging_DaftarSolusi;
    protected Listbox listbox_DaftarSolusi;
    protected Listheader listheader_Tanggal;
    protected Listheader listheader_By;
    protected Listheader listheader_Solusi;
    protected Borderlayout borderlayout_daftarSolusi;
    protected Panel panel_daftarSolusi;
    private transient TDeskripsiService tDeskripsiService;
    private int countRows;

    public DaftarSolusiCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Solusi(Event event) throws Exception {


        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        int panelHeight = 25;
        // TODO put the logic for working with panel in the ApplicationWorkspace
        boolean withPanel = false;
        if (withPanel == false) {
            panel_daftarSolusi.setVisible(false);
        } else {
            panel_daftarSolusi.setVisible(true);
            panelHeight = 0;
        }

        int height = ((Intbox) Path.getComponent("/outerIndexWindow/currentDesktopHeight")).getValue();
        height = height + panelHeight;
        int maxListBoxHeight = (height - 170);
        setCountRows(Math.round(maxListBoxHeight / 17));
        borderlayout_daftarSolusi.setHeight(String.valueOf(maxListBoxHeight) + "px");
        paging_DaftarSolusi.setPageSize(getCountRows());
        paging_DaftarSolusi.setDetailed(true);

        listheader_Tanggal.setSortAscending(new FieldComparator("updated_date", true));
        listheader_Tanggal.setSortDescending(new FieldComparator("updated_date", false));
        listheader_By.setSortAscending(new FieldComparator("updated_by", true));
        listheader_By.setSortDescending(new FieldComparator("updated_by", false));
        listheader_Solusi.setSortAscending(new FieldComparator("solusi", true));
        listheader_Solusi.setSortDescending(new FieldComparator("solusi", false));

        Map<String, Object> args = getCreationArgsMap(event);
        if (args.containsKey("t_idoss_penanganan_gangguan_id")) {
            String id = (String) args.get("t_idoss_penanganan_gangguan_id");
            setDaftarSolusi(id);
        }
    }

    private void setDaftarSolusi(String id) {

        List<TDeskripsi> tDeskripsis = gettDeskripsiService().getTDeskripsiByGangguanId(id);

        PagedListHolder<TDeskripsi> pagedListHolder = new PagedListHolder<TDeskripsi>(tDeskripsis);
        pagedListHolder.setPageSize(getCountRows());

        paging_DaftarSolusi.setPageSize(getCountRows());
        paging_DaftarSolusi.setDetailed(true);

        getPagedListWrapper().init(pagedListHolder, listbox_DaftarSolusi, paging_DaftarSolusi);
        listbox_DaftarSolusi.setItemRenderer(new DaftarSolusiModelItemRenderer());
    }

    public TDeskripsiService gettDeskripsiService() {
        return tDeskripsiService;
    }

    public void settDeskripsiService(TDeskripsiService tDeskripsiService) {
        this.tDeskripsiService = tDeskripsiService;
    }

    public int getCountRows() {
        return countRows;
    }

    public void setCountRows(int countRows) {
        this.countRows = countRows;
    }
}
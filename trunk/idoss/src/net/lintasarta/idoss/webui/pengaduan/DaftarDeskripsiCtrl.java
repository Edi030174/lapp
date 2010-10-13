package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.util.GFCBaseListCtrl;
import net.lintasarta.pengaduan.model.TDeskripsi;
import net.lintasarta.pengaduan.service.TDeskripsiService;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 13, 2010
 * Time: 4:46:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class DaftarDeskripsiCtrl extends GFCBaseListCtrl<TDeskripsi> implements Serializable {
    private transient static final Logger logger = Logger.getLogger(DaftarDeskripsiCtrl.class);
    protected Window window_Deskripsi;
    protected Paging paging_DaftarDeskripsi;
    protected Listbox listbox_DaftarDeskripsi;
    protected Listheader listheader_Tanggal;
    protected Listheader listheader_By;
    protected Listheader listheader_Deskripsi;
    protected Borderlayout borderlayout_daftarDeskripsi;
    protected Panel panel_daftarDeskripsi;
    private transient TDeskripsiService tDeskripsiService;
    private int countRows;

    public DaftarDeskripsiCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Deskripsi(Event event) throws Exception {


        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        int panelHeight = 25;
        // TODO put the logic for working with panel in the ApplicationWorkspace
        boolean withPanel = false;
        if (withPanel == false) {
            panel_daftarDeskripsi.setVisible(false);
        } else {
            panel_daftarDeskripsi.setVisible(true);
            panelHeight = 0;
        }

        int height = ((Intbox) Path.getComponent("/outerIndexWindow/currentDesktopHeight")).getValue();
        height = height + panelHeight;
        int maxListBoxHeight = (height - 170);
        setCountRows(Math.round(maxListBoxHeight / 17));
        borderlayout_daftarDeskripsi.setHeight(String.valueOf(maxListBoxHeight) + "px");
        paging_DaftarDeskripsi.setPageSize(getCountRows());
        paging_DaftarDeskripsi.setDetailed(true);

        listheader_Tanggal.setSortAscending(new FieldComparator("t_idoss_penanganan_gangguan_id", true));
        listheader_Tanggal.setSortDescending(new FieldComparator("t_idoss_penanganan_gangguan_id", false));
        listheader_By.setSortAscending(new FieldComparator("judul", true));
        listheader_By.setSortDescending(new FieldComparator("judul", false));
        listheader_Deskripsi.setSortAscending(new FieldComparator("status", true));
        listheader_Deskripsi.setSortDescending(new FieldComparator("status", false));
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
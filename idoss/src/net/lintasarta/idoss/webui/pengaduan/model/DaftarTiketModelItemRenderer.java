package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.sys.ComponentsCtrl;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Asri
 * Date: Jul 7, 2010
 * Time: 4:52:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class DaftarTiketModelItemRenderer implements ListitemRenderer {

    private transient final static Logger logger = Logger.getLogger(DaftarTiketModelItemRenderer.class);

    @Override
    public void render(Listitem item, Object data) throws Exception {

        TPenangananGangguan penangananGangguan = (TPenangananGangguan) data;

        if (logger.isDebugEnabled()) {
			logger.debug("--> " + penangananGangguan.getStatus() + ", " + penangananGangguan.getBagian_pelapor());
		}
        Listcell lc = new Listcell(penangananGangguan.getT_idoss_penanganan_gangguan_id());
        lc.setParent(item);

        lc = new Listcell(penangananGangguan.getJudul());
        lc.setParent(item);

        lc = new Listcell(penangananGangguan.getStatus());
        lc.setParent(item);

        lc = new Listcell(penangananGangguan.getNama_pelapor());
        lc.setParent(item);

        lc = new Listcell(penangananGangguan.getNama_pelaksana());
        lc.setParent(item);

        String i = penangananGangguan.getDurasi();
        String dur = String.valueOf(i);
        lc = new Listcell(dur);
        lc.setParent(item);

        lc = new Listcell(penangananGangguan.getMttr());
        lc.setParent(item);

        Timestamp ts = penangananGangguan.getUpdated_date();
        String tgl = new SimpleDateFormat("dd-MM-yyyy").format(ts);
        lc = new Listcell(tgl);
        lc.setParent(item);

        item.setAttribute("data", data);
        ComponentsCtrl.applyForward(item, "onDoubleClick=onDoubleClickedTiketItem");

    }
}
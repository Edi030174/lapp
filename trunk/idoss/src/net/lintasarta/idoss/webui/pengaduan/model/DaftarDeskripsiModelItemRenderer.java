package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.pengaduan.model.TDeskripsi;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import org.apache.log4j.Logger;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.zk.ui.sys.ComponentsCtrl;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DaftarDeskripsiModelItemRenderer implements ListitemRenderer {

    private transient final static Logger logger = Logger.getLogger(DaftarDeskripsiModelItemRenderer.class);

    @Override
    public void render(Listitem item, Object data) throws Exception {

        TDeskripsi tDeskripsi = (TDeskripsi) data;

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + tDeskripsi.getSolusi() + ", " + tDeskripsi.getDeskripsi());
        }
        if (tDeskripsi.getDeskripsi() != null) {
            Listcell lc = new Listcell(tDeskripsi.getT_idoss_penanganan_gangguan_id());
//        lc.setParent(item);

            Timestamp ts = tDeskripsi.getUpdated_date();
            String tgl = new SimpleDateFormat("dd-MM-yyyy").format(ts);
            lc = new Listcell(tgl);
            lc.setParent(item);

            lc = new Listcell(tDeskripsi.getUpdated_by());
            lc.setParent(item);

            lc = new Listcell(tDeskripsi.getDeskripsi());
            lc.setParent(item);
        }
        item.setAttribute("data", data);
        ComponentsCtrl.applyForward(item, "onDoubleClick=onDoubleClickedTiketItem");
    }
}
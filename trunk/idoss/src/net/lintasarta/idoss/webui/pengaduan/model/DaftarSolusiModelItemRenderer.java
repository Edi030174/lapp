package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.pengaduan.model.TDeskripsi;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.sys.ComponentsCtrl;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DaftarSolusiModelItemRenderer implements ListitemRenderer {

    private transient final static Logger logger = Logger.getLogger(DaftarSolusiModelItemRenderer.class);

    @Override
    public void render(Listitem item, Object data) throws Exception {

        TDeskripsi tDeskripsi = (TDeskripsi) data;

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + tDeskripsi.getSolusi() + ", " + tDeskripsi.getDeskripsi());
        }
        Listcell lc = new Listcell(tDeskripsi.getT_idoss_penanganan_gangguan_id());
//        lc.setParent(item);

        Timestamp ts = tDeskripsi.getUpdated_date();
        String tgl = new SimpleDateFormat("dd-MM-yyyy").format(ts);
        lc = new Listcell(tgl);
        lc.setParent(item);

        lc = new Listcell(tDeskripsi.getUpdated_by());
        lc.setParent(item);

        if (tDeskripsi.getSolusi() != null) {
            lc = new Listcell(tDeskripsi.getSolusi());
            lc.setParent(item);
        }

        item.setAttribute("data", data);
        ComponentsCtrl.applyForward(item, "onDoubleClick=onDoubleClickedTiketItem");
    }
}
package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.pengaduan.model.PRootCaused;
import org.apache.log4j.Logger;
import org.zkoss.zul.*;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jul 23, 2010
 * Time: 4:46:50 PM
 */
public class RootCausedListModelItemRenderer implements ListitemRenderer, Serializable {

    private transient static final Logger logger = Logger.getLogger(RootCausedListModelItemRenderer.class);

    @Override
    public void render(Listitem item, Object data) throws Exception {

        PRootCaused pRootCaused = (PRootCaused) data;

        Listcell lc = new Listcell(pRootCaused.getRoot_caused());
        lc.setParent(item);

        item.setAttribute("data", data);
    }

}

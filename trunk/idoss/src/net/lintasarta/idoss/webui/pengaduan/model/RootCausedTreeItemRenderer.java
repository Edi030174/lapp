package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.pengaduan.model.PRootCaused;
import org.apache.log4j.Logger;
import org.zkoss.zul.*;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 9, 2010
 * Time: 4:04:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class RootCausedTreeItemRenderer implements TreeitemRenderer, Serializable {
    private transient static final Logger logger = Logger.getLogger(RootCausedTreeItemRenderer.class);

    @Override
    public void render(Treeitem treeitem, Object data) throws Exception {
        SimpleTreeNode stn = (SimpleTreeNode) data;
        PRootCaused pRootCaused = (PRootCaused) stn.getData();

        Treecell tc = new Treecell(pRootCaused.getRoot_caused());
        Treerow tr = null;

        if(treeitem.getTreerow() == null){
            tr = new Treerow();
            tr.setParent(treeitem);
        }else {
            tr = treeitem.getTreerow();
            tr.getChildren().clear();
        }

        tc.setParent(tr);
        treeitem.setOpen(false);

    }
}

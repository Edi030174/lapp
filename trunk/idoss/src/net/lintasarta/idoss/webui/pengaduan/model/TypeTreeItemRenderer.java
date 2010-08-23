package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.pengaduan.model.PType;
import org.apache.log4j.Logger;
import org.zkoss.zul.*;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Asri
 * Date: Aug 16, 2010
 * Time: 2:20:10 PM
 */
public class TypeTreeItemRenderer implements TreeitemRenderer, Serializable {

    private transient static final Logger logger = Logger.getLogger(RootCausedTreeItemRenderer.class);

    @Override
    public void render(Treeitem treeitem, Object data) throws Exception {
        SimpleTreeNode stn = (SimpleTreeNode) data;
//        TypeTreeNode ttn = (TypeTreeNode) data;
         PType pType = (PType) stn.getChildren();

        Treecell tc = new Treecell(pType.getType_desc());
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

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

    private transient static final Logger logger = Logger.getLogger(TypeTreeItemRenderer.class);


    public void render(Treeitem item, Object data) throws Exception {
        SimpleTreeNode t = (SimpleTreeNode)data;
        PType pType = (PType)t.getData();
        Treecell treecell = new Treecell(pType.getType_desc());
        Treerow tr;

        if(item.getTreerow() == null){
            tr = new Treerow();
            tr.setParent(item);
        }else{
            tr = item.getTreerow();
            tr.getChildren().clear();
        }
        treecell.setParent(tr);
        item.setValue(pType);
        item.setOpen(false);
    }
}

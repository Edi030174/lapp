package net.lintasarta.idoss.webui.pengaduan.model;

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


    public void render(Treeitem item, Object data) throws Exception {
        SimpleTreeNode t = (SimpleTreeNode)data;
        String typeDesc = (String)t.getData();
//        PType pType = (PType)t.getData();
        //Contruct treecells
          Treecell treecell = new Treecell(typeDesc);
//        Treecell treecell = new Treecell(pType.getType_desc());
        Treerow tr;
        /*
         * Since only one treerow is allowed, if treerow is not null,
         * append treecells to it. If treerow is null, contruct a new
         * treerow and attach it to item.
         */
        if(item.getTreerow()==null){
            tr = new Treerow();
            tr.setParent(item);
        }else{
            tr = item.getTreerow();
            tr.getChildren().clear();
        }
        //Attach treecells to treerow
        treecell.setParent(tr);
        item.setOpen(false);

    }

//    @Override
//    public void render(Treeitem treeitem, Object data) throws Exception {
//        SimpleTreeNode stn = (SimpleTreeNode) data;
////        TypeTreeNode ttn = (TypeTreeNode) data;
//        List<PType> pTypes = stn.getChildren();
//        for (PType pType : pTypes) {
//            Treecell tc = new Treecell(pType.getType_desc());
//            Treerow tr;
//
//            if(treeitem.getTreerow() == null){
//                tr = new Treerow();
//                tr.setParent(treeitem);
//            }else {
//                tr = treeitem.getTreerow();
//                tr.getChildren().clear();
//            }
//            tc.setParent(tr);
//            treeitem.setValue(tc);
//        }
//        treeitem.setOpen(false);
//
////        treeitem.setValue(data);
//
//    }
}

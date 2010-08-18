package net.lintasarta.idoss.webui.pengaduan.model;

import org.zkoss.zul.AbstractTreeModel;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 18, 2010
 * Time: 11:02:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class TypeTreeModel extends AbstractTreeModel {

    TypeTreeNode root;

    public TypeTreeModel(TypeTreeNode root) {
        super(root);
        this.root = root;
    }

    @Override
    public Object getChild(Object arg0, int arg1) {
        return ((TypeTreeNode) arg0).getChild(arg1);
    }

    @Override
    public int getChildCount(Object arg0) {
        return ((TypeTreeNode) arg0).getChildCount();
    }

    @Override
    public boolean isLeaf(Object arg0) {
        if (arg0 == null)
            return true;

        return ((TypeTreeNode) arg0).isLeaf();
    }

}

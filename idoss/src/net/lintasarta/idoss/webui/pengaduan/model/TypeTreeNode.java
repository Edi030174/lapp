package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.pengaduan.model.PType;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 18, 2010
 * Time: 11:17:30 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TypeTreeNode {

    ArrayList<PType> children = null;

    public abstract void readChildren();

    public ArrayList<PType> getChildren() {
        return children;
    }

    public PType getChild(int arg1) {
        PType child = null;

        if (children == null)
            readChildren();

        if (children != null && (arg1 > -1 && arg1 < children.size()))
            child = children.get(arg1);

        return child;
    }

    public int getChildCount() {
        if (children == null)
            readChildren();

        if (children != null)
            return children.size();

        return 0;
    }

    public boolean isLeaf() {
        return (getChildCount() == 0);
    }
}

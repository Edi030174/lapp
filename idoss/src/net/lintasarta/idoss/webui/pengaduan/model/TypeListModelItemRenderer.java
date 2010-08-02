package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.pengaduan.model.PType;
import org.apache.log4j.Logger;
import org.zkoss.zul.*;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jul 20, 2010
 * Time: 3:34:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class TypeListModelItemRenderer implements ListitemRenderer, Serializable {

    private transient static final Logger logger = Logger.getLogger(TypeListModelItemRenderer.class);

    @Override
    public void render(Listitem item, Object data) throws Exception {
        PType pType = (PType) data;

        Listcell lc = new Listcell(pType.getType_desc());
        lc.setParent(item);

        item.setAttribute("data", data);
    }

    /*@Override
    public void render(Comboitem comboitem, Object data) throws Exception {

        PType pType = (PType) data;
        comboitem.setLabel(pType.getType_desc());
        comboitem.setValue(pType);
        comboitem.setAttribute("data", data);

    }*/
}

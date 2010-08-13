package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 13, 2010
 * Time: 5:30:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class PelaksanaListModelItemRenderer implements ListitemRenderer, Serializable {
    @Override
    public void render(Listitem item, Object data) throws Exception {
        VHrEmployeePelaksana vHrEmployeePelaksana = (VHrEmployeePelaksana)data;

        Listcell lc = new Listcell(vHrEmployeePelaksana.getEmployee_name());
        lc.setParent(item);

        item.setAttribute("data", data);
    }
}

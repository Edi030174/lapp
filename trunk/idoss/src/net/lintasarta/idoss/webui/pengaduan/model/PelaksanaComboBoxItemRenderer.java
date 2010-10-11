package net.lintasarta.idoss.webui.pengaduan.model;

import net.lintasarta.security.model.VHrEmployee;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 6, 2010
 * Time: 6:30:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class PelaksanaComboBoxItemRenderer implements ComboitemRenderer, Serializable {
    @Override
    public void render(Comboitem comboitem, Object o) throws Exception {
        VHrEmployee employee = (VHrEmployee) o;


        comboitem.setLabel(employee.getEmployee_name());
        comboitem.setValue(employee);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

package net.lintasarta.idoss.common.menu.dropdown;

import net.lintasarta.idoss.common.menu.util.ILabelElement;
import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Messagebox;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:30:10 PM
 */
class DefaultDropDownMenu extends Menu implements Serializable, ILabelElement, EventListener {
    private String zulNavigation;

    @Override
    public void onEvent(Event event) throws Exception {
        try {
            /* get an instance of the borderlayout defined in the zul-file */
            Borderlayout bl = (Borderlayout) Path.getComponent("/outerIndexWindow/borderlayoutMain");
            /* get an instance of the searched CENTER layout area */
            Center center = bl.getCenter();
            /* clear the center child comps */
            center.getChildren().clear();
            /*
             * create the page and put it in the center layout area
             */
            Executions.createComponents(getZulNavigation(), center, null);

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    private String getZulNavigation() {
        return this.zulNavigation;
    }

    @Override
    public void setZulNavigation(String zulNavigation) {
        this.zulNavigation = zulNavigation;
        if (!StringUtils.isEmpty(zulNavigation)) {
            addEventListener("onClick", this);
        }
    }
}

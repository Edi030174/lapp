package net.lintasarta.idoss.common.menu.dropdown;

import net.lintasarta.idoss.common.menu.util.ILabelElement;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:41:11 PM
 */
class DefaultDropDownMenuItem extends Menuitem implements EventListener, Serializable, ILabelElement {
    private transient static final Logger logger = Logger.getLogger(DefaultDropDownMenuItem.class);

    private String zulNavigation;

    @Override
    public void onEvent(Event event) throws Exception {

        try {
            // TODO get the parameter for working with tabs from the application
            // params
            int workWithTabs = 1;

            if (workWithTabs == 1) {

                /* get an instance of the borderlayout defined in the zul-file */
                Borderlayout bl = (Borderlayout) Path.getComponent("/outerIndexWindow/borderlayoutMain");
                /* get an instance of the searched CENTER layout area */
                Center center = bl.getCenter();

                Tabs tabs = (Tabs) center.getFellow("divCenter").getFellow("tabBoxIndexCenter").getFellow("tabsIndexCenter");

                // Check if the tab is already open, if not than create them
                Tab checkTab = null;
                try {
                    checkTab = (Tab) tabs.getFellow("tab_" + this.getLabel().trim());
                    checkTab.setSelected(true);
                } catch (ComponentNotFoundException ex) {
                    // Ignore if can not get tab.
                }

                if (checkTab == null) {
                    Tab tab = new Tab();
                    tab.setId("tab_" + this.getLabel().trim());
                    tab.setLabel(this.getLabel().trim());
                    tab.setClosable(true);

                    tab.setParent(tabs);

                    Tabpanels tabpanels = (Tabpanels) center.getFellow("divCenter").getFellow("tabBoxIndexCenter").getFellow("tabsIndexCenter").getFellow("tabpanelsBoxIndexCenter");
                    Tabpanel tabpanel = new Tabpanel();
                    tabpanel.setHeight("100%");
                    tabpanel.setStyle("padding: 0px;");
                    tabpanel.setParent(tabpanels);

                    Executions.createComponents(getZulNavigation(), tabpanel, null);
                    tab.setSelected(true);
                }
            } else {
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
            }

            if (logger.isDebugEnabled()) {
                logger.debug("-->[" + getId() + "] calling zul-file: " + getZulNavigation());
            }
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }

    }

    private String getZulNavigation() {
        return this.zulNavigation;
    }

    public void setZulNavigation(String zulNavigation) {
        this.zulNavigation = zulNavigation;
        if (!StringUtils.isEmpty(zulNavigation)) {
            addEventListener("onClick", this);
        }
    }
}

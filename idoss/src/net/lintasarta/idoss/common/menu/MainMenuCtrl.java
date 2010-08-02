package net.lintasarta.idoss.common.menu;

import net.lintasarta.idoss.common.menu.dropdown.ZkossDropDownMenuFactory;
import net.lintasarta.idoss.common.menu.tree.ZkossTreeMenuFactory;
import net.lintasarta.idoss.webui.util.WindowBaseCtrl;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:11:20 PM
 */
public class MainMenuCtrl extends WindowBaseCtrl implements Serializable {
    private transient static final Logger logger = Logger.getLogger(MainMenuCtrl.class);

    private Window mainMenuWindow;

    private static String bgColor = "D6DCDE";
    private static String bgColorInner = "white";

    public void onCreate$mainMenuWindow(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        doOnCreateCommon(getMainMenuWindow(), event);

        createMenu();
    }

    private void createMenu() throws InterruptedException {

        Div div = new Div();
        div.setWidth("100%");
        div.setHeight("100%");
        div.setStyle("padding:5px;" + "backgound-color: " + bgColorInner);
        div.setParent(getMainMenuWindow().getFellowIfAny("groupbox_menu"));

        div.appendChild(createSeparator(false));

        Hbox hbox = new Hbox();
        hbox.setStyle("backgound-color: " + bgColorInner);
        div.appendChild(hbox);
        Toolbarbutton toolbarbutton = new Toolbarbutton();
        hbox.appendChild(toolbarbutton);
        toolbarbutton.setId("btnMainMenuExpandAll");
        toolbarbutton.setImage("/images/icons/folder_open_16x16.gif");
        toolbarbutton.setTooltiptext(Labels.getLabel("btnFolderExpand.tooltiptext"));
        toolbarbutton.addEventListener("onClick", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                onClick$btnMainMenuExpandAll(event);
            }
        });

        toolbarbutton = new Toolbarbutton();
        hbox.appendChild(toolbarbutton);
        toolbarbutton.setId("btnMainMenuCollapseAll");

        toolbarbutton.setImage("/images/icons/folder_closed2_16x16.gif");
        toolbarbutton.setTooltiptext(Labels.getLabel("btnFolderCollapse.tooltiptext"));
        toolbarbutton.addEventListener("onClick", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                onClick$btnMainMenuCollapseAll(event);
            }
        });

        toolbarbutton = new Toolbarbutton();
        hbox.appendChild(toolbarbutton);
        toolbarbutton.setId("btnMainMenuChange");

        toolbarbutton.setImage("/images/icons/menu_16x16.gif");
        toolbarbutton.setTooltiptext(Labels.getLabel("btnMainMenuChange.tooltiptext"));
        toolbarbutton.addEventListener("onClick", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                onClick$btnMainMenuChange(event);
            }
        });

        toolbarbutton = new Toolbarbutton();
        hbox.appendChild(toolbarbutton);
        toolbarbutton.setId("btnMainMenuDocumentation");

        toolbarbutton.setImage("/images/icons/icon-pdf_16x16.png");
        toolbarbutton.setTooltiptext(Labels.getLabel("btnMainMenuDocumentation.tooltiptext"));
        toolbarbutton.addEventListener("onClick", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                Executions.getCurrent().sendRedirect("http://www.lintasarta.net/portals/0/file/solutions.pdf", "_blank");
            }
        });

        Separator separator = createSeparator(false);
        separator.setWidth("97%");
        separator.setStyle("background-color: " + bgColorInner);
        separator.setBar(false);
        div.appendChild(separator);
        separator = createSeparator(false);
        separator.setWidth("97%");
        separator.setBar(true);
        div.appendChild(separator);

        Tree tree = new Tree();
        div.appendChild(tree);

        tree.setZclass("z-dottree");
        tree.setStyle("border: none");

        Treechildren treechildren = new Treechildren();
        tree.appendChild(treechildren);

        ZkossTreeMenuFactory.addMainMenu(treechildren);

        Separator sep1 = new Separator();
        sep1.setWidth("97%");
        sep1.setBar(false);
        sep1.setParent(div);

        Separator sep2 = new Separator();
        sep2.setWidth("97%");
        sep2.setBar(true);
        sep2.setParent(div);

        Separator sep3 = new Separator();
        sep3.setWidth("97%");
        sep3.setBar(false);
        sep3.setParent(div);

        showPage("/WEB-INF/pages/welcome.zul", "Start");
    }

    private static Separator createSeparator(boolean withBar) {

        Separator sep = new Separator();
        sep.setStyle("backgound-color: " + bgColorInner);
        sep.setBar(withBar);

        return sep;
    }

    private void showPage(String zulFilePathName, String tabName) throws InterruptedException {

        try {
            // TODO get the parameter for working with tabs from the application
            int workWithTabs = 1;

            if (workWithTabs == 1) {

                Borderlayout bl = (Borderlayout) Path.getComponent("/outerIndexWindow/borderlayoutMain");
                Center center = bl.getCenter();
                Tabs tabs = (Tabs) center.getFellow("divCenter").getFellow("tabBoxIndexCenter").getFellow("tabsIndexCenter");

                Tab checkTab = null;
                try {
                    checkTab = (Tab) tabs.getFellow("tab_" + tabName.trim());
                    checkTab.setSelected(true);
                } catch (ComponentNotFoundException ex) {
                }

                if (checkTab == null) {

                    Tab tab = new Tab();
                    tab.setId("tab_" + tabName.trim());
                    tab.setLabel(tabName.trim());
                    tab.setClosable(true);

                    tab.setParent(tabs);

                    Tabpanels tabpanels = (Tabpanels) center.getFellow("divCenter").getFellow("tabBoxIndexCenter").getFellow("tabsIndexCenter").getFellow("tabpanelsBoxIndexCenter");
                    Tabpanel tabpanel = new Tabpanel();
                    tabpanel.setHeight("100%");
                    tabpanel.setStyle("padding: 0px;");
                    tabpanel.setParent(tabpanels);

                    Executions.createComponents(zulFilePathName, tabpanel, null);
                    tab.setSelected(true);
                }
            } else {
                Borderlayout bl = (Borderlayout) Path.getComponent("/outerIndexWindow/borderlayoutMain");
                Center center = bl.getCenter();
                center.getChildren().clear();
                Executions.createComponents(zulFilePathName, center, null);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("--> calling zul-file: " + zulFilePathName);
            }
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    public Window getMainMenuWindow() {
        return mainMenuWindow;
    }

    public void setMainMenuWindow(Window mainMenuWindow) {
        this.mainMenuWindow = mainMenuWindow;
    }

    public void onClick$btnMainMenuExpandAll(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doCollapseExpandAll(getMainMenuWindow(), true);
    }

    public void onClick$btnMainMenuCollapseAll(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doCollapseExpandAll(getMainMenuWindow(), false);
    }

    public void onClick$btnMainMenuChange(Event event) throws Exception {

        Checkbox cb = ((Checkbox) Path.getComponent("/outerIndexWindow/CBtreeMenu"));
        cb.setChecked(false);

        Borderlayout bl = (Borderlayout) Path.getComponent("/outerIndexWindow/borderlayoutMain");
        West west = bl.getWest();
        west.setVisible(false);

        North north = bl.getNorth();
        north.setFlex(true);

        Div div = (Div) north.getFellow("divDropDownMenu");

        Menubar menuBar = (Menubar) div.getFellow("mainMenuBar");
        menuBar.setVisible(true);

        ZkossDropDownMenuFactory.addDropDownMenu(menuBar);

        Menuitem changeToTreeMenu = new Menuitem();
        changeToTreeMenu.setLabel(Labels.getLabel("menu_Item_backToTree"));
        changeToTreeMenu.setImage("/images/icons/refresh2_yellow_16x16.gif");
        changeToTreeMenu.setParent(menuBar);
        changeToTreeMenu.addEventListener("onClick", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                Borderlayout bl = (Borderlayout) Path.getComponent("/outerIndexWindow/borderlayoutMain");
                West west = bl.getWest();
                west.setVisible(true);

                North north = bl.getNorth();

                Div div = (Div) north.getFellow("divDropDownMenu");

                Menubar menuBar = (Menubar) div.getFellow("mainMenuBar");
                menuBar.getChildren().clear();
                menuBar.setVisible(false);
                north.setFlex(false);

                Checkbox cb = ((Checkbox) Path.getComponent("/outerIndexWindow/CBtreeMenu"));
                cb.setChecked(true);

                Window win = (Window) Path.getComponent("/outerIndexWindow");
                win.invalidate();

            }
        });

        Window win = (Window) Path.getComponent("/outerIndexWindow");
        win.invalidate();

    }

    private void doCollapseExpandAll(Component component, boolean isOpenUp) {
        if (component instanceof Treeitem) {
            Treeitem treeitem = (Treeitem) component;
            treeitem.setOpen(isOpenUp);
        }
        Collection<?> com = component.getChildren();
        if (com != null) {
            for (Object aCom : com) {
                doCollapseExpandAll((Component) aCom, isOpenUp);
            }
        }
    }
}
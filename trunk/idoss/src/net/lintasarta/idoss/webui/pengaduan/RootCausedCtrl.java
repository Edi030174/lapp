package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.pengaduan.model.RootCausedTreeItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.service.RootCausedService;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 9, 2010
 * Time: 4:36:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class RootCausedCtrl extends GFCBaseCtrl implements Serializable {

    private transient static final Logger logger = Logger.getLogger(RootCausedCtrl.class);

    protected Window window_RootCaused;
    protected Tab tab_TambahRootCaused;
    protected Tabpanel tabpanel_TambahRootCaused;
    protected Tree tree_RootCaused;
    protected Treecol treecol_RootCaused;
    protected Treeitem treeitem_RootCaused;
    protected RootCausedCtrl rootCausedCtrl;

    private transient PRootCaused pRootCaused;
    private transient RootCausedService rootCausedService;

    public RootCausedCtrl() {
        super();

        if (logger.isDebugEnabled()) {
			logger.debug("--> super()");
		}
    }

    public void onCreate$window_RootCaused (Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        tab_TambahRootCaused.setVisible(true);
        tabpanel_TambahRootCaused.setVisible(true);

        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("pRootCaused")) {
            PRootCaused pRootCaused = (PRootCaused) args.get("pRootCaused");
            setpRootCaused(pRootCaused);
        }


        tree_RootCaused.setModel(new SimpleTreeModel((SimpleTreeNode) getRootCausedService().getAllRootCaused()) );
        tree_RootCaused.setTreeitemRenderer(new RootCausedTreeItemRenderer());
        doShowDialog(getpRootCaused());
    }

    private void doShowDialog(PRootCaused pRootCaused) throws InterruptedException {

        try {
            window_RootCaused.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }

    }

    public void onSelect$tab_TambahRootCaused(Event event) throws Exception{
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        PRootCaused pRootCaused = getpRootCaused();
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (pRootCaused != null) {
            map.put("pRootCaused", pRootCaused);
        } else {
            map.put("pRootCaused", getRootCausedService().getNewRootCaused());
        }
        map.put("rootCausedCtrl", this);


        Tabpanel orderTab = (Tabpanel) Path.getComponent("/window_RootCaused/tabpanel_TambahRootCaused");
        orderTab.getChildren().clear();

        Panel panel = new Panel();
        Panelchildren pChildren = new Panelchildren();

        panel.appendChild(pChildren);
        orderTab.appendChild(panel);

        Executions.createComponents("/WEB-INF/pages/pengaduan/tambahRootCaused.zul", pChildren, map);

    }

    public void onClose$window_RootCaused(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        window_RootCaused.onClose();

    }

    public PRootCaused getpRootCaused() {
        return pRootCaused;
    }

    public void setpRootCaused(PRootCaused pRootCaused) {
        this.pRootCaused = pRootCaused;
    }

    public RootCausedService getRootCausedService() {
        return rootCausedService;
    }

    public void setRootCausedService(RootCausedService rootCausedService) {
        this.rootCausedService = rootCausedService;
    }
}

package net.lintasarta.idoss.webui.index;

import net.lintasarta.idoss.webui.util.FDDateFormat;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.security.model.UserSession;
import net.lintasarta.security.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 2, 2010
 * Time: 1:48:49 PM
 */
public class IndexCtrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(IndexCtrl.class);

    protected Menubar mainMenuBar;

    protected Column statusBarAppVersion;
    protected Column statusBarColRole;
    protected Column statusBarColUser;
    protected Column statusBarColJob;
    protected Column colDate;

    protected Intbox currentDesktopHeight;
    protected Intbox currentDesktopWidth;
    protected Checkbox CBtreeMenu;

    private transient LoginService loginService;

    private int centerAreaHeightOffset = 50;

    public IndexCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("/*--> super()*/");
        }
    }

    public void onCreate$outerIndexWindow(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        mainMenuBar.setVisible(false);

        createMainTreeMenu();

        setUserSession();

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 12);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 1900 + 110);

        date.setTime(cal.getTimeInMillis());
        statusBarAppVersion.setLabel("IDOSS v1.0.0 / build: " + FDDateFormat.getDateFormater().format(date));
        statusBarColUser.setLabel(getUserWorkspace().getUserSession().getEmployeeName());
        statusBarColRole.setLabel(getUserWorkspace().getUserSession().getJobPositionCode());
        statusBarColJob.setLabel(getUserWorkspace().getUserSession().getDepartment() + " " + getUserWorkspace().getUserSession().getJobLocation());
        colDate.setLabel(new Date().toString());
    }

    public void onClientInfo(ClientInfoEvent event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("Current desktop height :" + event.getDesktopHeight());
            logger.debug("Current desktop width  :" + event.getDesktopWidth());
        }

        setCurrentDesktopHeight(event.getDesktopHeight() - centerAreaHeightOffset);
        setCurrentDesktopWidth(event.getDesktopWidth());

    }

    public String doGetLoggedInUser() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void onClick$btnLogout() throws IOException {

        if (logger.isDebugEnabled()) {
            logger.debug("/*-->*/");
        }

        getUserWorkspace().doLogout();
    }

    private void setUserSession() {

        getUserWorkspace().setUserSession((UserSession) Executions.getCurrent().getSession().getAttribute("userSession"));
    }

    private void createMainTreeMenu() {

        Borderlayout bl = (Borderlayout) Path.getComponent("/outerIndexWindow/borderlayoutMain");

        West west = bl.getWest();
        west.setFlex(true);
        west.getChildren().clear();

        Executions.createComponents("/WEB-INF/pages/mainTreeMenu.zul", west, null);
    }

    public void setCurrentDesktopHeight(int desktopHeight) {
        if (isTreeMenu()) {
            this.currentDesktopHeight.setValue(Integer.valueOf(desktopHeight));
        } else {
            this.currentDesktopHeight.setValue(Integer.valueOf(desktopHeight - 30));
        }
    }

    public int getCurrentDesktopHeight() {
        return currentDesktopHeight.getValue();
    }

    public void setCurrentDesktopWidth(int currentDesktopWidth) {
        this.currentDesktopWidth.setValue(Integer.valueOf(currentDesktopWidth));
    }

    public int getCurrentDesktopWidth() {
        return currentDesktopWidth.getValue();
    }

    public void setTreeMenu(boolean treeMenu) {
        this.CBtreeMenu.setChecked(treeMenu);
    }

    public boolean isTreeMenu() {
        return CBtreeMenu.isChecked();
    }
}
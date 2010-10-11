package net.lintasarta.idoss.webui.login;

import net.lintasarta.idoss.webui.util.SSOUtils;
import net.lintasarta.idoss.webui.util.WindowBaseCtrl;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Oct 1, 2010
 * Time: 3:50:30 PM
 */
public class ZkLoginDialogCtrlNoSSO extends WindowBaseCtrl implements Serializable {

    private transient final static Logger logger = Logger.getLogger(ZkLoginDialogCtrl.class);

    protected Window loginwin;
    protected Textbox txtbox_Username;
    protected Textbox txtbox_Password;

    public ZkLoginDialogCtrlNoSSO() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super() ");
        }
    }

    public void doOnCreateCommon(Window w) throws Exception {
        binder = new AnnotateDataBinder(w);
        binder.loadAll();
    }

    public void onCreate$loginwin(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doOnCreateCommon(loginwin);

        txtbox_Username.focus();

        loginwin.setShadow(false);
        loginwin.doModal();

//        SSOUtils.loginNoSSO();
    }

    public void onClick$button_ZKLoginDialog_Close() throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("-->");
        }
        Executions.sendRedirect("/j_spring_logout");
    }
}

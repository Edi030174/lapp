package net.lintasarta.idoss.webui.login;

import net.lintasarta.idoss.webui.util.SSOUtils;
import net.lintasarta.idoss.webui.util.WindowBaseCtrl;
import net.lintasarta.security.model.UserSession;
import net.lintasarta.security.service.LoginService;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 23, 2010
 * Time: 11:18:50 AM
 */
public class ZkLoginDialogCtrl extends WindowBaseCtrl implements Serializable {

    private transient final static Logger logger = Logger.getLogger(ZkLoginDialogCtrl.class);

    protected Window loginwin;
    protected Textbox txtbox_Username;

    public ZkLoginDialogCtrl() {
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

//        UserSession userSession = SSOUtils.login();
        UserSession userSession = SSOUtils.loginNoSSO();
        if (userSession != null) {
            txtbox_Username.setValue(userSession.getEmployeeNo());
            Clients.submitForm("f");
        }
    }

    public void onClick$button_ZKLoginDialog_Close() throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("-->");
        }
        Executions.sendRedirect("/j_spring_logout");
    }
}

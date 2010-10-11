package net.lintasarta.idoss.webui.login;

import net.lintasarta.idoss.webui.util.SSOUtils;
import net.lintasarta.idoss.webui.util.WindowBaseCtrl;
import net.lintasarta.pengaduan.model.PApplication;
import net.lintasarta.security.model.UserSession;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Oct 1, 2010
 * Time: 3:50:30 PM
 */
public class ZkLoginDialogCtrlUMC extends WindowBaseCtrl implements Serializable {

    private transient final static Logger logger = Logger.getLogger(ZkLoginDialogCtrl.class);

    protected Window loginwin;
    protected Textbox txtbox_Username;
    protected Textbox txtbox_Password;
    protected Button loginumc;

    public ZkLoginDialogCtrlUMC() {
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

//        PApplication pApplication = (PApplication) txtbox_Username.


//        System.out.println("opppppppppppppppppppppp"+pApplication.getEmployee_name());
        loginwin.setShadow(false);
        loginwin.doModal();

//        String usrName = login WitUMC();
        UserSession userSession = SSOUtils.loginNoSSO("OKR");
        if (userSession != null) {
            txtbox_Username.setValue("SU_PENGADUAN");
            txtbox_Password.setValue("idoss");
            Clients.showBusy("Wait for IDOSS Authorization");
            Clients.submitForm("f");
        }
    }

//    public void onClick$loginumc(Event event) throws Exception {
//        if (logger.isDebugEnabled()) {
//            logger.debug("--> " + event.toString());
//        }
//        String username = txtbox_Username.getValue();
//        UserSession userSession = SSOUtils.loginNoSSO(username);
//        if (userSession != null) {
//
//            // disisni ada logik
//            txtbox_Username.setValue("INPUT_HELP");
//            txtbox_Password.setValue("idoss");
//            Clients.showBusy("Wait for IDOSS Authorization");
//            Clients.submitForm("f");
//        }
//    }
    

    public void onClick$button_ZKLoginDialog_Close() throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("-->");
        }
        Executions.sendRedirect("/j_spring_logout");
    }
}
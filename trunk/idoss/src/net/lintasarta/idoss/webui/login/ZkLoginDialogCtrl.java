package net.lintasarta.idoss.webui.login;

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
 * Date: Jun 23, 2010
 * Time: 11:18:50 AM
 */
public class ZkLoginDialogCtrl extends WindowBaseCtrl implements Serializable {
//    protected transient AnnotateDataBinder binder;

    private transient final static Logger logger = Logger.getLogger(ZkLoginDialogCtrl.class);

    protected Window loginwin;
    protected Textbox txtbox_Username;

//    private transient LoginService loginService;
//
//    public LoginService getLoginService() {
//        if (loginService == null) {
//            loginService = (LoginService) SpringUtil.getBean("loginService");
//            setLoginService(loginService);
//        }
//        return loginService;
//    }
//
//    public void setLoginService(LoginService loginService) {
//        this.loginService = loginService;
//    }

    public ZkLoginDialogCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super() ");
        }
//        HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
//        String servletPath = request.getServletPath();
//        String userUrl = request.getRequestURL().toString();
//        if (servletPath.equalsIgnoreCase("/zkloginDialog.zul")) {
//            userUrl = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + request.getContextPath() + "/pages/indexLogin.zul";
//        }
//        HttpSession session = request.getSession();
//        DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
//        if (savedRequest != null) {
//            String queryString = savedRequest.getQueryString();
//            String ticketId = queryString.substring(queryString.indexOf("="));
//
////            UserSession userSession = getLoginService().getUserSession(savedRequest.getRequestURL(), ticketId);
//            UserSession userSession = getLoginService().getUserSession(request.getRequestURL().toString(), ticketId);
//            if (ticketId == null) {
//                Executions.sendRedirect("http://portal/sso2/SignIn.aspx?SenderUrl=" + userUrl);
//            }
//        } else {
//            Executions.sendRedirect("http://portal/sso2/SignIn.aspx?SenderUrl=" + userUrl);
//        }
//        System.out.println("ZkLoginDialogCtrl.ZkLoginDialogCtrl");
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
    }

    public void onClick$button_ZKLoginDialog_Close() throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("-->");
        }
        Executions.sendRedirect("/j_spring_logout");
    }

//    public void onClick$button_ZKLoginDialog_Login() {
//        String userName = txtbox_Username.getValue();
//        if (logger.isDebugEnabled()) {
//            logger.debug("--> onClick userName = " + userName);
//        }
////        Clients.submitForm("j_spring_security_check");
//        Executions.sendRedirect("/j_spring_security_check");
//    }

//    public void onCreate$button_ZKLoginDialog_Login() {
//        String userName = txtbox_Username.getValue();
//        if (logger.isDebugEnabled()) {
//            logger.debug("--> onCreate userName = " + userName);
//        }
//    }
}

package net.lintasarta.idoss.webui.util;

import net.lintasarta.security.model.UserSession;
import net.lintasarta.security.service.LoginService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.SpringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Aug 12, 2010
 * Time: 5:06:44 PM
 */
public class SSOUtils {
    public static UserSession login() {
        HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();

//        String action = request.getParameter("action");
        String ticketId = request.getParameter("LassoTicketId");
        String userUrl = request.getRequestURL().toString();
        if (ticketId != null) {
            LoginService loginService = (LoginService) SpringUtil.getBean("loginService");
            Session session = Executions.getCurrent().getSession();
            UserSession userSession = loginService.getUserSession(userUrl, ticketId);
            session.setAttribute("userSession", userSession);
            return userSession;
        } else {
            userUrl = "http://portal/sso2/SignIn.aspx?SenderUrl=" + userUrl;
            Executions.sendRedirect(userUrl);
        }
        return null;
    }
}

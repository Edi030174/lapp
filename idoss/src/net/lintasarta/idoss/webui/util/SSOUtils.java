package net.lintasarta.idoss.webui.util;

import net.lintasarta.security.dao.PApplicationUserDAO;
import net.lintasarta.security.model.UserSession;
import net.lintasarta.security.model.VHrEmployee;
import net.lintasarta.security.service.LoginService;
import net.lintasarta.security.util.LoginConstants;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zkplus.spring.SpringUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

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

    public static UserSession loginNoSSO() {

//        String employeeNo = "79040893";
//        String userName = "79040893-ZULHELMY";
//        String employeeName = "ZULHELMY";

//        String employeeNo = "73950481";
//        String userName = "73950481-RINA KUSMAYANTI";
//        String employeeName = "RINA KUSMAYANTI";

        String employeeNo = "84070998";
        String userName = "84070998-KURNIAWAN DWI PRASETYO";
        String employeeName = "KURNIAWAN DWI PRASETYO";

        LoginService loginService = (LoginService) SpringUtil.getBean("loginService");
        Session session = Executions.getCurrent().getSession();
        UserSession userSession = loginService.getUserSessionNoSSO(employeeNo, userName, employeeName);
        session.setAttribute("userSession", userSession);
        return userSession;
    }
}
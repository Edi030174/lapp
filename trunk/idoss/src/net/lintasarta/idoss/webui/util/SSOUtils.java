package net.lintasarta.idoss.webui.util;

import net.lintasarta.security.dao.PApplicationUserDAO;
import net.lintasarta.security.model.UserSession;
import net.lintasarta.security.model.VHrEmployee;
import net.lintasarta.security.service.LoginService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zkplus.spring.SpringUtil;

import javax.servlet.http.HttpServletRequest;

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
        UserSession userSession = new UserSession();
//        userSession.setEmployeeRole("pemohonpengadu");
//        userSession.setEmployeeRole("managerpemohon");
//        userSession.setEmployeeRole("gmpemohon");
//        userSession.setEmployeeRole("asmandukophar");
//        userSession.setEmployeeRole("managerdukophar");
//        userSession.setEmployeeRole("gmdukophar");
        userSession.setEmployeeRole("helpdeskpelaksana");
        userSession.setEmployeeNo("79040893");
        userSession.setEmployeeName("ZULHELMY");
        userSession.setOrganizationid(1413);
        userSession.setUserName("79040893-ZULHELMY");
        userSession.setDepartment("OPERASI TI..");
        userSession.setJobLocation("MENARA THAMRIN");

        Session session = Executions.getCurrent().getSession();
        session.setAttribute("userSession", userSession);
        return userSession;
    }

    public static UserSession loginNoSSO(String initial) {
        LoginService loginService = (LoginService) SpringUtil.getBean("loginService");
        PApplicationUserDAO pApplicationUserDAO = (PApplicationUserDAO) SpringUtil.getBean("pApplicationUserDAO");

        String employeeNo = pApplicationUserDAO.getEmployeeNoByUserName(initial);

        VHrEmployee employee = loginService.getVHrEmployee(employeeNo);

        String employeeRole = loginService.getAuthorization(employee);

        UserSession userSession = new UserSession();
        userSession.setEmployeeRole(employeeRole);
        userSession.setEmployeeNo(employee.getEmployee_no());
        userSession.setEmployeeName(employee.getEmployee_name());
        userSession.setOrganizationid(employee.getP_organization_id().intValue());
        userSession.setUserName(employee.getEmployee_name());
        userSession.setDepartment(employee.getOrganization_code());
        userSession.setJobLocation(employee.getJob_location());
        userSession.setWinHeight("800px");

        Session session = Executions.getCurrent().getSession();
        session.setAttribute("userSession", userSession);
        return userSession;
    }
}
package net.lintasarta.security.service;

import net.lintasarta.security.model.UserSession;
import net.lintasarta.security.model.VHrEmployee;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 21, 2010
 * Time: 1:16:28 PM
 */
public interface LoginService {
    UserSession getUserSession(String userUrl, String ticketId);
    UserSession getUserSessionNoSSO(String employeeNo, String userName, String employeeName);
}

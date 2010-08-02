package net.lintasarta.security.service.impl;

import net.lintasarta.security.dao.VHrEmployeeDAO;
import net.lintasarta.security.model.UserSession;
import net.lintasarta.security.model.VHrEmployee;
import net.lintasarta.security.service.LoginService;
import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.http.HTTPConstants;
import org.tempuri.ValidateTicketStub;

import java.rmi.RemoteException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 21, 2010
 * Time: 1:19:31 PM
 */
public class LoginServiceImpl implements LoginService {
    private VHrEmployeeDAO vHrEmployeeDAO;

    public VHrEmployeeDAO getvHrEmployeeDAO() {
        return vHrEmployeeDAO;
    }

    public void setvHrEmployeeDAO(VHrEmployeeDAO vHrEmployeeDAO) {
        this.vHrEmployeeDAO = vHrEmployeeDAO;
    }

    public String validateTicketId(String requestUrl, String ticketId) {
        try {
            ValidateTicketStub stub = new ValidateTicketStub("http://portal/sso2/validateTicket.asmx");
            stub._getServiceClient().getOptions().setProperty(HTTPConstants.CHUNKED, false);
            ValidateTicketStub.Validate val = new ValidateTicketStub.Validate();
            val.setRequestUrl(requestUrl);
            val.setLassoTicketId(ticketId);
            ValidateTicketStub.ValidateResponse resp = stub.Validate(val);
            return resp.getValidateResult();
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public VHrEmployee getVHrEmployee(String employeeNo) {
        List<VHrEmployee> vHrEmployees = getvHrEmployeeDAO().getVHrEmployeeByEmployeeNo(employeeNo);

        for (VHrEmployee vHrEmployee : vHrEmployees) {
            return vHrEmployee;
        }
        return null;
    }

    public UserSession getUserSession(String userUrl, String ticketId) {
        String result = validateTicketId(userUrl, ticketId);
        StringTokenizer token = new StringTokenizer(result, "|");
        if (!token.nextToken().equalsIgnoreCase("ERROR")) {
            String employeeNo = token.nextToken();
            String userName = token.nextToken();
            String employeeName = token.nextToken();
            VHrEmployee vHrEmployee = getVHrEmployee(employeeNo);
            if (vHrEmployee != null) {
//            String department = "N/A";
//            String jobLocation = "JAKARTA";
//            int orgId = 0;
                String department = vHrEmployee.getOrganization_code();
                String jobLocation = vHrEmployee.getJob_location();
                int orgId = vHrEmployee.getP_organization_id().intValue();
                UserSession userSession = new UserSession();
                userSession.setDepartment(department);
                userSession.setEmployeeName(employeeName);
                userSession.setEmployeeNo(employeeNo);
                userSession.setJobLocation(jobLocation);
                userSession.setOrganizationid(orgId);
                userSession.setUserName(userName);
                userSession.setWinHeight("800px");

                return userSession;
            }
        }
        return null;
    }

    public UserSession getUserSessionWithoutSSO(String employeeNo) {
        VHrEmployee vHrEmployee = getVHrEmployee(employeeNo);
        if (vHrEmployee != null) {
//            String department = "N/A";
//            String jobLocation = "JAKARTA";
//            int orgId = 0;
            String department = vHrEmployee.getOrganization_code();
            String jobLocation = vHrEmployee.getJob_location();
            int orgId = vHrEmployee.getP_organization_id().intValue();
            UserSession userSession = new UserSession();
            userSession.setDepartment(department);
            userSession.setEmployeeName(vHrEmployee.getEmployee_name());
            userSession.setEmployeeNo(employeeNo);
            userSession.setJobLocation(jobLocation);
            userSession.setOrganizationid(orgId);
            userSession.setUserName(vHrEmployee.getNama());
            userSession.setWinHeight("800px");

            return userSession;
        }
        return null;
    }
}

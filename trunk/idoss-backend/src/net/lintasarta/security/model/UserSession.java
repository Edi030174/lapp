package net.lintasarta.security.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 21, 2010
 * Time: 1:41:30 PM
 */
public class UserSession implements Serializable {

    private String employeeRole;
    private String employeeNo;
    private String userName;
    private String employeeName;
    private String department;
    private String jobLocation;
    private int organizationid;
    private String winHeight;

    public UserSession() {
    }

    public UserSession(String employeeRole, String employeeNo, String userName, String employeeName, String department, String jobLocation, int organizationid, String winHeight) {
        this.employeeRole = employeeRole;
        this.employeeNo = employeeNo;
        this.userName = userName;
        this.employeeName = employeeName;
        this.department = department;
        this.jobLocation = jobLocation;
        this.organizationid = organizationid;
        this.winHeight = winHeight;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public int getOrganizationid() {
        return organizationid;
    }

    public void setOrganizationid(int organizationid) {
        this.organizationid = organizationid;
    }

    public String getWinHeight() {
        return winHeight;
    }

    public void setWinHeight(String winHeight) {
        this.winHeight = winHeight;
    }
}

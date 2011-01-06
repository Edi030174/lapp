package net.lintasarta.security.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 21, 2010
 * Time: 1:41:30 PM
 */
public class UserSession implements Serializable {

    private List<Integer> employeeRole;
    private String employeeNo;
    private String userName;
    private String employeeName;
    private String department;
    private String passwordMask;
    private String jobLocation;
    private int organizationid;
    private String winHeight;
    private String jobPositionCode;
    private String emailAddr;

    public UserSession() {
    }

    public UserSession(List<Integer> employeeRole, String employeeNo, String userName, String employeeName, String department, String passwordMask, String jobLocation, int organizationid, String winHeight, String jobPositionCode, String emailAddr) {
        this.employeeRole = employeeRole;
        this.employeeNo = employeeNo;
        this.userName = userName;
        this.employeeName = employeeName;
        this.department = department;
        this.passwordMask = passwordMask;
        this.jobLocation = jobLocation;
        this.organizationid = organizationid;
        this.winHeight = winHeight;
        this.jobPositionCode = jobPositionCode;
        this.emailAddr = emailAddr;

    }

    public List<Integer> getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(List<Integer> employeeRole) {
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

    public String getPasswordMask() {
        return passwordMask;
    }

    public void setPasswordMask(String passwordMask) {
        this.passwordMask = passwordMask;
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

    public String getJobPositionCode() {
        return jobPositionCode;
    }

    public void setJobPositionCode(String jobPositionCode) {
        this.jobPositionCode = jobPositionCode;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }
}

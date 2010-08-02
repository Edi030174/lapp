package net.lintasarta.security.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 21, 2010
 * Time: 12:28:56 PM
 */
public class VHrEmployee implements Serializable {
    private String employee_no;
    private String employee_name;
    private BigDecimal p_organization_id;
    private Date inactive_date;
    private String organization_code;
    private String nama;
    private BigDecimal p_job_position_id;
    private String job_position_code;
    private BigDecimal p_job_location_id;
    private String job_location;
    private String e_mail_addr;
    private BigDecimal status;

    public VHrEmployee() {
    }

    public VHrEmployee(String employee_no, String employee_name, BigDecimal p_organization_id, Date inactive_date, String organization_code, String nama, BigDecimal p_job_position_id, String job_position_code, BigDecimal p_job_location_id, String job_location, String e_mail_addr, BigDecimal status) {
        this.employee_no = employee_no;
        this.employee_name = employee_name;
        this.p_organization_id = p_organization_id;
        this.inactive_date = inactive_date;
        this.organization_code = organization_code;
        this.nama = nama;
        this.p_job_position_id = p_job_position_id;
        this.job_position_code = job_position_code;
        this.p_job_location_id = p_job_location_id;
        this.job_location = job_location;
        this.e_mail_addr = e_mail_addr;
        this.status = status;
    }

    public String getEmployee_no() {
        return employee_no;
    }

    public void setEmployee_no(String employee_no) {
        this.employee_no = employee_no;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public BigDecimal getP_organization_id() {
        return p_organization_id;
    }

    public void setP_organization_id(BigDecimal p_organization_id) {
        this.p_organization_id = p_organization_id;
    }

    public Date getInactive_date() {
        return inactive_date;
    }

    public void setInactive_date(Date inactive_date) {
        this.inactive_date = inactive_date;
    }

    public String getOrganization_code() {
        return organization_code;
    }

    public void setOrganization_code(String organization_code) {
        this.organization_code = organization_code;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigDecimal getP_job_position_id() {
        return p_job_position_id;
    }

    public void setP_job_position_id(BigDecimal p_job_position_id) {
        this.p_job_position_id = p_job_position_id;
    }

    public String getJob_position_code() {
        return job_position_code;
    }

    public void setJob_position_code(String job_position_code) {
        this.job_position_code = job_position_code;
    }

    public BigDecimal getP_job_location_id() {
        return p_job_location_id;
    }

    public void setP_job_location_id(BigDecimal p_job_location_id) {
        this.p_job_location_id = p_job_location_id;
    }

    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public String getE_mail_addr() {
        return e_mail_addr;
    }

    public void setE_mail_addr(String e_mail_addr) {
        this.e_mail_addr = e_mail_addr;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }
}

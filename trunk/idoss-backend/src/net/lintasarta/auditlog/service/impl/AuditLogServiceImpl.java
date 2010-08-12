package net.lintasarta.auditlog.service.impl;

import net.lintasarta.auditlog.dao.AuditLogDAO;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 12, 2010
 * Time: 5:04:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuditLogServiceImpl {
    private AuditLogDAO auditLogDAO;

    public AuditLogDAO getAuditLogDAO() {
        return auditLogDAO;
    }

    public void setAuditLogDAO(AuditLogDAO auditLogDAO) {
        this.auditLogDAO = auditLogDAO;
    }
}

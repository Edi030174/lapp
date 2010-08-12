package net.lintasarta.auditlog.service;

import net.lintasarta.auditlog.model.AuditLog;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 12, 2010
 * Time: 5:00:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AuditLogService {
    void createHPermohonan (AuditLog auditLog);
    void createHVerifikasi (AuditLog auditLog);
    void createHPelaksanaan (AuditLog auditLog);
    void createHPenangananGangguan (AuditLog auditLog);
}

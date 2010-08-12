package net.lintasarta.auditlog.dao;

import net.lintasarta.auditlog.model.AuditLog;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 12, 2010
 * Time: 4:31:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AuditLogDAO {

    void createHPenangananGangguan(AuditLog auditLog);

    void createHPermohonan(AuditLog auditLog);

    void createHVerifikasi(AuditLog auditLog);

    void createHPelaksanaan(AuditLog auditLog);
}

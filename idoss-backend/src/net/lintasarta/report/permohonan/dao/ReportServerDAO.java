package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportServer;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Nov 8, 2010
 * Time: 12:53:25 PM
 */
public interface ReportServerDAO {
    int getGenerateId();

    ReportServer getReportServerByReportId(int gangguan_report_id);

    void createReportServer(ReportServer reportServer);

    void saveOrUpdateReportServer(ReportServer reportServer);
}
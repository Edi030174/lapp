package net.lintasarta.report.permohonan.service;

import net.lintasarta.report.permohonan.model.ReportServer;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Nov 8, 2010
 * Time: 2:53:37 PM
 */
public interface ReportServerService {
    String getReportServerId();

    ReportServer getReportServerByReportId(int gangguan_report_id);

    void createReportServer(ReportServer reportServer);

    void saveOrUpdateReportServer(ReportServer reportServer);
}
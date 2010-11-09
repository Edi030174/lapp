package net.lintasarta.report.permohonan.service.impl;

import net.lintasarta.report.permohonan.dao.ReportServerDAO;
import net.lintasarta.report.permohonan.model.ReportServer;
import net.lintasarta.report.permohonan.service.ReportServerService;
import net.lintasarta.util.PermohonanIdGenerator;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Nov 8, 2010
 * Time: 3:05:36 PM
 */
public class ReportServerServiceImpl implements ReportServerService {
    private ReportServerDAO reportServerDAO;

    public ReportServerDAO getReportServerDAO() {
        return reportServerDAO;
    }

    public void setReportServerDAO(ReportServerDAO reportServerDAO) {
        this.reportServerDAO = reportServerDAO;
    }

    @Override
    public String getReportServerId() {
        int i = reportServerDAO.getGenerateId();
        String seq = String.valueOf(i);
        PermohonanIdGenerator pid = new PermohonanIdGenerator(seq);
        String permohonanIdResult = pid.getPermohonanId();

        return permohonanIdResult;
    }

    @Override
    public ReportServer getReportServerByReportId(int gangguan_report_id) {
        return reportServerDAO.getReportServerByReportId(gangguan_report_id);
    }

    @Override
    public void createReportServer(ReportServer reportServer) {
        int i = reportServerDAO.getGenerateId();
        int g = Integer.parseInt(getReportServerId());
        reportServer.setGangguan_report_id(g);
        reportServer.setGen_id_col(i);
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        reportServer.setUpdate_date(ts);
        
        getReportServerDAO().createReportServer(reportServer);
    }

    @Override
    public void saveOrUpdateReportServer(ReportServer reportServer) {
        getReportServerDAO().saveOrUpdateReportServer(reportServer);
    }
}

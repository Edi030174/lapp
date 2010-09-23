package net.lintasarta.report.service.impl;

import net.lintasarta.report.permohonan.dao.ReportAduanDAO;
import net.lintasarta.report.permohonan.dao.ReportBelumSelesaiDAO;
import net.lintasarta.report.permohonan.dao.ReportRekapAduanDAO;
import net.lintasarta.report.permohonan.dao.ReportSudahSelesaiDAO;
import net.lintasarta.report.permohonan.model.ReportAduan;
import net.lintasarta.report.permohonan.model.ReportBelumSelesai;
import net.lintasarta.report.permohonan.model.ReportRekapAduan;
import net.lintasarta.report.permohonan.model.ReportSudahSelesai;
import net.lintasarta.report.service.ReportService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Sep 23, 2010
 * Time: 1:39:44 PM
 */
public class ReportServiceImpl implements ReportService {

    private ReportAduanDAO reportAduanDAO;
    private ReportBelumSelesaiDAO reportBelumSelesaiDAO;
    private ReportRekapAduanDAO reportRekapAduanDAO;
    private ReportSudahSelesaiDAO reportSudahSelesaiDAO;

    public ReportAduanDAO getReportAduanDAO() {
        return reportAduanDAO;
    }

    public void setReportAduanDAO(ReportAduanDAO reportAduanDAO) {
        this.reportAduanDAO = reportAduanDAO;
    }

    public ReportBelumSelesaiDAO getReportBelumSelesaiDAO() {
        return reportBelumSelesaiDAO;
    }

    public void setReportBelumSelesaiDAO(ReportBelumSelesaiDAO reportBelumSelesaiDAO) {
        this.reportBelumSelesaiDAO = reportBelumSelesaiDAO;
    }

    public ReportRekapAduanDAO getReportRekapAduanDAO() {
        return reportRekapAduanDAO;
    }

    public void setReportRekapAduanDAO(ReportRekapAduanDAO reportRekapAduanDAO) {
        this.reportRekapAduanDAO = reportRekapAduanDAO;
    }

    public ReportSudahSelesaiDAO getReportSudahSelesaiDAO() {
        return reportSudahSelesaiDAO;
    }

    public void setReportSudahSelesaiDAO(ReportSudahSelesaiDAO reportSudahSelesaiDAO) {
        this.reportSudahSelesaiDAO = reportSudahSelesaiDAO;
    }

    public List<ReportAduan> getAduan(String status) {
        return reportAduanDAO.getReportAduan(status);
    }

    public List<ReportBelumSelesai> getBelumSelesai() {
        return reportBelumSelesaiDAO.getReportBelumSelesai();
    }

    public List<ReportSudahSelesai> getSudahSelesai() {
        return reportSudahSelesaiDAO.getReportSudahSelesai();
    }

    public List<ReportRekapAduan> getRekapAduan(String tahun) {
        return reportRekapAduanDAO.getReportRekapAduan(tahun);
    }
}

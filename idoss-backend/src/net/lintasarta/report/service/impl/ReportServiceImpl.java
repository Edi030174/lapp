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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

    public JRDataSource getAduan(String bulan, String tahun) {
        ReportAduan reportAduan = new ReportAduan();
        reportAduan.setBulan(bulan);
        reportAduan.setTahun(tahun);
        List<ReportAduan> reportAduans =  reportAduanDAO.getReportAduan(reportAduan);
        return new JRBeanCollectionDataSource(reportAduans);
    }

    public JRDataSource getBelumSelesai() {
        List<ReportBelumSelesai> reportBelumSelesais =  reportBelumSelesaiDAO.getReportBelumSelesai();
        return new JRBeanCollectionDataSource(reportBelumSelesais);
    }

    public JRDataSource getSudahSelesai() {
        List<ReportSudahSelesai> reportSudahSelesais = reportSudahSelesaiDAO.getReportSudahSelesai();
        return new JRBeanCollectionDataSource(reportSudahSelesais);
    }

    public JRDataSource getRekapAduan(String tahun) {
        List<ReportRekapAduan> reportRekapAduans = reportRekapAduanDAO.getReportRekapAduan(tahun);
        return new JRBeanCollectionDataSource(reportRekapAduans);
    }
}

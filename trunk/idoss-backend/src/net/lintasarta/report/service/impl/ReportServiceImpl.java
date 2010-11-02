package net.lintasarta.report.service.impl;

import net.lintasarta.report.permohonan.dao.*;
import net.lintasarta.report.permohonan.model.*;
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
    private ReportRekapPermohonanDAO reportRekapPermohonanDAO;

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

    public ReportRekapPermohonanDAO getReportRekapPermohonanDAO() {
        return reportRekapPermohonanDAO;
    }

    public void setReportRekapPermohonanDAO(ReportRekapPermohonanDAO reportRekapPermohonanDAO) {
        this.reportRekapPermohonanDAO = reportRekapPermohonanDAO;
    }

    public JRDataSource getAduan(String bulan, String tahun) {
        ReportAduan reportAduan = new ReportAduan();
        reportAduan.setBulan(bulan);
        reportAduan.setTahun(tahun);
        List<ReportAduan> reportAduans = reportAduanDAO.getReportAduan(reportAduan);
        return new JRBeanCollectionDataSource(reportAduans);
    }

    public JRDataSource getBelumSelesai(String bulan, String tahun) {
        ReportBelumSelesai reportBelumSelesai = new ReportBelumSelesai();
        reportBelumSelesai.setBulan(bulan);
        reportBelumSelesai.setTahun(tahun);
        List<ReportBelumSelesai> reportBelumSelesais = reportBelumSelesaiDAO.getReportBelumSelesai(reportBelumSelesai);
        return new JRBeanCollectionDataSource(reportBelumSelesais);
    }

    public JRDataSource getSudahSelesai(String bulan, String tahun) {
        ReportSudahSelesai reportSudahSelesai = new ReportSudahSelesai();
        reportSudahSelesai.setBulan(bulan);
        reportSudahSelesai.setTahun(tahun);
        List<ReportSudahSelesai> reportSudahSelesais = reportSudahSelesaiDAO.getReportSudahSelesai(reportSudahSelesai);
        return new JRBeanCollectionDataSource(reportSudahSelesais);
    }

    public JRDataSource getRekapAduan(String tahun, String NamaPemohon, String NikPemohon, String NamaManager, String NikManager, String NamaGm, String NikGm) {
        List<ReportRekapAduan> reportRekapAduans = reportRekapAduanDAO.getReportRekapAduan(tahun);
        return new JRBeanCollectionDataSource(reportRekapAduans);
    }

    public JRDataSource getRekapPermohonan(String bulan, String tahun) {
        ReportRekapPermohonan reportRekapPermohonan = new ReportRekapPermohonan();
        reportRekapPermohonan.setBulan(bulan);
        reportRekapPermohonan.setTahun(tahun);
        List<ReportRekapPermohonan> reportRekapPermohonans = reportRekapPermohonanDAO.getReportRekapPermohonan(reportRekapPermohonan);
        return new JRBeanCollectionDataSource(reportRekapPermohonans);
    }
}

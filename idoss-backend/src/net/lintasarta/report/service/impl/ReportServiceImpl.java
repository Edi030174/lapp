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

    public JRDataSource getAduan(String bulan, String tahun, String nama_pemohon, String nik_pemohon, String nama_manager, String nik_manager, String nama_gm, String nik_gm) {
        ReportAduan reportAduann = new ReportAduan();
        reportAduann.setBulan(bulan);
        reportAduann.setTahun(tahun);
        List<ReportAduan> reportAduans = reportAduanDAO.getReportAduan(reportAduann);
        for (ReportAduan reportAduan : reportAduans) {
            reportAduan.setNik_pemohon(nik_pemohon);
            reportAduan.setNama_pemohon(nama_pemohon);
            reportAduan.setNik_manager(nik_manager);
            reportAduan.setNama_manager(nama_manager);
            reportAduan.setNik_gm(nik_gm);
            reportAduan.setNama_gm(nama_gm);
        }
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

    public JRDataSource getRekapAduan(String tahun, String nama_pemohon, String nik_pemohon, String nama_manager, String nik_manager, String nama_gm, String nik_gm) {
        List<ReportRekapAduan> reportRekapAduans = reportRekapAduanDAO.getReportRekapAduan(tahun);
        for (ReportRekapAduan reportRekapAduan : reportRekapAduans) {
            reportRekapAduan.setNik_pemohon(nik_pemohon);
            reportRekapAduan.setNama_pemohon(nama_pemohon);
            reportRekapAduan.setNik_manager(nik_manager);
            reportRekapAduan.setNama_manager(nama_manager);
            reportRekapAduan.setNik_gm(nik_gm);
            reportRekapAduan.setNama_gm(nama_gm);
        }
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

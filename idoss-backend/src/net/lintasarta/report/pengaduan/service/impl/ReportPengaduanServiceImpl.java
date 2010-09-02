package net.lintasarta.report.pengaduan.service.impl;

import net.lintasarta.report.pengaduan.dao.ReportPengaduanDAO;
import net.lintasarta.report.pengaduan.model.ReportPengaduan;
import net.lintasarta.report.pengaduan.service.ReportPengaduanService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Sep 2, 2010
 * Time: 10:20:38 AM
 */
public class ReportPengaduanServiceImpl implements ReportPengaduanService {
    private transient ReportPengaduanDAO reportPengaduanDAO;

    @Override
    public JRDataSource getReport() {
        ReportPengaduan reportPengaduan = new ReportPengaduan();
        List<ReportPengaduan> list = getReportPengaduanDAO().getReportPengaduan();
        list.add(reportPengaduan);
        return new JRBeanCollectionDataSource(list);
    }

    @Override
    public void compileReport(String aReportPathName) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(aReportPathName);
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        } catch (Exception ex) {
            String connectMsg = "JasperReports: Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.out.println(connectMsg);
        }
    }

    public ReportPengaduanDAO getReportPengaduanDAO() {
        return reportPengaduanDAO;
    }

    public void setReportPengaduanDAO(ReportPengaduanDAO reportPengaduanDAO) {
        this.reportPengaduanDAO = reportPengaduanDAO;
    }
}

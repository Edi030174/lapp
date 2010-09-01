package net.lintasarta.report.permohonan.service.impl;

import net.lintasarta.report.permohonan.model.ReportPermohonan;
import net.lintasarta.report.permohonan.service.ReportPermohonanService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 12, 2010
 * Time: 1:42:58 PM
 */
public class ReportPermohonanServiceImpl implements ReportPermohonanService {

    public void printReport(ReportPermohonan reportPermohonan, HashMap repParams) {
        try {

            InputStream inputStream = getClass().getResourceAsStream("/de/forsthaus/webui/reports/AuftragDetailsPojo_Report.jrxml");

            /* Liste mit Daten füllen */
//            List<ReportPermohonan> result = getOrderService().getOrderpositionsByOrder(reportPermohonan);

            /* DataSource mit der Liste erstellen */
//            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(result);

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, repParams, datasource);
//            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            String connectMsg = "JasperReports: Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.out.println(connectMsg);
        } catch (Exception ex) {
            String connectMsg = "Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.out.println(connectMsg);
        }


    }

    @Override
//    public JRDataSource getReport(ReportPermohonan reportPermohonan) {
//        return JRBeanCollectionDataSource();
//    }

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
}

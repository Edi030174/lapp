package net.lintasarta.report.permohonan.service;

import net.lintasarta.report.permohonan.model.ReportPermohonan;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 12, 2010
 * Time: 1:38:59 PM
 */
public interface ReportPermohonanService {
    void printReport(ReportPermohonan reportPermohonan, HashMap repParams);
//    public JRDataSource getReport(ReportPermohonan reportPermohonan);
    public void compileReport(String aReportPathName);
}

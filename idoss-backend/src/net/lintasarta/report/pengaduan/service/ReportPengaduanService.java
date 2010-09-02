package net.lintasarta.report.pengaduan.service;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Sep 2, 2010
 * Time: 10:11:29 AM
 */
public interface ReportPengaduanService {
    public JRDataSource getReport();
    public void compileReport(String aReportPathName);

}

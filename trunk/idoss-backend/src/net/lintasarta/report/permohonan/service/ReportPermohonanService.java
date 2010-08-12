package net.lintasarta.report.permohonan.service;

import net.lintasarta.report.permohonan.model.ReportPermohonan;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 12, 2010
 * Time: 1:38:59 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportPermohonanService {
    void printReport(ReportPermohonan reportPermohonan, HashMap repParams);
    public void compileReport(String aReportPathName);
}

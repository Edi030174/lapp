package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportAduan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 23, 2010
 * Time: 1:16:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportAduanDAO {
    List<ReportAduan> getReportAduan(ReportAduan reportAduan);
}

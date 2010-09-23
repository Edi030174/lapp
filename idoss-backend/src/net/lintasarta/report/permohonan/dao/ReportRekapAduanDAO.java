package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportRekapAduan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 23, 2010
 * Time: 1:23:42 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportRekapAduanDAO {
    List<ReportRekapAduan> getReportRekapAduan(ReportRekapAduan reportRekapAduan);
}

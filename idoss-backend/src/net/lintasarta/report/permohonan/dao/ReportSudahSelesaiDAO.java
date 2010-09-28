package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportSudahSelesai;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 23, 2010
 * Time: 1:21:58 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportSudahSelesaiDAO {
    List<ReportSudahSelesai> getReportSudahSelesai(ReportSudahSelesai reportSudahSelesai);
}

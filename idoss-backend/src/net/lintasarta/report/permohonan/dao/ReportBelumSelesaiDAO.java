package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportBelumSelesai;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 23, 2010
 * Time: 1:19:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportBelumSelesaiDAO {
    List<ReportBelumSelesai> getReportBelumSelesai(ReportBelumSelesai reportBelumSelesai);
}

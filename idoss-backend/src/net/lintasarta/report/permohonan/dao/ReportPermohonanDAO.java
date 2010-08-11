package net.lintasarta.report.permohonan.dao;


import net.lintasarta.report.permohonan.model.ReportPermohonan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 11, 2010
 * Time: 1:44:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportPermohonanDAO {

    List<ReportPermohonan> getReportBelumSelesai();
}

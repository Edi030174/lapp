package net.lintasarta.report.service;

import net.lintasarta.report.permohonan.model.ReportAduan;
import net.lintasarta.report.permohonan.model.ReportBelumSelesai;
import net.lintasarta.report.permohonan.model.ReportRekapAduan;
import net.lintasarta.report.permohonan.model.ReportSudahSelesai;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Sep 23, 2010
 * Time: 1:39:35 PM
 */
public interface ReportService {
    JRDataSource getAduan(String status);
    JRDataSource getBelumSelesai();
    JRDataSource getSudahSelesai();
    JRDataSource getRekapAduan(String tahun);
}

package net.lintasarta.report.service;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Sep 23, 2010
 * Time: 1:39:35 PM
 */
public interface ReportService {
    JRDataSource getAduan(String bulan, String tahun, String nama_pemohon, String nik_pemohon, String nama_manager, String nik_manager, String nama_gm, String nik_gm);
    JRDataSource getBelumSelesai(String bulan, String tahun);
    JRDataSource getSudahSelesai(String bulan, String tahun);
    JRDataSource getRekapAduan(String tahun, String nama_pemohon, String nik_pemohon, String nama_manager, String nik_manager, String nama_gm, String nik_gm, String jumlah_server);
    JRDataSource getRekapPermohonan(String bulan, String tahun);
}
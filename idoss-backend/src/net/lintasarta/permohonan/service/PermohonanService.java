package net.lintasarta.permohonan.service;

import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.model.VerifikasiPermohonan;
import net.lintasarta.security.model.VHrEmployee;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 22, 2010
 * Time: 4:52:07 PM
 */
public interface PermohonanService {
    TPermohonan getNewPermohonan();
    String getPermohonanID();
    TPermohonan getTPermohonanByTIdossPermohonanId(String t_idoss_permohonan_id);
    List<TPermohonan> getAllTPermohonan();
    void createTPermohonan (String uploadedFileName, TPermohonan tPermohonan, List<Integer> employeeRole);
    void simpanAllTPermohonan(String uploadedFileName, TPermohonan tPermohonan, Mttr mttr, List<Integer> employeeRole);
    void saveOrUpdateTPermohonan (TPermohonan tPermohonan);
    TVerifikasi getTVerifikasiByTIdossVerifikasiId(String t_idoss_verifikasi_id);
    TVerifikasi getNewVerifikasi();
    TPelaksanaan getTPelaksanaanByTIdossPelaksanaanId(String t_idoss_pelaksanaan_id);
    TPelaksanaan getNewPelaksanaan();
    String getManager(String nikPemohon);
    String getManagerReport(String nikPemohon);
    List<TPermohonan> getTPermohonanByStatusAndNikManager(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByStatusAndNikGM(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikPelaksanaStatus(String nik_pelaksana,String status_track_permohonan);
    List<TPermohonan> getTPermohonanByStatusTrackPermohonan(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByStatusTrackPermohonanAndDampak(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikPemohon(TPermohonan tPermohonan);
    List<VHrEmployee> getVHrEmployeeByEmployeeNo(String employeeNo);
    List<TPermohonan> getTPermohonanByNikPelaksana(VerifikasiPermohonan verifikasiPermohonan);
    List<TPermohonan> hitungDurasiMttr(List<TPermohonan> tPermohonans);
    String getFilePath();
    String getDuration(long duration);
}
package net.lintasarta.permohonan.service;

import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 23, 2010
 * Time: 11:00:18 AM
 */
public interface VerifikasiService {
    TVerifikasi getNewVerifikasi();
    int getCountAllTVerifikasi();
    List<TVerifikasi> getAllTVerifikasi();
    TVerifikasi getTVerifikasiByTIdossVerifikasiId(String t_idoss_verifikasi_id);
    List<VHrEmployeePelaksana> getEmployeeName();
    void createTVerifikasi(TVerifikasi tVerifikasi);
    void saveOrUpdateTVerifikasi (TVerifikasi tVerifikasi);
}

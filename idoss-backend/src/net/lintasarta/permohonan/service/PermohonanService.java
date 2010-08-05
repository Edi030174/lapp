package net.lintasarta.permohonan.service;

import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 22, 2010
 * Time: 4:52:07 PM
 */
public interface PermohonanService {
    TPermohonan getNewPermohonan();
    TPermohonan getTPermohonanByTIdossPermohonanId(String t_idoss_permohonan_id);
    List<TPermohonan> getAllTPermohonan();
    void createTPermohonan (String uploadedFileName, TPermohonan tPermohonan);
    void saveOrUpdateTPermohonan (TPermohonan tPermohonan);
    TVerifikasi getTVerifikasiByTIdossVerifikasiId(String t_idoss_verifikasi_id);
    TVerifikasi getNewVerifikasi();
    TPelaksanaan getTPelaksanaanByTIdossPelaksanaanId(String t_idoss_pelaksanaan_id);
    TPelaksanaan getNewPelaksanaan();

}

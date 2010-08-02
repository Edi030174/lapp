package net.lintasarta.permohonan.service;

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

    int getCountAllTVerifikasi();
    List<TVerifikasi> getAllTVerifikasi();
    TVerifikasi getTVerifikasiByTIdossVerifikasiId(String t_idoss_verifikasi_id);
    void createTVerifikasi (TPermohonan tPermohonan, TPelaksanaan tPelaksanaan, TVerifikasi tVerifikasi);
    void saveOrUpdateTVerifikasi (TVerifikasi tVerifikasi);
}

package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.TVerifikasi;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 22, 2010
 * Time: 8:53:54 AM
 */
public interface TVerifikasiDAO {
    int getCountAllTVerifikasi();
    List<TVerifikasi> getAllTVerifikasi();

    TVerifikasi getTVerifikasiByTIdossVerifikasiId(String t_idoss_verifikasi_id);
    List<TVerifikasi> getTVerifikasiByNikPelaksana(TVerifikasi nikPelaksana);
    List<TVerifikasi> getTVerifikasiByStatusPermohonanAsman(TVerifikasi statusPermohonanAsman);
    List<TVerifikasi> getTVerifikasiByStatusPermohonanManager(TVerifikasi statusPermohonanManager);

    List<TVerifikasi> getTVerifikasiByNikPelaksanaStatusPA(TVerifikasi tVerifikasi);
    List<TVerifikasi> getTVerifikasiByNikPelaksanaStatusPM(TVerifikasi tVerifikasi);

    void createTVerifikasi (TVerifikasi tVerifikasi);
    void saveOrUpdateTVerifikasi (TVerifikasi tVerifikasi);
}

package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.TPelaksanaan;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 21, 2010
 * Time: 11:14:25 AM
 */
public interface TPelaksanaanDAO {
    int getCountAllTPelaksanaan();
    List<TPelaksanaan> getAllTPelaksanaan();
    TPelaksanaan getTPelaksanaanByTIdossPelaksanaanId(String t_idoss_pelaksanaan_id);
    List<TPelaksanaan> getTPelaksananByIdPelaksana(String idPelaksana);
    List<TPelaksanaan> getTPelaksananByStatusPerubahan(String statusPerubahan);
    List<TPelaksanaan> getTPelaksananByIdPelaksanaStatus(TPelaksanaan tPelaksanaan);
    void createTPelaksanaan (TPelaksanaan tPelaksanaan);
    void saveOrUpdateTPelaksanaan (TPelaksanaan tPelaksanaan);
}
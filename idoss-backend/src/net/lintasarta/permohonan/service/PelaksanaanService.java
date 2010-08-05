package net.lintasarta.permohonan.service;

import net.lintasarta.permohonan.model.TPelaksanaan;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 23, 2010
 * Time: 10:54:50 AM
 */
public interface PelaksanaanService {
    
    TPelaksanaan getNewPelaksanaan();
    int getCountAllTPelaksanaan();
    List<TPelaksanaan> getAllTPelaksanaan();
    
    TPelaksanaan getTPelaksanaanByTIdossPelaksanaanId(String t_idoss_pelaksanaan_id);

    void createTPelaksanaan (TPelaksanaan tPelaksanaan);
    void saveOrUpdateTPelaksanaan (TPelaksanaan tPelaksanaan);

}
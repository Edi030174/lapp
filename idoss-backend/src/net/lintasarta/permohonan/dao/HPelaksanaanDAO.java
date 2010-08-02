package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.HPelaksanaan;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 18, 2010
 * Time: 11:04:42 AM
 */
public interface HPelaksanaanDAO {
    int getCountAllHPelaksanaan();
    List<HPelaksanaan> getAllHPelaksanaan();
    HPelaksanaan getHPelaksanaanByIdno (String idno);
    void createHPelaksanaan (HPelaksanaan hPelaksanaan);
    void saveOrUpdateHPelaksanaan (HPelaksanaan hPelaksanaan);
}
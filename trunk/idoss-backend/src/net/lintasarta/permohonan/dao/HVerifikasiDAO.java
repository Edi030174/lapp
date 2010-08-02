package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.HVerifikasi;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 21, 2010
 * Time: 9:03:08 AM
 */
public interface HVerifikasiDAO {
    int getCountAllHVerifikasi();
    List<HVerifikasi> getAllHVerifikasi();
    void createHVerifikasi (HVerifikasi hVerifikasi);
    void saveOrUpdateHVerifikasi (HVerifikasi hVerifikasi);
    void deleteHVerifikasi (HVerifikasi hVerifikasi);
}

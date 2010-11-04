package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.Mttr;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 11:24:06 AM
 */
public interface MttrService {

    String getGenerateId();

    Mttr getMttrByMttrId(int t_idoss_mttr_id);

    void createMttr(Mttr mttr);

    void saveOrUpdateMttr(Mttr mttr);

    List<Mttr> getMttrByNomorTiket(String nomorTiket);

    long getDurasi(Mttr mttr);

    long getLamaPending(Mttr mttr);
}
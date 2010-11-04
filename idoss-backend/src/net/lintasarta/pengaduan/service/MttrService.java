package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.Mttr;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 11:24:06 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MttrService {

    Mttr getNewMttr();

    String getGenerateId();

    Mttr getMttrByMttrId(int t_idoss_mttr_id);

    void createMttr(Mttr mttr);

    void saveOrUpdateMttr(Mttr mttr);

    Mttr getMttrByNomorTiket(String nomorTiket);

    long getDurasi(Mttr mttr);

    long getLamaPending(Mttr mttr);
}
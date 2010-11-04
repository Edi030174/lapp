package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.Mttr;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 19, 2010
 * Time: 3:04:02 PM
 */
public interface MttrDAO {
    int getGenerateId();
    Mttr getMttrByMttrId(int t_idoss_mttr_id);
    List<Mttr> getMttrByNomorTiket(String nomorTiket);
    void createMttr(Mttr mttr);
    void saveOrUpdateMttr(Mttr mttr);
}

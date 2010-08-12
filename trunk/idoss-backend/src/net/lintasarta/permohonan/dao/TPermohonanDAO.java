package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.TPermohonan;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 21, 2010
 * Time: 2:25:46 PM
 */
public interface TPermohonanDAO {
    int getCountAllTPermohonan();
    int getGeneratedID();
    List<TPermohonan> getAllTPermohonan();
    TPermohonan getTPermohonanByTIdossPermohonanId(String t_idoss_permohonan_id);
    List<TPermohonan> getTPermohonanByNikPemohon(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikAsman(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikManager(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikGm(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByStatusTrackPermohonan(TPermohonan tPermohonan);

    List<TPermohonan> getTPermohonanByNikPemohonStatus(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikAsmanStatus(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikManagerStatus(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikGmStatus(TPermohonan tPermohonan);


    void createTPermohonan (TPermohonan tPermohonan);
    void saveOrUpdateTPermohonan (TPermohonan tPermohonan);
}

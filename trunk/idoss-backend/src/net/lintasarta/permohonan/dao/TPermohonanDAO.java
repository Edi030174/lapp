package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.VerifikasiPermohonan;

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
    List<TPermohonan> getTPermohonanByNikPelaksana(VerifikasiPermohonan verifikasiPermohonan);
    List<TPermohonan> getTPermohonanByNikPemohon(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikAsman(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikManager(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikGm(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByStatusTrackPermohonan(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByStatusTrackPermohonanAndDampak(TPermohonan tPermohonan);

    List<TPermohonan> getTPermohonanByNikPemohonStatus(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikAsmanStatus(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikManagerStatus(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikGmStatus(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByNikPelaksanaStatus(String nik_pelaksana,String status_track_permohonan);

    void createTPermohonan (TPermohonan tPermohonan);
    void saveOrUpdateTPermohonan (TPermohonan tPermohonan);

    String getManager (String employeeNo);
    String getManagerReport (String employeeNo);

    List<TPermohonan> getTPermohonanByStatusAndNikManager(TPermohonan tPermohonan);
    List<TPermohonan> getTPermohonanByStatusAndNikGM(TPermohonan tPermohonan);
}

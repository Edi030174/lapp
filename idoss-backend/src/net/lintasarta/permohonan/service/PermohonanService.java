package net.lintasarta.permohonan.service;

import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 22, 2010
 * Time: 4:52:07 PM
 */
public interface PermohonanService {
    TPermohonan getNewPermohonan();
    TPermohonan getTPermohonanByNomorPermohonanId(String nomorPermohonanId);
    List<TPermohonan> getAllTPermohonan();
    void createTPermohonan (TPermohonan tPermohonan);
    void saveOrUpdateTPermohonan (TPermohonan tPermohonan);


}

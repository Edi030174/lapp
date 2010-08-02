package net.lintasarta.permohonan.dao;


import net.lintasarta.permohonan.model.HPermohonan;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 18, 2010
 * Time: 6:44:58 PM
 */
public interface HPermohonanDAO {
    int getCountAllHPermohonan();
    List<HPermohonan> getAllHPermohonan();

}

package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.PTypeRootCaused;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 27, 2010
 * Time: 2:09:32 PM
 */
public interface PTypeRootCausedDAO {
    void createPTypeRootCaused(PTypeRootCaused pTypeRootCaused);
    PTypeRootCaused getPTypeRootCausedByRootCausedId(Integer pRootCausedId);

}

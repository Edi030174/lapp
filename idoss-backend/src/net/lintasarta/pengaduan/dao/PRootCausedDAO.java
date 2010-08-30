package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.PRootCaused;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 8:50:11 AM
 */
public interface PRootCausedDAO {

    int getCountAllPRootCaused();

    int getGenerateId();

    List<PRootCaused> getAllPRootCaused();

    PRootCaused getPRootCausedByRootCausedId(int rootCausedId);

    void createPRootCaused(PRootCaused pRootCaused);

    void saveOrUpdate(PRootCaused pRootCaused);

    List<PRootCaused> getPRootCausedByPTypeID(String pTypeId);

}

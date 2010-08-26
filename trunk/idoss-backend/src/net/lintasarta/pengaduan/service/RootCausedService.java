package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PRootCaused;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 11:24:06 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RootCausedService {

    PRootCaused getNewRootCaused();

    List<PRootCaused> getAllRootCaused();

    PRootCaused getPRootCausedByRootCausedId(int rootCausedId);

    void createRootCaused(PRootCaused pRootCaused);

    void saveOrUpdate(PRootCaused pRootCaused);

    List<PRootCaused> getRootCausedByPTypeId(String typeId);

}
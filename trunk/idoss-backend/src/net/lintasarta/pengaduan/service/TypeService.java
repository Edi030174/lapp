package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 11:25:24 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TypeService {

    List<PType> getAllType();

    PType getTypeByTypeID(int typeId);

    void createType(PType pType);

    void saveOrUpdate(PType pType);
}

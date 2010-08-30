package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 11:25:24 AM
 */
public interface TypeService {

    List<PType> getAllType();

    PType getTypeByTypeID(String typeId);

    void createType(PType pType);

    void saveOrUpdate(PType pType);

    List<PType> getPTypeByParentId(String parentId);

    List<PType> getPTypeTree();
}

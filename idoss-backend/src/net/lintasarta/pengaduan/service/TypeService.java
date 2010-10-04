package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.PTypeRootCaused;

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

    PTypeRootCaused getPTypeRootCausedByRootCausedId(Integer pRootCausedId);

    void createType(PType pType);

    void saveOrUpdate(PType pType);

    List<PType> getPTypeByParentId(String parentId);

    PType getPTypeByTypeDesc(String typeDesc);

    List<PType> getPTypeTree();
}

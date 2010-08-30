package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.PType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 8:58:46 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PTypeDAO {
    
    int getCountAllPType();

    String getGenerateId();
    
    List<PType> getAllPType();
    
    PType getPTypeByTypeId(String typeId);

    void createPType (PType pType);

    void saveOrUpdate(PType pType);

    List<PType> getPTypeByParentId(String parentId);

    List<PType> getPTypeTree();

}

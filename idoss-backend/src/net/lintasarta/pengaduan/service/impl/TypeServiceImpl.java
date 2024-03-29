package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.pengaduan.dao.PTypeDAO;
import net.lintasarta.pengaduan.dao.PTypeRootCausedDAO;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.PTypeRootCaused;
import net.lintasarta.pengaduan.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 7:50:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class TypeServiceImpl implements TypeService {

    private PTypeDAO pTypeDAO;
    private PTypeRootCausedDAO pTypeRootCausedDAO;

    public PTypeDAO getpTypeDAO() {
        return pTypeDAO;
    }

    public void setpTypeDAO(PTypeDAO pTypeDAO) {
        this.pTypeDAO = pTypeDAO;
    }

    public PTypeRootCausedDAO getpTypeRootCausedDAO() {
        return pTypeRootCausedDAO;
    }

    public void setpTypeRootCausedDAO(PTypeRootCausedDAO pTypeRootCausedDAO) {
        this.pTypeRootCausedDAO = pTypeRootCausedDAO;
    }

    public List<PType> getAllType() {
        return getpTypeDAO().getAllPType();
    }

    public PType getTypeByTypeID(String typeId) {
        PType pType = pTypeDAO.getPTypeByTypeId(typeId);
        return pType;
    }

    public PTypeRootCaused getPTypeRootCausedByRootCausedId(Integer pRootCausedId) {
        PTypeRootCaused pTypeRootCaused = pTypeRootCausedDAO.getPTypeRootCausedByRootCausedId(pRootCausedId);
        return pTypeRootCaused;
    }

    public void createType(PType pType) {
        String i = pTypeDAO.getGenerateId();
        pType.setP_idoss_type_id(i);
        getpTypeDAO().createPType(pType);
    }

    public void saveOrUpdate(PType pType) {
        getpTypeDAO().saveOrUpdate(pType);
    }

    @Override
    public List<PType> getPTypeByParentId(String parentId) {
        List<PType> pTypes = pTypeDAO.getPTypeByParentId(parentId);
        return pTypes;
    }

    @Override
    public PType getPTypeByTypeId(String typeId) {
        PType pTypes = pTypeDAO.getPTypeByTypeId(typeId);
        return pTypes;
    }

    @Override
    public List<PType> getPTypeTree() {
        List<PType> pTypes = getpTypeDAO().getPTypeTree();
        return pTypes;
    }
}

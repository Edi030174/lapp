package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.pengaduan.dao.PApplicationDAO;
import net.lintasarta.pengaduan.service.PApplicationService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 8, 2010
 * Time: 11:45:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class PApplicationServiceImpl implements PApplicationService {
    private PApplicationDAO pApplicationDAO;

    public PApplicationDAO getpApplicationDAO() {
        return pApplicationDAO;
    }

    public void setpApplicationDAO(PApplicationDAO pApplicationDAO) {
        this.pApplicationDAO = pApplicationDAO;
    }

    @Override
    public List<Integer> getRoleByUsername(String user_name) {
        return getpApplicationDAO().getRoleByUsername(user_name);
    }
}

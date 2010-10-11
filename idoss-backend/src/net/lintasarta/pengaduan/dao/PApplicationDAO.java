package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.PApplication;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 7, 2010
 * Time: 5:55:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PApplicationDAO {
    List<PApplication> getRole();
    List<PApplication> getRoleByUsername(String user_name);
}

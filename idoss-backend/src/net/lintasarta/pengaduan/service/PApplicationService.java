package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PApplication;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 8, 2010
 * Time: 11:45:04 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PApplicationService {
    List<PApplication> getRole();
    List<PApplication> getRoleByUsername(String user_name);
}

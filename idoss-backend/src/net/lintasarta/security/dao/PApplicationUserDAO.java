package net.lintasarta.security.dao;

import net.lintasarta.security.model.VHrEmployee;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 28, 2010
 * Time: 3:47:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PApplicationUserDAO {
    String getEmployeeNoByUserName(String userName);
}

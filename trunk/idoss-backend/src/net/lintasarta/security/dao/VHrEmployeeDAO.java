package net.lintasarta.security.dao;

import net.lintasarta.security.model.VHrEmployee;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 21, 2010
 * Time: 12:40:37 PM
 */
public interface VHrEmployeeDAO {
    List<VHrEmployee> getVHrEmployeeByEmployeeNo(String employeeNo);
}

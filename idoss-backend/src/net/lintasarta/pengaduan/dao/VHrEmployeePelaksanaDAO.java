package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 13, 2010
 * Time: 4:46:58 PM
 */
public interface VHrEmployeePelaksanaDAO {
    List<VHrEmployeePelaksana> getAllVHrEmployeePelaksana();
    VHrEmployeePelaksana getVHrEmployeePelaksanaById(String id);
}

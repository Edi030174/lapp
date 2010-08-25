package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 19, 2010
 * Time: 4:35:05 PM
 */
public interface PelaksanaanGangguanService {

    TPenangananGangguan getDetail(String tiketId);

    List<PType> getType();

    List<PRootCaused> getRootCaused();

    List<VHrEmployeePelaksana> getEmployeeName();

    void saveOrUpdate(TPenangananGangguan tPenangananGangguan);

    List<PRootCaused> getRootCausedByPTypeId(int TypeId);
}

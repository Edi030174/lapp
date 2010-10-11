package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.security.model.VHrEmployee;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 21, 2010
 * Time: 1:57:37 PM
 */
public interface TPenangananGangguanDAO {

    int getCountAllTPenangananGangguan();

    int getSeqTiketId();

    List<TPenangananGangguan> getAllTPenangananGangguan();

    List<VHrEmployee> getVHrEmployeeByEmployeeName();

    TPenangananGangguan getTPenangananGangguanByTiketId(String tiketId);

    List<TPenangananGangguan> getAllTPenangananGangguanByNikPelapor(TPenangananGangguan tPenangananGangguan);

    List<TPenangananGangguan> getAllTPenangananGangguanByNikPelaksana(TPenangananGangguan tPenangananGangguan );

    List<TPenangananGangguan> getAllTPenangananGangguanByStatus(TPenangananGangguan tPenangananGangguan);

    List<TPenangananGangguan> getAllTPenangananGangguanByNikPelaporStatus(TPenangananGangguan tPenangananGangguan);

    List<TPenangananGangguan> getAllTPenangananGangguanByNikPelaksanaStatus(TPenangananGangguan tPenangananGangguan);

    void createTPenangananGangguan(TPenangananGangguan tPenangananGangguan);

    void saveOrUpdate(TPenangananGangguan tPenangananGangguan);

}

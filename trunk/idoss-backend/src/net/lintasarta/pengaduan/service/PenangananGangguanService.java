package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.Mttr;
import net.lintasarta.pengaduan.model.TDeskripsi;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.security.model.VHrEmployee;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 11:08:57 AM
 */
public interface PenangananGangguanService {

    TPenangananGangguan getNewPenangananGangguan();

    String getTiketId();

    List<TPenangananGangguan> getAllPenangananGangguan();

    List<VHrEmployee> getEmployeeName();
    
    TPenangananGangguan getPenangananGangguanbyTiketId(String tiketId);

    List<TPenangananGangguan> getAllPenangananGanguanByNikPelapor(TPenangananGangguan tPenangananGangguan);

    List<TPenangananGangguan> getAllTPenangananGangguanByNikPelaksana(TPenangananGangguan tPenangananGangguan);

//    List<TPenangananGangguan> getAllTPenangananGangguanByNikPelaksanaStatus(String nik_pelaksana, String status);

    List<TPenangananGangguan> getAllTPenangananGangguanByStatus(TPenangananGangguan tPenangananGangguan);

    void createPenangananGangguan(TPenangananGangguan tPenangananGangguan, TDeskripsi tDeskripsi, Mttr mttr);

    List<TPenangananGangguan> hitungDurasiMttr(List<TPenangananGangguan> tPenangananGangguans);
}

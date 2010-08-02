package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.TPenangananGangguan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 11:08:57 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PenangananGangguanService {

    TPenangananGangguan getNewPenangananGangguan();

    String getTiketId();

    List<TPenangananGangguan> getAllPenangananGangguan();

    TPenangananGangguan getPenangananGangguanbyTiketId(String tiketId);

    List<TPenangananGangguan> getAllPenangananGanguanByNikPelapor(TPenangananGangguan tPenangananGangguan);

    List<TPenangananGangguan> getAllPenangananGangguanByNikPelaksana(TPenangananGangguan tPenangananGangguan );

    List<TPenangananGangguan> getAllPenangananGangguanByStatus(TPenangananGangguan tPenangananGangguan);

    List<TPenangananGangguan> getAllPenangananGangguanByNikPelaporStatus(TPenangananGangguan tPenangananGangguan);

    List<TPenangananGangguan> getAllPenangananGangguanByNikPelaksanaStatus(TPenangananGangguan tPenangananGangguan);

    void createPenangananGangguan(TPenangananGangguan tPenangananGangguan);

    void saveOrUpdate(TPenangananGangguan tPenangananGangguan);


}

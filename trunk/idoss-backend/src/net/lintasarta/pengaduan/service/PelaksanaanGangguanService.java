package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.*;

import java.text.ParseException;
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

    void saveOrUpdate(TPenangananGangguan tPenangananGangguan, TDeskripsi tDeskripsi) throws ParseException;

    List<PRootCaused> getRootCausedByPTypeId(String typeId);

    VHrEmployeePelaksana getVHrEmployeePelaksanaById(String Id);
}

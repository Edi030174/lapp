package net.lintasarta.pengaduan.service.impl;

import net.lintasarta.pengaduan.dao.TDeskripsiDAO;
import net.lintasarta.pengaduan.model.TDeskripsi;
import net.lintasarta.pengaduan.service.TDeskripsiService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Oct 13, 2010
 * Time: 2:41:43 PM
 */
public class TDeskripsiServiceImpl implements TDeskripsiService {
    private TDeskripsiDAO tDeskripsiDAO;

    public void settDeskripsiDAO(TDeskripsiDAO tDeskripsiDAO) {
        this.tDeskripsiDAO = tDeskripsiDAO;
    }

    public List<TDeskripsi> getTDeskripsiByGangguanId(String id) {
        return tDeskripsiDAO.getTDeskripsiByGangguanId(id);
    }

    public void createTDeskripsi(TDeskripsi tDeskripsi, String nomorTiket) {
        int i = tDeskripsiDAO.getId();
        tDeskripsi.setT_idoss_deskripsi_id(i);
        tDeskripsi.setT_idoss_penanganan_gangguan_id(nomorTiket);
        tDeskripsi.setGen_id_col(i);
        tDeskripsiDAO.createTDeskripsi(tDeskripsi);
    }
}

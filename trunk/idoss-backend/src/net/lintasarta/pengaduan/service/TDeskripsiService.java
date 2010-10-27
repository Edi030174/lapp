package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.TDeskripsi;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Oct 13, 2010
 * Time: 2:41:59 PM
 */
public interface TDeskripsiService {
    public List<TDeskripsi> getTDeskripsiByGangguanId(String id);
    public void createTDeskripsi(TDeskripsi tDeskripsi, String nomorTiket);
}

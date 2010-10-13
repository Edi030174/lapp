package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.TDeskripsi;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Oct 13, 2010
 * Time: 1:06:09 PM
 */
public interface TDeskripsiDAO {
    List<TDeskripsi> getTDeskripsiByGangguanId(String id);
    int getId();
    void createTDeskripsi(TDeskripsi tDeskripsi);
}

package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.HPenangananGangguan;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 18, 2010
 * Time: 6:09:33 PM
 */
public interface HPenangananGangguanDAO {

    int getCountAllHPenangananGangguan();

    List<HPenangananGangguan> getAllHPenangananGangguan();

    void createHPenangananGanguan(HPenangananGangguan hPenangananGangguan);
    
}

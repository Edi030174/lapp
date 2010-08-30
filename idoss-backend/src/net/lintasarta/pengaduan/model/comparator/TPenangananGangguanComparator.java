package net.lintasarta.pengaduan.model.comparator;

import net.lintasarta.pengaduan.model.TPenangananGangguan;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 20, 2010
 * Time: 10:15:46 AM
 */
public class TPenangananGangguanComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((TPenangananGangguan) o2).getT_idoss_penanganan_gangguan_id().compareTo(((TPenangananGangguan) o1).getT_idoss_penanganan_gangguan_id());
    }
}

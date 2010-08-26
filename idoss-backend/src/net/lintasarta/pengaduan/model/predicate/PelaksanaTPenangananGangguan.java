package net.lintasarta.pengaduan.model.predicate;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 20, 2010
 * Time: 11:29:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class PelaksanaTPenangananGangguan implements Predicate {
    private String Pelaksana;

    public PelaksanaTPenangananGangguan(String pelaksana) {
        this.Pelaksana = pelaksana;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPenangananGangguan) o).getNama_pelaksana().toUpperCase().contains(Pelaksana.toUpperCase());
    }
}

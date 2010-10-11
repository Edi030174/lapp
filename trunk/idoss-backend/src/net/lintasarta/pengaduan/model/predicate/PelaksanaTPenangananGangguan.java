package net.lintasarta.pengaduan.model.predicate;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 20, 2010
 * Time: 11:29:57 AM
 */
public class PelaksanaTPenangananGangguan implements Predicate {
    String pelaksana;

    public PelaksanaTPenangananGangguan(String pelaksana) {
        this.pelaksana = pelaksana;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPenangananGangguan) o).getNama_pelaksana() != null && ((TPenangananGangguan) o).getNama_pelaksana().toUpperCase().contains(pelaksana.toUpperCase());
    }
}

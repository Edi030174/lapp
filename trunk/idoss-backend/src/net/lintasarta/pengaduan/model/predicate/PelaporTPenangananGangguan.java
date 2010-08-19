package net.lintasarta.pengaduan.model.predicate;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 19, 2010
 * Time: 4:21:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class PelaporTPenangananGangguan implements Predicate {
    String pelapor;

    public PelaporTPenangananGangguan(String pelapor) {
        this.pelapor = pelapor;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPenangananGangguan) o).getNama_pelapor().toUpperCase().contains(pelapor.toUpperCase());
    }
}

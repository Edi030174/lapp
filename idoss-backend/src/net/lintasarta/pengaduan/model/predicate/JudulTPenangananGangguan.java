package net.lintasarta.pengaduan.model.predicate;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Aug 6, 2010
 * Time: 5:20:17 PM
 */
public class JudulTPenangananGangguan implements Predicate {

    private String judul;

    public JudulTPenangananGangguan(String judul) {
        this.judul = judul;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPenangananGangguan) o).getJudul().toUpperCase().contains(judul.toUpperCase());
    }
}

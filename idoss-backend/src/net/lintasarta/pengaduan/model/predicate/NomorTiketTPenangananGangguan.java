package net.lintasarta.pengaduan.model.predicate;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 19, 2010
 * Time: 3:48:21 PM
 */
public class NomorTiketTPenangananGangguan implements Predicate {

    private String nomor_tiket;

    public NomorTiketTPenangananGangguan(String nomor_tiket) {
        this.nomor_tiket = nomor_tiket;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPenangananGangguan) o).getT_idoss_penanganan_gangguan_id().contains(nomor_tiket.toString());
    }
}

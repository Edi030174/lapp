package net.lintasarta.pengaduan.model.predicate;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 19, 2010
 * Time: 4:15:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class StatusTPenangananGangguan implements Predicate {
    private String status;

    public StatusTPenangananGangguan(String status) {
        this.status = status;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPenangananGangguan) o).getStatus().toUpperCase().contains(status.toUpperCase());
    }
}

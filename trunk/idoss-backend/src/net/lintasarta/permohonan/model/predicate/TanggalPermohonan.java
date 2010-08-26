package net.lintasarta.permohonan.model.predicate;

import net.lintasarta.permohonan.model.TPermohonan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 26, 2010
 * Time: 12:40:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class TanggalPermohonan implements Predicate{
    private String tgl;

    public TanggalPermohonan(String tgl) {
        this.tgl = tgl;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPermohonan) o).getTgl_permohonan().toString().toUpperCase().contains(tgl.toUpperCase());
    }
}

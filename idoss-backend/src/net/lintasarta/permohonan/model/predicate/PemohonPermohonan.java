package net.lintasarta.permohonan.model.predicate;

import net.lintasarta.permohonan.model.TPermohonan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Fachrul-Rozi
 * Date: Oct 28, 2010
 * Time: 10:10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class PemohonPermohonan implements Predicate {

    private String nama_pemohon;

    public PemohonPermohonan(String nama_pemohon) {
        this.nama_pemohon = nama_pemohon;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPermohonan) o).getNama_pemohon().toUpperCase().contains(nama_pemohon.toUpperCase());
    }
}

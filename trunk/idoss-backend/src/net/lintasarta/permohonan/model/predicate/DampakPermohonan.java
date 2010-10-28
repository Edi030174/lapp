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
public class DampakPermohonan implements Predicate {

    private String dampak;

    public DampakPermohonan(String dampak) {
        this.dampak = dampak;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPermohonan) o).getDampak().toUpperCase().contains(dampak.toUpperCase());
    }
}

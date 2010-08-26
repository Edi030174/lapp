package net.lintasarta.permohonan.model.predicate;

import net.lintasarta.permohonan.model.TPermohonan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 26, 2010
 * Time: 12:57:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class StatusPermohonan implements Predicate{
    private String st;

    public StatusPermohonan(String st) {
        this.st = st;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPermohonan) o).getStatus_track_permohonan().toUpperCase().contains(st.toUpperCase());
    }
}

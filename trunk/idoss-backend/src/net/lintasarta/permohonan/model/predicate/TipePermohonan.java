package net.lintasarta.permohonan.model.predicate;

import net.lintasarta.permohonan.model.TPermohonan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 26, 2010
 * Time: 12:53:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class TipePermohonan implements Predicate{
    private String tp;

    public TipePermohonan(String tp) {
        this.tp = tp;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPermohonan) o).getType_permohonan().toUpperCase().contains(tp.toUpperCase());
    }

}

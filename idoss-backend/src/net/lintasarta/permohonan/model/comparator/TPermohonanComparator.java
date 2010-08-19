package net.lintasarta.permohonan.model.comparator;

import net.lintasarta.permohonan.model.TPermohonan;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 19, 2010
 * Time: 2:44:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class TPermohonanComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return ((TPermohonan) o2).getT_idoss_permohonan_id().compareTo(((TPermohonan) o1).getT_idoss_permohonan_id());
    }
}

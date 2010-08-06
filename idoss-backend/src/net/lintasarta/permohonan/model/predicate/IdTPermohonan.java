package net.lintasarta.permohonan.model.predicate;

import net.lintasarta.permohonan.model.TPermohonan;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Aug 6, 2010
 * Time: 6:46:25 PM
 */
public class IdTPermohonan implements Predicate {
    private String id;

    public IdTPermohonan(String id) {
        this.id = id;
    }

    @Override
    public boolean evaluate(Object o) {
        return ((TPermohonan) o).getT_idoss_permohonan_id().toUpperCase().contains(id.toUpperCase());
    }
}

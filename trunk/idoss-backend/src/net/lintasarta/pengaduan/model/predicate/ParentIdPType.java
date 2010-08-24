package net.lintasarta.pengaduan.model.predicate;

import net.lintasarta.pengaduan.model.PType;
import org.apache.commons.collections.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Aug 24, 2010
 * Time: 2:44:56 PM
 */
public class ParentIdPType implements Predicate {

//    private Integer parentId;

    public ParentIdPType() {
    }

//    public ParentIdPType(Integer parentId) {
//        this.parentId = parentId;
//    }

    @Override
    public boolean evaluate(Object object) {
//        if (((PType) object).getParent_id() != null) {
//            return ((PType) object).getParent_id().equals(parentId);
//        }
        return ((PType) object).getParent_id() != null;
    }
}

package net.lintasarta.idoss.webui.util;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Textbox;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Aug 10, 2010
 * Time: 4:18:14 PM
 */
public class NoEmptyStringsConstraint implements Constraint, java.io.Serializable {

    public NoEmptyStringsConstraint() {
        super();
    }

    @Override
    public void validate(Component comp, Object value) throws WrongValueException {

        if (comp instanceof Textbox) {

            final String enteredValue = (String) value;

            if (enteredValue.isEmpty()) {
                throw new WrongValueException(comp, Labels.getLabel("message.error.CannotBeEmpty"));
            }
        }
    }
}

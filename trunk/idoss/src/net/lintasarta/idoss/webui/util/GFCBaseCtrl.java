package net.lintasarta.idoss.webui.util;

import net.lintasarta.UserWorkspace;
import org.springframework.security.access.annotation.Secured;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.sys.ComponentsCtrl;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 2, 2010
 * Time: 1:03:00 PM
 */
abstract public class GFCBaseCtrl extends GenericForwardComposer implements Serializable {
    protected transient Map<String, Object> args;

    /**
     * Get the params map that are overhanded at creation time. <br>
     * Reading the params that are binded to the createEvent.<br>
     *
     * @param event
     * @return params map
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCreationArgsMap(Event event) {
        CreateEvent ce = (CreateEvent) ((ForwardEvent) event).getOrigin();
        return ce.getArg();
    }

    @SuppressWarnings("unchecked")
    public void doOnCreateCommon(Window w, Event fe) throws Exception {
        CreateEvent ce = (CreateEvent) ((ForwardEvent) fe).getOrigin();
        args = ce.getArg();
    }

    private transient UserWorkspace userWorkspace;

    /**
     * Workaround! Do not use it otherwise!
     */
    @Override
    public void onEvent(Event evt) throws Exception {
        final Object controller = getController();
        final Method mtd = ComponentsCtrl.getEventMethod(controller.getClass(), evt.getName());

        if (mtd != null) {
            isAllowed(mtd);
        }
        super.onEvent(evt);
    }

    /**
     * With this method we get the @Secured Annotation for a method.<br>
     * Captured the method call and check if it's allowed. <br>
     *
     * @param mtd
     */
    private void isAllowed(Method mtd) {
        Annotation[] annotations = mtd.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Secured) {
                Secured secured = (Secured) annotation;
                for (String rightName : secured.value()) {
                    if (!userWorkspace.isAllowed(rightName)) {
                        throw new SecurityException("Call not allowed! Not enough right: \n\n" + "Name Required rights: " + rightName + "\n\n" + "In Method: " + mtd);
                    }
                }
                return;
            }
        }
    }

    final protected UserWorkspace getUserWorkspace() {
        return userWorkspace;
    }

    public void setUserWorkspace(UserWorkspace userWorkspace) {
        this.userWorkspace = userWorkspace;
    }
}

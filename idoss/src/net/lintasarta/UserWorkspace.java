package net.lintasarta;

import net.lintasarta.security.model.UserSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Window;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 2, 2010
 * Time: 1:44:10 PM
 */
public class UserWorkspace implements Serializable, DisposableBean {
    private transient final static Logger logger = Logger.getLogger(UserWorkspace.class);

    static private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private UserSession userSession;

    private Set<String> grantedAuthoritySet = null;

    public UserWorkspace() {
        if (logger.isDebugEnabled()) {
            logger.debug("create new Workspace [" + this + "]");
        }

        Window.setDefaultActionOnShow("");
    }

    public static UserWorkspace getInstance() {
        return (UserWorkspace) SpringUtil.getBean("userWorkspace");
    }

    public void doLogout() {
        destroy();
        Executions.sendRedirect("/j_spring_logout");
    }

    private Set<String> getGrantedAuthoritySet() {

        if (grantedAuthoritySet == null) {

            Collection<GrantedAuthority> list = getAuthentication().getAuthorities();
            grantedAuthoritySet = new HashSet<String>(list.size());

            for (GrantedAuthority grantedAuthority : list) {
                grantedAuthoritySet.add(grantedAuthority.getAuthority());
            }
        }
        return grantedAuthoritySet;
    }

    public boolean isAllowed(String rightName) {
        return getGrantedAuthoritySet().contains(rightName);
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public void destroy() {
        grantedAuthoritySet = null;
        SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("destroy Workspace [" + this + "]");
        }
    }
}

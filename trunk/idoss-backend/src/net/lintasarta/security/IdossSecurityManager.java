package net.lintasarta.security;

import com.xsis.security.model.SecRight;
import com.xsis.security.model.SecUser;
import com.xsis.security.service.UserService;
import net.lintasarta.security.dao.VHrEmployeeDAO;
import net.lintasarta.security.model.IdossUser;
import net.lintasarta.security.model.VHrEmployee;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 29, 2010
 * Time: 12:36:25 PM
 */
public class IdossSecurityManager implements UserDetailsService, Serializable {
    private transient static final Logger logger = Logger.getLogger(IdossSecurityManager.class);

    private transient UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SecUser getUserByLoginname(final String userName) {
        return getUserService().getUserByLoginname(userName);
    }

    private Collection<GrantedAuthority> getGrantedAuthority(SecUser user) {

        Collection<SecRight> rights = getUserService().getRightsByUser(user);

        ArrayList<GrantedAuthority> rechteGrantedAuthorities = new ArrayList<GrantedAuthority>(rights.size());

        for (SecRight right : rights) {
            rechteGrantedAuthorities.add(new GrantedAuthorityImpl(right.getRigName()));
        }
        return rechteGrantedAuthorities;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeRole) throws UsernameNotFoundException, DataAccessException {
        SecUser user;
        Collection<GrantedAuthority> grantedAuthorities;
        try {
            user = getUserByLoginname(employeeRole);
            if (user == null) {
                throw new UsernameNotFoundException("Invalid User");
            }
            grantedAuthorities = getGrantedAuthority(user);
        } catch (NumberFormatException e) {
            throw new DataRetrievalFailureException("Cannot loadUserByUsername userId:" + employeeRole + " Exception:" + e.getMessage(), e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Rights for '" + user.getUsrLoginname() + "' (ID: " + user.getId() + ") determined. (" + grantedAuthorities + ") [" + this + "]");
        }
        return new IdossUser(user, grantedAuthorities);
    }
}

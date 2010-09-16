package net.lintasarta.security.model;

import com.xsis.security.model.AppUser;
import com.xsis.security.model.SecUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 29, 2010
 * Time: 12:40:35 PM
 */
public class IdossUser extends AppUser {

    public IdossUser(SecUser user, Collection<GrantedAuthority> grantedAuthorities) throws IllegalArgumentException {
        super(user, grantedAuthorities);
    }
}

package net.lintasarta.security;

import com.xsis.security.spring.model.SecUser;
import net.lintasarta.security.dao.VHrEmployeeDAO;
import net.lintasarta.security.model.IdossUser;
import net.lintasarta.security.model.VHrEmployee;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 29, 2010
 * Time: 12:36:25 PM
 */
public class IdossSecurityManager implements UserDetailsService, Serializable {
    private VHrEmployeeDAO vHrEmployeeDAO;

    public void setvHrEmployeeDAO(VHrEmployeeDAO vHrEmployeeDAO) {
        this.vHrEmployeeDAO = vHrEmployeeDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeNo) throws UsernameNotFoundException, DataAccessException {
        System.out.println("IdossSecurityManager.loadUserByUsername");
        SecUser secUser = new SecUser();
        secUser.setUsr_id(Calendar.getInstance().getTimeInMillis());

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new GrantedAuthorityImpl("admin"));

        List<VHrEmployee> vHrEmployees = vHrEmployeeDAO.getVHrEmployeeByEmployeeNo(employeeNo);

        for (VHrEmployee vHrEmployee : vHrEmployees) {
            secUser.setUsr_loginname(vHrEmployee.getEmployee_no());
            secUser.setUsr_accountnonexpired(Boolean.TRUE);
            secUser.setUsr_accountnonlocked(Boolean.TRUE);
            secUser.setUsr_credentialsnonexpired(Boolean.TRUE);
            if (vHrEmployee.getStatus().intValue() == 1) {
                secUser.setUsr_enabled(Boolean.TRUE);
            }
            secUser.setUsr_email(vHrEmployee.getE_mail_addr());
            secUser.setUsr_firstname(vHrEmployee.getEmployee_name());
            secUser.setUsr_password("idoss");

            return new IdossUser(secUser, grantedAuthorities);
        }
        return null;
    }
}

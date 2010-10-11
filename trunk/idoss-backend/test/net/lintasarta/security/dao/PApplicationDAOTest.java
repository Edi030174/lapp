package net.lintasarta.security.dao;

import net.lintasarta.pengaduan.dao.PApplicationDAO;
import net.lintasarta.pengaduan.model.PApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 7, 2010
 * Time: 6:08:55 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:META-INF/ibatis/ibatis-spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-based-dao-config.xml",
                "classpath:META-INF/spring/spring-idoss-security-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml"
        }
)
public class PApplicationDAOTest {
    @Autowired
    private PApplicationDAO pApplicationDAO;

    @Test
    public void testGetRole() throws Exception {
        List<PApplication> pApplications = pApplicationDAO.getRole();
    }

    @Test
    public void testGetRoleByUsername() throws Exception{
        String user_name = "OKR";
        List<PApplication> pApplications = pApplicationDAO.getRoleByUsername(user_name);
        System.out.println("======================="+pApplications.get(0).getEmployee_no());
    }
}

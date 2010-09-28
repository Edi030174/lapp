package net.lintasarta.security.dao;

import net.lintasarta.security.model.VHrEmployee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 28, 2010
 * Time: 3:55:46 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
                "classpath:META-INF/ibatis/ibatis-spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-based-dao-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-report-config.xml",
				"classpath:META-INF/spring/spring-idoss-security-config.xml"
		}
)
public class PApplicationUserDAOTest {
    @Autowired
    private PApplicationUserDAO pApplicationUserDAO;

    @Test
    public void testGetEmployeeNoByUserName() {
        String userName = "PHS";
        String employeeNoActual = pApplicationUserDAO.getEmployeeNoByUserName(userName);
        String employeeNoExpected = "76000800";
        assertEquals(employeeNoExpected, employeeNoActual);
    }
}
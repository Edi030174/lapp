package net.lintasarta.security.service;

import net.lintasarta.security.model.UserSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 21, 2010
 * Time: 5:34:57 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
				"classpath:META-INF/spring/spring-config.xml",
				"classpath:META-INF/spring/datasource.xml",
				"classpath:META-INF/spring/spring-idoss-security-config.xml"
		}
)

public class LoginServiceTest {
    @Autowired
    private LoginService loginService;

    @Test
    public void testGetUserSession() {
        String userUrl = "";
        String ticketId = "";

        UserSession userSession = loginService.getUserSession(userUrl, ticketId);

        String expectedEmployeeName = "" ;
        String actualEmployeeName = userSession.getEmployeeName();
        assertEquals(expectedEmployeeName, actualEmployeeName);
    }
}

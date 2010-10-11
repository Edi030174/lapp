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
 * User: Antonius
 * Date: Jun 21, 2010
 * Time: 1:07:44 PM
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
public class VHrEmployeeDAOTest {
    @Autowired
    private VHrEmployeeDAO vHrEmployeeDAO;

    @Test
    public void testGetVHrEmployeeByEmployee_no() {
        String employeeNo = "56890021";
        List<VHrEmployee> vHrEmployees = vHrEmployeeDAO.getVHrEmployeeByEmployeeNo(employeeNo);
        String employee_nameActual = null;
        for (VHrEmployee vHrEmployee : vHrEmployees) {
            employee_nameActual = vHrEmployee.getEmployee_name();
        }
        String employee_nameExpected = "BENNY PRABOWOSUNU";
        assertEquals(employee_nameExpected, employee_nameActual);
    }

    @Test
    public void testGetVHrEmployeeByEmployeeName() {
        List<VHrEmployee> vHrEmployees = vHrEmployeeDAO.getVHrEmployeeByEmployeeName();
        String employee_nameActual = null;
        for (VHrEmployee vHrEmployee : vHrEmployees) {
            employee_nameActual = vHrEmployee.getEmployee_name();
        }
        String employee_nameExpected = "ZULHELMY";
        assertEquals(employee_nameExpected, employee_nameActual);
    }
}
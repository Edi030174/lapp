package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 13, 2010
 * Time: 4:59:06 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:META-INF/spring/spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-idoss-security-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-permohonan-config.xml"
        }
)

public class VHrEmployeePelaksanaDAOTest {

    @Autowired
    VHrEmployeePelaksanaDAO vHrEmployeePelaksanaDAO;

    @Test
    public void testGetAllVHrEmployeePelaksana() throws Exception {
        List<VHrEmployeePelaksana> vHrEmployeePelaksanas = vHrEmployeePelaksanaDAO.getAllVHrEmployeePelaksana();
        String employeeNameActual = null;
        for (VHrEmployeePelaksana vHrEmployeePelaksana : vHrEmployeePelaksanas) {
            employeeNameActual= vHrEmployeePelaksana.getEmployee_name();
        }
        String rootCausedExpected = "AKHMAD FAUZAN SYAFIQ";

        assertEquals(rootCausedExpected, employeeNameActual);
    }
    @Test
    public void testGetVHrEmployeePelaksanaById() throws Exception {
        VHrEmployeePelaksana vHrEmployeePelaksana = vHrEmployeePelaksanaDAO.getVHrEmployeePelaksanaById("84070998");
        assertEquals("KURNIAWAN DWI PRASETYO",vHrEmployeePelaksana.getEmployee_name());


    }

}


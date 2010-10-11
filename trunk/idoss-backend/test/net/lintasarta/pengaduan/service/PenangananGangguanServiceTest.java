package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.security.model.VHrEmployee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 6:40:49 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:META-INF/ibatis/ibatis-spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-idoss-security-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml",
                "classpath:META-INF/spring/spring-service-idoss-pengaduan-config.xml",
                "classpath:META-INF/spring/spring-based-dao-config.xml"
        }
)
public class PenangananGangguanServiceTest {

    @Autowired
    private PenangananGangguanService penangananGangguanService;

    @Test
    public void testGetAllPenangananGangguan() throws Exception {
        List<TPenangananGangguan> tPenangananGangguans = penangananGangguanService.getAllPenangananGangguan();
        String namaPelaporActual = null;
        for (TPenangananGangguan tPenangananGangguan : tPenangananGangguans) {
            namaPelaporActual = tPenangananGangguan.getNama_pelapor();
        }
        String namaPelaporExpected=null;
        assertEquals(namaPelaporExpected, namaPelaporActual);

    }

    @Test
    public void testGetPenangananGangguanbyTiketId() throws Exception {
        String tiketId ="000022ZHL87987897DELIVERY0000000";
        TPenangananGangguan tPenangananGangguan = penangananGangguanService.getPenangananGangguanbyTiketId(tiketId);
        assertEquals("000022ZHL87987897DELIVERY0000000",tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        assertEquals("JONTORR", tPenangananGangguan.getNama_pelapor());
    }

    @Test
    public void testGetEmployeeName() throws Exception {
        List<VHrEmployee> vHrEmployees = penangananGangguanService.getEmployeeName();
        String employee_nameActual = null;
        for (VHrEmployee vHrEmployee : vHrEmployees) {
            employee_nameActual = vHrEmployee.getEmployee_name();
        }
        String employee_nameExpected = "ZULHELMY";
        assertEquals(employee_nameExpected, employee_nameActual);
    }



    @Test
    public void testGetAllPenangananGanguanByNikPelapor() throws Exception {
        String nikPelapor="73950481";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelapor(nikPelapor);
        String statusActual = null;
        List<TPenangananGangguan> tPenangananGangguans = penangananGangguanService.getAllPenangananGanguanByNikPelapor(tPenangananGangguan);
        for (TPenangananGangguan penangananGangguan : tPenangananGangguans) {
             statusActual = penangananGangguan.getStatus();
        }
        String statusExpected="Open";
        assertEquals(statusExpected,statusActual);
    }
}
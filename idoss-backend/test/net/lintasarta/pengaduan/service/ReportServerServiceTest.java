package net.lintasarta.pengaduan.service;

import net.lintasarta.report.permohonan.model.ReportServer;
import net.lintasarta.report.permohonan.service.ReportServerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 20, 2010
 * Time: 3:42:36 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
                "classpath:META-INF/ibatis/ibatis-spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-based-dao-config.xml",
                "classpath:META-INF/spring/spring-service-idoss-pengaduan-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml",
                "classpath:META-INF/spring/spring-idoss-security-config.xml"
		}
)
public class ReportServerServiceTest {
    @Autowired
    private ReportServerService reportServerService;

    @Test
    public void testGetReportServerByReportId() throws Exception {
        ReportServer reportServer = reportServerService.getReportServerByReportId(5);
        assertNotNull(reportServer);
//        assertEquals(1,reportServer.getGangguan_report_id());
        assertEquals("Nope",reportServer.getBulan());
    }

    @Test
    public void testCreateReportServer() throws Exception{
        ReportServer reportServer = new ReportServer();
        reportServer.setTahun(2010);
        reportServer.setBulan("November");
        reportServer.setJumlah(1000);
        reportServer.setUpdate_by("jos");

        reportServerService.createReportServer(reportServer);
    }

    @Test
    public void testSaveOrUpdateReportServer() throws Exception{
        int gangguan_report_id = 6;

        ReportServer reportServer = reportServerService.getReportServerByReportId(gangguan_report_id);
        reportServer.setTahun(2012);
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        reportServer.setUpdate_date(ts);
        reportServer.setUpdate_by("zz");

        reportServerService.saveOrUpdateReportServer(reportServer);
        ReportServer reportServerResult = reportServerService.getReportServerByReportId(gangguan_report_id);
        String updated_byActual = reportServer.getUpdate_by();
        String updated_byExpected = "zz";

        assertEquals(updated_byExpected, updated_byActual);
    }
}

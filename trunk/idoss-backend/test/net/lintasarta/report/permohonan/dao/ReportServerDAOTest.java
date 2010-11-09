package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportServer;
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
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 6:12:00 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:META-INF/ibatis/ibatis-spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-based-dao-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-report-config.xml"
        }
)
public class ReportServerDAOTest {

    @Autowired
    ReportServerDAO reportServerDAO;

    @Test
    public void testGetReportServerByReportId() throws Exception {
        ReportServer reportServer = reportServerDAO.getReportServerByReportId(3);
        assertNotNull(reportServer);
        assertEquals("Maret", reportServer.getBulan());
    }

    @Test
    public void testCreateReportServer() throws Exception {
        ReportServer reportServer = new ReportServer();
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        reportServer.setGangguan_report_id(002);
        reportServer.setTahun(2010);
        reportServer.setBulan("NOV");
        reportServer.setJumlah(11);
        reportServer.setGen_id_col(1);
        reportServer.setUpdate_date(ts);
        reportServer.setUpdate_by("john");

        reportServerDAO.createReportServer(reportServer);
    }

    @Test
    public void testSaveOrUpdateMttr() throws Exception {
        int gangguan_report_id = 5;

        ReportServer reportServer = reportServerDAO.getReportServerByReportId(gangguan_report_id);
        reportServer.setBulan("Nope");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        reportServer.setUpdate_date(ts);
        reportServer.setUpdate_by("nn");

        reportServerDAO.saveOrUpdateReportServer(reportServer);
        ReportServer reportServerResult = reportServerDAO.getReportServerByReportId(gangguan_report_id);
        String updated_byActual = reportServer.getUpdate_by();
        String updated_byExpected = "nn";

        assertEquals(updated_byExpected, updated_byActual);
    }

    @Test
    public void testGetGenerateId() throws Exception {
        int i = reportServerDAO.getGenerateId();
    }
}
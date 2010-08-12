package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportPermohonan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 11, 2010
 * Time: 2:28:44 PM
 * To change this template use File | Settings | File Templates.
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
public class ReportPermohonanDAOTest {
    @Autowired
    private ReportPermohonanDAO reportPermohonanDAO;

    @Test
    public void testGetReportBelumSelesai() throws Exception {
        ReportPermohonan reportPermohonan = new ReportPermohonan();

        String tglPermohonan = "2010-08-19 00:00:00";
        reportPermohonan.setTgl_permohonan(Timestamp.valueOf(tglPermohonan));
        reportPermohonan.setType_permohonan("User RO");
        reportPermohonan.setNama_asman("i");

        String actualId = null;
        List<ReportPermohonan> reportPermohonans = reportPermohonanDAO.getReportBelumSelesai(reportPermohonan);
        for (ReportPermohonan permohonan : reportPermohonans) {
            actualId = permohonan.getT_idoss_permohonan_id();
        }
        String expectedId = "000000001";

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetReportSudahSelesai() throws Exception {
        ReportPermohonan reportPermohonan = new ReportPermohonan();

        String tglPermohonan = "2010-08-27 00:00:00";
        reportPermohonan.setTgl_permohonan(Timestamp.valueOf(tglPermohonan));
        reportPermohonan.setType_permohonan("User RO");
        reportPermohonan.setNama_asman("f");

        String actualId = null;
        List<ReportPermohonan> reportPermohonans = reportPermohonanDAO.getReportSudahSelesai(reportPermohonan);
        for (ReportPermohonan permohonan : reportPermohonans) {
            actualId = permohonan.getT_idoss_permohonan_id();
        }
        String expectedId = "000000002";

        assertEquals(expectedId, actualId);
    }
}
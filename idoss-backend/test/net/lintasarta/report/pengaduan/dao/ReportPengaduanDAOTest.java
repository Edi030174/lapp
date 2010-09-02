package net.lintasarta.report.pengaduan.dao;

import net.lintasarta.report.pengaduan.model.ReportPengaduan;
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
 * Date: Sep 2, 2010
 * Time: 9:52:39 AM
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
public class ReportPengaduanDAOTest {

    @Autowired
    private ReportPengaduanDAO reportPengaduanDAO;

    @Test
    public void testGetReportPengaduan() throws Exception {
        List<ReportPengaduan> reportPengaduans = reportPengaduanDAO.getReportPengaduan();
        assertEquals(reportPengaduans.size(),52);
    }
}

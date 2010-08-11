package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportPermohonan;
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
        List<ReportPermohonan> reportPermohonans = reportPermohonanDAO.getReportBelumSelesai();
        String updateduserActual = null;
        for (ReportPermohonan reportPermohonan : reportPermohonans) {
            updateduserActual = reportPermohonan.getT_idoss_permohonan_id();
        }
        String updateduserExpected = "000000001";
        assertEquals(updateduserExpected, updateduserActual);
    }
}

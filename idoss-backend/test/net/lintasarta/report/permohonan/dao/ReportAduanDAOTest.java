package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportAduan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 23, 2010
 * Time: 2:04:06 PM
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
public class ReportAduanDAOTest {
    @Autowired
    private ReportAduanDAO reportAduanDAO;

    @Test
    public void tesGetReportAduan() throws Exception{
        String bulan = "08";
        String tahun = "2010";
        ReportAduan reportAduan = new ReportAduan();
        reportAduan.setBulan(bulan);
        reportAduan.setTahun(tahun);
        List<ReportAduan> reportAduans = reportAduanDAO.getReportAduan(reportAduan);
        assertNotNull(reportAduans);
    }
}

package net.lintasarta.report.permohonan.dao;

import net.lintasarta.report.permohonan.model.ReportSudahSelesai;
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
 * Time: 3:12:52 PM
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
public class ReportSudahSelesaiDAOTest {
    @Autowired
    private ReportSudahSelesaiDAO reportSudahSelesaiDAO;

//    @Test
//    public void tesGetReportSudahSelesai() throws Exception{
//        List<ReportSudahSelesai> reportSudahSelesais = reportSudahSelesaiDAO.getReportSudahSelesai();
//        assertNotNull(reportSudahSelesais);
//    }
}

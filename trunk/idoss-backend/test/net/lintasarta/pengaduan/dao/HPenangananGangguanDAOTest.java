package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.HPenangananGangguan;
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
 * Time: 6:28:17 PM
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
public class HPenangananGangguanDAOTest {

    @Autowired
    private HPenangananGangguanDAO hPenangananGangguanDAO;

    @Test
    public void testGetCountAllHPenangananGangguan() throws Exception{
        assertEquals(0,hPenangananGangguanDAO.getCountAllHPenangananGangguan());
    }

    @Test
    public void testGetAllHPenangananGangguan() throws Exception{
        List<HPenangananGangguan> hPenangananGangguans = hPenangananGangguanDAO.getAllHPenangananGangguan();
        String fieldNameActual = null;
        for (HPenangananGangguan hPenangananGangguan : hPenangananGangguans) {
            fieldNameActual = hPenangananGangguan.getField_name();
        }
        String fieldNameExpected =null;
    }
    
    @Test
    public void createHPenangananGangguan() throws Exception{
        int i = hPenangananGangguanDAO.getCountAllHPenangananGangguan();
        HPenangananGangguan hPenangananGangguan = new HPenangananGangguan();
        hPenangananGangguan.setField_name("aa");
        hPenangananGangguan.setAction("a");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        hPenangananGangguan.setCreated_date(ts);
        hPenangananGangguan.setCreated_user("2344");
        hPenangananGangguanDAO.createHPenangananGanguan(hPenangananGangguan);
        assertEquals(i+1, hPenangananGangguanDAO.getCountAllHPenangananGangguan());

    }
}

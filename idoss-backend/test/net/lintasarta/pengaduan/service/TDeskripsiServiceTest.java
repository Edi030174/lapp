package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.TDeskripsi;
import net.lintasarta.pengaduan.service.TDeskripsiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Oct 13, 2010
 * Time: 2:45:23 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:META-INF/ibatis/ibatis-spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-based-dao-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml",
                "classpath:META-INF/spring/spring-service-idoss-pengaduan-config.xml",
                "classpath:META-INF/spring/spring-idoss-security-config.xml"
        }
)
public class TDeskripsiServiceTest {
    @Autowired
    private TDeskripsiService tDeskripsiService;

    @Test
    public void testGetTDeskripsiByGangguanId() {
        String id = "000001072";
        List<TDeskripsi> tDeskripsiList = tDeskripsiService.getTDeskripsiByGangguanId(id);
        assertEquals(1, tDeskripsiList.size());
    }

    @Test
    public void testCreateTDeskripsi() {
        TDeskripsi tDeskripsi = new TDeskripsi();
        tDeskripsi.setDeskripsi("deskripsi test lagi");
        tDeskripsi.setSolusi("solusi test lagi");
        tDeskripsi.setUpdated_by("Anton lagi");
        tDeskripsi.setUpdated_date(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        tDeskripsi.setT_idoss_penanganan_gangguan_id("0");
        tDeskripsiService.createTDeskripsi(tDeskripsi, null);

        assertEquals(5, tDeskripsiService.getTDeskripsiByGangguanId("0").size());
    }
}

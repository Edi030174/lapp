package net.lintasarta.permohonan.service;

import net.lintasarta.permohonan.model.TPelaksanaan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 4, 2010
 * Time: 7:32:50 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
				"classpath:META-INF/spring/spring-config.xml",
				"classpath:META-INF/spring/datasource.xml",
				"classpath:META-INF/spring/spring-idoss-security-config.xml",
				"classpath:META-INF/spring/spring-dao-idoss-permohonan-config.xml",
				"classpath:META-INF/spring/spring-service-idoss-permohonan-config.xml"
		}
)
public class PelaksanaanServiceTest {
    @Autowired
    private PelaksanaanService pelaksanaanService;

    @Test
    public void testCreateTPelaksanaan() throws Exception {
        int z = pelaksanaanService.getCountAllTPelaksanaan();
        TPelaksanaan tPelaksanaan = new TPelaksanaan();
        tPelaksanaan.setT_idoss_pelaksanaan_id("GGG888");

        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPelaksanaan.setTgl_permohonan(now);
        tPelaksanaan.setRfs("9");
        tPelaksanaan.setId_pelaksana("333");
        tPelaksanaan.setCreated_date(now);
        tPelaksanaan.setCreated_user("7777");

        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPelaksanaan.setUpdated_date(ts);

        tPelaksanaan.setUpdated_user("6363");
        pelaksanaanService.createTPelaksanaan(tPelaksanaan);
        assertEquals(z + 1, pelaksanaanService.getCountAllTPelaksanaan());
    }
}

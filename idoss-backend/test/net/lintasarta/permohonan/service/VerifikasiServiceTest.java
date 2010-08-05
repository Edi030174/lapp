package net.lintasarta.permohonan.service;

import net.lintasarta.permohonan.model.TVerifikasi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 4, 2010
 * Time: 7:32:33 PM
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

public class VerifikasiServiceTest {
    @Autowired
    private VerifikasiService verifikasiService;

    @Test
    public void testCreateTVerifikasi() throws Exception {
        TVerifikasi tVerifikasi = new TVerifikasi();
        tVerifikasi.setT_idoss_verifikasi_id("xxx");
        tVerifikasi.setNik_pelaksana("1234");
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
        tVerifikasi.setTgl_permohonan(ts);
        tVerifikasi.setUpdated_asman(ts);
        tVerifikasi.setCatatan_asman("tes");
        tVerifikasi.setCatatan_manager("test");
        tVerifikasi.setCatatan_per_type("test");
        tVerifikasi.setCreated_date(ts);
        tVerifikasi.setUpdated_date(ts);
        tVerifikasi.setCreated_user("jos");
        tVerifikasi.setUpdated_user("jos");
        tVerifikasi.setDampak("T");
        tVerifikasi.setRfs("t");
        tVerifikasi.setStatus_permohonan_asman("t");
        tVerifikasi.setStatus_permohonan_manager("t");
        tVerifikasi.setType_permohonan("a");
        tVerifikasi.setUpdated_asman(ts);
        tVerifikasi.setUpdated_manager(ts);
        tVerifikasi.setUrgensi("d");

        int i = verifikasiService.getAllTVerifikasi().size();

        verifikasiService.createTVerifikasi(tVerifikasi);
        assertEquals(i+1, verifikasiService.getAllTVerifikasi().size());
    }
}

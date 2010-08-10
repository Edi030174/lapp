package net.lintasarta.permohonan.service;

import net.lintasarta.permohonan.model.TPermohonan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 4, 2010
 * Time: 7:32:07 PM
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
public class PermohonanServiceTest {
    @Autowired
    private PermohonanService permohonanService;

    @Test
    public void testGetTPermohonanByNomorPermohonanId() throws Exception {
        String t_idoss_permohonan_id = "JOSH167";
        TPermohonan tPermohonan = permohonanService.getTPermohonanByTIdossPermohonanId(t_idoss_permohonan_id);
        assertNotNull(tPermohonan);
        assertEquals("JOSH167",tPermohonan.getT_idoss_permohonan_id());
    }
    @Test
    public void testCreateTPermohonan() throws Exception {
//        int r = permohonanService.getCountAllTPermohonan();
        TPermohonan tPermohonan = new TPermohonan();

        tPermohonan.setT_idoss_permohonan_id("RR150");

        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPermohonan.setTgl_permohonan(now);
        tPermohonan.setDampak("1");
        tPermohonan.setUrgensi("1");

        String tglIsian = "1987-01-07 08:20:10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date tglDate = sdf.parse(tglIsian);
        Timestamp dt = new Timestamp(tglDate.getTime());
        tPermohonan.setTarget_mulai_digunakan(dt);

        tPermohonan.setStatus_track_permohonan("PERMOHONAN_BARU");
        tPermohonan.setNik_pemohon("894928423");
        tPermohonan.setNama_pemohon("JOHN");
        tPermohonan.setBagian_pemohon("DIREKSI");
//        tPermohonan.setNik_divisi("60700077");
//        tPermohonan.setNama_divisi("DOE");
//        tPermohonan.setDivisi("DIREKSI");
        tPermohonan.setNik_asman("9999999");
        tPermohonan.setNama_asman("VVV");
//        tPermohonan.setAsman("SECURITY");
        tPermohonan.setNik_manager("6666666");
        tPermohonan.setNama_manager("EG");
        tPermohonan.setNik_gm("3333333");
        tPermohonan.setNama_gm("CCC");

        tPermohonan.setType_permohonan("READ_ONLY");

        tPermohonan.setCreated_date(now);

        tPermohonan.setCreated_user("39393939");

        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPermohonan.setUpdated_date(ts);

        tPermohonan.setUpdated_user("39393939");

        int i = permohonanService.getAllTPermohonan().size();

        permohonanService.createTPermohonan("ttk", tPermohonan);
        assertEquals(i+1, permohonanService.getAllTPermohonan().size());

//        assertEquals(r + 1, tPermohonanDAO.getCountAllTPermohonan());
    }

    @Test
    public void testSimpanAllTPermohonan() throws Exception{
        TPermohonan tPermohonan = new TPermohonan();

        tPermohonan.setT_idoss_permohonan_id("RR151");

        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPermohonan.setTgl_permohonan(now);
        tPermohonan.setDampak("1");
        tPermohonan.setUrgensi("1");

        String tglIsian = "1987-01-07 08:20:10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date tglDate = sdf.parse(tglIsian);
        Timestamp dt = new Timestamp(tglDate.getTime());
        tPermohonan.setTarget_mulai_digunakan(dt);

        tPermohonan.setStatus_track_permohonan("PERMOHONAN_BARU");
        tPermohonan.setNik_pemohon("894928423");
        tPermohonan.setNama_pemohon("JOHN");
        tPermohonan.setBagian_pemohon("DIREKSI");
        tPermohonan.setNik_asman("9999999");
        tPermohonan.setNama_asman("VVV");
        tPermohonan.setNik_manager("6666666");
        tPermohonan.setNama_manager("EG");
        tPermohonan.setNik_gm("3333333");
        tPermohonan.setNama_gm("CCC");

        tPermohonan.setType_permohonan("READ_ONLY");

        tPermohonan.setCreated_date(now);

        tPermohonan.setCreated_user("39393939");

        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPermohonan.setUpdated_date(ts);

        tPermohonan.setUpdated_user("39393939");

        int i = permohonanService.getAllTPermohonan().size();

        permohonanService.simpanAllTPermohonan("okok", tPermohonan);
        assertEquals(i+1, permohonanService.getAllTPermohonan().size());

    }

}

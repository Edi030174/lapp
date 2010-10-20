package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.Mttr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 20, 2010
 * Time: 3:42:36 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
				"classpath:META-INF/ibatis/ibatis-spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-based-dao-config.xml",
                "classpath:META-INF/spring/spring-service-idoss-pengaduan-config.xml"
		}
)
public class MttrServiceTest {
    @Autowired
    private MttrService mttrService;

    @Test
    public void testGetPRootCausedByRootCausedId() throws Exception {
        Mttr mttr = mttrService.getMttrByMttrId(1);
        assertNotNull(mttr);
        assertEquals(1,mttr.getT_idoss_mttr_id());
        assertEquals(5,mttr.getMttr());
    }

    @Test
    public void testCreateMttr() throws Exception{
        Mttr mttr = new Mttr();
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        mttr.setT_idoss_mttr_id(2);
        mttr.setNomor_tiket("002");
        mttr.setMttr(4);
        mttr.setOpened(7);
        mttr.setClosed(9);
        mttr.setInprogress(8);
        mttr.setPending(4);
        mttr.setTarget(6);
        mttr.setUpdated_by("johnny");
        mttr.setUpdated_date(ts);

        mttrService.createMttr(mttr);
    }

    @Test
    public void testSaveOrUpdateMttr() throws Exception{
        int t_idoss_mttr_id = 2;

        Mttr mttr = mttrService.getMttrByMttrId(t_idoss_mttr_id);
        mttr.setMttr(9);
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        mttr.setUpdated_date(ts);
        mttr.setUpdated_by("re");

        mttrService.saveOrUpdateMttr(mttr);
        Mttr mttrResult = mttrService.getMttrByMttrId(t_idoss_mttr_id);
        String updated_byActual = mttr.getUpdated_by();
        String updated_byExpected = "re";

        assertEquals(updated_byExpected, updated_byActual);
    }
}

package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PRootCaused;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Joshua
 * Date: Jun 23, 2010
 * Time: 6:41:31 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
				"classpath:META-INF/spring/spring-config.xml",
				"classpath:META-INF/spring/datasource.xml",
				"classpath:META-INF/spring/spring-idoss-security-config.xml",
				"classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml",
				"classpath:META-INF/spring/spring-dao-idoss-permohonan-config.xml",
                "classpath:META-INF/spring/spring-service-idoss-pengaduan-config.xml"
		}
)
public class RootCausedServiceTest {

    @Autowired
    private RootCausedService rootCausedService;

    @Test
    public void testGetAllPRootCaused (){
        List<PRootCaused> pRootCauseds = rootCausedService.getAllRootCaused();
        String rootCausedActual = null;
        for(PRootCaused pRootCaused :pRootCauseds){
            rootCausedActual = pRootCaused.getRoot_caused();
        }
        String rootCausedExpected = "test";
        assertEquals(rootCausedExpected, rootCausedActual);
    }

    @Test
    public void testGetRootCausedById() throws Exception {
        PRootCaused pRootCaused = rootCausedService.getPRootCausedByRootCausedId(1);
        assertNotNull(pRootCaused);
        assertEquals(1,pRootCaused.getP_idoss_root_caused_id());
        assertEquals("jafja",pRootCaused.getRoot_caused());
    }

    @Test
    public void testCreateRootCaused() throws Exception {
        PRootCaused pRootCaused = new PRootCaused();
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pRootCaused.setRoot_caused("haelo");
        pRootCaused.setActive("T");
        pRootCaused.setCreated_date(ts);
        pRootCaused.setCreated_user("2472898");
        pRootCaused.setUpdated_date(ts);
        pRootCaused.setUpdated_user("2472897");
        rootCausedService.createRootCaused(pRootCaused);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        int rootCausudId = 3;

        PRootCaused pRootCaused = rootCausedService.getPRootCausedByRootCausedId(rootCausudId);
        pRootCaused.setRoot_caused("MANTAP");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pRootCaused.setUpdated_date(ts);
        pRootCaused.setUpdated_user("2472898");
        rootCausedService.saveOrUpdate(pRootCaused);

        String rootCausedActual = pRootCaused.getRoot_caused();
        String rootCausedExpected = "MANTAP";

        assertEquals(rootCausedExpected, rootCausedActual);

    }
}
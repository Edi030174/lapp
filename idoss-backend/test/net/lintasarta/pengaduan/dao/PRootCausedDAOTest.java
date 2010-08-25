package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.dao.PRootCausedDAO;
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
import static org.junit.Assert.assertNull;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 6:12:00 PM
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
public class PRootCausedDAOTest {

    @Autowired
    PRootCausedDAO pRootCausedDAO;

     @Test
    public void testGetCountALLPRootCAusedDAO() throws Exception{
        assertEquals(1, pRootCausedDAO.getCountAllPRootCaused());
    }

    @Test
    public void testGetAllPRootCaused() throws Exception{
        List<PRootCaused> pRootCauseds = pRootCausedDAO.getAllPRootCaused();
        String rootCausedActual = null;
        for(PRootCaused pRootCaused :pRootCauseds){
            rootCausedActual = pRootCaused.getRoot_caused();
        }
        String rootCausedExpected = "test";

        assertEquals(rootCausedExpected, rootCausedActual);
    }

    @Test
    public void testGetPRootCausedByRootCausedId() throws Exception {
        PRootCaused pRootCaused = pRootCausedDAO.getPRootCausedByRootCausedId(1);

        assertNotNull(pRootCaused);
        assertEquals(1,pRootCaused.getP_idoss_root_caused_id());
        assertEquals("jafja",pRootCaused.getRoot_caused());
    }

    @Test
    public void testCreatePRootCaused() throws Exception{
        int i = pRootCausedDAO.getCountAllPRootCaused();

        PRootCaused pRootCaused = new PRootCaused();
        int j = pRootCausedDAO.getGenerateId();
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pRootCaused.setP_idoss_root_caused_id(j);
        pRootCaused.setRoot_caused("fhahf");
        pRootCaused.setActive("a");
        pRootCaused.setCreated_date(ts);
        pRootCaused.setCreated_user("10");
        pRootCaused.setUpdated_date(ts);
        pRootCaused.setUpdated_user("2472897");
        pRootCausedDAO.createPRootCaused(pRootCaused);

        assertEquals(i + 1,pRootCausedDAO.getCountAllPRootCaused());
    }

    @Test
    public void saveOrUpdate() throws Exception{
        int rootCausedId = 2;

        PRootCaused pRootCaused = pRootCausedDAO.getPRootCausedByRootCausedId(rootCausedId);
        pRootCaused.setRoot_caused("jafja");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pRootCaused.setUpdated_date(ts);
        pRootCaused.setUpdated_user("afaf");

        pRootCausedDAO.saveOrUpdate(pRootCaused);
        PRootCaused pRootCausedResult = pRootCausedDAO.getPRootCausedByRootCausedId(rootCausedId);
        String rootCausedActual = pRootCaused.getRoot_caused();
        String rootCausedExpected = "jafja";

        assertEquals(rootCausedExpected, rootCausedActual);
    }

    @Test
    public void testGetPRootCausedByPTypeID() throws Exception {
        int ptypeId = 4011;
        List<PRootCaused> pRootCauseds = pRootCausedDAO.getPRootCausedByPTypeID(ptypeId);
        assertEquals(pRootCauseds.size(),7);
    }
}

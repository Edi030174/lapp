package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.PTypeRootCaused;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 30, 2010
 * Time: 9:09:40 AM
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

public class PTypeRootCausedTest {
    @Autowired
    PTypeRootCausedDAO pTypeRootCausedDAO;

    @Test
    public void testCreatePTypeRootCaused() throws Exception {
        PTypeRootCaused pTypeRootCaused = new PTypeRootCaused();

        pTypeRootCaused.setP_idoss_root_caused_id(34);
        pTypeRootCaused.setP_idoss_type_id("4012");
        pTypeRootCaused.setActive("Y");
        pTypeRootCaused.setCreated_user("ZHL");
        pTypeRootCaused.setUpdated_user("ZHL");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pTypeRootCaused.setCreated_date(ts);
        pTypeRootCaused.setUpdated_date(ts);
        pTypeRootCausedDAO.createPTypeRootCaused(pTypeRootCaused);
        
    }
}

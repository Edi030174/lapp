package net.lintasarta.pengaduan.dao;

import net.lintasarta.pengaduan.model.TDeskripsi;
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
 * User: Antonius
 * Date: Oct 13, 2010
 * Time: 1:18:44 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:META-INF/ibatis/ibatis-spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-based-dao-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml"
        }
)
public class TDeskripsiDAOTest {
    @Autowired
    private TDeskripsiDAO tDeskripsiDAO;

    @Test
    public void testGetTDeskripsiByGangguanId() {
        String id = "0";
        List<TDeskripsi> tDeskripsiList = tDeskripsiDAO.getTDeskripsiByGangguanId(id);
        assertEquals(1, tDeskripsiList.size());

    }

    @Test
    public void testGenerateTDeskripsiId() {
        int id = tDeskripsiDAO.getId();
        assertEquals(-2, id);
    }

    @Test
    public void testCreateTDeskripsi() {
        TDeskripsi tDeskripsi = new TDeskripsi();
        tDeskripsi.setT_idoss_deskripsi_id(-3);
        tDeskripsi.setSolusi("solusi test 1");
        tDeskripsi.setDeskripsi("deskripsi test 1");
        tDeskripsi.setT_idoss_penanganan_gangguan_id("0");
        tDeskripsi.setUpdated_by("Anton");
        tDeskripsi.setUpdated_date(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        tDeskripsiDAO.createTDeskripsi(tDeskripsi);

        List<TDeskripsi> tDeskripsiList = tDeskripsiDAO.getTDeskripsiByGangguanId("0");
        assertEquals(1, tDeskripsiList.size());
    }
}

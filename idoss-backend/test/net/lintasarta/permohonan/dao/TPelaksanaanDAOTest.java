package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.TPelaksanaan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Joshua
 * Date: Jun 24, 2010
 * Time: 9:07:54 AM
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
public class TPelaksanaanDAOTest {
    @Autowired
    private TPelaksanaanDAO tPelaksanaanDAO;

    @Test
    public void testGetCountAllTPelaksanaan() throws Exception {
        assertEquals(1005, tPelaksanaanDAO.getCountAllTPelaksanaan());
    }

    @Test
    public void testGetAllTPelaksanaan() throws Exception {
        List<TPelaksanaan> tPelaksanaans = tPelaksanaanDAO.getAllTPelaksanaan();
        String updateduserActual = null;
        for (TPelaksanaan tPelaksanaan : tPelaksanaans){
            updateduserActual = tPelaksanaan.getUpdated_user();
        }
        String updateduserExpected = "0606";
        assertEquals(updateduserExpected, updateduserActual);
    }

    @Test
    public void testGetTPelaksanaanByTIdossPelaksanaanId() throws Exception {
        String t_idoss_pelaksanaan_id = "JOSH121";
        TPelaksanaan tPelaksanaan = tPelaksanaanDAO.getTPelaksanaanByTIdossPelaksanaanId(t_idoss_pelaksanaan_id);
        assertNotNull(tPelaksanaan);
        assertEquals("JOSH121",tPelaksanaan.getT_idoss_pelaksanaan_id());
    }
    @Test
    public void testGetTPelaksananByIdPelaksana() throws Exception {
        String idPelaksana = "4234324";
        List<TPelaksanaan> tPelaksanaans = tPelaksanaanDAO.getTPelaksananByIdPelaksana(idPelaksana);
        String idPelaksanaActual = null;
        for (TPelaksanaan pelaksanaan : tPelaksanaans) {
            idPelaksanaActual = pelaksanaan.getId_pelaksana();
        }
        String idPelaksanaExpected = "4234324";
        assertEquals(idPelaksanaExpected,idPelaksanaActual);
    }
    @Test
    public void testGetTPelaksananByStatusPerubahan() throws Exception {
        String statusPerubahan = "OPEN";
        List<TPelaksanaan> tPelaksanaans = tPelaksanaanDAO.getTPelaksananByStatusPerubahan(statusPerubahan);
        String statusperubahanActual = null;
        for (TPelaksanaan tPelaksanaan : tPelaksanaans) {
            statusperubahanActual = tPelaksanaan.getStatus_perubahan();
        }
        String statusperubahanExpected = "OPEN";
        assertEquals(statusperubahanExpected, statusperubahanActual);
    }
    @Test
    public void testGetTPelaksananByIdPelaksanaStatus() throws Exception {
        String idPelaksana = "4234324";
        String statusPerubahan = "OPEN";

        TPelaksanaan tPelaksanaan = new TPelaksanaan();
        tPelaksanaan.setId_pelaksana(idPelaksana);
        tPelaksanaan.setStatus_perubahan(statusPerubahan);
        List<TPelaksanaan> tPelaksanaans = tPelaksanaanDAO.getTPelaksananByIdPelaksanaStatus(tPelaksanaan);
        String statusperubahanActual = null;
        for (TPelaksanaan pelaksanaan : tPelaksanaans) {
             statusperubahanActual=pelaksanaan.getStatus_perubahan();
        }
        String statusperubahanExpected = "OPEN";
        assertEquals(statusperubahanExpected, statusperubahanActual);
    }

    @Test
    public void testCreateTPelaksanaan() throws Exception {
        int z = tPelaksanaanDAO.getCountAllTPelaksanaan();
        TPelaksanaan tPelaksanaan = new TPelaksanaan();
        tPelaksanaan.setT_idoss_pelaksanaan_id("ZZZ999");

        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPelaksanaan.setTgl_permohonan(now);
        tPelaksanaan.setRfs("9");
        tPelaksanaan.setId_pelaksana("333");
        tPelaksanaan.setCreated_date(now);
        tPelaksanaan.setCreated_user("333333");

        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPelaksanaan.setUpdated_date(ts);
        
        tPelaksanaan.setUpdated_user("6363");
        tPelaksanaanDAO.createTPelaksanaan(tPelaksanaan);
        assertEquals(z + 1, tPelaksanaanDAO.getCountAllTPelaksanaan());
    }

    @Test
    public void testSaveOrUpdateTPelaksanaan() throws ParseException {
        String t_idoss_pelaksanaan_id = "00555FSHOPHAR2010";

        TPelaksanaan tPelaksanaan = tPelaksanaanDAO.getTPelaksanaanByTIdossPelaksanaanId(t_idoss_pelaksanaan_id);
        
        tPelaksanaan.setRfs("RFS76");

//        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        String tglIsian = "1976-01-26 06:05:10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date tglDate = sdf.parse(tglIsian);
//        Timestamp ts = new Timestamp(tglDate.getTime());
        Timestamp dt = new Timestamp(tglDate.getTime());
        tPelaksanaan.setRfs_date(dt);

        tPelaksanaan.setId_pelaksana("7676");
        tPelaksanaan.setNama_pelaksana("Ants");
        tPelaksanaan.setStatus_perubahan("CLOSED");

        tPelaksanaanDAO.saveOrUpdateTPelaksanaan(tPelaksanaan);

        String namaPelaksanaActual = tPelaksanaan.getNama_pelaksana();
        String namaPelaksanaExpected = "Ants";

        assertEquals(namaPelaksanaExpected, namaPelaksanaActual);
    }
}
package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.TVerifikasi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Joshua
 * Date: Jun 24, 2010
 * Time: 9:24:28 AM
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
public class TVerifikasiDAOTest {
    @Autowired
    private TVerifikasiDAO tVerifikasiDAO;

    @Test
    public void testCountAllTVerifikasi() throws Exception {
        assertEquals(1000, tVerifikasiDAO.getCountAllTVerifikasi());
    }

    @Test
    public void testGetAllTVerifikasi()throws Exception{
        List<TVerifikasi> tVerifikasis = tVerifikasiDAO.getAllTVerifikasi();
        String updateduserActual = null;
        for (TVerifikasi tVerifikasi : tVerifikasis) {
                  updateduserActual = tVerifikasi.getUpdated_user();
        }
        String updateduserExpected = "31242342";
        assertEquals(updateduserExpected, updateduserActual);
    }

    @Test
    public void testGetTPelaksanaanByNomorPermohonanId() throws Exception {
        String t_idoss_verifikasi_id = "00777FSHOPHAR2010";
        TVerifikasi tVerifikasi = tVerifikasiDAO.getTVerifikasiByTIdossVerifikasiId(t_idoss_verifikasi_id);
        assertNotNull(tVerifikasi);
        assertEquals("00777FSHOPHAR2010",tVerifikasi.getT_idoss_verifikasi_id());
    }

    @Test
        public void testGetTVerifikasiByNikPelaksana() throws Exception {
        String nikPelaksana = "333333";
        TVerifikasi tVerifikasi = new TVerifikasi();
        tVerifikasi.setNik_pelaksana(nikPelaksana);
        List<TVerifikasi> tVerifikasis = tVerifikasiDAO.getTVerifikasiByNikPelaksana(tVerifikasi);
        String nikPelaksanaActual = null;
        for (TVerifikasi verifikasi : tVerifikasis) {
            nikPelaksanaActual = verifikasi.getNik_pelaksana();
        }
        String nikPelaksanaExpected = "333333";
        assertEquals(nikPelaksanaExpected, nikPelaksanaActual);
    }

    @Test
        public void testGetTVerifikasiByStatusPermohonanAsman() throws Exception {
        String statusPermohonanAsman = "READ_ONLY";
        TVerifikasi tVerifikasi = new TVerifikasi();
        tVerifikasi.setStatus_permohonan_asman(statusPermohonanAsman);
        List<TVerifikasi> tVerifikasis = tVerifikasiDAO.getTVerifikasiByStatusPermohonanAsman(tVerifikasi);
        String statusPermohonanAsmanActual = null;
        for (TVerifikasi verifikasi : tVerifikasis) {
            statusPermohonanAsmanActual = verifikasi.getStatus_permohonan_asman();
        }
        String statusPermohonanAsmanExpected = "READ_ONLY";
        assertEquals(statusPermohonanAsmanExpected, statusPermohonanAsmanActual);
    }

    @Test
        public void testGetTVerifikasiByStatusPermohonanManager() throws Exception {
        String statusPermohonanManager = "FULL";
        TVerifikasi tVerifikasi = new TVerifikasi();
        tVerifikasi.setStatus_permohonan_manager(statusPermohonanManager);
        List<TVerifikasi> tVerifikasis = tVerifikasiDAO.getTVerifikasiByStatusPermohonanManager(tVerifikasi);
        String statusPermohonanManagerActual = null;
        for (TVerifikasi verifikasi : tVerifikasis) {
            statusPermohonanManagerActual = verifikasi.getStatus_permohonan_manager();
        }
        String statusPermohonanManagerExpected = "FULL";
        assertEquals(statusPermohonanManagerExpected, statusPermohonanManagerActual);
    }

    @Test
    public void testGetTVerifikasiByNikPelaksanaStatusPA() throws Exception {
        String nikPelaksana = "333333";
        String statusPermohonanAsman = "READ_ONLY";

        TVerifikasi tVerifikasi = new TVerifikasi();
        tVerifikasi.setNik_pelaksana(nikPelaksana);
        tVerifikasi.setStatus_permohonan_asman(statusPermohonanAsman);
        List<TVerifikasi> tVerifikasis = tVerifikasiDAO.getTVerifikasiByNikPelaksanaStatusPA(tVerifikasi);
        String statusPermohonanAsmanActual = null;
        for (TVerifikasi verifikasi : tVerifikasis) {
             statusPermohonanAsmanActual=verifikasi.getStatus_permohonan_asman();
        }
        String statusPermohonanAsmanExpected = "READ_ONLY";
        assertEquals(statusPermohonanAsmanExpected, statusPermohonanAsmanActual);
    }

    @Test
    public void testGetTVerifikasiByNikPelaksanaStatusPM() throws Exception {
        String nikPelaksana = "222222";
        String statusPermohonanManager = "FULL";

        TVerifikasi tVerifikasi = new TVerifikasi();
        tVerifikasi.setNik_pelaksana(nikPelaksana);
        tVerifikasi.setStatus_permohonan_manager(statusPermohonanManager);
        List<TVerifikasi> tVerifikasis = tVerifikasiDAO.getTVerifikasiByNikPelaksanaStatusPM(tVerifikasi);
        String statusPermohonanManagerActual = null;
        for (TVerifikasi verifikasi : tVerifikasis) {
             statusPermohonanManagerActual=verifikasi.getStatus_permohonan_manager();
        }
        String statusPermohonanManagerExpected = "FULL";
        assertEquals(statusPermohonanManagerExpected, statusPermohonanManagerActual);
    }

    @Test
    public void testCreateTVerifikasi() throws Exception {
        int z = tVerifikasiDAO.getCountAllTVerifikasi();
        TVerifikasi tVerifikasi = new TVerifikasi();

        tVerifikasi.setT_idoss_verifikasi_id("JOSH999");

        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tVerifikasi.setTgl_permohonan(now);

        tVerifikasi.setUrgensi("1");
        tVerifikasi.setDampak("1");
        tVerifikasi.setType_permohonan("USER RO");
        tVerifikasi.setNik_pelaksana("897897");
        tVerifikasi.setRfs("4");
//        tVerifikasi.setCatatan_per_type("jejejejeje");
        tVerifikasi.setStatus_permohonan_asman("NEW");

        String tglIsian = "2010-06-29 15:00:10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date tglDate = sdf.parse(tglIsian);
        Timestamp dt = new Timestamp(tglDate.getTime());
        tVerifikasi.setUpdated_asman(dt);

//        Blob b = new BLOB();
//        tVerifikasi.setCatatan_asman();
        tVerifikasi.setStatus_permohonan_manager("FULL");
        tVerifikasi.setUpdated_manager(dt);
        tVerifikasi.setCreated_date(dt);
        tVerifikasi.setCreated_user("S");
        tVerifikasi.setUpdated_date(dt);
        tVerifikasi.setUpdated_user("A");

        
        tVerifikasiDAO.createTVerifikasi(tVerifikasi);
        assertEquals(z + 1, tVerifikasiDAO.getCountAllTVerifikasi());
    }

    @Test
    public void saveOrUpdateTVerifikasi() throws Exception{
        String t_idoss_verifikasi_id = "00777FSHOPHAR2010";
        TVerifikasi tVerifikasi = tVerifikasiDAO.getTVerifikasiByTIdossVerifikasiId(t_idoss_verifikasi_id);
        tVerifikasi.setDampak("MAYOR");
        tVerifikasi.setUpdated_user("WTF");
        tVerifikasiDAO.saveOrUpdateTVerifikasi(tVerifikasi);
        String updatedUserActual = tVerifikasi.getUpdated_user();
        String updatedUserExpected = "WTF";
        assertEquals(updatedUserExpected,updatedUserActual);
        
    }
}

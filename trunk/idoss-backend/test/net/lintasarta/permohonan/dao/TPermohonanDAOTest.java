package net.lintasarta.permohonan.dao;

import net.lintasarta.permohonan.model.TPermohonan;
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

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Joshua
 * Date: Jun 23, 2010
 * Time: 10:25:41 AM
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
public class TPermohonanDAOTest {
    @Autowired
    private TPermohonanDAO tPermohonanDAO;

    @Test
    public void testGetCountAllTPermohonan() throws Exception {
        assertEquals(1000, tPermohonanDAO.getCountAllTPermohonan());
    }

    @Test
    public void testGetAllTPermohonan(){
        List<TPermohonan> tPermohonans = tPermohonanDAO.getAllTPermohonan();
        String updateuserActual = null;
        for (TPermohonan tPermohonan : tPermohonans){
            updateuserActual = tPermohonan.getUpdated_user();
        }
        String updateuserExpected = "77960587-FAJAR BAYU KURNIADHI";
        assertEquals(updateuserExpected, updateuserActual);
    }

    @Test
    public void testGetTPermohonanByTIdossPermohonanId() throws Exception {
        String t_idoss_permohonan_id = "JOSH167";
        TPermohonan tPermohonan = tPermohonanDAO.getTPermohonanByTIdossPermohonanId(t_idoss_permohonan_id);
        assertNotNull(tPermohonan);
        assertEquals("JOSH167",tPermohonan.getT_idoss_permohonan_id());
    }
    @Test
    public void testGetTPermohonanByNikPemohon() throws Exception {
        String nikPemohon = "12313123";
        TPermohonan tPermohonan = new TPermohonan();
        tPermohonan.setNik_pemohon(nikPemohon);
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikPemohon(tPermohonan);
        String nikPemohonActual = null;
        for (TPermohonan permohonan : tPermohonans) {
            nikPemohonActual = permohonan.getNik_pemohon();
        }
        String nikPemohonExpected = "12313123";
        assertEquals(nikPemohonExpected, nikPemohonActual);
    }
//    @Test
//    public void testGetTPermohonanByNikDivisi() throws Exception {
//        String nikDivisi = "51900065";
//        TPermohonan tPermohonan = new TPermohonan();
//        tPermohonan.setNik_divisi(nikDivisi);
//        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikDivisi(tPermohonan);
//        String nikDivisiActual = null;
//        for (TPermohonan permohonan : tPermohonans) {
//            nikDivisiActual = permohonan.getNik_divisi();
//        }
//        String nikDivisiExpected = "51900065";
//        assertEquals(nikDivisiExpected, nikDivisiActual);
//    }
    @Test
    public void testGetTPermohonanByNikAsman() throws Exception {
        String nikAsman = "341213221";
        TPermohonan tPermohonan = new TPermohonan();
        tPermohonan.setNik_asman(nikAsman);
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikAsman(tPermohonan);
        String nikAsmanActual = null;
        for (TPermohonan permohonan : tPermohonans) {
            nikAsmanActual = permohonan.getNik_asman();
        }
        String nikAsmanExpected = "341213221";
        assertEquals(nikAsmanExpected, nikAsmanActual);
    }

    @Test
    public void testGetTPermohonanByNikManager() throws Exception {
        String nikManager = "21321312";
        TPermohonan tPermohonan = new TPermohonan();
        tPermohonan.setNik_manager(nikManager);
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikManager(tPermohonan);
        String nikManagerActual = null;
        for (TPermohonan permohonan : tPermohonans) {
            nikManagerActual = permohonan.getNik_manager();
        }
        String nikManagerExpected = "21321312";
        assertEquals(nikManagerExpected, nikManagerActual);
    }
    @Test
    public void testGetTPermohonanByNikGm() throws Exception {
        String nikGm = "3423432";
        TPermohonan tPermohonan = new TPermohonan();
        tPermohonan.setNik_gm(nikGm);
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikGm(tPermohonan);
        String nikGmActual = null;
        for (TPermohonan permohonan : tPermohonans) {
            nikGmActual = permohonan.getNik_gm();
        }
        String nikGmExpected = "3423432";
        assertEquals(nikGmExpected, nikGmActual);
    }
    @Test
    public void testGetTPermohonanByStatusTrackPermohonan() throws Exception {
        String statusTrackPermohonan = "NEW";
        TPermohonan tPermohonan = new TPermohonan();
        tPermohonan.setStatus_track_permohonan(statusTrackPermohonan);
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByStatusTrackPermohonan(tPermohonan);
        String statusTrackPermohonanActual = null;
        for (TPermohonan permohonan : tPermohonans) {
            statusTrackPermohonanActual = permohonan.getStatus_track_permohonan();
        }
        String statusTrackPermohonanActualExpected = "NEW";
        assertEquals(statusTrackPermohonanActualExpected, statusTrackPermohonanActual);
    }

    @Test
    public void testGetTPermohonanByNikPemohonStatus() throws Exception {
        String nikPemohon = "12313123";
        String statusTrackPermohonan = "NEW";

        TPermohonan tPermohonan = new TPermohonan();
        tPermohonan.setNik_pemohon(nikPemohon);
        tPermohonan.setStatus_track_permohonan(statusTrackPermohonan);
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikPemohonStatus(tPermohonan);
        String nikPemohonActual = null;
        for (TPermohonan permohonan : tPermohonans) {
             nikPemohonActual=permohonan.getNik_pemohon();
        }
        String nikPemohonExpected = "12313123";
        assertEquals(nikPemohonExpected, nikPemohonActual);
    }

//    @Test
//    public void testGetTPermohonanByNikDivisiStatus() throws Exception {
//        String nikDivisi = "51900065";
//        String statusTrackPermohonan = "CLOSED";
//
//        TPermohonan tPermohonan = new TPermohonan();
//        tPermohonan.setNik_divisi(nikDivisi);
//        tPermohonan.setStatus_track_permohonan(statusTrackPermohonan);
//        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikDivisiStatus(tPermohonan);
//        String nikDivisiActual = null;
//        for (TPermohonan permohonan : tPermohonans) {
//             nikDivisiActual=permohonan.getNik_divisi();
//        }
//        String nikDivisiExpected = "51900065";
//        assertEquals(nikDivisiExpected, nikDivisiActual);
//    }

    @Test
    public void testGetTPermohonanByNikAsmanStatus() throws Exception {
        String nikAsman = "341213221";
        String statusTrackPermohonan = "NEW";

        TPermohonan tPermohonan = new TPermohonan();
        tPermohonan.setNik_asman(nikAsman);
        tPermohonan.setStatus_track_permohonan(statusTrackPermohonan);
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikAsmanStatus(tPermohonan);
        String nikAsmanActual = null;
        for (TPermohonan permohonan : tPermohonans) {
             nikAsmanActual=permohonan.getNik_asman();
        }
        String nikAsmanExpected = "341213221";
        assertEquals(nikAsmanExpected, nikAsmanActual);
    }
    @Test
    public void testGetTPermohonanByNikManagerStatus() throws Exception {
        String nikManager = "21321312";
        String statusTrackPermohonan = "NEW";

        TPermohonan tPermohonan = new TPermohonan();
        tPermohonan.setNik_manager(nikManager);
        tPermohonan.setStatus_track_permohonan(statusTrackPermohonan);
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikManagerStatus(tPermohonan);
        String nikManagerActual = null;
        for (TPermohonan permohonan : tPermohonans) {
             nikManagerActual=permohonan.getNik_manager();
        }
        String nikManagerExpected = "21321312";
        assertEquals(nikManagerExpected, nikManagerActual);
    }
    @Test
    public void testGetTPermohonanByNikGmStatus() throws Exception {
        String nikGm = "3423432";
        String statusTrackPermohonan = "NEW";

        TPermohonan tPermohonan = new TPermohonan();
        tPermohonan.setNik_gm(nikGm);
        tPermohonan.setStatus_track_permohonan(statusTrackPermohonan);
        List<TPermohonan> tPermohonans = tPermohonanDAO.getTPermohonanByNikGmStatus(tPermohonan);
        String nikGmActual = null;
        for (TPermohonan permohonan : tPermohonans) {
             nikGmActual=permohonan.getNik_gm();
        }
        String nikGmExpected = "3423432";
        assertEquals(nikGmExpected, nikGmActual);
    }

    @Test
    public void testCreateTPermohonan() throws Exception {
        int r = tPermohonanDAO.getCountAllTPermohonan();
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

        tPermohonanDAO.createTPermohonan(tPermohonan);
        assertEquals(r + 1, tPermohonanDAO.getCountAllTPermohonan());
    }

    @Test
    public void testSaveOrUpdateTPermohonan() throws ParseException{
        String t_idoss_permohonan_id = "00212FSHOPHAR2010";

        TPermohonan tPermohonan = tPermohonanDAO.getTPermohonanByTIdossPermohonanId(t_idoss_permohonan_id);

        tPermohonan.setDampak("MINOR");
        tPermohonan.setUrgensi("N");

        String tglIsian = "2010-08-12 18:00:10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date tglDate = sdf.parse(tglIsian);
        Timestamp dt = new Timestamp(tglDate.getTime());
        tPermohonan.setTarget_mulai_digunakan(dt);

        tPermohonan.setStatus_track_permohonan("CLOSED");
        tPermohonan.setNik_pemohon("7777777");
        tPermohonan.setNama_pemohon("JOSH");
        tPermohonanDAO.saveOrUpdateTPermohonan(tPermohonan);
        String namaPemohonActual = tPermohonan.getNama_pemohon();
        String namaPemohonExpected = "JOSH";
        assertEquals(namaPemohonExpected,namaPemohonActual);
    }
    @Test
    public void getManager() throws Exception{
        String employeeNo = "79040893";
//        TPermohonan tPermohonan = tPermohonanDAO.getManager(employeeNo);
//        assertEquals("ZULHELMY", tPermohonan.getNama_pemohon());

    }
}
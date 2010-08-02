package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
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
 * User: Xsis
 * Date: Jul 19, 2010
 * Time: 5:42:07 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:META-INF/spring/spring-config.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-idoss-security-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml",
                "classpath:META-INF/spring/spring-service-idoss-pengaduan-config.xml"

        }
)
public class PelaksanaanGangguanServiceTest {

    @Autowired
    private PelaksanaanGangguanService pelaksanaanGangguanService;

    @Test
    public void testGetDetail() throws Exception {
        String tiketId ="BBBB579286                      ";
        TPenangananGangguan tPenangananGangguan = pelaksanaanGangguanService.getDetail(tiketId);
        assertEquals("BBBB579286                      ", tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        assertEquals("Donna", tPenangananGangguan.getNama_pelapor());
    }

    @Test
    public void testGetType() throws Exception {
        List<PType> pTypes = pelaksanaanGangguanService.getType();
        String typeDescActual = null;
        for (PType pType : pTypes) {
            typeDescActual = pType.getType_desc();
        }
        String typeDescEXpected = "KEREN";
        assertEquals(typeDescActual,typeDescEXpected);
    }

    @Test
    public void testGetRootCaused() throws Exception {
        List<PRootCaused> pRootCauseds= pelaksanaanGangguanService.getRootCaused();
        String rootCausedActual = null;
        for(PRootCaused pRootCaused :pRootCauseds){
            rootCausedActual = pRootCaused.getRoot_caused();
        }
        String rootCausedExpected = "fhahf";
        assertEquals(rootCausedExpected, rootCausedActual);
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
        pelaksanaanGangguanService.createRootCaused(pRootCaused);

    }
    @Test
    public void testSaveOrUpdate() throws Exception {
        String tiketId = "BBBB579285                      ";

        TPenangananGangguan tPenangananGangguan = pelaksanaanGangguanService.getDetail(tiketId);
        tPenangananGangguan.setNik_pelapor("878991233");
        tPenangananGangguan.setNama_pelapor("JOSRI");
        tPenangananGangguan.setBagian_pelapor("PROGRAMMER ABAL-ABAL");
        tPenangananGangguan.setJudul("UJICOBA");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setUpdated_date(ts);
        tPenangananGangguan.setUpdated_user("2432432433");

        pelaksanaanGangguanService.saveOrUpdate(tPenangananGangguan);
        TPenangananGangguan tPenangananGangguanResult = pelaksanaanGangguanService.getDetail(tiketId);
        String namaPelaporActual = tPenangananGangguanResult.getNama_pelapor();
        String namaPelaporExpected = "JOSRI";
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }
}

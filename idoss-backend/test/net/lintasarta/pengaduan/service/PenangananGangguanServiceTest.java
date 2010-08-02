package net.lintasarta.pengaduan.service;

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
 * Date: Jun 23, 2010
 * Time: 6:40:49 PM
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
public class PenangananGangguanServiceTest {

    @Autowired
    private PenangananGangguanService penangananGangguanService;

    @Test
    public void testGetAllPenangananGangguan() throws Exception {
        List<TPenangananGangguan> tPenangananGangguans = penangananGangguanService.getAllPenangananGangguan();
        String namaPelaporActual = null;
        for (TPenangananGangguan tPenangananGangguan : tPenangananGangguans) {
            namaPelaporActual = tPenangananGangguan.getNama_pelapor();
        }
        String namaPelaporExpected=null;
        assertEquals(namaPelaporExpected, namaPelaporActual);

    }

    @Test
    public void testGetPenangananGangguanbyTiketId() throws Exception {
        String tiketId ="000022ZHL87987897DELIVERY0000000";
        TPenangananGangguan tPenangananGangguan = penangananGangguanService.getPenangananGangguanbyTiketId(tiketId);
        assertEquals("000022ZHL87987897DELIVERY0000000",tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        assertEquals("JONTORR", tPenangananGangguan.getNama_pelapor());
    }

    @Test
    public void testGetAllPenangananGanguanByNikPelapor() throws Exception {
        String nikPelapor="878991233";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelapor(nikPelapor);
        String statusActual = null;
        List<TPenangananGangguan> tPenangananGangguans = penangananGangguanService.getAllPenangananGanguanByNikPelapor(tPenangananGangguan);
        for (TPenangananGangguan penangananGangguan : tPenangananGangguans) {
             statusActual = penangananGangguan.getStatus();
        }
        String statusExpected=null;
        assertEquals(statusExpected,statusActual);
    }

    @Test
    public void testGetAllPenangananGangguanByNikPelaksana() throws Exception {
        String nikPelaksana="2222222";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelaksana(nikPelaksana);
        String namaPelaporActual = null;
        List<TPenangananGangguan> tPenangananGangguans = penangananGangguanService.getAllPenangananGangguanByNikPelaksana(tPenangananGangguan);
        for (TPenangananGangguan penangananGangguan : tPenangananGangguans) {
            namaPelaporActual = penangananGangguan.getNama_pelapor();
        }
        String namaPelaporExpected="RONI";
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }

    @Test
    public void testGetAllPenangananGangguanByStatus() throws Exception {
        String status="RONI";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setStatus(status);
        String namaPelaporActual = null;
        List<TPenangananGangguan> tPenangananGangguans = penangananGangguanService.getAllPenangananGangguanByStatus(tPenangananGangguan);
        for (TPenangananGangguan penangananGangguan : tPenangananGangguans) {
            namaPelaporActual = penangananGangguan.getNama_pelapor();
        }
        String namaPelaporExpected =null;
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }

    @Test
    public void testgetAllPenangananGangguanByNikPelaporStatus() throws Exception {
        String nikPelapor = "878991231";
        String status = "TUNDA";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelapor(nikPelapor);
        tPenangananGangguan.setStatus(status);

        String namaPelaporActual = null;
        List<TPenangananGangguan> tPenangananGangguans = penangananGangguanService.getAllPenangananGangguanByNikPelaporStatus(tPenangananGangguan);
        for (TPenangananGangguan penangananGangguan : tPenangananGangguans) {
            namaPelaporActual = penangananGangguan.getNama_pelapor();
        }
        String namaPelaporExpected=null;
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }

    @Test
    public void testGetAllPenangananGangguanByNikPelaksanaStatus() throws Exception {
        String nikPelaksana="2222222";
        String status = "TUNDA";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelaksana(nikPelaksana);
        tPenangananGangguan.setStatus(status);

        String namaPelaporActual = null;
        List<TPenangananGangguan> tPenangananGangguans = penangananGangguanService.getAllPenangananGangguanByNikPelaksanaStatus(tPenangananGangguan);
        for (TPenangananGangguan penangananGangguan : tPenangananGangguans) {
            namaPelaporActual = penangananGangguan.getNama_pelapor();
        }
        String namaPelaporExpected = null;
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }

    @Test
    public void testCreatePenangananGangguan() throws Exception {
        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        
        tPenangananGangguan.setNik_pelaksana("2222222");
        tPenangananGangguan.setStatus("TUNDA");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setCreated_date(ts);
        tPenangananGangguan.setCreated_user("894928423");
        tPenangananGangguan.setUpdated_date(ts);
        tPenangananGangguan.setUpdated_user("894928423");

        penangananGangguanService.createPenangananGangguan(tPenangananGangguan);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        String tiketId = "000025ZHL87987897DELIVERY0000000";

        TPenangananGangguan tPenangananGangguan = penangananGangguanService.getPenangananGangguanbyTiketId(tiketId);
        tPenangananGangguan.setNik_pelapor("878991233");
        tPenangananGangguan.setNama_pelapor("JOSRI");
        tPenangananGangguan.setBagian_pelapor("PROGRAMMER ABAL-ABAL");
        tPenangananGangguan.setJudul("UJICOBA");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setUpdated_date(ts);
        tPenangananGangguan.setUpdated_user("2432432433");

        penangananGangguanService.saveOrUpdate(tPenangananGangguan);
        TPenangananGangguan tPenangananGangguanResult = penangananGangguanService.getPenangananGangguanbyTiketId(tiketId);
        String namaPelaporActual = tPenangananGangguanResult.getNama_pelapor();
        String namaPelaporExpected = "JOSRI";
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }
}
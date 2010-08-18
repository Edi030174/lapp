package net.lintasarta.pengaduan.dao;


import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.util.TicketIdGenerator;
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
 * Time: 6:27:07 PM
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
public class TpenangananGangguanDAOTest {

    @Autowired
    private TPenangananGangguanDAO tPenangananGangguanDAO;

    @Test
    public void testGetCountALLTPenangananGangguan() throws Exception {
        assertEquals(0, tPenangananGangguanDAO.getCountAllTPenangananGangguan());
    }

    @Test
    public void testGetAllTPenangananaGangguan() throws Exception {
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguan();
        String bagianPelaporActual = null;
        for(TPenangananGangguan tPenangananGangguan:tPenangananGangguans){
            bagianPelaporActual = tPenangananGangguan.getBagian_pelapor();
        }
        String bagianPelaporExpected="MANAJEMEN PRODUKSI.";
        assertEquals(bagianPelaporExpected, bagianPelaporActual);
    }

    @Test
    public void testGetTPenangananaGangguanByTiketId() throws Exception {
        String tiketId = "000022ZHL87987897DELIVERY0000000";
        TPenangananGangguan tPenangananGangguan = tPenangananGangguanDAO.getTPenangananGangguanByTiketId(tiketId);
        assertNotNull(tPenangananGangguan);
        assertEquals("000022ZHL87987897DELIVERY0000000",tPenangananGangguan.getT_idoss_penanganan_gangguan_id());
        assertEquals("JONTORR",tPenangananGangguan.getNama_pelapor());
    }

    @Test
    public void testGetAllTPenangananGangguanByNikPelapor() throws Exception{
        String nikPelapor="878991233";
//        String tiketId = "000012ZHL87987897DELIVERY0000000";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelapor(nikPelapor);
//        tPenangananGangguan.setT_idoss_penanganan_gangguan_id(tiketId);

        String statusActual = null;
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelapor(tPenangananGangguan);
        for (TPenangananGangguan tPenangananGangguanResult : tPenangananGangguans) {
            statusActual = tPenangananGangguanResult.getStatus();
        }
        String statusExpected="TUNDA";
        assertEquals(statusExpected,statusActual);
    }

    @Test
    public void testGetAllTPenangananGangguanByNikPelaksana() throws Exception {
        String nikPelaksana="2222222";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelaksana(nikPelaksana);

        String namaPelaporActual = null;
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelaksana(tPenangananGangguan);
        for (TPenangananGangguan tPenangananGangguanResult : tPenangananGangguans) {
            namaPelaporActual = tPenangananGangguanResult.getNama_pelapor();
        }
        String namaPelaporExpected =null;
        assertEquals(namaPelaporExpected,namaPelaporActual);
    }

    @Test
    public void testGetAllTPenangananGangguanByStatus() throws Exception {
        String status="TUNDA";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setStatus(status);

        String namaPelaporActual = null;
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByStatus(tPenangananGangguan);
        for (TPenangananGangguan tPenangananGangguanResult : tPenangananGangguans) {
            namaPelaporActual = tPenangananGangguanResult.getNama_pelapor();
        }
        String namaPelaporExpected =null;
        assertEquals(namaPelaporExpected,namaPelaporActual);
    }

    @Test
    public void testGetAllTPenangananGangguanByNikPelaporStatus() throws Exception {
        String nikPelapor = "878991231";
        String status = "TUNDA";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelapor(nikPelapor);
        tPenangananGangguan.setStatus(status);

        String namaPelaporActual = null;
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelaporStatus(tPenangananGangguan);
        for (TPenangananGangguan tPenangananGangguanResult : tPenangananGangguans) {
            namaPelaporActual = tPenangananGangguanResult.getNama_pelapor();
        }
        String namaPelaporExpected = "JONTORR";
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }

    @Test
    public void testGetAllTPenangananGangguanByNikPelaksanaStatus() throws Exception {
        String nikPelaksana="2222222";
        String status = "TUNDA";

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
        tPenangananGangguan.setNik_pelaksana(nikPelaksana);
        tPenangananGangguan.setStatus(status);

        String namaPelaporActual = null;
        List<TPenangananGangguan> tPenangananGangguans = tPenangananGangguanDAO.getAllTPenangananGangguanByNikPelaksanaStatus(tPenangananGangguan);
        for (TPenangananGangguan tPenangananGangguanResult : tPenangananGangguans) {
            namaPelaporActual = tPenangananGangguanResult.getNama_pelapor();
        }
        String namaPelaporExpected ="RONI";
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }

    @Test
    public void testCreateTpenangananGangguan() throws Exception {
        int i = tPenangananGangguanDAO.getCountAllTPenangananGangguan();

        TPenangananGangguan tPenangananGangguan = new TPenangananGangguan();
//        int j = tPenangananGangguanDAO.getSeqTiketId();
//        String seq = String.valueOf(j);
//        TicketIdGenerator tid = new TicketIdGenerator(seq);
//        String ticketIdResult = tid.getTicketId();
//        tPenangananGangguan.setT_idoss_penanganan_gangguan_id(ticketIdResult);
//        tPenangananGangguan.setNik_pelapor("878991231");
//        tPenangananGangguan.setNik_pelaksana("2222222");
//        tPenangananGangguan.setStatus("AKTIF");
//        tPenangananGangguan.setDeskripsi("Testing input data");
//        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
//        tPenangananGangguan.setCreated_date(ts);
//        tPenangananGangguan.setCreated_user("894928423");
//        tPenangananGangguan.setUpdated_date(ts);
//        tPenangananGangguan.setUpdated_user("894928423");

        tPenangananGangguan.setT_idoss_penanganan_gangguan_id("aaaaa111");
        tPenangananGangguan.setNik_pelapor("12345");
        tPenangananGangguan.setNama_pelapor("asri");
        tPenangananGangguan.setJudul("test");
        tPenangananGangguan.setDeskripsi("<p>test</p>");
        tPenangananGangguan.setNo_hp("1234556");
        tPenangananGangguan.setExt("123");


        tPenangananGangguanDAO.createTPenangananGangguan(tPenangananGangguan);
        assertEquals(i+1, tPenangananGangguanDAO.getCountAllTPenangananGangguan());
    }

    @Test
    public void testSaveOrUpdate() {
        String tiketId = "BBBB579285                      ";
        /*
        UPDATE  T_IDOSS_PENANGANAN_GANGGUAN SET
        NIKPELAPOR='878991231',
        NAMAPELAPOR='JONTOR',
        BAGIANPELAPOR='MARTABAK TELOR',
        JUDUL='TEST',
        UPDATEDDATE=SYSDATE,
        UPDATEDUSER ='2432432432'
        */
        TPenangananGangguan tPenangananGangguan = tPenangananGangguanDAO.getTPenangananGangguanByTiketId(tiketId);
        tPenangananGangguan.setNik_pelapor("878991231");
        tPenangananGangguan.setNama_pelapor("JONTORR");
        tPenangananGangguan.setBagian_pelapor("MARTABAK TELOR");
        tPenangananGangguan.setJudul("TEST");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPenangananGangguan.setUpdated_date(ts);
        tPenangananGangguan.setUpdated_user("2432432432");

        tPenangananGangguanDAO.saveOrUpdate(tPenangananGangguan);

        TPenangananGangguan tPenangananGangguanResult = tPenangananGangguanDAO.getTPenangananGangguanByTiketId(tiketId);
        String namaPelaporActual = tPenangananGangguanResult.getNama_pelapor();
        String namaPelaporExpected = "JONTORR";
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }
}
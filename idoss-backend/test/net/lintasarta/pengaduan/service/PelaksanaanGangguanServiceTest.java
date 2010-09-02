package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.TPenangananGangguan;
import net.lintasarta.pengaduan.model.VHrEmployeePelaksana;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jul 19, 2010
 * Time: 5:42:07 PM
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
    public void testGetEmployeeName() throws Exception {
        List<VHrEmployeePelaksana> vHrEmployeePelaksanas = pelaksanaanGangguanService.getEmployeeName();
        String employeeNameActual = null;
        for (VHrEmployeePelaksana vHrEmployeePelaksana : vHrEmployeePelaksanas) {
            employeeNameActual= vHrEmployeePelaksana.getEmployee_name();
        }
        String rootCausedExpected = "AKHMAD FAUZAN SYAFIQ";

        assertEquals(rootCausedExpected, employeeNameActual);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        String tiketId = "000000052";
        Timestamp ts = new Timestamp(java.util.Calendar.getInstance().getTimeInMillis());

        TPenangananGangguan tPenangananGangguan = pelaksanaanGangguanService.getDetail(tiketId);
        tPenangananGangguan.setNik_pelapor("79040893");
        tPenangananGangguan.setNama_pelapor("ZULHELMY");
        tPenangananGangguan.setBagian_pelapor("OPERASI TI.");
        tPenangananGangguan.setJudul("helooo");
        tPenangananGangguan.setUpdated_user("ZHL");
        tPenangananGangguan.setUpdated_date(ts);
        if(tPenangananGangguan.getInserted_root_caused()==null){
            tPenangananGangguan.setInserted_root_caused(ts);
        }
/*
        String dateAwal = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(tPenangananGangguan.getCreated_date());
        String dateAkhir = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(ts);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date awal = getDateTime(dateAwal);
        Date akhir = getDateTime(dateAkhir);
        long durasi = (akhir.getTime()- awal.getTime());
        float durasiResult =((float)durasi/(1000.0f*60.0f*60.0f));
//        long awal = tPenangananGangguan.getCreated_date().getTime();
//        long akhir = ts.getTime();
//        double durasi = (double )(akhir - awal)/(1000);
        DecimalFormat durasiResult1 = new DecimalFormat("##0.000000");
        tPenangananGangguan.setDurasi(durasiResult1.format(durasiResult));
*/
        String firtUpadate = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(tPenangananGangguan.getInserted_root_caused());
        String lastUpdate = new SimpleDateFormat("yyyy-MM-dd hh:mm aa").format(ts);
        Date first = getDateTime(firtUpadate);
        Date last = getDateTime(lastUpdate);
        long diffMttr = (last.getTime()- first.getTime());
        float diffResultMttr =((float)diffMttr/(1000.0f*60.0f*60.0f));
        DecimalFormat durasiResultMttr = new DecimalFormat("##0.000000");
        tPenangananGangguan.setMttr(durasiResultMttr.format(diffResultMttr));

        pelaksanaanGangguanService.saveOrUpdate(tPenangananGangguan);
        TPenangananGangguan tPenangananGangguanResult = pelaksanaanGangguanService.getDetail(tiketId);
        String namaPelaporActual = tPenangananGangguanResult.getNama_pelapor();
        String namaPelaporExpected = "ZULHELMY";
        assertEquals(namaPelaporExpected, namaPelaporActual);
    }

    @Test
    public void testGetRootCausedByPTypeId() throws Exception {
        String ptypeId = "4011";
        List<PRootCaused> pRootCauseds = pelaksanaanGangguanService.getRootCausedByPTypeId(ptypeId);
        assertEquals(pRootCauseds.size(),7);
    }

    private static Date getDateTime(String dateTime) throws ParseException {

        DateFormat formatOldDateTime = new SimpleDateFormat( "yyyy-MM-dd hh:mm aa");
        Date date = formatOldDateTime.parse(dateTime);

        return date;
    }
}

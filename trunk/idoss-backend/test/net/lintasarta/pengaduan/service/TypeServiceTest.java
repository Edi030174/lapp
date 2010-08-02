package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 6:42:30 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
				"classpath:META-INF/spring/spring-config.xml",
				"classpath:META-INF/spring/datasource.xml",
				"classpath:META-INF/spring/spring-idoss-security-config.xml",
				"classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml",
				"classpath:META-INF/spring/spring-dao-idoss-permohonan-config.xml",
                "classpath:META-INF/spring/spring-service-idoss-pengaduan-config.xml"

		}
)

public class TypeServiceTest {
    @Autowired
    private TypeService typeService;

    @Test
    public void testGetAllType() throws Exception {
        List<PType> pTypes = typeService.getAllType();
        String typeDescActual = null;
        for (PType pType : pTypes) {
           typeDescActual = pType.getType_desc();
        }
        String typeDescEXpected = "KEREN";
        assertEquals(typeDescActual,typeDescEXpected);
    }
    
    @Test
    public void testGetTypeByTypeID() throws Exception {
        PType pType = typeService.getTypeByTypeID(2);
        assertNotNull(pType);
        assertEquals("oke",pType.getType_desc());
    }

    @Test
    public void testCreateType() throws Exception {
        PType pType = new PType();
        pType.setType_desc("KEREN");
        pType.setActive("T");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pType.setCreated_date(ts);
        pType.setCreated_user("12345");
        pType.setUpdated_date(ts);
        pType.setUpdated_user("12345");
        typeService.createType(pType);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        int typeId = 3;

        PType pType = typeService.getTypeByTypeID(typeId);
        pType.setType_desc("COBA");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pType.setUpdated_date(ts);
        pType.setUpdated_user("12346");
        typeService.saveOrUpdate(pType);
        String typeDescActual = pType.getType_desc();
        String typeDescExpected ="COBA";
        assertEquals(typeDescActual,typeDescExpected);

    }
}
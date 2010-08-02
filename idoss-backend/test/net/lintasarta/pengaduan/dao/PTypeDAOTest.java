package net.lintasarta.pengaduan.dao;

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
import static org.junit.Assert.assertNull;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 5:51:48 PM
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
public class PTypeDAOTest {
    
    @Autowired
    private PTypeDAO pTypeDAO;

    @Test
    public void testGetCountALLPType() throws Exception{
        assertEquals(0, pTypeDAO.getCountAllPType());
    }

    @Test
    public void testGetAllPType() throws Exception{
        List<PType> pTypes = pTypeDAO.getAllPType();
        String descActual = null;
        for (PType pType : pTypes) {
            descActual = pType.getType_desc();
        }
        String descExpected = "KEREN";
        assertEquals(descExpected, descActual);
  }

    @Test
    public void testGetPTypeByTypeId() throws Exception{
        PType pType = pTypeDAO.getPTypeByTypeId(2);
        assertNotNull(pType);
        assertEquals("oke",pType.getType_desc());

    }

    @Test
    public void testCreatePType() throws Exception{
        int i = pTypeDAO.getCountAllPType();

        PType pType = new PType();
        int j = pTypeDAO.getGenerateId();
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pType.setP_idoss_type_id(j);
        pType.setType_desc("KEREN");
        pType.setActive("T");
        pType.setCreated_date(ts);
        pType.setCreated_user("12345");
        pType.setUpdated_date(ts);
        pType.setUpdated_user("12345");
        pTypeDAO.createPType(pType);

        assertEquals(i+1, pTypeDAO.getCountAllPType());
    }

    @Test
    public void saveOrUpdate() throws Exception{
        int typeId = 2;
        PType pType = pTypeDAO.getPTypeByTypeId(typeId);
        pType.setType_desc("oke");
        Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pType.setUpdated_date(ts);
        pType.setUpdated_user("ali");

        pTypeDAO.saveOrUpdate(pType);
        String typeDescActual = pType.getType_desc();
        String typeDescExpected ="oke";
        assertEquals(typeDescActual,typeDescExpected);

    }
}

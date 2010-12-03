package net.lintasarta.security.dao;

import net.lintasarta.pengaduan.dao.PApplicationDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Oct 7, 2010
 * Time: 6:08:55 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {
                "classpath:META-INF/ibatis/ibatis-spring-config.xml",
                "classpath:datasource-impl.xml",
                "classpath:META-INF/spring/datasource.xml",
                "classpath:META-INF/spring/spring-based-dao-config.xml",
                "classpath:META-INF/spring/spring-dao-idoss-pengaduan-config.xml"
        }
)
public class PApplicationDAOTest {
    @Autowired
    PApplicationDAO pApplicationDAO;

    @Test
    public void testGetRoleByUsername()throws Exception{
        List<Integer> integerList= pApplicationDAO.getRoleByUsername("KDP");
        integerList.contains(new Integer(1516));
        System.out.println(integerList.size());
    }

}

package net.lintasarta.pengaduan.service;

import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.predicate.ParentIdPType;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jun 23, 2010
 * Time: 6:42:30 PM
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
        PType pType = typeService.getTypeByTypeID("2");
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
        String typeId = "3";

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

//    public List iterateSameLevel(PType pType, String parentId, ListIterator<PType> iterator, List child, List<PType> filteredPTypes, List root) throws Exception {
//        if (iterator.hasNext()) {
//            pType = iterator.next();
//            child.add(pType);
//            parentId = pType.getParent_id();
//        }
//        while (iterator.hasNext()) {
//            pType = iterator.next();
//            if (!pType.getParent_id().equals(parentId)) {
//                final String substrParentId = pType.getParent_id().substring(0, 1);
//                if (pType.getParent_id().length() < parentId.length()) {
//                    pType = iterator.previous();
//                    parentId = pType.getParent_id();
//                    List nextParentList = iterateSameLevel(pType, parentId, iterator, child, filteredPTypes, root);
//                    root.addAll(nextParentList);
//                } else if (substrParentId.equals(parentId.substring(0, 1))) {
//                    List grandChildList = new ArrayList(filteredPTypes);
//                    CollectionUtils.filter(grandChildList, new Predicate() {
//                        @Override
//                        public boolean evaluate(Object object) {
//                            if (((PType) object).getParent_id().length() > 1) {
//                                if (((PType) object).getParent_id().substring(0, 1).equals(substrParentId)) {
//                                    return true;
//                                }
//                            }
//                            return false;
//                        }
//                    });
//                    child.add(grandChildList);
//                    int size = grandChildList.size();
//                    for (int i = 1; i < size; i++) {
//                        iterator.next();
//                    }
//                    root.add(child);
//                    child = new ArrayList<PType>();
//                } else {
//                    root.add(child);
//                    child = new ArrayList<PType>();
//                }
//            } else {
//                child.add(pType);
//            }
//            parentId = pType.getParent_id();
//        }
//        if (root.size() > 0) {
//            List<PType> lastChild = (List<PType>) root.get(root.size() - 1);
//            PType lastPType = lastChild.get(lastChild.size() - 1);
//            if (!lastPType.getParent_id().equals(parentId)) {
//                root.add(child);
//            }
//        }
//        return root;
//    }

    public List<PType> filterByParentId(List<PType> pTypes, String parentId) {
        List<PType> resultList = new ArrayList<PType>(pTypes);
        CollectionUtils.filter(resultList, new ParentIdPType(parentId));
        return resultList;
    }

    public List<Object> constructTreeModel(List<PType> originalList, Iterator<PType> iterator, List<Object> treeList) {
        while (iterator.hasNext()) {
            PType pType = iterator.next();
            List<PType> childList = filterByParentId(originalList, pType.getP_idoss_type_id());
            List<Object> resultChildList = new ArrayList<Object>(childList);
            int size = childList.size();
            if (size > 0) {
                treeList.add(constructTreeModel(originalList, iterator, treeList));
                for (int i = 0; i < size; i++) {
                    iterator.next();
                }
            } else {

            }
            treeList.add(resultChildList);
        }
        return treeList;
    }

//    public List iterateDeep(List<PType> childs, List<Object> resultChilds, Iterator<PType> iterator, List<PType> pTypes, List resultList, int countDeep, int count) {
//        for (PType child : childs) {
//            count++;
//            if (count < countDeep) {
//                List<Object> resultGrandChilds = new ArrayList<Object>();
//                resultChilds.add(resultGrandChilds);
//                iterator.next();
//                List<PType> grandChilds = filterByParentId(pTypes, child.getP_idoss_type_id());
//                iterateDeep(grandChilds, resultGrandChilds, iterator, pTypes, resultList, countDeep, count);
//            } else {
//                List<PType> greateGrandChilds = filterByParentId(pTypes, child.getP_idoss_type_id());
//                for (PType greateGrandChild : greateGrandChilds) {
//                    resultChilds.add(greateGrandChild);
//                    iterator.next();
//                }
//                count = 0;
//            }
//        }
//        return resultList;
//    }

//    public List addChild(List<PType> pTypes, int countDeep) {
//        List<Object> resultList = new ArrayList<Object>();
//        Iterator<PType> iterator = pTypes.iterator();
//        while (iterator.hasNext()) {
//            PType pType = iterator.next();
//
//            List<Object> resultChilds = new ArrayList<Object>();
//            resultList.add(resultChilds);
//
//            List<PType> childs = filterByParentId(pTypes, pType.getP_idoss_type_id());
//
//            int count = 0;
//            iterateDeep(childs, resultChilds, iterator, pTypes, resultList, countDeep, count);
//        }
//        return resultList;
//    }

    public List addChild(List<PType> pTypes) {
        List<Object> resultList = new ArrayList<Object>();
        Iterator<PType> iterator = pTypes.iterator();
        while (iterator.hasNext()) {
            PType pType = iterator.next();

            List<Object> resultChilds = new ArrayList<Object>();
            resultList.add(resultChilds);

            List<PType> childs = filterByParentId(pTypes, pType.getP_idoss_type_id());
            for (PType child : childs) {
                List<Object> resultGrandChilds = new ArrayList<Object>();
                resultChilds.add(resultGrandChilds);
                iterator.next();

                List<PType> grandChilds = filterByParentId(pTypes, child.getP_idoss_type_id());
                for (PType grandChild : grandChilds) {
                    List<Object> resultGreatGrandChilds = new ArrayList<Object>();
                    resultGrandChilds.add(resultGreatGrandChilds);
                    iterator.next();

                    List<PType> greateGrandChilds = filterByParentId(pTypes, grandChild.getP_idoss_type_id());
                    for (PType greateGrandChild : greateGrandChilds) {
                        resultGreatGrandChilds.add(greateGrandChild);
                        iterator.next();
                    }
                }
            }
        }
        return resultList;
    }

    @Test
    public void testGetTreeModel() throws Exception {
        List<PType> pTypes = typeService.getAllType();
//        List<PType> notRootPTypes = new ArrayList<PType>(pTypes);
//        CollectionUtils.filter(notRootPTypes, new ParentIdPType());
//
//        List<PType> child = new ArrayList<PType>();
//        ListIterator<PType> iterator = notRootPTypes.listIterator();
//        PType pType = null;
//        String parentId = null;

//        List root = iterateSameLevel(pType, parentId, iterator, child, notRootPTypes, new ArrayList());
//        Iterator iterator = pTypes.listIterator();

//        List treeList = constructTreeModel(pTypes, iterator, new ArrayList());

//        List treeList = addChild(pTypes, 3);
        List treeList = addChild(pTypes);

        assertNotNull(treeList);
//        assertEquals(11, root.size());
    }

    @Test
    public void testGetPTypeByParentId() throws Exception {
        String parentid = "3";
        List<PType> pTypes = typeService.getPTypeByParentId(parentid);
        assertEquals(pTypes.size(), 6);
    }
}
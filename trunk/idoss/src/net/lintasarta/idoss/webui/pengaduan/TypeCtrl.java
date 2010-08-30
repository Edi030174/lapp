package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.pengaduan.model.TypeTreeItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.TypeTreeModel;
import net.lintasarta.idoss.webui.pengaduan.model.TypeTreeNode;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.predicate.ParentIdPType;
import net.lintasarta.pengaduan.service.TypeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 18, 2010
 * Time: 9:55:18 AM
 */
public class TypeCtrl extends GFCBaseCtrl implements Serializable {
    private transient static final Logger logger = Logger.getLogger(RootCausedCtrl.class);
    protected Window window_Type;
    protected Tree tree_Type;

    protected TypeTreeItemRenderer renderer;
    protected SimpleTreeModel stm;

    private transient PType pType;

    private transient TypeService typeService;

    public TypeCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public List<PType> filterByParentId(List<PType> pTypes, String parentId) {
        List<PType> resultList = new ArrayList<PType>(pTypes);
        CollectionUtils.filter(resultList, new ParentIdPType(parentId));
        return resultList;
    }

    public List addChild(List<PType> pTypes) {
        List<Object> resultList = new ArrayList<Object>();
        Iterator<PType> iterator = pTypes.iterator();
        while (iterator.hasNext()) {
            PType pType = iterator.next();

            List<Object> resultChilds = new ArrayList<Object>();
            SimpleTreeNode rcNode = new SimpleTreeNode(pType.getType_desc(), resultChilds);
            resultList.add(rcNode);

            List<PType> childs = filterByParentId(pTypes, pType.getP_idoss_type_id());
            for (PType child : childs) {
                List<Object> resultGrandChilds = new ArrayList<Object>();
                SimpleTreeNode rgcNode = new SimpleTreeNode(child.getType_desc(), resultGrandChilds);
                resultChilds.add(rgcNode);
                iterator.next();

                List<PType> grandChilds = filterByParentId(pTypes, child.getP_idoss_type_id());
                for (PType grandChild : grandChilds) {
                    List<Object> resultGreatGrandChilds = new ArrayList<Object>();
                    SimpleTreeNode rggcNode = new SimpleTreeNode(grandChild.getType_desc(), resultGreatGrandChilds);
                    resultGrandChilds.add(rggcNode);
                    iterator.next();

                    List<PType> greateGrandChilds = filterByParentId(pTypes, grandChild.getP_idoss_type_id());
                    for (PType greateGrandChild : greateGrandChilds) {
                        SimpleTreeNode ggcNode = new SimpleTreeNode(greateGrandChild.getType_desc(), new ArrayList());
                        resultGreatGrandChilds.add(ggcNode);
                        iterator.next();
                    }
                }
            }
        }
        return resultList;
    }

    public void getTreeModel() {
        List<PType> pTypes = getTypeService().getAllType();
        List root = addChild(pTypes);

        SimpleTreeNode rootNode = new SimpleTreeNode("ROOT", root);
        stm = new SimpleTreeModel(rootNode);
        tree_Type.setModel(stm);

        tree_Type.setTreeitemRenderer(new TypeTreeItemRenderer());
    }

    public void onCreate$window_Type(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("pType")) {
            PType pType = (PType) args.get("pType");
            setpType(pType);
        }

//        List<PType> pTypes = getTypeService().getPTypeTree();
//
//        SimpleTreeNode stn = new SimpleTreeNode(pTypes, new ArrayList());
//        ArrayList al = new ArrayList();
//        al.add(stn);
//
//        SimpleTreeNode root = new SimpleTreeNode("ROOT",al);
//        SimpleTreeModel stm = new SimpleTreeModel(root);
//        tree_Type.setModel(stm);
//        tree_Type.setTreeitemRenderer(new TypeTreeItemRenderer());

        getTreeModel();
        doShowDialog(getpType());

//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("pType", pType);
//        map.put("tree_Type", tree_Type);

    }
    public void onSelect$tree_Type(Event event){

    }

    private void doShowDialog(PType pType) throws InterruptedException {
        try {
            window_Type.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }

    }

    public PType getpType() {
        return pType;
    }

    public void setpType(PType pType) {
        this.pType = pType;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
}

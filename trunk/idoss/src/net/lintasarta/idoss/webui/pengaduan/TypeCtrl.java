package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.pengaduan.model.RootCausedListModelItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.TypeTreeItemRenderer;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.predicate.ParentIdPType;
import net.lintasarta.pengaduan.service.PelaksanaanGangguanService;
import net.lintasarta.pengaduan.service.TypeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
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
    private transient static final Logger logger = Logger.getLogger(TypeCtrl.class);
    protected Window window_Type;
    protected Tree tree_Type;
    protected Textbox textbox_Type;
    protected Listbox listbox_RootCaused;
    protected SimpleTreeModel stm;

    private transient PType pType;
    private transient PRootCaused pRootCaused;
    private transient TypeService typeService;
    private transient PelaksanaanGangguanService pelaksanaanGangguanService;

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
//            SimpleTreeNode rcNode = new SimpleTreeNode(pType.getType_desc(), resultChilds);
            SimpleTreeNode rcNode = new SimpleTreeNode(pType, resultChilds);
            if (pType.getActive().equalsIgnoreCase("Y")) {
                resultList.add(rcNode);
            }

            List<PType> childs = filterByParentId(pTypes, pType.getP_idoss_type_id());
            for (PType child : childs) {
                List<Object> resultGrandChilds = new ArrayList<Object>();
//                SimpleTreeNode rgcNode = new SimpleTreeNode(child.getType_desc(), resultGrandChilds);
                SimpleTreeNode rgcNode = new SimpleTreeNode(child, resultGrandChilds);
                if (child.getActive().equalsIgnoreCase("Y")) {
                    resultChilds.add(rgcNode);
                }
                iterator.next();

                List<PType> grandChilds = filterByParentId(pTypes, child.getP_idoss_type_id());
                for (PType grandChild : grandChilds) {
                    List<Object> resultGreatGrandChilds = new ArrayList<Object>();
//                    SimpleTreeNode rggcNode = new SimpleTreeNode(grandChild.getType_desc(), resultGreatGrandChilds);
                    SimpleTreeNode rggcNode = new SimpleTreeNode(grandChild, resultGreatGrandChilds);
                    if (grandChild.getActive().equalsIgnoreCase("Y")) {
                        resultGrandChilds.add(rggcNode);
                    }
                    iterator.next();

                    List<PType> greateGrandChilds = filterByParentId(pTypes, grandChild.getP_idoss_type_id());
                    for (PType greateGrandChild : greateGrandChilds) {
//                        SimpleTreeNode ggcNode = new SimpleTreeNode(greateGrandChild.getType_desc(), new ArrayList());
                        SimpleTreeNode ggcNode = new SimpleTreeNode(greateGrandChild, new ArrayList());
                        if (greateGrandChild.getActive().equalsIgnoreCase("Y")) {
                            resultGreatGrandChilds.add(ggcNode);
                        }
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

        if(args.containsKey("pType")) {
            PType pType = (PType) args.get("pType");
            setpType(pType);
        }
        if(args.containsKey("textbox_Type")){
            textbox_Type = (Textbox) args.get("textbox_Type");
        }else {
            textbox_Type = null;
        }
        if(args.containsKey("listbox_RootCaused")){
            listbox_RootCaused = (Listbox) args.get("listbox_RootCaused");
        }else {
            listbox_RootCaused =null;
        }

        getTreeModel();
        doShowDialog(getpType());
    }

    public void onSelect$tree_Type(Event event) throws InterruptedException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Treeitem item = tree_Type.getSelectedItem();
        Treeitem itemParent = item.getParentItem();
        Treeitem itemGrandParent = itemParent.getParentItem();

        textbox_Type.setValue(itemGrandParent.getLabel() + ", " + itemParent.getLabel() + ", " + item.getLabel());

        pType = (PType) item.getValue();
        textbox_Type.setAttribute("pType", pType);

        listbox_RootCaused.setModel(new ListModelList(getPelaksanaanGangguanService().getRootCausedByPTypeId(pType.getP_idoss_type_id())));
        listbox_RootCaused.setItemRenderer(new RootCausedListModelItemRenderer());
        listbox_RootCaused.setSelectedIndex(0);
        
        window_Type.onClose();

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

    public PelaksanaanGangguanService getPelaksanaanGangguanService() {
        return pelaksanaanGangguanService;
    }

    public void setPelaksanaanGangguanService(PelaksanaanGangguanService pelaksanaanGangguanService) {
        this.pelaksanaanGangguanService = pelaksanaanGangguanService;
    }

    public PRootCaused getpRootCaused() {
        return pRootCaused;
    }

    public void setpRootCaused(PRootCaused pRootCaused) {
        this.pRootCaused = pRootCaused;
    }
}
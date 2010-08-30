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

    public void getTreeModel() {
        List<PType> pTypes = typeService.getAllType();

        java.util.Collections.sort(pTypes, new Comparator<PType>() {
            @Override
            public int compare(PType o1, PType o2) {
                if (o1.getParent_id() != null) {
                    if (o2.getParent_id() != null) {
                        return o1.getParent_id().compareTo(o2.getParent_id());
                    }
                    return -1;
                } else if (o2.getParent_id() != null) {
                    return 1;
                }
                return 0;
            }
        });
        List<PType> notRootPTypes = new ArrayList<PType>(pTypes);
        CollectionUtils.filter(notRootPTypes, new ParentIdPType());

        List child = new ArrayList();
        Iterator<PType> iterator = notRootPTypes.iterator();
        PType pType = null;
        String parentId = null;
        if (iterator.hasNext()) {
            pType = iterator.next();
            SimpleTreeNode stnPType = new SimpleTreeNode(pType, new ArrayList());
            child.add(stnPType);
            parentId = pType.getParent_id();
        }
        List root = new ArrayList();
        while (iterator.hasNext()) {
            pType = iterator.next();
            if (pType.getParent_id().compareTo(parentId) > 0) {
                SimpleTreeNode stnChild = new SimpleTreeNode(typeService.getTypeByTypeID(pType.getParent_id()), child);
                root.add(stnChild);
                child = new ArrayList<PType>();
            } else {
                SimpleTreeNode stnPType = new SimpleTreeNode(pType, new ArrayList());
                child.add(stnPType);
            }
            parentId = pType.getParent_id();
        }
//        if (root.size() > 0) {
//            List<PType> lastChild = (List<PType>) root.get(root.size() - 1);
//            PType lastPType = lastChild.get(lastChild.size() - 1);
//            if (lastPType.getParent_id().compareTo(parentId) < 0) {
//                SimpleTreeNode stnPType = new SimpleTreeNode(pType, new ArrayList());
//                child.add(stnPType);
//                SimpleTreeNode stnChild = new SimpleTreeNode(typeService.getTypeByTypeID(pType.getParent_id()), child);
//                root.add(stnChild);
//            }
//        }

        SimpleTreeNode rootNode = new SimpleTreeNode("ROOT", root);
        stm = new SimpleTreeModel(rootNode);
        tree_Type.setModel(stm);

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

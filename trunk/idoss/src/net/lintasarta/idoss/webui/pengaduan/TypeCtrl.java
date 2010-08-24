package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.pengaduan.model.TypeTreeItemRenderer;
import net.lintasarta.idoss.webui.pengaduan.model.TypeTreeModel;
import net.lintasarta.idoss.webui.pengaduan.model.TypeTreeNode;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.service.TypeService;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private transient PType pType;
    private transient TypeService typeService;

    public TypeCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
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

        List<PType> pTypes = getTypeService().getAllType();
        SimpleTreeNode stn = new SimpleTreeNode("ROOT",pTypes);
        ArrayList al = new ArrayList();
        al.add(stn);

        SimpleTreeNode root = new SimpleTreeNode("ROOT",al);
        SimpleTreeModel stm = new SimpleTreeModel(root);
        tree_Type.setModel(stm);

//        tree_Type.setModel(new SimpleTreeModel(new SimpleTreeNode(getpType(), pTypes)) );
//        tree_Type.setModel(new TypeTreeModel(( TypeTreeNode)getTypeService().getAllType()));
        
        tree_Type.setTreeitemRenderer(new TypeTreeItemRenderer());
        doShowDialog(getpType());
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

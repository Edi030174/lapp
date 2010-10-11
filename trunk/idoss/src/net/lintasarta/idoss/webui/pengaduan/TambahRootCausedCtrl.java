package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.model.PType;
import net.lintasarta.pengaduan.model.PTypeRootCaused;
import net.lintasarta.pengaduan.service.RootCausedService;
import net.lintasarta.pengaduan.service.TypeService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jul 27, 2010
 * Time: 9:25:04 AM
 */
public class TambahRootCausedCtrl extends GFCBaseCtrl implements Serializable {

    private transient static final Logger logger = Logger.getLogger(TambahRootCausedCtrl.class);

    protected Window window_TambahRootCaused;
    protected Textbox textbox_TambahRootCaused;
    protected Listbox listbox_RootCaused;
    protected Textbox textbox_Type;
    protected Checkbox checkbox_Aktif;
    protected Checkbox checkbox_NonAktif;

    private transient PRootCaused pRootCaused;
    private transient PTypeRootCaused typeRootCaused;
    private transient RootCausedService rootCausedService;
    private transient TypeService typeService;
    private transient PType pType;

    public TambahRootCausedCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("-->" + super.toString());
        }
    }

    public void onCreate$window_TambahRootCaused(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("pRootCaused")) {
            PRootCaused pRootCaused = (PRootCaused) args.get("pRootCaused");
            setpRootCaused(pRootCaused);
        } else {
            setpRootCaused(null);
        }
        if (args.containsKey("listbox_RootCaused")) {
            listbox_RootCaused = (Listbox) args.get("listbox_RootCaused");
        } else {
            listbox_RootCaused = null;
        }
        if(args.containsKey("textbox_Type")) {
            textbox_Type = (Textbox) args.get("textbox_Type");
            PType pType = (PType) textbox_Type.getAttribute("pType");
            setpType(pType);
        } else {
            textbox_Type = null;
        }


        doShowDialog(getpRootCaused());
    }

    public void onCheck$checkbox_Aktif(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        checkbox_NonAktif.setChecked(false);

    }

    public void onCheck$checkbox_NonAktif(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        checkbox_Aktif.setChecked(false);
    }

    private void doShowDialog(PRootCaused pRootCaused) throws InterruptedException {

        if (pRootCaused == null) {
            pRootCaused = getRootCausedService().getNewRootCaused();
        }

        try {
            window_TambahRootCaused.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }

    }

    public void onClick$btnSimpan_RootCaused(Event event) throws InterruptedException {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpanRootCaused();
        window_TambahRootCaused.onClose();
    }

    private void doSimpanRootCaused() throws InterruptedException {

        PRootCaused pRootCaused = getpRootCaused();
        PTypeRootCaused pTypeRootCaused = getTypeRootCaused();

        doWriteComponentsToBean(pRootCaused,pTypeRootCaused);

        try {
            getRootCausedService().createRootCaused(pRootCaused, pTypeRootCaused);

        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }

        ListModelList lml = (ListModelList) listbox_RootCaused.getListModel();

        if (lml.indexOf(pRootCaused) == -1) {
            lml.add(pRootCaused);
        } else {
            lml.set(lml.indexOf(pRootCaused), pRootCaused);
        }

    }

    private void doWriteComponentsToBean(PRootCaused pRootCaused, PTypeRootCaused pTypeRootCaused) {
        pRootCaused.setRoot_caused(textbox_TambahRootCaused.getValue());
        if (checkbox_Aktif.isChecked()) {
            pRootCaused.setActive("Y");
        } else if (checkbox_NonAktif.isChecked()) {
            pRootCaused.setActive("T");
        }
        pRootCaused.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        pRootCaused.setUpdated_user(getUserWorkspace().getUserSession().getUserName());

        String typeId = getpType().getP_idoss_type_id();
        pTypeRootCaused.setP_idoss_type_id(typeId);
        if (checkbox_Aktif.isChecked()) {
            pTypeRootCaused.setActive("Y");
        } else if (checkbox_NonAktif.isChecked()) {
            pTypeRootCaused.setActive("T");
        }
        pTypeRootCaused.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        pTypeRootCaused.setUpdated_user(getUserWorkspace().getUserSession().getUserName());
    }

    public void onClick$btnBatal_RootCaused(Event event) throws Exception {
        window_TambahRootCaused.onClose();
    }

    public RootCausedService getRootCausedService() {
        return rootCausedService;
    }

    public void setRootCausedService(RootCausedService rootCausedService) {
        this.rootCausedService = rootCausedService;
    }

    public PRootCaused getpRootCaused() {
        return new PRootCaused();
    }

    public void setpRootCaused(PRootCaused pRootCaused) {
        this.pRootCaused = pRootCaused;
    }

    public PTypeRootCaused getTypeRootCaused() {
        return new PTypeRootCaused();
    }

    public void setTypeRootCaused(PTypeRootCaused typeRootCaused) {
        this.typeRootCaused = typeRootCaused;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public PType getpType() {
        return pType;
    }

    public void setpType(PType pType) {
        this.pType = pType;
    }
}
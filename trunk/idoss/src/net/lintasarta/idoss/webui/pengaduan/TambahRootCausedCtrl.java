package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.pengaduan.model.PRootCaused;
import net.lintasarta.pengaduan.service.RootCausedService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jul 27, 2010
 * Time: 9:25:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class TambahRootCausedCtrl extends GFCBaseCtrl implements Serializable {

    private transient static final Logger logger = Logger.getLogger(TambahRootCausedCtrl.class);

    protected Window window_TambahRootCaused;
    protected Textbox textbox_TambahRootCaused;

    protected Checkbox checkbox_Aktif;
    protected Checkbox checkbox_NonAktif;

    private transient PRootCaused pRootCaused;
    private transient RootCausedService rootCausedService;

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

        doWriteComponentsToBean(pRootCaused);

        try {
            getRootCausedService().createRootCaused(pRootCaused);

        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }

    }

    private void doWriteComponentsToBean(PRootCaused pRootCaused) {

        pRootCaused.setRoot_caused(textbox_TambahRootCaused.getValue());
        if (checkbox_Aktif.isChecked()) {
            pRootCaused.setActive("Y");
        } else if (checkbox_NonAktif.isChecked()) {
            pRootCaused.setActive("T");
        }
        pRootCaused.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        pRootCaused.setUpdated_user(getUserWorkspace().getUserSession().getUserName());
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
}

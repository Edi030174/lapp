package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.service.PelaksanaanService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 3, 2010
 * Time: 11:49:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class PelaksanaanCtrl extends GFCBaseCtrl implements Serializable {

    private transient final static Logger logger = Logger.getLogger(PermohonanCtrl.class);
    protected Window window_Permohonan;
    protected Window window_Pelaksanaan;

    protected Radiogroup radiogroup_StatusPerubahan;
    protected Radio selesai;
    protected Radio tunda;
    protected Radio mulai;
    protected Datebox datebox_TglPermohonan;
    protected Datebox datebox_Pending;
    protected FCKeditor fckCatatan_pelaksana;
    protected Checkbox checkbox_Rfs;

    private transient String oldVar_selesai;
    private transient String oldVar_tunda;
    private transient String oldVar_mulai;
    private transient String oldVar_dateboxTglPermohonan;
    private transient String oldVar_dateboxPending;
    private transient boolean oldVar_checkboxRfs;

    protected Listbox listbox_DaftarPermohonan;
    protected Button btnSimpan_Pelaksanaan;
    protected PelaksanaanCtrl pelaksanaanCtrl;

    private transient TPelaksanaan tPelaksanaan;
    private transient PelaksanaanService pelaksanaanService;

    public PelaksanaanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Pelaksanaan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        Map<String, Object> args = getCreationArgsMap(event);
        if (args.containsKey("tPelaksanaan")) {
            TPelaksanaan tPelaksanaan = (TPelaksanaan) args.get("tPelaksanaan");
            settPelaksanaan(tPelaksanaan);
        } else {
            settPelaksanaan(null);
        }

        if (args.containsKey("pelaksanaanCtrl")) {
            pelaksanaanCtrl = (PelaksanaanCtrl) args.get("pelaksanaanCtrl");
        } else {
            pelaksanaanCtrl = null;
        }

        if (args.containsKey("listbox_DaftarPermohonan")) {
            listbox_DaftarPermohonan = (Listbox) args.get("listbox_DaftarPermohonan");
        } else {
            listbox_DaftarPermohonan = null;
        }

        doShowDialog(gettPelaksanaan());
    }

    private void doShowDialog(TPelaksanaan tPelaksanaan) throws InterruptedException {
        try {
            doWriteBeanToComponents(tPelaksanaan);

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }

    }

    private void doWriteBeanToComponents(TPelaksanaan tPelaksanaan) throws Exception {
        datebox_TglPermohonan.setValue(tPelaksanaan.getTgl_permohonan());
        fckCatatan_pelaksana.setValue(tPelaksanaan.getCatatan_pelaksana());
        String z = tPelaksanaan.getStatus_perubahan();

//        radiogroup_StatusPerubahan.setSelectedItem(tPelaksanaan.getStatus_perubahan().);


    }

    public void onClick$btnSimpan_Pelaksanaan(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
        window_Permohonan.onClose();
    }

    public void onClick$btnBatal(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }

        window_Permohonan.onClose();
    }

    private void doSimpan() throws Exception {
        TPelaksanaan tPelaksanaan = gettPelaksanaan();

        doWriteComponentsToBean(tPelaksanaan);
        try {
            getPelaksanaanService().saveOrUpdateTPelaksanaan(tPelaksanaan);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
        doStoreInitValues();

    }

    private void doStoreInitValues() {

        

    }

    private void doWriteComponentsToBean(TPelaksanaan tPelaksanaan) {
        tPelaksanaan.setTgl_permohonan(new Timestamp(datebox_TglPermohonan.getValue().getTime()));
        Radio status = radiogroup_StatusPerubahan.getSelectedItem();
        tPelaksanaan.setStatus_perubahan(status.getValue());
        tPelaksanaan.setTgl_pending(new Timestamp(datebox_Pending.getValue().getTime()));
        tPelaksanaan.setCatatan_pelaksana(fckCatatan_pelaksana.getValue());
        if (checkbox_Rfs.isChecked()) {
            tPelaksanaan.setRfs("1");
        } else if (checkbox_Rfs.isDisabled()) {
            tPelaksanaan.setRfs("0");
        }
        tPelaksanaan.setCreated_user(getUserWorkspace().getUserSession().getUserName());
        tPelaksanaan.setUpdated_user(getUserWorkspace().getUserSession().getUserName());
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        tPelaksanaan.setUpdated_date(now);
        tPelaksanaan.setCreated_date(now);
    }

    public TPelaksanaan gettPelaksanaan() {
        return tPelaksanaan;
    }

    public void settPelaksanaan(TPelaksanaan tPelaksanaan) {
        this.tPelaksanaan = tPelaksanaan;
    }

    public PelaksanaanService getPelaksanaanService() {
        return pelaksanaanService;
    }

    public void setPelaksanaanService(PelaksanaanService pelaksanaanService) {
        this.pelaksanaanService = pelaksanaanService;
    }
}
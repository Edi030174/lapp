package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.permohonan.model.TPelaksanaan;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.model.TVerifikasi;
import net.lintasarta.permohonan.service.PelaksanaanService;
import org.apache.log4j.Logger;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Aug 3, 2010
 * Time: 11:49:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class PelaksanaanCtrl extends GFCBaseCtrl implements Serializable {

    private transient final static Logger logger = Logger.getLogger(PermohonanCtrl.class);
    protected Window window_Pelaksanaan;
    protected Radiogroup radiogroupStatus_perubahan;
    protected Datebox dateboxRfs_date;
    protected FCKeditor fckCatatan_pelaksana;

    protected Listbox listbox_DaftarPermohonan;

    protected PelaksanaanCtrl pelaksanaanCtrl;

    private transient TPermohonan tPermohonan;
    private transient TVerifikasi tVerifikasi;
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

//        if (args.containsKey("tPelaksanaan")) {
//            TPelaksanaan tPelaksanaan = (TPelaksanaan) args.get("tPelaksanaan");
//            settPelaksanaan(tPelaksanaan);
//        } else {
//            settPelaksanaan(null);
//        }
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
        if (tPelaksanaan == null) {
            tPelaksanaan = getPelaksanaanService().getNewPelaksanaan();
            settPelaksanaan(tPelaksanaan);

        }else {
            settPelaksanaan(tPelaksanaan);
        }
        try {
            doWriteBeanToComponents(tPelaksanaan);
//            window_Pelaksanaan.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }

    }

    private void doWriteBeanToComponents(TPelaksanaan tPelaksanaan) throws Exception{
        dateboxRfs_date.setValue(tPelaksanaan.getRfs_date());
        fckCatatan_pelaksana.setValue(tPelaksanaan.getCatatan_pelaksana());
        
    }
    


    public TPermohonan gettPermohonan() {
        return tPermohonan;
    }

    public void settPermohonan(TPermohonan tPermohonan) {
        this.tPermohonan = tPermohonan;
    }

    public TVerifikasi gettVerifikasi() {
        return tVerifikasi;
    }

    public void settVerifikasi(TVerifikasi tVerifikasi) {
        this.tVerifikasi = tVerifikasi;
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
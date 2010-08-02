package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.permohonan.service.VerifikasiService;
import org.apache.log4j.Logger;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.zul.*;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: JosH
 * Date: Jul 23, 2010
 * Time: 12:48:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class VerifikasiCtrl extends GFCBaseCtrl implements Serializable{
    private static final long serialVersionUID = -546886879998950467L;
    private transient static final Logger logger = Logger.getLogger(VerifikasiCtrl.class);

    protected Window verifikasiWindow;

    protected Radio high;
    protected Radio normal;
    protected Radio major;
    protected Radio minor;

    protected Datebox dateboxTanggal1;
    protected Combobox comboboxNik_pelaksana;
    protected Checkbox checkbox1;
    protected Radio radioDisetujui;
    protected Radio radioDitolak;
    protected FCKeditor fckCatatan_asman;

    protected Datebox dateboxTanggal2;
    protected Checkbox checkbox2;
    protected Radio radioDisetujui2;
    protected Radio radioDitolak2;
    protected FCKeditor fckCatatan_manager;

    private transient String oldVar_high;
    private transient String oldVar_normal;
    private transient String oldVar_major;
    private transient String oldVar_minor;

    private transient String oldVar_dateboxTanggal1;
    private transient String oldVar_comboboxNikPelaksana;
    private transient boolean oldVar_checkbox1;
    private transient boolean oldVar_radioDisetujui;
    private transient boolean oldVar_radioDitolak;
    private transient String oldVar_fckCatatanAsman;

    private transient String oldVar_dateboxTanggal2;
    private transient String oldVar_checkbox2;
    private transient boolean oldVar_radioDisetujui2;
    private transient boolean oldVar_radioDitolak2;
    private transient String oldVar_fckCatatanManager;

    protected Button btnRefreshVerifikasi;
    protected Button btnBuatVerifikasi;
    protected Button btnCariVerifikasi;
    protected Button btnSimpan_Verifikasi;

    private transient VerifikasiService verifikasiService;

    public VerifikasiCtrl() {
        super();

		if (logger.isDebugEnabled()) {
//			logger.debug("--> super()");
		}
    }

//    public void onCreate$verifikasiWindow(Event)
}

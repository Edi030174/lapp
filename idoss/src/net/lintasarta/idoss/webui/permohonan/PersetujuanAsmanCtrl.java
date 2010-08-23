package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import org.apache.log4j.Logger;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Aug 19, 2010
 * Time: 9:10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersetujuanAsmanCtrl extends GFCBaseCtrl implements Serializable {

    private transient final static Logger logger = Logger.getLogger(PersetujuanAsmanCtrl.class);

    protected Window window_Permohonan;
    protected Window window_PersetujuanAsman;
    protected Button btn_SimpanPersetujuanAsman;
    protected Button btn_Batal;
    protected Textbox textbox_TIdossPermohonanId;
    protected Textbox textbox_NamaPemohon;
    protected Datebox datebox_Tanggal;
    protected Textbox textbox_NikPemohon;
    protected Textbox textbox_DetailPermohonan;
    



}

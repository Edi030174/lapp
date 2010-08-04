package net.lintasarta.idoss.webui.permohonan.model;

import net.lintasarta.permohonan.model.TPermohonan;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.sys.ComponentsCtrl;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 15, 2010
 * Time: 10:58:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class DaftarPermohonanModelItemRenderer implements ListitemRenderer, Serializable {

	private static final long serialVersionUID = 1925499383404057064L;
	private transient final static Logger logger = Logger.getLogger(DaftarPermohonanModelItemRenderer.class);

	@Override
	public void render(Listitem item, Object data) throws Exception {

		TPermohonan tPermohonan = (TPermohonan) data;

		if (logger.isDebugEnabled()) {
			logger.debug("--> " + tPermohonan.getT_idoss_permohonan_id() + ", " + tPermohonan.getNama_pemohon());
		}
		Listcell lc = new Listcell(tPermohonan.getT_idoss_permohonan_id());
		lc.setParent(item);

        Timestamp ts = tPermohonan.getTgl_permohonan();
        String tgl = new SimpleDateFormat("dd-MM-yyyy").format(ts);
        lc = new Listcell(tgl);
		lc.setParent(item);

		lc = new Listcell(tPermohonan.getType_permohonan());
		lc.setParent(item);
		lc = new Listcell(tPermohonan.getStatus_track_permohonan());
		lc.setParent(item);
		
        lc = new Listcell(tPermohonan.getNama_asman());
		lc.setParent(item);
        lc = new Listcell(tPermohonan.getNama_manager());
		lc.setParent(item);
        lc = new Listcell(tPermohonan.getNama_gm());
		lc.setParent(item);

		/*lc = new Listcell();
		Checkbox cb = new Checkbox();
		cb.setDisabled(true);
		cb.setChecked(tPermohonan.getAsman());
		lc.appendChild(cb);
		lc.setParent(item);*/

		item.setAttribute("data", data);
		ComponentsCtrl.applyForward(item, "onDoubleClick=onPermohonanItemDoubleClicked");
	}
}
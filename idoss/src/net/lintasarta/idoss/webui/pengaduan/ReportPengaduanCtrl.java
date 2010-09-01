package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Sep 1, 2010
 * Time: 5:20:10 PM
 */
public class ReportPengaduanCtrl extends GFCBaseCtrl implements Serializable{
    private transient static final Logger logger = Logger.getLogger(ReportPengaduanCtrl.class);

    protected Window window_report;
    protected Jasperreport report;

    public ReportPengaduanCtrl() {
        super();
        if (logger.isDebugEnabled()) {
            logger.debug("-->" + super.toString());
        }
    }
    public void onCreate$window_report(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        try {
            window_report.doModal();

        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
        showReport();
    }

    private void showReport() {
        Map parameters = new HashMap();
        parameters.put("ReportTitle", "Address Report");
//        parameters.put("DataFile", "CustomDataSource.java");

        report.setSrc("Permohonan.jasper");
        report.setParameters(parameters);
//        report.setDatasource();
    }

}

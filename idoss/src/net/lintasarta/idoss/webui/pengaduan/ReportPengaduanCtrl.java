package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.report.pengaduan.service.ReportPengaduanService;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Sessions;
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

    private transient ReportPengaduanService reportPengaduanService;

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

        String repSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/report/pengaduan/Pengaduan.jasper");
        String subDir = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reports/pengaduan") + "/";

        Map parameters = new HashMap();
        parameters.put("ReportTitle", "sample Report");
        parameters.put("SUBREPORT_DIR", subDir);


        report.setSrc(repSrc);
        report.setParameters(parameters);
        report.setDatasource(getReportPengaduanService().getReport());
    }

    public ReportPengaduanService getReportPengaduanService() {
        return reportPengaduanService;
    }

    public void setReportPengaduanService(ReportPengaduanService reportPengaduanService) {
        this.reportPengaduanService = reportPengaduanService;
    }
}

package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.report.util.JRreportWindow;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.report.service.ReportService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.log4j.Logger;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 8, 2010
 * Time: 10:44:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReportAduanCtrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(ReportAduanCtrl.class);
    protected Window window_Report1;
    protected Iframe report;
    protected Button btnReport;
    protected Listbox listbox_bulan;
    protected Listbox listbox_tahun;
    protected ReportAduanCtrl reportAduanCtrl;

    private transient ReportService reportService;

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public ReportAduanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Report1(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("reportAduanCtrl")) {
            reportAduanCtrl = (ReportAduanCtrl) args.get("reportAduanCtrl");
        } else {
            reportAduanCtrl = null;
        }
    }


    public void onClick$btnReport(Event event) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        String repSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/report/permohonan/reportAduan.jasper");

//        String status = null;
        String bulan = (String) listbox_bulan.getSelectedItem().getValue();
        String tahun = listbox_tahun.getSelectedItem().getLabel();
        JRDataSource ds = reportService.getAduan(bulan, tahun);

        Component parent = window_Report1.getRoot();

        new JRreportWindow(parent, true, null, repSrc, ds, "pdf");
    }
}
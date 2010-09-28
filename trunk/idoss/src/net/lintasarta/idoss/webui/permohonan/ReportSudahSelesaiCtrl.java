package net.lintasarta.idoss.webui.permohonan;

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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 14, 2010
 * Time: 12:24:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportSudahSelesaiCtrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(ReportSudahSelesaiCtrl.class);
    protected Window window_Report3;
    protected Iframe report;
    protected Button btnReport;
    protected Listbox listbox_bulan;
    protected Listbox listbox_tahun;
    protected ReportSudahSelesaiCtrl reportSudahSelesaiCtrl;

    private transient ReportService reportService;

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public ReportSudahSelesaiCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Report3(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("reportSudahSelesaiCtrl")) {
            reportSudahSelesaiCtrl = (ReportSudahSelesaiCtrl) args.get("reportSudahSelesaiCtrl");
        } else {
            reportSudahSelesaiCtrl = null;
        }
    }


    public void onClick$btnReport(Event event) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        String repSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/report/permohonan/reportSudahSelesai.jasper");
        String bulan = (String) listbox_bulan.getSelectedItem().getValue();
        String tahun = listbox_tahun.getSelectedItem().getLabel();
        JRDataSource ds = reportService.getSudahSelesai(bulan,tahun);
        Component parent = window_Report3.getRoot();
        new JRreportWindow(parent, true, null, repSrc, ds, "pdf");
    }
}
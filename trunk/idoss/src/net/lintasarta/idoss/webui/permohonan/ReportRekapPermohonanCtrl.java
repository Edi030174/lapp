package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.report.util.JRreportWindow;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.report.service.ReportService;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 28, 2010
 * Time: 2:05:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportRekapPermohonanCtrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(ReportRekapPermohonanCtrl.class);
    protected Window window_Report5;
    protected Iframe report;
    protected Button btnReport;
    protected Listbox listbox_bulan;
    protected Listbox listbox_tahun;
    protected ReportRekapPermohonanCtrl reportRekapPermohonanCtrl;

    private transient ReportService reportService;

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public ReportRekapPermohonanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Report5(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("reportRekapPermohonanCtrl")) {
            reportRekapPermohonanCtrl = (ReportRekapPermohonanCtrl) args.get("reportRekapPermohonanCtrl");
        } else {
            reportRekapPermohonanCtrl = null;
        }
    }


    public void onClick$btnReport(Event event) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        String repSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/report/permohonan/reportRekapPermohonan.jasper");
        String bulan = (String) listbox_bulan.getSelectedItem().getValue();
        String tahun = listbox_tahun.getSelectedItem().getLabel();
        JRDataSource ds = reportService.getRekapPermohonan(bulan, tahun);
        Component parent = window_Report5.getRoot();
        new JRreportWindow(parent, true, null, repSrc, ds, "pdf");
    }
}
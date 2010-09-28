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
//    private transient final static Logger logger = Logger.getLogger(ReportRekapPermohonanCtrl.class);
//    protected Window window_Report;
//    protected Iframe report;
//    protected Button btnReport;
//    protected Listbox listbox_bulan;
//    protected Listbox listbox_tahun;
//    protected ReportBelumSelesaiCtrl reportBelumSelesaiCtrl;
//
//    private transient ReportService reportService;
//
//    public void setReportService(ReportService reportService) {
//        this.reportService = reportService;
//    }
//
//    public ReportBelumSelesaiCtrl() {
//        super();
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("--> super()");
//        }
//    }
//
//    public void onCreate$window_Report2(Event event) throws Exception {
//        if (logger.isDebugEnabled()) {
//            logger.debug("--> " + event.toString());
//        }
//        Map<String, Object> args = getCreationArgsMap(event);
//
//        if (args.containsKey("reportBelumSelesaiCtrl")) {
//            reportBelumSelesaiCtrl = (ReportBelumSelesaiCtrl) args.get("reportBelumSelesaiCtrl");
//        } else {
//            reportBelumSelesaiCtrl = null;
//        }
//    }
//
//
//    public void onClick$btnReport(Event event) throws IOException {
//        if (logger.isDebugEnabled()) {
//            logger.debug("--> " + event.toString());
//        }
//        String repSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/report/permohonan/reportBelumSelesai.jasper");
//        String bulan = (String) listbox_bulan.getSelectedItem().getValue();
//        String tahun = listbox_tahun.getSelectedItem().getLabel();
//        JRDataSource ds = reportService.getBelumSelesai(bulan, tahun);
//        Component parent = window_Report2.getRoot();
//        new JRreportWindow(parent, true, null, repSrc, ds, "pdf");
//    }
}
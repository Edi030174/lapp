package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.report.util.JRreportWindow;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.report.service.ReportService;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 14, 2010
 * Time: 12:24:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportBelumSelesaiCtrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(ReportBelumSelesaiCtrl.class);
    protected Window window_Report2;
    protected Iframe report;
    protected Button btnReport;
    protected Combobox combobox_bulan;
    protected Combobox combobox_tahun;
    protected ReportBelumSelesaiCtrl reportBelumSelesaiCtrl;

    private transient ReportService reportService;

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public ReportBelumSelesaiCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Report2(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("reportBelumSelesaiCtrl")) {
            reportBelumSelesaiCtrl = (ReportBelumSelesaiCtrl) args.get("reportBelumSelesaiCtrl");
        } else {
            reportBelumSelesaiCtrl = null;
        }
    }


    public void onClick$btnReport(Event event) throws IOException, InterruptedException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        if (bulanTahun()) {
            String repSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/report/permohonan/reportBelumSelesai.jasper");
            /*String bulan = (String) listbox_bulan.getSelectedItem().getValue();
         String tahun = listbox_tahun.getSelectedItem().getLabel();*/
            String bulan = (String) combobox_bulan.getSelectedItem().getValue();
            String tahun = (String) combobox_tahun.getSelectedItem().getValue();
            JRDataSource ds = reportService.getBelumSelesai(bulan, tahun);
            Component parent = window_Report2.getRoot();
            new JRreportWindow(parent, true, null, repSrc, ds, "pdf");
        }
    }

    private boolean bulanTahun() throws InterruptedException {
        if (combobox_bulan.getValue().length() < 1) {
            Messagebox.show("Silakan pilih bulan...", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return false;
        }
        if (combobox_tahun.getValue().length() < 1) {
            Messagebox.show("Silakan pilih tahun...", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return false;
        }
        return true;
    }
}
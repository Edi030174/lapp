package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.report.util.JRreportWindow;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.service.PermohonanService;
import net.lintasarta.report.service.ReportService;
import net.lintasarta.security.model.VHrEmployee;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
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
    protected Combobox combobox_bulan;
    protected Combobox combobox_tahun;
    protected ReportAduanCtrl reportAduanCtrl;

    private transient TPermohonan tPermohonan;
    private transient ReportService reportService;
    private transient PermohonanService permohonanService;

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

    private void doShowDialog(TPermohonan tPermohonan) {
        tPermohonan = getPermohonanService().getNewPermohonan();
        settPermohonan(tPermohonan);

//        String NamaPemohon = getUserWorkspace().getUserSession().getEmployeeName();
//        String NikPemohon = getUserWorkspace().getUserSession().getEmployeeNo();
        tPermohonan = setBoss(tPermohonan, getUserWorkspace().getUserSession().getEmployeeNo());
//        String NamaManager = tPermohonan.getNama_manager();
//        String NikManager = tPermohonan.getNik_manager();
//        String NamaGm = tPermohonan.getNama_gm();
//        String NikGm = tPermohonan.getNik_gm();
        settPermohonan(tPermohonan);
    }

    private VHrEmployee getNamaVHrEmployee(String vHrEmployeeNo) {
        List<VHrEmployee> vHrEmployees = getPermohonanService().getVHrEmployeeByEmployeeNo(vHrEmployeeNo);
        for (VHrEmployee vHrEmployee : vHrEmployees) {
            return vHrEmployee;
        }
        return null;
    }

    private TPermohonan setNikNama(TPermohonan tPermohonan, String employeeNo) {
        VHrEmployee parentEmployee = getNamaVHrEmployee(employeeNo);
        if (parentEmployee.getJob_position_code().equals("Assistant Manager") || parentEmployee.getJob_position_code().equals("Analyst")) {
            tPermohonan.setNik_asman(employeeNo);
            tPermohonan.setNama_asman(parentEmployee.getEmployee_name());
        } else if (parentEmployee.getJob_position_code().equals("Manager") || parentEmployee.getJob_position_code().equals("POH Manager")) {
            tPermohonan.setNik_manager(employeeNo);
            tPermohonan.setNama_manager(parentEmployee.getEmployee_name());
        } else if (parentEmployee.getJob_position_code().equals("General Manager") || parentEmployee.getJob_position_code().equals("POH General Manager")) {
            tPermohonan.setNik_gm(employeeNo);
            tPermohonan.setNama_gm(parentEmployee.getEmployee_name());
        }
        return tPermohonan;
    }

    private TPermohonan setBoss(TPermohonan tPermohonan, String employeeNo) {

        tPermohonan = setNikNama(tPermohonan, employeeNo);

        String parentEmployeeNo = getPermohonanService().getManagerReport(employeeNo);
        tPermohonan = setNikNama(tPermohonan, parentEmployeeNo);

        String grandParentEmployeeNo = getPermohonanService().getManagerReport(parentEmployeeNo);
        tPermohonan = setNikNama(tPermohonan, grandParentEmployeeNo);

        return tPermohonan;
    }

    public void onClick$btnReport(Event event) throws IOException, InterruptedException, JRException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        if (validasiBulanTahun()) {
            String repSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/report/permohonan/reportAduan.jasper");
            doShowDialog(gettPermohonan());
            String nama_pemohon = getUserWorkspace().getUserSession().getEmployeeName();
            String nik_pemohon = getUserWorkspace().getUserSession().getEmployeeNo();
            String nama_manager = gettPermohonan().getNama_asman();
            String nik_manager = gettPermohonan().getNik_asman();
            String nama_gm = gettPermohonan().getNama_manager();
            String nik_gm = gettPermohonan().getNik_manager();
            String bulan = (String) combobox_bulan.getSelectedItem().getValue();
            String tahun = (String) combobox_tahun.getSelectedItem().getValue();
            JRDataSource ds = reportService.getAduan(bulan, tahun, nama_pemohon, nik_pemohon, nama_manager, nik_manager, nama_gm, nik_gm);
            if (ds.next()) {
                Component parent = window_Report1.getRoot();
                new JRreportWindow(parent, true, null, repSrc, ds, "pdf");
            } else {
                String msg = "Data tidak ditemukan di bulan " + bulan + " tahun " + tahun;
                String title = Labels.getLabel("message_Information");

                MultiLineMessageBox.doSetTemplate();
                MultiLineMessageBox.show(msg, title, MultiLineMessageBox.OK, "INFORMATION", true);
            }
        }
    }

    private boolean validasiBulanTahun() throws InterruptedException {
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

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public TPermohonan gettPermohonan() {
        return tPermohonan;
    }

    public void settPermohonan(TPermohonan tPermohonan) {
        this.tPermohonan = tPermohonan;
    }
}
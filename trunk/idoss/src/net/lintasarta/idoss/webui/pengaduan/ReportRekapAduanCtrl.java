package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.report.util.JRreportWindow;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.idoss.webui.util.MultiLineMessageBox;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.service.PermohonanService;
import net.lintasarta.report.permohonan.model.ReportServer;
import net.lintasarta.report.permohonan.service.ReportServerService;
import net.lintasarta.report.service.ReportService;
import net.lintasarta.security.model.VHrEmployee;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
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
 * Date: Sep 20, 2010
 * Time: 4:32:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportRekapAduanCtrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(ReportRekapAduanCtrl.class);
    protected Window window_Report4;
    protected Iframe report;
    protected Button btnReport;
    protected Button button_Tambah;
    protected Combobox combobox_tahun;
    protected Combobox combobox_tahun2;
    protected Combobox combobox_bulan;
    protected Intbox intbox_Jumlah;
    protected ReportRekapAduanCtrl reportRekapAduanCtrl;
    private transient ReportService reportService;
    private transient PermohonanService permohonanService;
    private transient TPermohonan tPermohonan;
    private transient ReportServerService reportServerService;

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public ReportRekapAduanCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super()");
        }
    }

    public void onCreate$window_Report4(Event event) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        Map<String, Object> args = getCreationArgsMap(event);

        if (args.containsKey("reportRekapAduanCtrl")) {
            reportRekapAduanCtrl = (ReportRekapAduanCtrl) args.get("reportRekapAduanCtrl");
        } else {
            reportRekapAduanCtrl = null;
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

    public void onClick$btnReport(Event event) throws IOException, InterruptedException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        String repSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/report/permohonan/reportRekapAduan.jasper");
        //
        doShowDialog(gettPermohonan());
        if (validasiTahun()) {
            String nama_pemohon = getUserWorkspace().getUserSession().getEmployeeName();
            String nik_pemohon = getUserWorkspace().getUserSession().getEmployeeNo();
            String nama_manager = gettPermohonan().getNama_asman();
            String nik_manager = gettPermohonan().getNik_asman();
            String nama_gm = gettPermohonan().getNama_manager();
            String nik_gm = gettPermohonan().getNik_manager();
//            HashMap params = new HashMap();
//            params.put("nama_pemohon", nama_pemohon);
//            params.put("nik_pemohon", nik_pemohon);
//            params.put("nama_manager", nama_manager);
//            params.put("nik_manager", nik_manager);
//            params.put("nama_gm", nama_gm);
//            params.put("nik_gm", nik_gm);
            String tahun = (String) combobox_tahun.getSelectedItem().getValue();
            JRDataSource ds = reportService.getRekapAduan(tahun, nama_pemohon, nik_pemohon, nama_manager, nik_manager, nama_gm, nik_gm);
            Component parent = window_Report4.getRoot();
            new JRreportWindow(parent, true, null, repSrc, ds, "pdf");
        }
    }

    public void onClick$button_Tambah(Event event) throws InterruptedException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        doSimpan();
        Messagebox.show("Anda berhasil menyimpan", "Save succeed", Messagebox.OK, Messagebox.INFORMATION);
    }

    private void doSimpan() throws InterruptedException {
        ReportServer reportServer = new ReportServer();
        doWriteComponentsToBean(reportServer);
        try {
            getReportServerService().createReportServer(reportServer);
        } catch (DataAccessException e) {
            String message = e.getMessage();
            String title = Labels.getLabel("message_Error");
            MultiLineMessageBox.doSetTemplate();
            MultiLineMessageBox.show(message, title, MultiLineMessageBox.OK, "ERROR", true);
        }
    }

    private void doWriteComponentsToBean(ReportServer reportServer) {
        String bulan = (String) combobox_bulan.getSelectedItem().getValue();
        reportServer.setBulan(bulan);
        int tahun = Integer.parseInt(combobox_tahun2.getSelectedItem().getValue().toString());
//        int tahun = Integer.parseInt(combobox_tahun2.getName());
        reportServer.setTahun(tahun);
        reportServer.setJumlah(intbox_Jumlah.getValue());
        reportServer.setUpdate_by(getUserWorkspace().getUserSession().getUserName());

    }

    private boolean validasiTahun() throws InterruptedException {
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

    public ReportServerService getReportServerService() {
        return reportServerService;
    }

    public void setReportServerService(ReportServerService reportServerService) {
        this.reportServerService = reportServerService;
    }

    public TPermohonan gettPermohonan() {
        return tPermohonan;
    }

    public void settPermohonan(TPermohonan tPermohonan) {
        this.tPermohonan = tPermohonan;
    }
}
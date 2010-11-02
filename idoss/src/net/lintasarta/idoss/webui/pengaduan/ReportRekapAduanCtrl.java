package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.report.util.JRreportWindow;
import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.lintasarta.permohonan.model.TPermohonan;
import net.lintasarta.permohonan.service.PermohonanService;
import net.lintasarta.report.service.ReportService;
import net.lintasarta.security.model.VHrEmployee;
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
    protected Listbox listbox_tahun;
    protected ReportRekapAduanCtrl reportRekapAduanCtrl;

    private transient ReportService reportService;
    //pimbag
    private transient PermohonanService permohonanService;
    private transient TPermohonan tPermohonan;
    //

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


    //pimbag

    private void doShowDialog(TPermohonan tPermohonan) {
        tPermohonan = getPermohonanService().getNewPermohonan();
        settPermohonan(tPermohonan);

        String NamaPemohon = getUserWorkspace().getUserSession().getEmployeeName();
        String NikPemohon = getUserWorkspace().getUserSession().getEmployeeNo();
        tPermohonan = setBoss(tPermohonan, getUserWorkspace().getUserSession().getEmployeeNo());
        String NamaManager = tPermohonan.getNama_manager();
        String NikManager = tPermohonan.getNik_manager();
        String NamaGm = tPermohonan.getNama_gm();
        String NikGm = tPermohonan.getNik_gm();
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

        String parentEmployeeNo = getPermohonanService().getManager(employeeNo);
        tPermohonan = setNikNama(tPermohonan, parentEmployeeNo);

        String grandParentEmployeeNo = getPermohonanService().getManager(parentEmployeeNo);
        tPermohonan = setNikNama(tPermohonan, grandParentEmployeeNo);

        return tPermohonan;
    }
    //


    public void onClick$btnReport(Event event) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        String repSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/report/permohonan/reportRekapAduan.jasper");
        //
        doShowDialog(gettPermohonan());
        String NamaPemohon = getUserWorkspace().getUserSession().getEmployeeName();
        String NikPemohon = getUserWorkspace().getUserSession().getEmployeeNo();
        String NamaManager = gettPermohonan().getNama_manager();
        String NikManager = gettPermohonan().getNik_manager();
        String NamaGm = gettPermohonan().getNama_gm();
        String NikGm = gettPermohonan().getNik_gm();
        //
        String tahun = listbox_tahun.getSelectedItem().getLabel();
        JRDataSource ds = reportService.getRekapAduan(tahun, NamaPemohon, NikPemohon, NamaManager, NikManager, NamaGm, NikGm);
        Component parent = window_Report4.getRoot();
        new JRreportWindow(parent, true, null, repSrc, ds, "pdf");
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
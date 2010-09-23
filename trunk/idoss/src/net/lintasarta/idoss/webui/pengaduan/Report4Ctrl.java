package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.log4j.Logger;
import org.zkoss.util.media.AMedia;
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
 * Date: Sep 20, 2010
 * Time: 4:32:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Report4Ctrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(Report4Ctrl.class);
    protected Window window_Report4;
    protected Iframe report;
    protected Button btnReport;
    protected Listbox listbox_tahun;
    protected Report4Ctrl report4Ctrl;

    public Report4Ctrl() {
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

        if (args.containsKey("report4Ctrl")) {
            report4Ctrl = (Report4Ctrl) args.get("report4Ctrl");
        } else {
            report4Ctrl = null;
        }
    }


    public void onClick$btnReport(Event event) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
        InputStream is = null;
        try {
            //generate report pdf stream
//            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/WEB-INF/report/permohonan/reportAduan.jasper");
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("net/lintasarta/idoss/report/permohonan/reportRekapAduan.jasper");
//            is = this.getClass().getResourceAsStream("/WEB-INF/report/permohonan/reportAduan.jasper");

            final Map params = new HashMap();
//            params.put("ReportTitle", "The First Jasper Report Ever");

            String th = listbox_tahun.getSelectedItem().getLabel();
            params.put("tahun", th);

            final byte[] buf = JasperRunManager.runReportToPdf(is, params, getConnection());

            //prepare the AMedia for iframe
            final InputStream mediais = new ByteArrayInputStream(buf);
            final AMedia amedia = new AMedia("reportRekapAduan.pdf", "pdf", "application/pdf", mediais);

            //set iframe content
            report.setContent(amedia);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        //Change these settings according to your local configuration
        String driver = "oracle.jdbc.OracleDriver";
        String connectString = "jdbc:oracle:thin:@10.24.8.78:1521:db11";
        String user = "sni";
        String password = "sni";

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectString, user, password);
        return conn;
    }

    public Report4Ctrl getReport4Ctrl() {
        return report4Ctrl;
    }

    public void setReport4Ctrl(Report4Ctrl report4Ctrl) {
        this.report4Ctrl = report4Ctrl;
    }
}
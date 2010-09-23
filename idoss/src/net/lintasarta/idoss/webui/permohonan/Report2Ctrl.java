package net.lintasarta.idoss.webui.permohonan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.log4j.Logger;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
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
 * Time: 12:24:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Report2Ctrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(Report2Ctrl.class);
    protected Window window_Report2;
    protected Iframe report;
    protected Button btnReport;
    protected Report2Ctrl report2Ctrl;

    public Report2Ctrl() {
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

        if (args.containsKey("report2Ctrl")) {
            report2Ctrl = (Report2Ctrl) args.get("report2Ctrl");
        } else {
            report2Ctrl = null;
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
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("net/lintasarta/idoss/report/permohonan/reportBelumSelesai.jasper");
//            is = this.getClass().getResourceAsStream("/WEB-INF/report/permohonan/reportAduan.jasper");

            final Map params = new HashMap();
//            params.put("ReportTitle", "The First Jasper Report Ever");
            params.put("status", "Open");

            final byte[] buf = JasperRunManager.runReportToPdf(is, params, getConnection());

            //prepare the AMedia for iframe
            final InputStream mediais = new ByteArrayInputStream(buf);
            final AMedia amedia = new AMedia("reportPermohonanBelumSelesai.pdf", "pdf", "application/pdf", mediais);

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

    public Report2Ctrl getReport2Ctrl() {
        return report2Ctrl;
    }

    public void setReport2Ctrl(Report2Ctrl report2Ctrl) {
        this.report2Ctrl = report2Ctrl;
    }
}
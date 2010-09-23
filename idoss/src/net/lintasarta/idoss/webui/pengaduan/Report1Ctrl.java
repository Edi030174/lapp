package net.lintasarta.idoss.webui.pengaduan;

import net.lintasarta.idoss.webui.util.GFCBaseCtrl;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.log4j.Logger;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import javax.sql.DataSource;
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
 * Date: Sep 8, 2010
 * Time: 10:44:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Report1Ctrl extends GFCBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(Report1Ctrl.class);
    protected Window window_Report1;
    protected Iframe report;
    protected Button btnReport;
    protected Textbox txt_tahun;
    protected Report1Ctrl report1Ctrl;
    private transient DataSource dataSource;

    public Report1Ctrl() {
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

        if (args.containsKey("report1Ctrl")) {
            report1Ctrl = (Report1Ctrl) args.get("report1Ctrl");
        } else {
            report1Ctrl = null;
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
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("net/lintasarta/idoss/report/permohonan/reportAduan.jasper");
//            is = this.getClass().getResourceAsStream("/WEB-INF/report/permohonan/reportAduan.jasper");

            final Map params = new HashMap();
//            params.put("ReportTitle", "The First Jasper Report Ever");
            params.put("status", "Open");

            final byte[] buf = JasperRunManager.runReportToPdf(is, params, dataSource.getConnection());

            //prepare the AMedia for iframe
            final InputStream mediais = new ByteArrayInputStream(buf);
            final AMedia amedia = new AMedia("reportAduan.pdf", "pdf", "application/pdf", mediais);

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

//    private static Connection getConnection()
//            throws ClassNotFoundException, SQLException {
        //Change these settings according to your local configuration
//        String driver = "oracle.jdbc.OracleDriver";
//        String connectString = "jdbc:oracle:thin:@10.24.8.78:1521:db11";
//        String user = "sni";
//        String password = "sni";

//        Class.forName(driver);
//        Connection conn = DriverManager.getConnection(connectString, user, password);
//        return conn;
//    }

    public Report1Ctrl getReport1Ctrl() {
        return report1Ctrl;
    }

    public void setReport1Ctrl(Report1Ctrl report1Ctrl) {
        this.report1Ctrl = report1Ctrl;
    }
}
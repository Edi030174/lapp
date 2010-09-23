package net.lintasarta.idoss.report.util;

import net.sf.jasperreports.engine.JRDataSource;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.Window;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Sep 23, 2010
 * Time: 11:51:53 AM
 */
public class JRreportWindow extends Window implements Serializable {
    private transient static final Logger logger = Logger.getLogger(JRreportWindow.class);

    private transient JRreportWindow window;
    private transient Jasperreport report;

    private transient Component parent;

    private transient boolean modal;

    private transient HashMap<String, Object> reportParams;

    private transient String reportPathName;

    private transient JRDataSource ds;

    private transient String type;

    public JRreportWindow(Component parent, boolean modal, HashMap<String, Object> reportParams, String reportPathName, JRDataSource ds, String type) {
        super();
        this.parent = parent;
        this.modal = modal;
        this.reportParams = reportParams;
        this.reportPathName = reportPathName;
        this.ds = ds;
        this.type = type;
        this.window = this;

        try {
            createReport();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createReport() throws FileNotFoundException {

        if ((Boolean) modal == null) {
            modal = true;
        }

        if (reportPathName.isEmpty()) {
            throw new FileNotFoundException(reportPathName);
        }

        if (ds == null) {
            throw new FileNotFoundException("JRDataSource is empty");
        }

        if (type.isEmpty()) {
            type = "pdf";
        }

        this.setParent(parent);

        this.setTitle("IDOSS Reports");
        this.setVisible(true);
        this.setMaximizable(true);
        this.setMinimizable(true);
        this.setSizable(true);
        this.setClosable(true);
        this.setHeight("100%");
        this.setWidth("80%");
        this.addEventListener("onClose", new OnCloseReportEventListener());

        report = new Jasperreport();
        report.setId("jasperReportId");
        report.setSrc(reportPathName);
        report.setParameters(reportParams);
        report.setDatasource(ds);
        report.setType(type);
        report.setParent(this); // needed ?

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + report.getId());
        }

        this.appendChild(report);

        if (modal) {
            try {
                this.doModal();
            } catch (SuspendNotAllowedException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public final class OnCloseReportEventListener implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            closeReportWindow();
        }
    }

    private void closeReportWindow() {

        if (logger.isDebugEnabled()) {
            logger.debug("detach Report and close ReportWindow");
        }

        window.removeEventListener("onClose", new OnCloseReportEventListener());

        // TODO check this
        report.detach();
        window.getChildren().clear();
        window.onClose();

    }
}

package net.lintasarta.report.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.InputStream;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Sep 1, 2010
 * Time: 2:47:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class JRreportCompiler {
    public JRreportCompiler() {
    }
    public boolean compileReport(String aReportName) {

        boolean result = false;

        try {

            InputStream inputStream = getClass().getResourceAsStream(aReportName);

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            Collection collection = JasperCompileManager.verifyDesign(jasperDesign);
            for (Object object : collection) {
                object.toString();
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            if (jasperReport != null) {
                result = true;
            }

        } catch (JRException ex) {
            String connectMsg = "JasperReports: Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.out.println(connectMsg);
        } catch (Exception ex) {
            String connectMsg = "Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.out.println(connectMsg);
        }
        return result;
    }

}

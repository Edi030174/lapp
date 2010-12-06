package net.lintasarta.idoss.webui;

import net.lintasarta.idoss.statistic.Statistic;
import net.lintasarta.idoss.webui.util.WindowBaseCtrl;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.Hr;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkmax.zul.Tablechildren;
import org.zkoss.zkmax.zul.Tablelayout;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 14, 2010
 * Time: 1:28:48 PM
 */
public class InitApplicationCtrl extends WindowBaseCtrl implements Serializable {
    private transient final static Logger logger = Logger.getLogger(InitApplicationCtrl.class);

    protected Window startWindow;
    protected North bl_north;
    protected South bl_south;
    protected Center bl_center;

    private Tablelayout tableLayout;
    private Tablechildren tableChildrenStatistic;
    private Tablechildren tableChildrenButtons;
    private Panelchildren panelChildren_Buttons;

    public InitApplicationCtrl() {
        super();

        if (logger.isDebugEnabled()) {
            logger.debug("--> super() ");
        }
    }

    public void onCreate$startWindow(Event event) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("--> " + event.toString());
        }
//        doOnCreateCommon(startWindow);
//
//        createMainGrid();
//
//        showStatistic();
//
//        showUsersOnlineChart();
        Executions.sendRedirect("/pages/indexLogin.zul");

    }

    private void createMainGrid() {

        Div div = new Div();
        div.setParent(bl_center);

        Hr hr = new Hr();
        hr.setParent(div);

        Borderlayout bl = new Borderlayout();
        bl.setParent(div);
        Center ct = new Center();
        ct.setAutoscroll(true);
        ct.setStyle("background-color: #EBEBEB");
        ct.setBorder("none");
        ct.setFlex(true);
        ct.setParent(bl);
        Div divCt = new Div();
        divCt.setParent(ct);

        tableLayout = new Tablelayout();
        tableLayout.setColumns(3);
        tableLayout.setParent(divCt);

        tableChildrenStatistic = new Tablechildren();
        tableChildrenStatistic.setRowspan(1);
        tableChildrenStatistic.setWidth("450px");
        tableChildrenStatistic.setStyle("padding-left: 5px;");
        tableChildrenStatistic.setParent(tableLayout);

        tableChildrenButtons = new Tablechildren();
        tableChildrenButtons.setRowspan(1);
        tableChildrenButtons.setWidth("240px");
        tableChildrenButtons.setStyle("padding-left: 5px;");
        tableChildrenButtons.setParent(tableLayout);

        Panel pb = new Panel();
        pb.setWidth("240px");
        pb.setBorder("none");
        pb.setStyle("align:left; color:red");
        pb.setParent(tableChildrenButtons);

        panelChildren_Buttons = new Panelchildren();
        panelChildren_Buttons.setParent(pb);

        Separator sep = new Separator();
        sep.setParent(divCt);
        Separator sep2 = new Separator();
        sep2.setParent(divCt);

        Div divFooter = new Div();
        divFooter.setAlign("center");
        divFooter.setParent(bl_south);

        Hr hr2 = new Hr();
        hr2.setParent(divFooter);

        Label footerLabel = new Label();
        footerLabel.setValue(" Help to prevent vendor bankrupt by giving projects.");
        footerLabel.setStyle("align:center; padding-top:0px; font-family:Verdana;  font-size: 0.6em; ");
        footerLabel.setParent(divFooter);
    }

    private void showStatistic() {

        Statistic stat = Statistic.getStatistic();

        Panel panel = new Panel();
        panel.setWidth("450px");
        panel.setBorder("none");
        panel.setStyle("align:left; color:red;");
        panel.setParent(tableChildrenStatistic);

        Panelchildren panelchildren = new Panelchildren();
        panelchildren.setParent(panel);
        panelchildren.setStyle("background-color: #EBEBEB;");

        Groupbox gb = new Groupbox();
        gb.setMold("3d");
        gb.setParent(panelchildren);

        Caption caption = new Caption();
        caption.setParent(gb);
        caption.setImage("/images/icons/monitorView.gif");
        caption.setLabel("Application Statistic");
        caption.setStyle("color: #000000;font-weight:bold; text-align:left ");

        Grid grid = new Grid();
        grid.setWidth("100%");
        grid.setParent(gb);

        Columns columns = new Columns();
        columns.setSizable(true);
        columns.setParent(grid);

        Column column1 = new Column();
        column1.setWidth("55%");
        column1.setLabel("Subject");
        column1.setParent(columns);
        Column column2 = new Column();
        column2.setWidth("45%");
        column2.setLabel("value");
        column2.setParent(columns);

        Rows rows = new Rows();
        rows.setParent(grid);

        addNewRow(rows, "Application Start-Time", String.valueOf(new Date(stat.getStartTime())));

        addNewRow(rows, "Application runing hours", getRoundedDouble(stat.getRuningHours()));

        addNewRow(rows, "Count of active Desktops", String.valueOf(stat.getActiveDesktopCount()));
        addNewRow(rows, "Count of active Sessions", String.valueOf(stat.getActiveSessionCount()));
        addNewRow(rows, "Count of active Updates", String.valueOf(stat.getActiveUpdateCount()));

        addNewRow(rows, "Average Count of active Desktops/hour", getRoundedDouble(stat.getAverageDesktopCount()));
        addNewRow(rows, "Average Count of active Sessions/hour", getRoundedDouble(stat.getAverageSessionCount()));
        addNewRow(rows, "Average Count of active Updates/hour", getRoundedDouble(stat.getAverageUpdateCount()));

        addNewRow(rows, "Count of total Desktops since start", String.valueOf(stat.getTotalDesktopCount()));
        addNewRow(rows, "Count of total Sessions since start", String.valueOf(stat.getTotalSessionCount()));
        addNewRow(rows, "Count of total Updates since start", String.valueOf(stat.getTotalUpdateCount()));

        double value = ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * 100.0) / Runtime.getRuntime().maxMemory();
        addNewRow(rows, "current free memory on the JAVA VM", getRoundedDouble(value) + " MB", "red");
        int countCPU = Runtime.getRuntime().availableProcessors();
        addNewRow(rows, "available processors to the JAVA VM", countCPU, "red");
    }

    private String getRoundedDouble(double d) {

        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(d);
    }

    private void showUsersOnlineChart() {

        Panel panel = new Panel();
        panel.setWidth("240px");
        panel.setHeight("260px");
        panel.setBorder("none");
        panel.setParent(panelChildren_Buttons);

        Panelchildren panelchildren = new Panelchildren();
        panelchildren.setParent(panel);
        panelchildren.setStyle("background-color: #EBEBEB;");

        Groupbox gb = new Groupbox();
        gb.setMold("3d");
        gb.setParent(panelchildren);

        Caption caption = new Caption();
        caption.setParent(gb);
        caption.setImage("/images/icons/console_view.gif");
        caption.setLabel("Users online");
        caption.setStyle("color: #000000;font-weight:bold; text-align:left ");

        Div div = new Div();
        div.setWidth("100%");
        div.setHeight("100%");
        div.setParent(gb);

        Random random = new Random();

        int val = random.nextInt(100);
        DialModel dialmodel = new DialModel();
        DialModelScale scale = dialmodel.newScale(0.0, 500.0, -120.0, -300.0, 100.0, 4);// scale's

        scale.setText("Users");
        scale.newRange(450, 500, "#FF0000", 0.83, 0.89);
        scale.newRange(360, 450, "#FFC426", 0.83, 0.89);
        scale.setValue(val);

        Chart chart = new Chart();
        chart.setType("dial");
        chart.setWidth("228px");
        chart.setHeight("220px");
        chart.setThreeD(false);
        chart.setFgAlpha(128);
        chart.setBgColor("#FFFFFF");
        chart.setModel(dialmodel);
        chart.setParent(div);

    }

//    private Separator createNewSeparator(Component parent, String orientation, boolean isBarVisible, String spacing, String bkgrColor) {
//
//        Separator sep = new Separator();
//
//        sep.setOrient(orientation);
//        sep.setBar(isBarVisible);
//
//        if (!StringUtils.trim(bkgrColor).isEmpty()) {
//            sep.setStyle("background-color:" + bkgrColor);
//        }
//
//        if (StringUtils.isEmpty(spacing)) {
//            sep.setSpacing(0 + "px");
//        } else {
//            sep.setSpacing(spacing + "px");
//        }
//
//        sep.setParent(parent);
//
//        return sep;
//    }
//

    private void addNewRow(Rows rowParent, String tableName, Object value) {
        Row row;
        Label label_TableName;
        Label label_RecordCount;

        row = new Row();
        label_TableName = new Label(tableName);
        label_TableName.setParent(row);
        label_RecordCount = new Label(String.valueOf(value));
        label_RecordCount.setId("label_RecordCount_" + tableName);
        label_RecordCount.setParent(row);
        row.setParent(rowParent);
    }

    private void addNewRow(Rows rowParent, String tableName, Object value, String color) {

        Row row;
        Label label_TableName;
        Label label_RecordCount;
        row = new Row();
        label_TableName = new Label(tableName);

        if (color.equalsIgnoreCase("red")) {
            label_TableName.setStyle("color: " + color + ";");
        }

        label_TableName.setParent(row);
        label_RecordCount = new Label(String.valueOf(value));

        if (color.equalsIgnoreCase("red")) {
            label_RecordCount.setStyle("color: " + color + ";");
        }

        label_RecordCount.setParent(row);
        row.setParent(rowParent);
    }
}
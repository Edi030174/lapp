package net.lintasarta.idoss.webui.util;

import org.apache.commons.lang.time.FastDateFormat;
import org.zkoss.util.resource.Labels;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 23, 2010
 * Time: 11:23:32 AM
 */
final public class FDDateFormat {
    static public FastDateFormat getDateFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.date", "dd.MM.yyyy"));
    }

    static public FastDateFormat getTimeFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.time", "HH:mm"));
    }

    static public FastDateFormat getTimeLongFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.timelong", "HH:mm:ss"));
    }

    static public FastDateFormat getDateTimeFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.datetime", "dd.MM.yyyy HH:mm"));
    }

    static public FastDateFormat getDateTimeLongFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.datetimelong", "dd.MM.yyyy HH:mm:ss"));
    }
}

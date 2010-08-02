package net.lintasarta.idoss.statistic;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.Monitor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 13, 2010
 * Time: 3:32:56 PM
 */
public class Statistic implements Monitor, Serializable {
    private transient final long _startTime;
    private transient int _nsess, _actsess, _ndt, _actdt, _nupd, _actupd;

    private static Statistic stat;

    public Statistic() {
        _startTime = System.currentTimeMillis();
        stat = this;
    }

    public static Statistic getStatistic() {
        return stat;
    }

    public double getRuningHours() {
        long v = System.currentTimeMillis() - getStartTime();
        return ((double) v) / 3600000;
    }

    public long getStartTime() {
        return _startTime;
    }

    public int getTotalSessionCount() {
        return _nsess;
    }

    public int getActiveSessionCount() {
        return _actsess;
    }

    public double getAverageSessionCount() {
        return _nsess / getEscapedHours();
    }

    public int getTotalDesktopCount() {
        return _ndt;
    }

    public int getActiveDesktopCount() {
        return _actdt;
    }

    public double getAverageDesktopCount() {
        return _ndt / getEscapedHours();
    }

    public int getTotalUpdateCount() {
        return _nupd;
    }

    public int getActiveUpdateCount() {
        return _actupd;
    }

    public double getAverageUpdateCount() {
        return _nupd / getEscapedHours();
    }

    private double getEscapedHours() {
        long v = System.currentTimeMillis() - _startTime;
        return ((double) v) / 3600000;
    }

    synchronized public void sessionCreated(Session sess) {
        ++_nsess;
        ++_actsess;
    }

    synchronized public void sessionDestroyed(Session sess) {
        --_actsess;
    }

    synchronized public void desktopCreated(Desktop desktop) {
        ++_ndt;
        ++_actdt;
    }

    synchronized public void desktopDestroyed(Desktop desktop) {
        --_actdt;
    }

    synchronized public void beforeUpdate(Desktop desktop, List requests) {
        ++_nupd;
        ++_actupd;
    }

    synchronized public void afterUpdate(Desktop desktop) {
        --_actupd;
    }
}

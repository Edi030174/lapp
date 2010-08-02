package net.lintasarta.pengaduan.model;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jun 14, 2010
 * Time: 2:03:55 PM
 */
public interface Entity {
    public boolean isNew();

    public long getId();

    public void setId(long id);
}

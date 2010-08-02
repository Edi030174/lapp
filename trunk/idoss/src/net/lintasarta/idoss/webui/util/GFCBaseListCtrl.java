package net.lintasarta.idoss.webui.util;

import net.lintasarta.idoss.webui.util.pagging.PagedListWrapper;

/**
 * Created by IntelliJ IDEA.
 * User: Xsis
 * Date: Jul 14, 2010
 * Time: 3:14:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class GFCBaseListCtrl<T> extends GFCBaseCtrl {

    private PagedListWrapper<T> pagedListWrapper;

    public PagedListWrapper<T> getPagedListWrapper() {
        return pagedListWrapper;
    }

    public void setPagedListWrapper(PagedListWrapper<T> pagedListWrapper) {
        this.pagedListWrapper = pagedListWrapper;
    }
}

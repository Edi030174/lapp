package net.lintasarta.idoss.webui.util.pagging;

import org.apache.log4j.Logger;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 19, 2010
 * Time: 11:18:42 AM
 */
public class PagedListWrapper<E> extends ListModelList implements Serializable {
    static final Logger logger = Logger.getLogger(PagedListWrapper.class);

    private Paging paging;

    private PagedListHolder<E> pagedListHolder;

    // not used yet. so it's init to 'true'.
    private final boolean supportPaging = true;

    public PagedListWrapper() {
        super();
    }

    public void init(PagedListHolder<E> pagedListHolder, Listbox listBox, Paging paging1) {
        setPaging(paging1);
        setListeners(listBox);

        setPagedListHolder(pagedListHolder);
    }

    private void initModel() {
        getPagedListHolder().setPage(0);
//        getPagedListHolder().setMaxLinkedPages(getPageSize());

        clear();
        getPaging().setTotalSize(getPagedListHolder().getNrOfElements());
        addAll(getPagedListHolder().getPageList());
    }

    void refreshModel(int pageNo) {
        getPagedListHolder().setPage(pageNo);
//        getPagedListHolder().setMaxLinkedPages(getPageSize());

        clear();

        addAll(getPagedListHolder().getPageList());
    }

    boolean isSupportPagging() {
        return supportPaging;
    }

//    public void clearFilters() {
//        getPagedListHolder().getPageList().clear();
//        initModel();
//    }
//
    private void setListeners(Listbox listBox) {

        // Add 'onPaging' listener to the paging component
        getPaging().addEventListener("onPaging", new OnPagingEventListener());

        Listhead listhead = listBox.getListhead();
        List<?> list = listhead.getChildren();

        OnSortEventListener onSortEventListener = new OnSortEventListener();
        for (Object object : list) {
            if (object instanceof Listheader) {
                Listheader lheader = (Listheader) object;

                if (lheader.getSortAscending() != null || lheader.getSortDescending() != null) {

                    if (logger.isDebugEnabled()) {
                        logger.debug("--> : " + lheader.getId());
                    }
                    lheader.addEventListener("onSort", onSortEventListener);
                }
            }
        }
        listBox.setModel(this);
    }

    public final class OnPagingEventListener implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {

            PagingEvent pe = (PagingEvent) event;
            int pageNo = pe.getActivePage();
//            pageNo++;
//            int start = pageNo * getPageSize();

            if (logger.isDebugEnabled()) {
                logger.debug("--> : " + pageNo + "/" + getPageSize());
            }

            // refresh the list
            refreshModel(pageNo);
        }
    }

    public final class OnSortEventListener implements EventListener, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public void onEvent(Event event) throws Exception {
            final Listheader lh = (Listheader) event.getTarget();
            final String sortDirection = lh.getSortDirection();

            if ("ascending".equals(sortDirection)) {
                final Comparator<?> cmpr = lh.getSortDescending();
                if (cmpr instanceof FieldComparator) {
                    getPagedListHolder().setSort(null);
                    MutableSortDefinition msd = new MutableSortDefinition();
                    msd.setAscending(false);
                    getPagedListHolder().setSort(msd);
                }
            } else if ("descending".equals(sortDirection) || "natural".equals(sortDirection) || Strings.isBlank(sortDirection)) {
                final Comparator<?> cmpr = lh.getSortAscending();
                if (cmpr instanceof FieldComparator) {

                    getPagedListHolder().setSort(null);
                    MutableSortDefinition msd = new MutableSortDefinition();
                    msd.setAscending(true);
                    getPagedListHolder().setSort(msd);
                }
            }

            if (logger.isDebugEnabled()) {
                logger.debug("--> : " + lh.getId() + "/" + sortDirection);
                logger.debug("--> added  getSorts() : " + getPagedListHolder().getSort().toString());
            }

            if (isSupportPagging()) {
                // refresh the list
                getPaging().setActivePage(0);
                refreshModel(0);
            }
        }
    }

    public int getPageSize() {
        return getPaging().getPageSize();
    }

    Paging getPaging() {
        return paging;
    }

    private void setPaging(Paging paging) {
        this.paging = paging;
    }

    public PagedListHolder<E> getPagedListHolder() {
        return pagedListHolder;
    }

    public void setPagedListHolder(PagedListHolder<E> pagedListHolder) {
        this.pagedListHolder = pagedListHolder;
        initModel();
    }
}

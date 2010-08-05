package net.lintasarta.idoss.common.menu.util;

import net.lintasarta.UserWorkspace;
import net.lintasarta.idoss.common.menu.domain.IMenuDomain;
import net.lintasarta.idoss.common.menu.domain.MenuDomain;
import net.lintasarta.idoss.common.menu.domain.MetaMenuFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:13:57 PM
 */
abstract public class ZkossMenuFactory implements Serializable {
    private final Log loger = LogFactory.getLog(getClass());

    final private LinkedList<Component> stack;
    final private UserWorkspace workspace;

    protected ZkossMenuFactory(Component component) {
        super();
        this.workspace = UserWorkspace.getInstance();

        assert component != null : "Parent component is null!";
        assert this.workspace != null : "No UserWorkspace exists!";

        long t1 = System.nanoTime();

        this.stack = new LinkedList<Component>();
        push(component);

        createMenu(MetaMenuFactory.getRootMenuDomain().getItems());

        if (getLogger().isTraceEnabled()) {
            t1 = System.nanoTime() - t1;
            getLogger().trace("Needed time for inserting the menu: " + t1 / 1000000 + "ms");
            // getLogger().trace("\n" + ZkossBaumUtil.getZulBaum(component));
        }
    }

    private void createMenu(List<IMenuDomain> items) {
        if (items.isEmpty()) {
            return;
        }
        for (IMenuDomain menuDomain : items) {
            if (menuDomain instanceof MenuDomain) {
                MenuDomain menu = (MenuDomain) menuDomain;
                if (addSubMenuImpl(menu)) {
                    createMenu(menu.getItems());
                    ebeneHoch();
                }
            } else {
                addItemImpl(menuDomain);
            }
        }
    }

    private void addItemImpl(IMenuDomain itemDomain) {
        if (isAllowed(itemDomain)) {
            setAttributes(itemDomain, createItemComponent(getCurrentComponent()));
        }
    }

    abstract protected ILabelElement createItemComponent(Component parent);

    private boolean addSubMenuImpl(MenuDomain menu) {
        if (isAllowed(menu)) {
            MenuFactoryDto dto = createMenuComponent(getCurrentComponent());

            setAttributes(menu, dto.getNode());

            push(dto.getParent());

            return true;
        }
        return false;
    }

    abstract protected MenuFactoryDto createMenuComponent(Component parent);

    private boolean isAllowed(IMenuDomain treecellValue) {
        return isAllowed(treecellValue.getRightName());
    }

    public void ebeneHoch() {
        poll();
    }

    private Component getCurrentComponent() {
        return peek();
    }

    private Log getLogger() {
        return this.loger;
    }

    private UserWorkspace getWorkspace() {
        return this.workspace;
    }

    private boolean isAllowed(String rightName) {
        if (StringUtils.isEmpty(rightName)) {
            return true;
        }
        return getWorkspace().isAllowed(rightName);
    }

    private Component peek() {
        return this.stack.peek();
    }

    private Component poll() {
        try {
            return this.stack.poll();
        } finally {
            if (this.stack.isEmpty()) {
                throw new RuntimeException("Root no longer exists!");
            }
        }
    }

    private void push(Component e) {
        this.stack.push(e);
    }

    protected void setAttributes(IMenuDomain treecellValue, ILabelElement defaultTreecell) {
        if (treecellValue.isWithOnClickAction() == null || treecellValue.isWithOnClickAction().booleanValue()) {
            defaultTreecell.setZulNavigation(treecellValue.getZulNavigation());
        }

        setAttributesWithoutAction(treecellValue, defaultTreecell);
    }

    private void setAttributesWithoutAction(IMenuDomain treecellValue, ILabelElement defaultTreecell) {
        assert treecellValue.getId() != null : "In mainmenu.xml file is a node who's ID is missing!";

        defaultTreecell.setId(treecellValue.getId());
        String label = treecellValue.getLabel();
        if (StringUtils.isEmpty(label)) {
            label = Labels.getLabel(treecellValue.getId());
        } else {
            label = Labels.getLabel(label);
        }
        defaultTreecell.setLabel(" " + label);
    }
}
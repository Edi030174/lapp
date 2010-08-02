package net.lintasarta.idoss.common.menu.tree;

import net.lintasarta.idoss.common.menu.util.ILabelElement;
import net.lintasarta.idoss.common.menu.util.MenuFactoryDto;
import net.lintasarta.idoss.common.menu.util.ZkossMenuFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:13:30 PM
 */
public class ZkossTreeMenuFactory extends ZkossMenuFactory {
    public static void addMainMenu(Component component) {
        new ZkossTreeMenuFactory(component);
    }

    private ZkossTreeMenuFactory(Component component) {
        super(component);
    }

    @Override
    protected MenuFactoryDto createMenuComponent(Component parent) {
        Treeitem treeitem = new Treeitem();
        parent.appendChild(treeitem);

        ILabelElement item = insertTreeCell(treeitem);

        Treechildren treechildren = new Treechildren();
        treeitem.appendChild(treechildren);

        return new MenuFactoryDto(treechildren, item);
    }

    @Override
    protected ILabelElement createItemComponent(Component parent) {
        Treeitem treeitem = new Treeitem();
        parent.appendChild(treeitem);

        return insertTreeCell(treeitem);
    }

    private ILabelElement insertTreeCell(Component parent) {
        Treerow treerow = new Treerow();
        parent.appendChild(treerow);

        DefaultTreecell treecell = new DefaultTreecell();
        treerow.appendChild(treecell);

        return treecell;
    }
}

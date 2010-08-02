package net.lintasarta.idoss.common.menu.dropdown;

import net.lintasarta.idoss.common.menu.domain.IMenuDomain;
import net.lintasarta.idoss.common.menu.util.ILabelElement;
import net.lintasarta.idoss.common.menu.util.MenuFactoryDto;
import net.lintasarta.idoss.common.menu.util.ZkossMenuFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Menupopup;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:40:13 PM
 */
public class ZkossDropDownMenuFactory extends ZkossMenuFactory {

    public static void addDropDownMenu(Component component) {
        new ZkossDropDownMenuFactory(component);
    }

    private ZkossDropDownMenuFactory(Component component) {
        super(component);
    }

    @Override
    protected MenuFactoryDto createMenuComponent(Component parent) {
        DefaultDropDownMenu menu = new DefaultDropDownMenu();
        parent.appendChild(menu);

        Menupopup menupopup = new Menupopup();
        menu.appendChild(menupopup);

        return new MenuFactoryDto(menupopup, menu);
    }

    @Override
    protected ILabelElement createItemComponent(Component parent) {
        DefaultDropDownMenuItem item = new DefaultDropDownMenuItem();
        parent.appendChild(item);
        return item;
    }

    @Override
    protected void setAttributes(IMenuDomain treecellValue, ILabelElement defaultTreecell) {
        super.setAttributes(treecellValue, defaultTreecell);
        defaultTreecell.setImage(treecellValue.getIconName());
    }
}

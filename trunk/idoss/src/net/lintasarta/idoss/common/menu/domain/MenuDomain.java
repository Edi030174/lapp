package net.lintasarta.idoss.common.menu.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:21:09 PM
 */
public class MenuDomain extends MenuItemDomain{
    @XmlElements( { @XmlElement(name = "menu", type = MenuDomain.class), @XmlElement(name = "menuItem", type = MenuItemDomain.class) })
    public List<IMenuDomain> getItems() {
        return this.items;
    }

    public void setItems(List<IMenuDomain> items) {
        this.items = items;
    }

    private List<IMenuDomain> items = new ArrayList<IMenuDomain>();
}

package net.lintasarta.idoss.common.menu.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:18:35 PM
 */
@XmlRootElement(name = "XMLRootMenu", namespace = "http://www.daibutsu.de/zk/menu")
public class RootMenuDomain {
    private List<IMenuDomain> items;

    /**
     * @return the items
     */
    @XmlElements( { @XmlElement(name = "menu", type = MenuDomain.class), @XmlElement(name = "menuItem", type = MenuItemDomain.class) })
    public List<IMenuDomain> getItems() {
        return this.items;
    }

    /**
     * @param items
     *            the items to set
     */
    public void setItems(List<IMenuDomain> items) {
        this.items = items;
    }
}

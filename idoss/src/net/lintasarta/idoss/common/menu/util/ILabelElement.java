package net.lintasarta.idoss.common.menu.util;

import org.zkoss.zk.ui.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:23:04 PM
 */
public interface ILabelElement extends Component {
    void setZulNavigation(String zulNavigation);

    void setLabel(String string);

    void setImage(String image);
}

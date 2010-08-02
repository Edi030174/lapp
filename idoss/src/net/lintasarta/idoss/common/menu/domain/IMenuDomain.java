package net.lintasarta.idoss.common.menu.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:19:10 PM
 */
public interface IMenuDomain {
    String getRightName();

    String getId();

    String getLabel();

    Boolean isWithOnClickAction();

    String getZulNavigation();

    String getIconName();
}

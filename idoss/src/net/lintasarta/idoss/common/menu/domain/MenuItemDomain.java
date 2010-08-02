package net.lintasarta.idoss.common.menu.domain;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by IntelliJ IDEA.
 * User: Antonius
 * Date: Jul 12, 2010
 * Time: 4:19:55 PM
 */
public class MenuItemDomain implements IMenuDomain {
    private String id;

    private String zulNavigation;
    private String label;

    private String rightName = null;

    private Boolean withOnClickAction = null;

    private String iconName;

    @XmlAttribute
    public String getIconName() {
        return this.iconName;
    }

    @XmlAttribute(required = true)
    public String getId() {
        return this.id;
    }

    @XmlAttribute
    public String getLabel() {
        return this.label;
    }

    @XmlAttribute
    public String getRightName() {
        return this.rightName;
    }

    @XmlAttribute
    public String getZulNavigation() {
        return this.zulNavigation;
    }

    @XmlAttribute
    public Boolean isWithOnClickAction() {
        return this.withOnClickAction;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public void setWithOnClickAction(Boolean withOnClickAction) {
        this.withOnClickAction = withOnClickAction;
    }

    public void setZulNavigation(String zulNavigation) {
        this.zulNavigation = zulNavigation;
    }
}

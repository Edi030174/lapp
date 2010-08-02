package net.lintasarta.state.dao;

import net.lintasarta.state.model.UiObject;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 18, 2010
 * Time: 2:59:31 PM
 */
public interface UiObjectDAO {
    int getCountAllUiObject();
    List<UiObject> getAllUiObject();
}

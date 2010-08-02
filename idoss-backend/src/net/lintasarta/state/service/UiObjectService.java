package net.lintasarta.state.service;

import net.lintasarta.state.model.UiObject;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 23, 2010
 * Time: 11:22:51 AM
 */
public interface UiObjectService {
    List<UiObject> getAllUiObject();
    public UiObject getAllUiObjectByNik(String nik);
    public UiObject getUiObjectByNomorPermohonan(String nomorPermohonanid);
    void createUiObject (UiObject uiObject);
    void saveOrUpdateUiObject (UiObject uiObject);
}

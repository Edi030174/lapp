package net.lintasarta.state.dao;

import net.lintasarta.state.model.UiState;

import java.util.List;

/**
 * Created by Joshua
 * Date: Jun 18, 2010
 * Time: 4:26:24 PM
 */
public interface UiStateDAO {
    int getCountAllUiState();
    List<UiState> getAllUiState();
}

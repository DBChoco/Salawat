package io.github.dbchoco.salawat.controllers.settings.tabs;

import io.github.dbchoco.salawat.helpers.Controllers;

public class AdvancedController implements SettingsPage{
    public void initialize() throws ClassNotFoundException {
        loadSettings();
        Controllers.setAdvancedController(this);
    }
    @Override
    public void saveSettings() throws ClassNotFoundException {

    }

    @Override
    public void loadSettings() throws ClassNotFoundException {

    }
}

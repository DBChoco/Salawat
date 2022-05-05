package io.github.dbchoco.salawat.controllers.settings.tabs;

import io.github.dbchoco.salawat.app.UserSettings;
import io.github.dbchoco.salawat.helpers.Controllers;
import io.github.palexdev.materialfx.controls.MFXComboBox;

public class GeneralController implements SettingsPage{
    public MFXComboBox languageCombo;

    public void initialize(){
        loadSettings();
        Controllers.setGeneralController(this);
    }

    @Override
    public void saveSettings() {

    }

    @Override
    public void loadSettings() {

    }
}

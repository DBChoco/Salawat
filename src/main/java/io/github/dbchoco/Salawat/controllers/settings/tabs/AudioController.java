package io.github.dbchoco.Salawat.controllers.settings.tabs;

import io.github.dbchoco.Salawat.helpers.Controllers;

public class AudioController implements SettingsPage{

    public void initialize() throws ClassNotFoundException {
        loadSettings();
        Controllers.setAudioController(this);
    }
    @Override
    public void saveSettings() throws ClassNotFoundException {

    }

    @Override
    public void loadSettings() throws ClassNotFoundException {

    }
}

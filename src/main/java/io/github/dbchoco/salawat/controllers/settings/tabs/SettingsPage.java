package io.github.dbchoco.salawat.controllers.settings.tabs;

public interface SettingsPage {

    void saveSettings() throws ClassNotFoundException;
    void loadSettings() throws ClassNotFoundException;
}

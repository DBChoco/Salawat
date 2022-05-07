package io.github.dbchoco.Salawat.controllers.settings.tabs;

public interface SettingsPage {

    void saveSettings() throws ClassNotFoundException;
    void loadSettings() throws ClassNotFoundException;
}

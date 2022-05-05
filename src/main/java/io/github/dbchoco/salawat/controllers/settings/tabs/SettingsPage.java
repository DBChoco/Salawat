package io.github.dbchoco.salawat.controllers.settings.tabs;

public interface SettingsPage {

    public void saveSettings() throws ClassNotFoundException;
    public void loadSettings() throws ClassNotFoundException;
}

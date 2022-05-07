package io.github.dbchoco.Salawat.controllers.settings.tabs;

import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.ListGenerator;
import io.github.dbchoco.Salawat.helpers.ListItem;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;

public class GeneralController implements SettingsPage{
    public MFXComboBox languageCombo;
    public MFXRadioButton halfTime;
    public MFXRadioButton fullTime;
    public MFXCheckbox showSecondsCheck;
    public MFXCheckbox notificationsCheck;
    public MFXCheckbox systrayCheck;
    public MFXCheckbox autoStartCheck;
    public MFXCheckbox minStartCheck;

    public void initialize(){
        ListItem[] items = {new ListItem("english", "en"),
                new ListItem("french", "fr"),
                new ListItem("spanish", "es")};
        ListGenerator.generateList(languageCombo, items);
        loadSettings();
        Controllers.setGeneralController(this);
    }

    @Override
    public void saveSettings() {
        switch (languageCombo.getSelectedIndex()) {
            case 0 -> UserSettings.language = "en";
            case 1 -> UserSettings.language = "fr";
            case 2 -> UserSettings.language = "es";
        }
        if (halfTime.isSelected()) UserSettings.timeFormat = 12;
        else UserSettings.timeFormat = 24;
        UserSettings.showSeconds = showSecondsCheck.isSelected();
        UserSettings.notifications = notificationsCheck.isSelected();
        UserSettings.systemTray = systrayCheck.isSelected();
        UserSettings.launchStart = autoStartCheck.isSelected();
        UserSettings.launchMinimized = minStartCheck.isSelected();
    }

    @Override
    public void loadSettings() {
        switch (UserSettings.language) {
            case "en" -> languageCombo.getSelectionModel().selectIndex(0);
            case "fr" -> languageCombo.getSelectionModel().selectIndex(1);
            case "es" -> languageCombo.getSelectionModel().selectIndex(2);
        }
        halfTime.setSelected(UserSettings.timeFormat == 12);
        fullTime.setSelected(UserSettings.timeFormat == 24);
        halfTime.selectedProperty().addListener((obs, wasSelected, isNowSelected) ->
                fullTime.setSelected(!isNowSelected));
        fullTime.selectedProperty().addListener((obs, wasSelected, isNowSelected) ->
                halfTime.setSelected(!isNowSelected));

        showSecondsCheck.setSelected(UserSettings.showSeconds);
        notificationsCheck.setSelected(UserSettings.notifications);
        systrayCheck.setSelected(UserSettings.systemTray);
        autoStartCheck.setSelected(UserSettings.launchStart);
        minStartCheck.setSelected(UserSettings.launchMinimized);
    }
}

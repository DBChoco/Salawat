package io.github.dbchoco.Salawat.controllers.settings.tabs;

import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.ListGenerator;
import io.github.dbchoco.Salawat.helpers.ListItem;
import io.github.dbchoco.Salawat.helpers.ListItemArray;
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
    private ListItemArray languageItems = new ListItemArray();

    public void initialize(){
        languageItems.add(new ListItem(I18N.get("english"), "en"));
        languageItems.add(new ListItem(I18N.get("french"), "fr"));
        languageItems.add(new ListItem(I18N.get("spanish"), "es"));
        ListGenerator.generateList(languageCombo, languageItems);
        loadSettings();
        Controllers.setGeneralController(this);
    }

    @Override
    public void saveSettings() {
        UserSettings.language  = languageItems.getItembyName(languageCombo.getText()).getValue();
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
        languageCombo.getSelectionModel().selectItem(languageItems.getItembyValue(UserSettings.language));
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

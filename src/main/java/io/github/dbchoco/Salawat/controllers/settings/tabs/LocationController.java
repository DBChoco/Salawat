package io.github.dbchoco.Salawat.controllers.settings.tabs;


import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.helpers.*;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.util.TimeZone;

public class LocationController implements SettingsPage{

    public MFXTextField latField;
    public MFXTextField lonField;
    public MFXFilterComboBox timezoneCombo;

    private ListItemArray timezones = new ListItemArray();

    public void initialize() throws ClassNotFoundException {
        latField.setTextLimit(6);
        latField.delegateSetTextFormatter(new CustomTextFormatter(0, 2, true));
        lonField.setTextLimit(6);
        lonField.delegateSetTextFormatter(new CustomTextFormatter(0, 2, true));
        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            timezones.add(new ListItem(TimeZone.getTimeZone(id).getID(), TimeZone.getTimeZone(id).getID()));
        }
        ListGenerator.generateList(timezoneCombo, timezones);

        loadSettings();
        Controllers.setLocationController(this);
    }



    @Override
    public void saveSettings() throws ClassNotFoundException {
        UserSettings.latitude = Double.parseDouble(latField.getText());
        UserSettings.longitude = Double.parseDouble(lonField.getText());
        UserSettings.timezone = timezones.getItembyName(timezoneCombo.getText()).getValue();
    }

    @Override
    public void loadSettings() throws ClassNotFoundException {
        timezoneCombo.getSelectionModel().selectItem(timezones.getItembyValue(UserSettings.timezone));
        double latitude = (double) UserSettings.latitude;
        latField.setText(String.valueOf(latitude));
        double longitude = (double) UserSettings.longitude;
        lonField.setText(String.valueOf(longitude));
    }
}

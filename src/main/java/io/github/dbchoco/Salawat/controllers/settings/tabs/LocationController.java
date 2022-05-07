package io.github.dbchoco.Salawat.controllers.settings.tabs;


import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.CustomTextFormatter;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class LocationController implements SettingsPage{

    public MFXTextField latField;
    public MFXTextField lonField;

    public void initialize() throws ClassNotFoundException {
        latField.setTextLimit(6);
        latField.delegateSetTextFormatter(new CustomTextFormatter(0, 2, true));
        lonField.setTextLimit(6);
        lonField.delegateSetTextFormatter(new CustomTextFormatter(0, 2, true));
        loadSettings();
        Controllers.setLocationController(this);
    }

    @Override
    public void saveSettings() throws ClassNotFoundException {
        UserSettings.latitude = Double.parseDouble(latField.getText());
        UserSettings.longitude = Double.parseDouble(lonField.getText());
    }

    @Override
    public void loadSettings() throws ClassNotFoundException {
        double latitude = (double) UserSettings.latitude;
        latField.setText(String.valueOf(latitude));
        double longitude = (double) UserSettings.longitude;
        lonField.setText(String.valueOf(longitude));
    }
}

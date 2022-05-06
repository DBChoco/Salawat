package io.github.dbchoco.salawat.controllers.settings.tabs;


import io.github.dbchoco.salawat.app.UserSettings;
import io.github.dbchoco.salawat.helpers.Controllers;
import io.github.palexdev.materialfx.controls.MFXTextField;
public class LocationController implements SettingsPage{

    public MFXTextField latField;
    public MFXTextField lonField;

    public void initialize() throws ClassNotFoundException {
        loadSettings();
        Controllers.setLocationController(this);
        latField.setTextLimit(6);
        //latField.setTextFormatter(new CustomTextFormatter(0, 2)); //It works, but it doesn't show the correct text.
        lonField.setTextLimit(6);
        //lonField.setTextFormatter(new CustomTextFormatter(0, 2)); //It works, but it doesn't show the correct text.
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
        System.out.println(latitude + " " + longitude);
    }
}

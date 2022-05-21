package io.github.dbchoco.Salawat.controllers.settings.tabs;


import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.*;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.Label;

import java.util.TimeZone;

public class LocationController extends BaseController implements SettingsPage{

    public MFXTextField latField;
    public MFXTextField lonField;
    public MFXFilterComboBox timezoneCombo;
    public Label coordinatesLabel;
    public Label timezoneLabel;

    private final ListItemArray timezones = new ListItemArray();

    public void initialize() throws ClassNotFoundException {
        latField.setTextLimit(10);
        latField.delegateSetTextFormatter(new CustomTextFormatter(0, 7, true));
        lonField.setTextLimit(10);
        lonField.delegateSetTextFormatter(new CustomTextFormatter(0, 7, true));

        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            timezones.add(new ListItem(TimeZone.getTimeZone(id).getID(), TimeZone.getTimeZone(id).getID()));
        }
        ListGenerator.generateList(timezoneCombo, timezones);

        loadSettings();
        Controllers.setLocationController(this);
        translate();

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
        double latitude = UserSettings.latitude;
        latField.setText(String.valueOf(latitude));
        double longitude = UserSettings.longitude;
        lonField.setText(String.valueOf(longitude));
    }

    @Override
    protected void translate() {
        I18N.bindString(coordinatesLabel, "coordinates");
        I18N.bindString(timezoneLabel, "timezone");

        I18N.bindString(latField, "latitude");
        I18N.bindString(lonField, "longitude");
        I18N.bindString(timezoneCombo, "timezone");
    }

    @Override
    protected void makeResizable() {

    }
}

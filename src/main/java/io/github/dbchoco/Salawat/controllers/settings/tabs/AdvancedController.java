package io.github.dbchoco.Salawat.controllers.settings.tabs;

import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.helpers.*;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class AdvancedController implements SettingsPage{
    public MFXComboBox calcMethodCombo;
    public MFXComboBox madhabCombo;
    public MFXComboBox hlrCombo;
    public MFXComboBox shafaqCombo;
    public MFXCheckbox delayCheck;
    public MFXTextField delayInput;
    public MFXCheckbox customSettingsCheck;
    public MFXTextField fajrAngle;
    public MFXTextField ishaAngle;

    private ListItemArray calcMethodItems = new ListItemArray();
    private ListItemArray madhabItems = new ListItemArray();
    private ListItemArray hlrItems = new ListItemArray();

    public void initialize() throws ClassNotFoundException {
        addListItems();
        delayInput.setTextLimit(4);
        TextFormatter<Integer> formatter = new TextFormatter<>(
                new IntegerStringConverter(), // Standard converter form JavaFX
                0,
                new IntegerFilter());
        delayInput.delegateSetTextFormatter(formatter);
        fajrAngle.setTextLimit(4);
        fajrAngle.delegateSetTextFormatter(new CustomTextFormatter(0, 1, false));
        ishaAngle.setTextLimit(4);
        ishaAngle.delegateSetTextFormatter(new CustomTextFormatter(0, 1, false));

        loadSettings();
        Controllers.setAdvancedController(this);
    }
    @Override
    public void saveSettings() {
        UserSettings.calculationMethod = calcMethodItems.getItembyName(calcMethodCombo.getText()).getValue();
        UserSettings.madhab = madhabItems.getItembyName(madhabCombo.getText()).getValue();
        UserSettings.highLatitudeRule = hlrItems.getItembyName(hlrCombo.getText()).getValue();
        UserSettings.enableIshaDelay = delayCheck.isSelected();
        UserSettings.ishaDelay = Integer.parseInt(delayInput.getText());
        UserSettings.customCalculationSettings = customSettingsCheck.isSelected();
        UserSettings.fajrAngle = Double.parseDouble(fajrAngle.getText());
        UserSettings.ishaAngle = Double.parseDouble(ishaAngle.getText());
    }

    @Override
    public void loadSettings() {
        calcMethodCombo.getSelectionModel().selectItem(calcMethodItems.getItembyValue(UserSettings.calculationMethod));
        madhabCombo.getSelectionModel().selectItem(madhabItems.getItembyValue(UserSettings.madhab));
        hlrCombo.getSelectionModel().selectItem(hlrItems.getItembyValue(UserSettings.highLatitudeRule));

        delayCheck.setSelected(UserSettings.enableIshaDelay);
        delayInput.disableProperty().bind(delayCheck.selectedProperty().not());
        delayInput.setText(String.valueOf(UserSettings.ishaDelay));

        customSettingsCheck.setSelected(UserSettings.customCalculationSettings);
        fajrAngle.disableProperty().bind(customSettingsCheck.selectedProperty().not());
        ishaAngle.disableProperty().bind(customSettingsCheck.selectedProperty().not());
        fajrAngle.setText(String.valueOf(UserSettings.fajrAngle));
        ishaAngle.setText(String.valueOf(UserSettings.ishaAngle));
    }

    private void addListItems(){
        calcMethodItems.add(new ListItem(I18N.get("MWL"), "MWL"));
        calcMethodItems.add(new ListItem(I18N.get("ISNA"), "ISNA"));
        calcMethodItems.add(new ListItem(I18N.get("egyptian"), "egyptian"));
        calcMethodItems.add(new ListItem(I18N.get("turkey"), "turkey"));
        calcMethodItems.add(new ListItem(I18N.get("karachi"), "karachi"));
        calcMethodItems.add(new ListItem(I18N.get("UAQ"), "UAQ"));
        calcMethodItems.add(new ListItem(I18N.get("dubai"), "dubai"));
        calcMethodItems.add(new ListItem(I18N.get("kuwait"), "kuwait"));
        calcMethodItems.add(new ListItem(I18N.get("MC"), "MC"));
        calcMethodItems.add(new ListItem(I18N.get("singapore"), "singapore"));
        calcMethodItems.add(new ListItem(I18N.get("france12"), "france12"));
        calcMethodItems.add(new ListItem(I18N.get("france15"), "france15"));
        calcMethodItems.add(new ListItem(I18N.get("france18"), "france18"));
        calcMethodItems.add(new ListItem(I18N.get("russia"), "russia"));
        calcMethodItems.add(new ListItem(I18N.get("gulf"), "gulf"));
        madhabItems.add(new ListItem(I18N.get("shafi"), "shafi"));
        madhabItems.add(new ListItem(I18N.get("hanafi"), "hanafi"));
        hlrItems.add(new ListItem(I18N.get("MOTN"), "MOTN"));
        hlrItems.add(new ListItem(I18N.get("SOTN"), "SOTN"));
        hlrItems.add(new ListItem(I18N.get("TA"), "TA"));

        ListGenerator.generateList(calcMethodCombo, calcMethodItems);
        ListGenerator.generateList(madhabCombo, madhabItems);
        ListGenerator.generateList(hlrCombo, hlrItems);
    }
}

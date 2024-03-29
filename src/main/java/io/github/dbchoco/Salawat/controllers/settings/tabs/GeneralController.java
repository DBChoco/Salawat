package io.github.dbchoco.Salawat.controllers.settings.tabs;

import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.*;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Locale;

public class GeneralController extends BaseController implements SettingsPage{
    public MFXComboBox languageCombo;
    public MFXRadioButton halfTime;
    public MFXRadioButton fullTime;
    public MFXCheckbox showSecondsCheck;
    public MFXCheckbox notificationsCheck;
    public MFXCheckbox systrayCheck;
    public MFXCheckbox minStartCheck;
    public AnchorPane root;
    public Label languageLabel;
    public Label timeformatLabel;
    public Label notificationsLabel;
    public Label systrayLabel;
    private final ListItemArray languageItems = new ListItemArray();
    public Label dateFormatLabel;
    public MFXRadioButton ddmmyyyy;
    public MFXRadioButton mmddyyyy;
    public MFXRadioButton yyyymmdd;

    public void initialize(){
        languageItems.add(new ListItem(I18N.get("english"), "en"));
        languageItems.add(new ListItem(I18N.get("french"), "fr"));
        languageItems.add(new ListItem(I18N.get("spanish"), "es"));
        languageItems.add(new ListItem(I18N.get("arabic"), "ar"));
        languageItems.add(new ListItem(I18N.get("turkish"), "tr"));
        languageItems.add(new ListItem(I18N.get("russian"), "ru"));
        ListGenerator.generateList(languageCombo, languageItems);
        loadSettings();
        Controllers.setGeneralController(this);
        setupLanguageListener();
        translate();
    }

    private void setupLanguageListener() {
        languageCombo.valueProperty().addListener((observableValue, o, t1) -> {
            I18N.setLocale(Locale.forLanguageTag(((ListItem) t1).getValue()));
            if (o != null && (((ListItem) o).getValue().equals("ar")) || ((ListItem) t1).getValue().equals("ar")){
                UserSettings.language  = ((ListItem) t1).getValue();
                FontBinder.reloadFonts();
            }
        });
    }

    @Override
    public void saveSettings() {
        UserSettings.language  = languageItems.getItembyName(languageCombo.getText()).getValue();
        if (halfTime.isSelected()) UserSettings.timeFormat = 12;
        else UserSettings.timeFormat = 24;
        UserSettings.showSeconds = showSecondsCheck.isSelected();
        UserSettings.notifications = notificationsCheck.isSelected();
        UserSettings.systemTray = systrayCheck.isSelected();
        UserSettings.launchMinimized = minStartCheck.isSelected();

        if (ddmmyyyy.isSelected()) UserSettings.dateformat = "ddmmyyyy";
        else if (mmddyyyy.isSelected()) UserSettings.dateformat = "mmddyyyy";
        else UserSettings.dateformat = "yyyymmdd";
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
        minStartCheck.setSelected(UserSettings.launchMinimized);

        ddmmyyyy.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1){
                mmddyyyy.setSelected(false);
                yyyymmdd.setSelected(false);
            }
        });
        mmddyyyy.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1) {
                ddmmyyyy.setSelected(false);
                yyyymmdd.setSelected(false);
            }
        });
        yyyymmdd.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1) {
                mmddyyyy.setSelected(false);
                ddmmyyyy.setSelected(false);
            }
        });
        if (UserSettings.dateformat.equals("ddmmyyyy")) ddmmyyyy.setSelected(true);
        else if (UserSettings.dateformat.equals("mmddyyyy")) mmddyyyy.setSelected(true);
        else yyyymmdd.setSelected(true);
    }

    @Override
    protected void translate() {
        I18N.bindString(languageLabel, "language");
        I18N.bindString(timeformatLabel, "timeformat");
        I18N.bindString(notificationsLabel, "notifications");
        I18N.bindString(systrayLabel, "systray");

        I18N.bindString(languageCombo, "language");
        I18N.bindString(halfTime, "12hour");
        I18N.bindString(fullTime, "24hour");
        I18N.bindString(showSecondsCheck, "showSeconds");
        I18N.bindString(notificationsCheck, "notifCheck");
        I18N.bindString(systrayCheck, "systrayCheck");
        I18N.bindString(minStartCheck, "minStart");

        I18N.bindString(dateFormatLabel, "dateformat");
        I18N.bindString(ddmmyyyy, "ddmmyyyy");
        I18N.bindString(mmddyyyy, "mmddyyyy");
        I18N.bindString(yyyymmdd, "yyyymmdd");
    }

    @Override
    protected void makeResizable() {

    }
}

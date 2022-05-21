package io.github.dbchoco.Salawat.controllers.settings.tabs;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class AudioController extends BaseController implements SettingsPage{
    public MFXCheckbox adhanCheck;
    public MFXComboBox adhanCombo;
    public MFXCheckbox customAdhanCheck;
    public MFXButton customAdhanButton;
    public MFXCheckbox customFajrAdhanCheck;
    public MFXButton customFajrAdhanButton;
    public MFXCheckbox duaCheck;
    public MFXCheckbox startupSoundCheck;
    public Label adhanLabel;
    public Label statupSoundLabel;
    public Label duaAfterAdhanLabel;

    private final ListItemArray adhanItems = new ListItemArray();

    public void initialize() throws ClassNotFoundException {
        addListItems();
        translate();
        setupFilePickers();
        Controllers.setAudioController(this);
        loadSettings();
    }


    @Override
    public void saveSettings() throws ClassNotFoundException {
        UserSettings.enableAdhan = adhanCheck.isSelected();
        if (adhanCheck.isSelected()){
            if (!customAdhanCheck.isSelected()){
                try {
                    UserSettings.adhanPath = adhanItems.getItembyName(adhanCombo.getText()).getValue();
                } catch (NullPointerException nullPointerException){
                    UserSettings.adhanPath = Main.class.getResource("audio/Adhan - Ahmed Al-Nufais.mp3").toExternalForm();
                }
            }
            else {
                UserSettings.adhanPath = Main.class.getResource("audio/Adhan - Ahmed Al-Nufais.mp3").toExternalForm();
            }
            UserSettings.customAdhan = customAdhanCheck.isSelected();
            UserSettings.customFajrAdhan = customFajrAdhanCheck.isSelected();
        }
        else {
            UserSettings.adhanPath = Main.class.getResource("audio/Adhan - Ahmed Al-Nufais.mp3").toExternalForm();
            UserSettings.customAdhan = false;
            UserSettings.customFajrAdhan = false;
        }
        UserSettings.dua = duaCheck.isSelected();
        UserSettings.startupSound = startupSoundCheck.isSelected();
    }

    @Override
    public void loadSettings() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                adhanCombo.disableProperty().bind(adhanCheck.selectedProperty().not().or(customAdhanCheck.selectedProperty()));
                customAdhanCheck.disableProperty().bind(adhanCheck.selectedProperty().not());
                customAdhanButton.disableProperty().bind(adhanCheck.selectedProperty().not().or(customAdhanCheck.selectedProperty().not()));
                customFajrAdhanCheck.disableProperty().bind(adhanCheck.selectedProperty().not());
                customFajrAdhanButton.disableProperty().bind(adhanCheck.selectedProperty().not().or(customFajrAdhanCheck.selectedProperty().not()));

                adhanCheck.setSelected(UserSettings.enableAdhan);
                if (adhanCheck.isSelected()){
                    customAdhanCheck.setSelected(UserSettings.customAdhan);
                    customFajrAdhanCheck.setSelected(UserSettings.customFajrAdhan);
                    if (UserSettings.customAdhan){
                        customAdhanButton.setText(StringShortener.shortenString(UserSettings.customAdhanPath, 25));
                    }
                    else {
                        if (adhanItems.getItembyValue(UserSettings.adhanPath) != null){
                            adhanCombo.getSelectionModel().selectItem(adhanItems.getItembyValue(UserSettings.adhanPath));
                        }
                        else {
                            adhanCombo.getSelectionModel().selectIndex(0);
                        }
                    }
                    if (UserSettings.customFajrAdhan){
                        customFajrAdhanButton.setText(StringShortener.shortenString(UserSettings.customFajrAdhanPath, 25));
                    }
                }
                duaCheck.setSelected(UserSettings.dua);
                startupSoundCheck.setSelected(UserSettings.startupSound);
            }
        });
    }

    private void addListItems() throws ClassNotFoundException {
        adhanItems.add(new ListItem(I18N.get("adhanAhmed"), Main.class.getResource("audio/Adhan - Ahmed Al-Nufais.mp3").toExternalForm()));
        adhanItems.add(new ListItem(I18N.get("adhanMecca"), Main.class.getResource("audio/Adhan - Mecca.mp3").toExternalForm()));
        adhanItems.add(new ListItem(I18N.get("adhanAqsa"), Main.class.getResource("audio/Adhan - al-Aqsa.mp3").toExternalForm()));
        ListGenerator.generateList(adhanCombo, adhanItems);
    }

    private void setupFilePickers() {
        customAdhanButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose custom Adhan audio");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Audio", "*.mp3", "*.wav", "*.acc", "*.flac"),
                        new FileChooser.ExtensionFilter("MP3", "*.mp3"),
                        new FileChooser.ExtensionFilter("WAV", "*.wav")
                );
                File file = fileChooser.showOpenDialog(StageController.getStage());
                if (file != null) {
                    customAdhanButton.setText(StringShortener.shortenString(file.getName(), 25));
                    UserSettings.customAdhanPath = file.toURI().toString();
                }
            }
        });
        customFajrAdhanButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose custom Fajr Adhan audio");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Audio", "*.mp3", "*.wav", "*.acc", "*.flac"),
                        new FileChooser.ExtensionFilter("MP3", "*.mp3"),
                        new FileChooser.ExtensionFilter("WAV", "*.wav")
                );
                File file = fileChooser.showOpenDialog(StageController.getStage());
                if (file != null) {
                    customFajrAdhanButton.setText(StringShortener.shortenString(file.getName(), 25));
                    UserSettings.customFajrAdhanPath = file.toURI().toString();
                }
            }
        });
    }

    @Override
    protected void translate() {
        I18N.bindString(adhanLabel, "adhan");
        I18N.bindString(duaAfterAdhanLabel, "duaAfterAdhan");
        I18N.bindString(statupSoundLabel, "startUpSound");

        I18N.bindString(adhanCheck, "adhanCheck");
        I18N.bindString(adhanCombo, "adhan");
        I18N.bindString(customAdhanCheck, "customAdhan");
        customAdhanButton.setText(I18N.get("selectFile"));
        I18N.bindString(customFajrAdhanCheck, "customFajrAdhan");
        customFajrAdhanButton.setText(I18N.get("selectFile"));
        I18N.bindString(duaCheck, "playDua");
        I18N.bindString(startupSoundCheck, "playSound");
    }

    @Override
    protected void makeResizable() {

    }
}

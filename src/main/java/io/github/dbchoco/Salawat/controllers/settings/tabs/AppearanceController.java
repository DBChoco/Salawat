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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class AppearanceController extends BaseController implements SettingsPage{

    public MFXButton bgImageButton;
    public MFXCheckbox bgImageCheck;
    public MFXCheckbox darkmodeCheck;
    public Label themeLabel;
    public Label backgroundImageLabel;
    public Label weatherLabel;
    public MFXCheckbox weatherCheck;
    public MFXComboBox unitCombo;
    private ListItemArray weatherUnits = new ListItemArray();

    public void initialize() throws ClassNotFoundException {
        weatherUnits.add(new ListItem(I18N.get("celsius"), "C"));
        weatherUnits.add(new ListItem(I18N.get("fahrenheit"), "F"));
        weatherUnits.add(new ListItem(I18N.get("kelvin"), "K"));
        ListGenerator.generateList(unitCombo, weatherUnits);
        translate();
        setupFilePickers();
        setupThemeListener();
        loadSettings();
        Controllers.setAppearanceController(this);
    }

    @Override
    public void saveSettings() {
        UserSettings.darkMode = darkmodeCheck.isSelected();
        UserSettings.bgImage = bgImageCheck.isSelected();

        if (!bgImageCheck.isSelected()){
            UserSettings.bgImagePath = Main.class.getResource("images/bgImage.jpg").toExternalForm();
        }

        if (UserSettings.darkMode && UserSettings.bgImagePath.equals(Main.class.getResource("images/bgImage.jpg").toExternalForm())){
            UserSettings.bgImagePath = Main.class.getResource("images/bgImage_dark.jpg").toExternalForm();
        }
        else if (!UserSettings.darkMode && UserSettings.bgImagePath.equals(Main.class.getResource("images/bgImage_dark.jpg").toExternalForm())){
            UserSettings.bgImagePath = Main.class.getResource("images/bgImage.jpg").toExternalForm();
        }

        UserSettings.showWeather = weatherCheck.isSelected();
        UserSettings.weatherUnit  = weatherUnits.getItembyName(unitCombo.getText()).getValue();
    }

    @Override
    public void loadSettings() {
        bgImageButton.disableProperty().bind(bgImageCheck.selectedProperty().not());

        darkmodeCheck.setSelected(UserSettings.darkMode);
        bgImageCheck.setSelected(UserSettings.bgImage);
        bgImageButton.setText(StringShortener.shortenString(UserSettings.bgImagePath, 25));

        weatherCheck.setSelected(UserSettings.showWeather);
        System.out.println(unitCombo.getSelectionModel().getSelectedItem());
        unitCombo.getSelectionModel().selectItem(weatherUnits.getItembyValue(UserSettings.weatherUnit));
    }

    private void setupFilePickers() {
        bgImageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose background image");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg", "*.aviv"),
                        new FileChooser.ExtensionFilter("PNG", "*.png"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg")
                );
                File file = fileChooser.showOpenDialog(StageController.getStage());
                if (file != null) {
                    bgImageButton.setText(StringShortener.shortenString(file.getName(), 25));
                    UserSettings.bgImagePath = file.toURI().toString();
                }
            }
        });
    }

    private void setupThemeListener() {
        darkmodeCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                StageController.changeTheme(StageController.getSettingsScene(), t1);
                Controllers.getSettingsController().loadBackground(t1);
            }
        });
    }

    @Override
    protected void translate() {
        I18N.bindString(themeLabel, "theme");
        I18N.bindString(backgroundImageLabel, "bgImage");

        I18N.bindString(darkmodeCheck, "darkMode");
        I18N.bindString(bgImageCheck, "bgImageCheck");
        bgImageButton.setText(I18N.get("selectFile"));

        I18N.bindString(weatherLabel, "weather");
        I18N.bindString(weatherCheck, "showWeather");
        I18N.bindString(unitCombo, "unit");
    }

    @Override
    protected void makeResizable() {

    }
}

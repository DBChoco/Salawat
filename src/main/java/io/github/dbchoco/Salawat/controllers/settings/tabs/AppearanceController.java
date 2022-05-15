package io.github.dbchoco.Salawat.controllers.settings.tabs;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.StageController;
import io.github.dbchoco.Salawat.helpers.StringShortener;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class AppearanceController implements SettingsPage{

    public MFXButton bgImageButton;
    public MFXCheckbox bgImageCheck;
    public MFXCheckbox darkmodeCheck;

    public void initialize() throws ClassNotFoundException {
        setupFilePickers();
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
    }

    @Override
    public void loadSettings() {
        bgImageButton.disableProperty().bind(bgImageCheck.selectedProperty().not());

        darkmodeCheck.setSelected(UserSettings.darkMode);
        bgImageCheck.setSelected(UserSettings.bgImage);
        bgImageButton.setText(StringShortener.shortenString(UserSettings.bgImagePath, 25));
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
}

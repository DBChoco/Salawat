package io.github.dbchoco.salawat.controllers.settings;

import io.github.dbchoco.salawat.Main;
import io.github.dbchoco.salawat.app.UserSettings;
import io.github.dbchoco.salawat.helpers.Controllers;
import io.github.dbchoco.salawat.helpers.StageController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

public class FooterController {
    public MFXButton returnButton;

    public void initialize() {
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                try {
                    Controllers.getSettingsPage(SideBarController.currentTab).saveSettings();
                    UserSettings.saveAllSettings();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Main.reload();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/main.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 1280, 720);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                URL cssURL = Main.class.getResource("css/main.css");
                assert cssURL != null;
                scene.getStylesheets().add(cssURL.toExternalForm());
                StageController.setCurrentScene(scene);
            }
        });
    }
}

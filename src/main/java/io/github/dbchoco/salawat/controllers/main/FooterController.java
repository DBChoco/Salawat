package io.github.dbchoco.salawat.controllers.main;

import io.github.dbchoco.salawat.Main;
import io.github.dbchoco.salawat.helpers.StageController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

public class FooterController {
    public MFXButton settingsButton;

    public void initialize(){
        settingsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/settings.fxml"));
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

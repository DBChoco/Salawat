package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.AudioPlayer;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.StageController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

public class FooterController {
    public MFXButton settingsButton;
    public MFXButton stopButton;
    public MFXButton playButton;
    public MFXSlider volumeSlider;

    public void initialize(){
        Controllers.setMainFooterController(this);
        loadHandlers();
        loadVolume();
        loadListeners();
    }

    private void loadVolume(){
        volumeSlider.setValue(UserSettings.volume * 100);
    }

    private void loadHandlers(){
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

        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                AudioPlayer.play(false);
            }
        });

        stopButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                AudioPlayer.stop();
            }
        });
    }

    private void loadListeners(){
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                UserSettings.volume = (double)number/100.00;
            }
        });
    }
}

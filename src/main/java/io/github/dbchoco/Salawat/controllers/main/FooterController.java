package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.AudioPlayer;
import io.github.dbchoco.Salawat.app.TrayMenu;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import io.github.dbchoco.Salawat.helpers.StageController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;

public class FooterController extends BaseController {
    public MFXButton settingsButton;
    public MFXButton stopButton;
    public MFXButton playButton;
    public MFXSlider volumeSlider;
    public AnchorPane root;
    public HBox hbox;
    public HBox volumeBox;
    public HBox buttonBox;

    public void initialize(){
        Controllers.setMainFooterController(this);
        loadHandlers();
        loadVolume();
        loadListeners();
        makeResizable();
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

    @Override
    protected void translate() {

    }

    @Override
    protected void makeResizable() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                SizeBinder.bindSize(root, 1280, 50);
                SizeBinder.bindSize(hbox, 1280, 50);
                SizeBinder.bindSize(volumeBox, 640, 50);
                SizeBinder.bindSize(buttonBox, 640, 50);
            }
        });
    }
}
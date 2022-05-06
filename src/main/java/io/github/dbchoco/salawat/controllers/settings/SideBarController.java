package io.github.dbchoco.salawat.controllers.settings;

import io.github.dbchoco.salawat.Main;
import io.github.dbchoco.salawat.helpers.Controllers;
import io.github.dbchoco.salawat.helpers.StageController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

public class SideBarController {
    public static String currentTab = "general";
    public MFXButton generalButton;
    public MFXButton locationButton;
    public MFXButton audioButton;
    public MFXButton appearanceButton;
    public MFXButton advancedButton;

    public void initialize(){
        currentTab = "general";

        setupButtonHandler(generalButton, "general");
        setupButtonHandler(locationButton, "location");
        setupButtonHandler(audioButton, "audio");
        setupButtonHandler(appearanceButton, "appearance");
        setupButtonHandler(advancedButton, "advanced");
    }

    private void setupButtonHandler(MFXButton button, String title){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    showTab(title);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void showTab(String tab) throws IOException, ClassNotFoundException {
        if (currentTab != tab){
            Controllers.getSettingsPage(currentTab).saveSettings();
            Controllers.getSettingsController().mainSettings.getChildren().remove(0);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/settings/tabs/" + tab + ".fxml"));
            Controllers.getSettingsController().mainSettings.getChildren().add(fxmlLoader.load());
            currentTab = tab;
        }
    }
}

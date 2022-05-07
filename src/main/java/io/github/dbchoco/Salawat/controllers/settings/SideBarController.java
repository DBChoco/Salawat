package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SideBarController extends BaseController {
    public static String currentTab = "general";
    public MFXButton generalButton;
    public MFXButton locationButton;
    public MFXButton audioButton;
    public MFXButton appearanceButton;
    public MFXButton advancedButton;

    public void initialize(){
        translate();
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

    @Override
    protected void translate() {
        generalButton.textProperty().bind(I18N.createStringBinding("general"));
        locationButton.textProperty().bind(I18N.createStringBinding("location"));
        audioButton.textProperty().bind(I18N.createStringBinding("audio"));
        appearanceButton.textProperty().bind(I18N.createStringBinding("appearance"));
        advancedButton.textProperty().bind(I18N.createStringBinding("advanced"));
    }
}

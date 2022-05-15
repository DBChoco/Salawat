package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SideBarController extends BaseController{
    public static String currentTab = "general";
    public MFXButton generalButton;
    public MFXButton locationButton;
    public MFXButton audioButton;
    public MFXButton appearanceButton;
    public MFXButton advancedButton;
    public VBox vbox;
    public AnchorPane root;

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
        I18N.bindString(generalButton, "general");
        I18N.bindString(locationButton, "location");
        I18N.bindString(audioButton, "audio");
        I18N.bindString(appearanceButton, "appearance");
        I18N.bindString(advancedButton, "advanced");
    }

    @Override
    protected void makeResizable() {

    }
}

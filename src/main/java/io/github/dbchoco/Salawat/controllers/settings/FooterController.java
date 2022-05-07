package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.StageController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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


                StageController.setCurrentScene(StageController.getMainScene());
            }
        });
    }
}

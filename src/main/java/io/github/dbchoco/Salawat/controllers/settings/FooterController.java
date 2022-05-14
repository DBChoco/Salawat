package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import io.github.dbchoco.Salawat.helpers.StageController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class FooterController extends BaseController {
    public MFXButton returnButton;
    public AnchorPane root;
    public HBox hbox;

    public void initialize() {
        
        makeResizable();
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

    @Override
    protected void translate() {

    }

    @Override
    protected void makeResizable() {
        SizeBinder.bindSize(root, 1280, 50, "settings");
        SizeBinder.bindSize(hbox, 1280, 50, "settings");
    }
}

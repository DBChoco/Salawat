package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.helpers.Controllers;
import javafx.scene.layout.FlowPane;

public class SettingsController {
    public FlowPane mainSettings;

    public void initialize(){
        Controllers.setSettingsController(this);
    }
}

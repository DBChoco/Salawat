package io.github.dbchoco.salawat.controllers.settings;

import io.github.dbchoco.salawat.helpers.Controllers;
import javafx.scene.layout.FlowPane;

public class SettingsController {
    public FlowPane mainSettings;

    public void initialize(){
        Controllers.setSettingsController(this);
    }
}

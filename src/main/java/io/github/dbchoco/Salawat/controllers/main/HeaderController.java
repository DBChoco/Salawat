package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.helpers.FontBinder;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class HeaderController {
    @FXML
    private Label title;
    public void initialize(){
        FontBinder.bindFontSize(title, "smaller");
    }
}

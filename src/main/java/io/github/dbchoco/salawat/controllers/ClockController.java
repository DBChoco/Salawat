package io.github.dbchoco.salawat.controllers;

import io.github.dbchoco.salawat.helpers.Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.Timer;
import java.util.TimerTask;

public class ClockController {
    @FXML
    public Label clock;
    @FXML
    public Label date;
    @FXML
    public Label timeLeft;

    public void initialize(){
        Controllers.setClockController(this);
    }
}

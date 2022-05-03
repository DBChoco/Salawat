package io.github.dbchoco.salawat.controllers;

import io.github.dbchoco.salawat.helpers.Controllers;
import javafx.scene.control.Label;
import java.util.Timer;
import java.util.TimerTask;

public class ClockController {

    public Label clock;
    public void initialize(){
        Controllers.setClockController(this);
    }
}

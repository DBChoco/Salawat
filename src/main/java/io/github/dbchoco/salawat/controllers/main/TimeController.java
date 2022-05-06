package io.github.dbchoco.salawat.controllers.main;

import io.github.dbchoco.salawat.Main;
import io.github.dbchoco.salawat.helpers.Controllers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeController {
    @FXML
    public Label clock;
    @FXML
    public Label date;
    @FXML
    public Label timeLeft;

    public void initialize(){
        Controllers.setTimeController(this);
        displayTimeLeft();
        displayGregorianDate();
    }

    public void displayGregorianDate(){
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        date.setText(formatter.format(now));
    }

    public void displayTimeLeft(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        clock.setText(formatter.format(date));
        timeLeft.setText("Time\suntil " +
                Main.getPrayerTimesCalculator().getNextPrayer().getName() +
                ": " +  Main.getPrayerTimesCalculator().timeUntilNextPrayer().toString());
    }
}

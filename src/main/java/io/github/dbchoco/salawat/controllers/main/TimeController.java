package io.github.dbchoco.salawat.controllers.main;

import io.github.dbchoco.salawat.Main;
import io.github.dbchoco.salawat.app.Displayer;
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
        loadTimer();
        loadDate();
    }

    private void loadDate(){
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        date.setText(formatter.format(now));
    }

    private void loadTimer(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                        clock.setText(formatter.format(date));
                        timeLeft.setText("Time until " +
                                Main.getPrayerTimesCalculator().getNextPrayer().getName() +
                                ": " +  Main.getPrayerTimesCalculator().timeUntilNextPrayer().toString());
                    }
                });
            }
        }, 0, 1000);
    }
}

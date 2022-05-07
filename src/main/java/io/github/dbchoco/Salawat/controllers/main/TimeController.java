package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.FormatLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat formatter = (new FormatLoader()).getTimeFormatter();
        clock.setText(formatter.format(date));
        timeLeft.setText("Time\suntil " +
                Main.getPrayerTimesCalculator().getNextPrayer().getName() +
                ": " +  Main.getPrayerTimesCalculator().timeUntilNextPrayer().toString());
    }
}

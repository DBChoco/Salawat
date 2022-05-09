package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.FormatLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class TimeController {
    @FXML
    public Label clock;
    @FXML
    public Label date;
    @FXML
    public Label timeLeft;

    private Boolean hijri = false;

    public void initialize(){
        Controllers.setTimeController(this);
        displayTimeLeft();
        displayGregorianDate();
    }

    public void displayGregorianDate(){
        /*Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        date.setText(formatter.format(now));*/

        if (hijri){
            Date now = new Date();
            DateFormat formatter = new SimpleDateFormat("EEEE, d MMMM y", I18N.getLocale());
            String stringDate = formatter.format(now);
            stringDate = stringDate.substring(0, 1).toUpperCase() + stringDate.substring(1);
            date.setText(stringDate);
        }
        else {
            HijrahDate hijriDate = HijrahDate.now();
            String stringDate = DateTimeFormatter.ofPattern("EEEE, d MMMM y",I18N.getLocale()).format(hijriDate);
            stringDate = stringDate.substring(0, 1).toUpperCase() + stringDate.substring(1);
            date.setText(stringDate);
        }
        hijri = !hijri;
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

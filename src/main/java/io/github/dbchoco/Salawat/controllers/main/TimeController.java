package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.FormatLoader;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeController extends BaseController{
    @FXML
    public Label clock;
    @FXML
    public Label date;
    @FXML
    public Label timeLeft;
    public GridPane grid;

    private Boolean hijri = false;

    public void initialize(){
        Controllers.setTimeController(this);
        displayTimeLeft();
        displayGregorianDate();
        makeResizable();
    }

    public void displayGregorianDate(){
        /*Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        date.setText(formatter.format(now));*/

        if (hijri){
            Date now = new Date();
            DateFormat formatter = new SimpleDateFormat("EEEE, d MMMM y", I18N.getLocale());
            String stringDate = formatter.format(now);
            formatter.setTimeZone(TimeZone.getTimeZone(UserSettings.timezone));
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

    @Override
    protected void translate() {

    }

    @Override
    protected void makeResizable() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                SizeBinder.bindSize(grid, 1280, 300, "main");
                SizeBinder.bindFontSize(clock, "larger");
                SizeBinder.bindFontSize(date, "small");
                SizeBinder.bindFontSize(timeLeft, "large");
            }
        });
    }
}

package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.FontBinder;
import io.github.dbchoco.Salawat.helpers.FormatLoader;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    String format;

    public void initialize(){
        Controllers.setTimeController(this);
        displayTimeLeft("now");
        loadFormat();
        displayGregorianDate();
        makeResizable();
        loadFormat();
    }

    public void loadFormat(){
        if (UserSettings.dateformat.equals("ddmmyyyy")) format = "EEEE, d MMMM y";
        else if (UserSettings.dateformat.equals("mmddyyyy")) format = "EEEE, MMMM d, y";
        else format = "EEEE, y MMMM d";
    }

    public void displayGregorianDate(){
        if (hijri){
            Date now = new Date();
            DateFormat formatter = new SimpleDateFormat(format, I18N.getLocale());
            String stringDate = formatter.format(now);
            formatter.setTimeZone(TimeZone.getTimeZone(UserSettings.timezone));
            stringDate = stringDate.substring(0, 1).toUpperCase() + stringDate.substring(1);
            date.setText(stringDate);
        }
        else {
            HijrahDate hijriDate = HijrahDate.now();
            String stringDate = DateTimeFormatter.ofPattern(format,I18N.getLocale()).format(hijriDate);
            stringDate = stringDate.substring(0, 1).toUpperCase() + stringDate.substring(1);
            date.setText(stringDate);
        }
        hijri = !hijri;
    }

    public void displayTimeLeft(String state){
        Date date = new Date();
        SimpleDateFormat formatter = (new FormatLoader()).getTimeFormatter();
        clock.setText(formatter.format(date));

        switch (state) {
            case "default" -> timeLeft.setText(I18N.get("timeUntil").replace("%prayer",
                    Main.getPrayerTimesCalculator().getNextPrayer().getI18Name()) + " " +
                    Main.getPrayerTimesCalculator().timeUntilNextPrayer().toString());
            case "now" -> timeLeft.setText(I18N.get("now").replace("%prayer",
                    Main.getPrayerTimesCalculator().getCurrentPrayer().getI18Name()));
            case "adhan" -> timeLeft.setText(I18N.get("adhan"));
        }
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
                FontBinder.bindFontSize(clock, "larger");
                FontBinder.bindFontSize(date, "small");
                FontBinder.bindFontSize(timeLeft, "large");
            }
        });
    }
}

package io.github.dbchoco.salawat.controllers;

import com.batoulapps.adhan.PrayerTimes;
import io.github.dbchoco.salawat.helpers.Controllers;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;

public class PrayerGridController {
    public Label fajrText;
    public Label fajrTime;
    public Label sunriseText;
    public Label sunriseTime;
    public Label dhuhrText;
    public Label dhuhrTime;
    public Label asrText;
    public Label asrTime;
    public Label maghribText;
    public Label maghribTime;
    public Label ishaText;
    public Label ishaTime;

    public void setPrayerTimes(PrayerTimes prayerTimes){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
        fajrTime.setText(formatter.format(prayerTimes.fajr));
        sunriseTime.setText(formatter.format(prayerTimes.sunrise));
        dhuhrTime.setText(formatter.format(prayerTimes.dhuhr));
        asrTime.setText(formatter.format(prayerTimes.asr));
        maghribTime.setText(formatter.format(prayerTimes.maghrib));
        ishaTime.setText(formatter.format(prayerTimes.isha));
    }

    public void initialize(){
        Controllers.setPrayerGridController(this);
    }
}

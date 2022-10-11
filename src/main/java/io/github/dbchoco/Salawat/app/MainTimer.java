package io.github.dbchoco.Salawat.app;

import com.batoulapps.adhan.PrayerTimes;
import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.Reloader;
import io.github.dbchoco.Salawat.helpers.StageController;
import javafx.application.Platform;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

import static java.time.LocalDate.now;

public class MainTimer{
    private final LocalDate startTime;
    public MainTimer(){
        startTime = now();
    }

    private Boolean launchedAlerts = false;

    public void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        checkAdhan();
                        Controllers.getPrayerGridController().createProgressAnimation();
                    }
                });
            }
        }, 0, 1000);

        Timer fiveTimer = new Timer();
        fiveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        Controllers.getTimeController().displayGregorianDate();
                        checkIfNewDay();
                    }
                });
            }
        }, 0, 5000);
    }

    private void checkIfNewDay(){
        if (startTime.getDayOfMonth() != now().getDayOfMonth()){
            try {
                Reloader.reload();
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't Reload prayer times");
                throw new RuntimeException(e);
            }
        }
    }

    private void checkAdhan(){
        if (UserSettings.enableAdhan || UserSettings.notifications){
            int hours = Main.getPrayerTimesCalculator().timeAfterCurrentPrayer().getHours();
            int minutes = Main.getPrayerTimesCalculator().timeAfterCurrentPrayer().getMinutes();
            int seconds = Main.getPrayerTimesCalculator().timeAfterCurrentPrayer().getSeconds();
            if (hours == 00 && minutes <= 10){
                if (minutes == 0 && seconds <= 10){
                    if (!launchedAlerts){
                        if (UserSettings.enableAdhan){
                            if (!AudioPlayer.getIsPlaying()){
                                if (UserSettings.customFajrAdhan &&
                                        (Main.getPrayerTimesCalculator().getCurrentPrayer().getName()).equals("fajr"))
                                    AudioPlayer.play(UserSettings.customFajrAdhanPath, true);
                                else AudioPlayer.play(true);
                            }
                        }
                        if (UserSettings.notifications){
                            TrayMenu.showNotification(I18N.get("timeForPrayer").replace("%prayer",
                                            Main.getPrayerTimesCalculator().getCurrentPrayer().getI18Name()) ,
                                    I18N.get("comeToPrayer"));
                        }
                        launchedAlerts = true;
                    }
                }
                if (AudioPlayer.getIsPlaying()){
                    Controllers.getTimeController().displayTimeLeft("adhan");
                }
                else {
                    Controllers.getTimeController().displayTimeLeft("now");
                }
            }
            else {
                Controllers.getTimeController().displayTimeLeft("default");
                launchedAlerts = false;
            }
        }
    }
}

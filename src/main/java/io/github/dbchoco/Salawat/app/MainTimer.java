package io.github.dbchoco.Salawat.app;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.helpers.Controllers;
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
                        checkIfNewDay();
                        Controllers.getTimeController().displayTimeLeft();
                        Controllers.getPrayerGridController().createProgressAnimation();
                        checkAdhan();
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
                    }
                });
            }
        }, 0, 5000);
    }

    private void checkIfNewDay(){
        if (startTime.getDayOfMonth() != now().getDayOfMonth()){
            try {
                Main.reload();
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
            if (hours == 0 && minutes == 0 && seconds <= 10){
                if (!launchedAlerts){
                    if (UserSettings.enableAdhan){
                        if (!AudioPlayer.getIsPlaying()){
                            AudioPlayer.play();
                        }
                    }
                    if (UserSettings.notifications){
                        Notifier notifier = new Notifier(StageController.getStage());
                        notifier.show("Time for prayer", "Time for Salah");
                    }
                    launchedAlerts = true;
                }
            }
            else launchedAlerts = false;
        }
    }
}

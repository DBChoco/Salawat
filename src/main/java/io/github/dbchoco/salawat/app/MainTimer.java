package io.github.dbchoco.salawat.app;

import io.github.dbchoco.salawat.Main;
import io.github.dbchoco.salawat.helpers.Controllers;
import javafx.application.Platform;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static java.time.LocalDate.now;

public class MainTimer{
    private final LocalDate startTime;
    public MainTimer(){
        startTime = now();
    }

    public void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (startTime.getDayOfMonth() != now().getDayOfMonth()){
                    try {
                        Main.reload();
                    } catch (ClassNotFoundException e) {
                        System.err.println("Couldn't Reload prayer times");
                        throw new RuntimeException(e);
                    }
                }
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        Controllers.getTimeController().displayTimeLeft();
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
                    }
                });
            }
        }, 0, 5000);
    }
}

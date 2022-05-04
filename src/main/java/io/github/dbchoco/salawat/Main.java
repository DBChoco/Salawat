package io.github.dbchoco.salawat;

import io.github.dbchoco.salawat.app.PrayerTimesCalculator;
import io.github.dbchoco.salawat.helpers.Controllers;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        URL cssURL = getClass().getResource("css/main.css");
        scene.getStylesheets().add(cssURL.toExternalForm());
        stage.setTitle("Salawat");
        stage.setScene(scene);
        stage.show();


        PrayerTimesCalculator prayerTimesCalculator = new PrayerTimesCalculator();
        prayerTimesCalculator.printPrayerTimes();
        prayerTimesCalculator.calculatePrayers();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                        Controllers.getClockController().clock.setText(formatter.format(date));
                        Controllers.getClockController().timeLeft.setText("Time until " + prayerTimesCalculator.getNextPrayer().getName() + ": " +  prayerTimesCalculator.timeUntilNextPrayer().toString());
                    }
                });
            }
        }, 0, 1000);
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Controllers.getClockController().date.setText(formatter.format(date));
                Controllers.getPrayerGridController().setPrayerTimes(prayerTimesCalculator.getPrayerTimes());
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

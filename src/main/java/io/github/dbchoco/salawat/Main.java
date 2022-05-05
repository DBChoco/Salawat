package io.github.dbchoco.salawat;

import io.github.dbchoco.salawat.app.Displayer;
import io.github.dbchoco.salawat.app.PrayerTimesCalculator;
import io.github.dbchoco.salawat.helpers.Controllers;
import io.github.dbchoco.salawat.helpers.StageController;
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

    private static PrayerTimesCalculator prayerTimesCalculator;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        loadPrayerTimes();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        URL cssURL = getClass().getResource("css/main.css");
        scene.getStylesheets().add(cssURL.toExternalForm());
        stage.setTitle("Salawat");
        StageController.setStage(stage);
        StageController.setCurrentScene(scene);

        stage.show();
    }

    public static void loadPrayerTimes() throws ClassNotFoundException {
        prayerTimesCalculator = new PrayerTimesCalculator();
        prayerTimesCalculator.printPrayerTimes();
        prayerTimesCalculator.calculatePrayers();
    }


    public static PrayerTimesCalculator getPrayerTimesCalculator() {
        return prayerTimesCalculator;
    }
    public static void reload() throws ClassNotFoundException {
        loadPrayerTimes();
    }
    public static void main(String[] args) {
        launch();
    }
}

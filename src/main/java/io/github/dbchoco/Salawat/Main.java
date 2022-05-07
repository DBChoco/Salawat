package io.github.dbchoco.Salawat;

import io.github.dbchoco.Salawat.app.*;
import io.github.dbchoco.Salawat.helpers.StageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static PrayerTimesCalculator prayerTimesCalculator;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        loadPrayerTimes();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        URL cssURL = getClass().getResource("css/main.css");
        scene.getStylesheets().add(cssURL.toExternalForm());
        StageController.setMainScene(scene);
        stage.setTitle("Salawat");

        StageController.setStage(stage);
        StageController.setCurrentScene(scene);

        //TrayMenu trayMenu = new TrayMenu();
        //trayMenu.show();

        MainTimer mainTimer = new MainTimer();
        mainTimer.start();

        //Notifier notifier = new Notifier(StageController.getStage());

        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                TrayMenu trayMenu = new TrayMenu();
                trayMenu.show();
            }
        });


        Platform.setImplicitExit(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (UserSettings.systemTray){
                    stage.hide();
                    windowEvent.consume();
                }
            }
        });

        if(!UserSettings.launchMinimized) stage.show();
    }

    private void launchPrayerTimer(){

    }
    public static void loadPrayerTimes() throws ClassNotFoundException {
        prayerTimesCalculator = new PrayerTimesCalculator();
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

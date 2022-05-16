package io.github.dbchoco.Salawat;

import io.github.dbchoco.Salawat.app.*;
import io.github.dbchoco.Salawat.helpers.*;
import io.github.palexdev.materialfx.dialogs.MFXDialogs;
import io.github.palexdev.materialfx.dialogs.MFXFilterDialog;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static PrayerTimesCalculator prayerTimesCalculator;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        StageController.setStage(stage);
        loadPrayerTimes();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        URL cssURL = getClass().getResource("css/main.css");
        URL theme;
        if (UserSettings.darkMode){
            theme = getClass().getResource("css/dark.css");
        }
        else {
            theme = getClass().getResource("css/light.css");
        }
        scene.getStylesheets().add(cssURL.toExternalForm());
        scene.getStylesheets().add(1, theme.toExternalForm());
        StageController.setMainScene(scene);
        stage.setTitle("Salawat");

        StageController.setCurrentScene(scene);

        SizeBinder.init();

        MainTimer mainTimer = new MainTimer();
        mainTimer.start();

        //Notifier notifier = new Notifier(StageController.getStage());

        minimizeToTray();
        launchTrayIcon();

        if(!UserSettings.launchMinimized) {
            stage.setMaximized(true);
            stage.show();
        }

        playStartupSound();
        checkFirstTime();
        checkForUpdates();
    }

    private void checkForUpdates() {
        UpdateChecker updateChecker = new UpdateChecker();
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
        Reloader.reload();
    }

    private void playStartupSound(){
        if (UserSettings.startupSound) AudioPlayer.play("audio/Bismillah - Fatih Sefaragic.mp3", false);
    }

    private void minimizeToTray(){
        StageController.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (UserSettings.systemTray){
                    Platform.setImplicitExit(false);
                    StageController.getStage().hide();
                    windowEvent.consume();
                }
            }
        });
    }

    private void launchTrayIcon(){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                TrayMenu.launch();
            }
        });
    }

    private void checkFirstTime(){
        if (UserSettings.firstTime){
            UserSettings.firstTime = false;
            ApiRequester apiRequester = new ApiRequester();
            apiRequester.requestLocation();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}

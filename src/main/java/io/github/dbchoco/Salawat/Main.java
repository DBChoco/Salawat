package io.github.dbchoco.Salawat;

import io.github.dbchoco.Salawat.app.*;
import io.github.dbchoco.Salawat.helpers.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;

public class Main extends Application {

    private static PrayerTimesCalculator prayerTimesCalculator;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        //Fix arabic text bug
        System.setProperty("prism.text", "t2k");
        System.setProperty("prism.lcdtext", "false");

        Thread thread = new Thread(() -> {
            try {
                loadPrayerTimes();
                FontBinder.init();
                loadLocale();
                checkFirstTime();
                checkForUpdates();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();

        StageController.setStage(stage);

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
        stage.getIcons().add(new Image(getClass().getResource("images/icon.png").toExternalForm()));
        stage.setTitle("Salawat");

        StageController.setCurrentScene(scene);

        MainTimer mainTimer = new MainTimer();
        mainTimer.start();

        launchTrayIcon();


        if (UserSettings.windowWidth != 0){
            stage.setWidth(UserSettings.windowWidth);
            stage.setHeight(UserSettings.windowHeight);
        }
        else stage.setMaximized(true);
        addStageSizeListener(stage);

        playStartupSound();
    }

    private void loadLocale() {
        I18N.setLocale(Locale.forLanguageTag(UserSettings.language));
    }

    private void addStageSizeListener(Stage stage){
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            UserSettings.windowWidth = stage.getWidth();
            UserSettings.windowHeight = stage.getHeight();
        };
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);
    }

    private void checkForUpdates() {
        ApiTimer apiTimer = new ApiTimer();
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
        if (UserSettings.startupSound) AudioPlayer.play(getClass().getResource("audio/Bismillah - Fatih Sefaragic.mp3").toExternalForm(), false);
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
        if (UserSettings.systemTray) {
            TrayMenu.launch();
            minimizeToTray();
        }
    }

    private void checkFirstTime(){
        if (UserSettings.firstTime){
            UserSettings.firstTime = false;
            ApiRequester apiRequester = new ApiRequester();
            apiRequester.requestLocation();
        }
    }

    @Override
    /**
     * When closing the app, settings are saved (especially for window size)
     */
    public void stop(){
        UserSettings.saveWhenClosing();
    }

    public static void main(String[] args) {
        launch();
    }
}

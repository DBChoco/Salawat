package io.github.dbchoco.salawat;

import io.github.dbchoco.salawat.controllers.ClockController;
import io.github.dbchoco.salawat.helpers.Controllers;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        URL cssURL = getClass().getResource("css/main.css");
        scene.getStylesheets().add(cssURL.toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        Controllers.getClockController().clock.setText(String.valueOf(LocalTime.now()));
                    }
                });

                System.out.println();
            }
        }, 0, 1000);
    }

    public static void main(String[] args) {
        launch();
    }
}

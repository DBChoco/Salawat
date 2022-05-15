package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.UserSettings;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class StageController {
    private static Stage stage;
    private static Scene currentScene;

    private static Scene mainScene;
    private static Scene settingsScene;

    public static  Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        StageController.stage = stage;
    }

    public static  Scene getCurrentScene() {
        return currentScene;
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static Scene getSettingsScene() {
        return settingsScene;
    }

    public static void setCurrentScene(Scene currentScene) {
        StageController.currentScene = currentScene;
        stage.setScene(currentScene);
    }

    public static void setMainScene(Scene mainScene) {
        StageController.mainScene = mainScene;
    }

    public static void setSettingsScene(Scene settingsScene) {
        StageController.settingsScene = settingsScene;
    }

    public static void reloadTheme() {
        changeTheme(mainScene);
    }

    public static void changeTheme(Scene scene){
        URL theme;
        if (UserSettings.darkMode){
            theme = Main.class.getResource("css/dark.css");
        }
        else {
            theme = Main.class.getResource("css/light.css");
        }
        scene.getStylesheets().remove(1);
        assert theme != null;
        scene.getStylesheets().add(1, theme.toExternalForm());
    }

    public static void changeTheme(Scene scene, Boolean darkMode){
        URL theme;
        if (darkMode){
            theme = Main.class.getResource("css/dark.css");
        }
        else {
            theme = Main.class.getResource("css/light.css");
        }
        scene.getStylesheets().remove(1);
        assert theme != null;
        scene.getStylesheets().add(1, theme.toExternalForm());
    }
}

package io.github.dbchoco.Salawat.helpers;

import javafx.scene.Scene;
import javafx.stage.Stage;

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
}

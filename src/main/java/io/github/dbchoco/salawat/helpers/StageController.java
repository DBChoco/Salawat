package io.github.dbchoco.salawat.helpers;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageController {
    private static Stage stage;
    private static Scene currentScene;

    public static  Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        StageController.stage = stage;
    }

    public static  Scene getCurrentScene() {
        return currentScene;
    }

    public static void setCurrentScene(Scene currentScene) {
        StageController.currentScene = currentScene;
        stage.setScene(currentScene);
    }
}

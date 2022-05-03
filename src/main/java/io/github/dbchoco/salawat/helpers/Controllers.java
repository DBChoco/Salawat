package io.github.dbchoco.salawat.helpers;

import io.github.dbchoco.salawat.controllers.ClockController;
import javafx.fxml.FXMLLoader;

public class Controllers {
    private static ClockController clockController;

    public static ClockController getClockController() {
        return clockController;
    }

    public static void setClockController(ClockController clockController) {
        Controllers.clockController = clockController;
    }
}

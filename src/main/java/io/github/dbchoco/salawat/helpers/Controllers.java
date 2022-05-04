package io.github.dbchoco.salawat.helpers;

import io.github.dbchoco.salawat.controllers.ClockController;
import io.github.dbchoco.salawat.controllers.PrayerGridController;
import javafx.fxml.FXMLLoader;

public class Controllers {
    private static ClockController clockController;
    private static PrayerGridController prayerGridController;

    public static ClockController getClockController() {
        return clockController;
    }

    public static PrayerGridController getPrayerGridController() {
        return prayerGridController;
    }

    public static void setClockController(ClockController clockController) {
        Controllers.clockController = clockController;
    }

    public static void setPrayerGridController(PrayerGridController prayerGridController) {
        Controllers.prayerGridController = prayerGridController;
    }
}

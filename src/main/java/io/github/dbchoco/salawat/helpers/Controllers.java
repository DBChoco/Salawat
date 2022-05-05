package io.github.dbchoco.salawat.helpers;

import io.github.dbchoco.salawat.controllers.main.TimeController;
import io.github.dbchoco.salawat.controllers.main.PrayerGridController;
import io.github.dbchoco.salawat.controllers.settings.SettingsController;
import io.github.dbchoco.salawat.controllers.settings.tabs.GeneralController;
import io.github.dbchoco.salawat.controllers.settings.tabs.LocationController;
import io.github.dbchoco.salawat.controllers.settings.tabs.SettingsPage;

public class Controllers {
    private static TimeController timeController;
    private static PrayerGridController prayerGridController;
    private static SettingsController settingsController;
    private static GeneralController generalController;
    private static LocationController locationController;

    public static TimeController getClockController() {
        return timeController;
    }

    public static PrayerGridController getPrayerGridController() {
        return prayerGridController;
    }

    public static SettingsController getSettingsController() {
        return settingsController;
    }

    public static SettingsPage getSettingsPage(String tab) throws ClassNotFoundException {
        if (tab.equalsIgnoreCase("general")) return generalController;
        else if (tab.equalsIgnoreCase("location")) return locationController;
        else throw new ClassNotFoundException();
    }

    public static void setTimeController(TimeController timeController) {
        Controllers.timeController = timeController;
    }

    public static void setPrayerGridController(PrayerGridController prayerGridController) {
        Controllers.prayerGridController = prayerGridController;
    }

    public static void setSettingsController(SettingsController settingsController) {
        Controllers.settingsController = settingsController;
    }

    public static void setGeneralController(GeneralController generalController) {
        Controllers.generalController = generalController;
    }

    public static void setLocationController(LocationController locationController) {
        Controllers.locationController = locationController;
    }
}

package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.controllers.main.FooterController;
import io.github.dbchoco.Salawat.controllers.main.TimeController;
import io.github.dbchoco.Salawat.controllers.main.PrayerGridController;
import io.github.dbchoco.Salawat.controllers.settings.SettingsController;
import io.github.dbchoco.Salawat.controllers.settings.tabs.*;

public class Controllers {
    private static TimeController timeController;
    private static PrayerGridController prayerGridController;
    private static SettingsController settingsController;
    private static GeneralController generalController;
    private static LocationController locationController;
    private static AudioController audioController;
    private static AppearanceController appearanceController;
    private static AdvancedController advancedController;

    private static FooterController mainFooterController;

    public static TimeController getTimeController() {
        return timeController;
    }

    public static PrayerGridController getPrayerGridController() {
        return prayerGridController;
    }

    public static SettingsController getSettingsController() {
        return settingsController;
    }

    public static FooterController getMainFooterController() {
        return mainFooterController;
    }

    public static SettingsPage getSettingsPage(String tab) throws ClassNotFoundException {
        if (tab.equalsIgnoreCase("general")) return generalController;
        else if (tab.equalsIgnoreCase("location")) return locationController;
        else if (tab.equalsIgnoreCase("audio")) return audioController;
        else if (tab.equalsIgnoreCase("appearance")) return appearanceController;
        else if (tab.equalsIgnoreCase("advanced")) return advancedController;
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

    public static void setAudioController(AudioController audioController) {
        Controllers.audioController = audioController;
    }

    public static void setAppearanceController(AppearanceController appearanceController) {
        Controllers.appearanceController = appearanceController;
    }

    public static void setAdvancedController(AdvancedController advancedController) {
        Controllers.advancedController = advancedController;
    }

    public static void setMainFooterController(FooterController mainFooterController) {
        Controllers.mainFooterController = mainFooterController;
    }
}

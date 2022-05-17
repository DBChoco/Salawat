package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.Main;

public class Reloader {

    public static void reload() throws ClassNotFoundException {
        Main.loadPrayerTimes();
        Controllers.getPrayerGridController().setPrayerTimes(Main.getPrayerTimesCalculator().getPrayerTimes());
        Controllers.getMainController().reload();
        StageController.reloadTheme();
        FontBinder.reloadFonts();
    }
}

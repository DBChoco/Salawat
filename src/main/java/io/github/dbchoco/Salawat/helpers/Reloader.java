package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.Main;

import java.time.LocalDate;

public class Reloader {

    public static void reload() throws ClassNotFoundException {
        Main.loadPrayerTimes();
        Controllers.getPrayerGridController().setPrayerTimes(Main.getPrayerTimesCalculator().getPrayerTimes());
        Controllers.getPrayerGridController().datePicker.setValue(LocalDate.now());
        Controllers.getMainController().reload();
        StageController.reloadTheme();
        FontBinder.reloadFonts();
    }
}

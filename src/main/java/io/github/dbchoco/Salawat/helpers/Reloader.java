package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.UserSettings;

import java.time.LocalDate;

public class Reloader {

    public static void reload() throws ClassNotFoundException {
        Main.loadPrayerTimes();
        Controllers.getPrayerGridController().setPrayerTimes(Main.getPrayerTimesCalculator().getPrayerTimes());
        Controllers.getPrayerGridController().datePicker.setValue(LocalDate.now());
        StageController.reloadTheme();
        Controllers.getMainController().loadBGImage();

        //TODO add if value of dateformat changed
        Controllers.getPrayerGridController().setupDatePicker();
        Controllers.getTimeController().loadFormat();

        Main.getApiTimer().reloadWeather();
    }
}

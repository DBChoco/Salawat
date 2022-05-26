package io.github.dbchoco.Salawat.app;

import com.batoulapps.adhan.*;
import com.batoulapps.adhan.data.DateComponents;

import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

public class PrayerTimesCalculator {
    Coordinates coordinates = new Coordinates(UserSettings.latitude, UserSettings.longitude);
    DateComponents dateComponents;
    DateComponents tomorrowDateComponents;

    CalculationParameters parameters;

    private PrayerTimes prayerTimes;
    private PrayerTimes tomorrowPrayerTimes;
    private Prayer currentPrayer;
    private Prayer nextPrayer;

    public PrayerTimesCalculator(){
        dateComponents = DateComponents.from(new Date());
        tomorrowDateComponents = DateComponents.from(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));

        if (!UserSettings.customCalculationSettings){
            switch (UserSettings.calculationMethod) {
                case "MWL" -> parameters = CalculationMethod.MUSLIM_WORLD_LEAGUE.getParameters();
                case "egyptian" -> parameters = CalculationMethod.EGYPTIAN.getParameters();
                case "karachi" -> parameters = CalculationMethod.KARACHI.getParameters();
                case "UAQ" -> parameters = CalculationMethod.UMM_AL_QURA.getParameters();
                case "dubai" -> parameters = CalculationMethod.DUBAI.getParameters();
                case "kuwait" -> parameters = CalculationMethod.KUWAIT.getParameters();
                case "MC" -> parameters = CalculationMethod.MOON_SIGHTING_COMMITTEE.getParameters();
                case "singapore" -> parameters = CalculationMethod.SINGAPORE.getParameters();
                case "ISNA" -> parameters = CalculationMethod.NORTH_AMERICA.getParameters();
                case "france12" -> {
                    parameters = CalculationMethod.OTHER.getParameters();
                    parameters.fajrAngle = 12;
                    parameters.ishaAngle = 12;
                }
                case "france15" -> {
                    parameters = CalculationMethod.OTHER.getParameters();
                    parameters.fajrAngle = 15;
                    parameters.ishaAngle = 15;
                }
                case "france18" -> {
                    parameters = CalculationMethod.OTHER.getParameters();
                    parameters.fajrAngle = 18;
                    parameters.ishaAngle = 18;
                }
                case "russia" -> {
                    parameters = CalculationMethod.OTHER.getParameters();
                    parameters.fajrAngle = 16;
                    parameters.ishaAngle = 15;
                }
                case "gulf" -> {
                    parameters = CalculationMethod.OTHER.getParameters();
                    parameters.fajrAngle = 19.5;
                    parameters.ishaInterval = 90;
                }
                case "turkey" -> {
                    parameters = CalculationMethod.OTHER.getParameters();
                    parameters.fajrAngle = 18;
                    parameters.ishaAngle = 17;
                }
                default -> parameters = CalculationMethod.MUSLIM_WORLD_LEAGUE.getParameters();
            }
        }
        else{
            parameters = CalculationMethod.OTHER.getParameters();
            parameters.fajrAngle = UserSettings.fajrAngle;
            parameters.ishaAngle = UserSettings.ishaAngle;
        }

        if ("shafi".equals(UserSettings.madhab)) {
            parameters.madhab = Madhab.SHAFI;
        } else {
            parameters.madhab = Madhab.HANAFI;
        }
        switch (UserSettings.highLatitudeRule) {
            case "MOTN" -> parameters.highLatitudeRule = HighLatitudeRule.MIDDLE_OF_THE_NIGHT;
            case "SOTN" -> parameters.highLatitudeRule = HighLatitudeRule.SEVENTH_OF_THE_NIGHT;
            default -> parameters.highLatitudeRule = HighLatitudeRule.TWILIGHT_ANGLE;
        }

        if (UserSettings.adjustments){
            parameters.adjustments.fajr = UserSettings.fajrAdjustments;
            parameters.adjustments.dhuhr = UserSettings.dhuhrAdjustments;
            parameters.adjustments.asr = UserSettings.asrAdjustments;
            parameters.adjustments.maghrib = UserSettings.maghribAdjustments;
            parameters.adjustments.isha = UserSettings.ishaAdjustments;
        }

        if (UserSettings.enableIshaDelay){
            parameters.ishaInterval = UserSettings.ishaDelay;
        }
    }

    public PrayerTimes calculateDatePrayers(LocalDate localDate){
        PrayerTimes datePrayers = new PrayerTimes(coordinates, DateComponents.from(Date.from(localDate.atStartOfDay(TimeZone.getTimeZone(UserSettings.timezone).toZoneId()).toInstant())), parameters);
        return datePrayers;
    }

    public void calculatePrayers(){
        prayerTimes = new PrayerTimes(coordinates, dateComponents, parameters);
        tomorrowPrayerTimes = new PrayerTimes(coordinates, tomorrowDateComponents, parameters);
        Date now = new Date();
        if (now.compareTo(prayerTimes.fajr) < 0){
            currentPrayer = new Prayer("isha", prayerTimes.isha);
            nextPrayer = new Prayer("fajr", prayerTimes.fajr);
        }
        else if (now.compareTo(prayerTimes.dhuhr) < 0){
            currentPrayer = new Prayer("fajr", prayerTimes.fajr);
            nextPrayer = new Prayer("dhuhr", prayerTimes.dhuhr);
        }
        else if (now.compareTo(prayerTimes.asr) < 0){
            currentPrayer = new Prayer("dhuhr", prayerTimes.dhuhr);
            nextPrayer = new Prayer("asr", prayerTimes.asr);
        }
        else if (now.compareTo(prayerTimes.maghrib) < 0){
            currentPrayer = new Prayer("asr", prayerTimes.asr);
            nextPrayer = new Prayer("maghrib", prayerTimes.maghrib);
        }
        else if (now.compareTo(prayerTimes.isha) < 0){
            currentPrayer = new Prayer("maghrib", prayerTimes.maghrib);
            nextPrayer = new Prayer("isha", prayerTimes.isha);
        }
        else{
            currentPrayer = new Prayer("isha", prayerTimes.isha);
            nextPrayer = new Prayer("fajr", tomorrowPrayerTimes.fajr);
        }
    }

    public TimeLeft timeAfterCurrentPrayer(){
        return TimeLeft.timeAfterDate(currentPrayer.getPrayerTimes());
    }
    public TimeLeft timeUntilNextPrayer(){
        return TimeLeft.timeUntilDate(nextPrayer.getPrayerTimes());
    }

    public double getProgressTime(){
        Date now = new Date();
        double progress;
        if (!currentPrayer.getName().equalsIgnoreCase("isha")) progress = (double) 1 - (nextPrayer.getPrayerTimes().getTime() - now.getTime()) /
                (double) (nextPrayer.getPrayerTimes().getTime() - currentPrayer.getPrayerTimes().getTime());
        else if (nextPrayer.getPrayerTimes().after(currentPrayer.getPrayerTimes())){
            progress = (double) 1 - (nextPrayer.getPrayerTimes().getTime() - now.getTime()) /
                    (double) (nextPrayer.getPrayerTimes().getTime() - currentPrayer.getPrayerTimes().getTime());
        }
        else {
            progress = (1000 * 60 * 60 * 24 - currentPrayer.getPrayerTimes().getTime() + now.getTime()) /
                    (double) (1000 * 60 * 60 * 24 - currentPrayer.getPrayerTimes().getTime() + nextPrayer.getPrayerTimes().getTime());
        }
        if (progress >= 1) calculatePrayers();
        return progress;
    }

    public Prayer getNextPrayer() {
        return nextPrayer;
    }

    public Prayer getCurrentPrayer() {
        return currentPrayer;
    }

    public PrayerTimes getPrayerTimes(){
        return prayerTimes;
    }

    public PrayerTimes getTomorrowPrayerTimes() {
        return tomorrowPrayerTimes;
    }
}

package io.github.dbchoco.salawat.app;

import com.batoulapps.adhan.CalculationMethod;
import com.batoulapps.adhan.CalculationParameters;
import com.batoulapps.adhan.Coordinates;
import com.batoulapps.adhan.PrayerTimes;
import com.batoulapps.adhan.data.DateComponents;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class PrayerTimesCalculator {
    final Coordinates coordinates = new Coordinates((Double) UserSettings.getSettings("lat", 50.24), (Double) UserSettings.getSettings("lon", 4.26));
    final DateComponents dateComponents = DateComponents.from(new Date());
    final CalculationParameters parameters = CalculationMethod.MUSLIM_WORLD_LEAGUE.getParameters();

    private PrayerTimes prayerTimes;
    private Prayer currentPrayer;
    private Prayer nextPrayer;

    public PrayerTimesCalculator() throws ClassNotFoundException {
    }

    public void calculatePrayerTimes(){

    }

    public void printPrayerTimes(){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Brussels"));

        PrayerTimes prayerTimes = new PrayerTimes(coordinates, dateComponents, parameters);
        System.out.println("Fajr: " + formatter.format(prayerTimes.fajr));
        System.out.println("Sunrise: " + formatter.format(prayerTimes.sunrise));
        System.out.println("Dhuhr: " + formatter.format(prayerTimes.dhuhr));
        System.out.println("Asr: " + formatter.format(prayerTimes.asr));
        System.out.println("Maghrib: " + formatter.format(prayerTimes.maghrib));
        System.out.println("Isha: " + formatter.format(prayerTimes.isha));
    }


    public void calculatePrayers(){
        prayerTimes = new PrayerTimes(coordinates, dateComponents, parameters);
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
            nextPrayer = new Prayer("fajr", prayerTimes.fajr); //TODO Make it tomorrow prayers
        }
    }

    public TimeLeft timeUntilNextPrayer(){
        Date now = new Date();
        TimeLeft timeLeft = new TimeLeft();
        timeLeft.setMS(nextPrayer.getPrayerTimes().getTime() - now.getTime());
        return timeLeft;
    }

    public double progressTime(){
        Date now = new Date();
        return (double) 1 - (nextPrayer.getPrayerTimes().getTime() - now.getTime()) /
                (double) (nextPrayer.getPrayerTimes().getTime() - currentPrayer.getPrayerTimes().getTime());
    }

    public Prayer getNextPrayer() {
        return nextPrayer;
    }

    public PrayerTimes getPrayerTimes(){
        return prayerTimes;
    }
}

package io.github.dbchoco.Salawat.app;

import java.util.Date;

public class TimeLeft {
    private int hours;
    private int minutes;
    private int seconds;

    private double ms;

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setTime(int hours, int minutes, int seconds){
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void setMS(long ms){
        this.ms = ms;
        ms = (long) Math.ceil(ms/1000.00) * 1000;
        seconds = (int) (ms / 1000.00 % 60);
        minutes = (int) (ms / (1000.00*60) % 60);
        hours = (int) (ms / (1000.00 * 60 * 60) % 60);

        if (seconds == 60){
            minutes++;
            seconds = 0;
        }
        if (minutes == 60){
            hours++;
            minutes = 0;
        }
    }

    public double getMs() {
        return ms;
    }

    public static TimeLeft timeUntilDate(Date date){
        Date now = new Date();
        TimeLeft timeLeft = new TimeLeft();
        timeLeft.setMS(date.getTime() - now.getTime());
        return timeLeft;
    }

    public static TimeLeft timeAfterDate(Date date){
        Date now = new Date();
        TimeLeft timeLeft = new TimeLeft();
        timeLeft.setMS(now.getTime() - date.getTime());
        return timeLeft;
    }

    public String toString(){
        if (UserSettings.showSeconds || (hours == 0 && minutes == 0)) return addZero(hours)+":"+addZero(minutes)+":"+addZero(seconds);
        else return addZero(hours)+":"+addZero(minutes);
    }

    private String addZero(int number){
        if (number >= 10){
            return Integer.toString(number);
        }
        else{
            return "0"+number;
        }
    }
}

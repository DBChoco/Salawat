package io.github.dbchoco.salawat.app;

public class TimeLeft {
    private int hours;
    private int minutes;
    private int seconds;

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
        ms = Math.round(ms/1000.00) * 1000;
        seconds = (int) (ms / 1000.00 % 60); //TODO fix double second jump stop working with ms values, but with seconds
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

    public String toString(){
        return addZero(hours)+":"+addZero(minutes)+":"+addZero(seconds);
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

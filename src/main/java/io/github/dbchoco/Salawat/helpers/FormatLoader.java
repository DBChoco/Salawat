package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.app.UserSettings;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class FormatLoader {
    private SimpleDateFormat timeFormatter;
    private SimpleDateFormat shortTimeFormatter;

    public FormatLoader(){
        if (UserSettings.timeFormat == 12){
            if (UserSettings.showSeconds){

                timeFormatter = new SimpleDateFormat("hh:mm:ss aa");

            }
            else{
                timeFormatter = new SimpleDateFormat("hh:mm aa");
            }
            shortTimeFormatter = new SimpleDateFormat("hh:mm aa");
        }
        else{
            if (UserSettings.showSeconds){
                timeFormatter = new SimpleDateFormat("HH:mm:ss");
            }
            else{
                timeFormatter = new SimpleDateFormat("HH:mm");
            }
            shortTimeFormatter = new SimpleDateFormat("HH:mm");
        }
        timeFormatter.setTimeZone(TimeZone.getTimeZone(UserSettings.timezone));
        shortTimeFormatter.setTimeZone(TimeZone.getTimeZone(UserSettings.timezone));
    }

    public SimpleDateFormat getTimeFormatter() {
        return timeFormatter;
    }

    public SimpleDateFormat getShortTimeFormatter() {
        return shortTimeFormatter;
    }
}

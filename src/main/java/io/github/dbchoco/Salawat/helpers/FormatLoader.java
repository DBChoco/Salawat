package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.app.UserSettings;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class FormatLoader {
    private SimpleDateFormat timeFormatter;
    private SimpleDateFormat shortTimeFormatter;

    public FormatLoader(){
        if (UserSettings.timeFormat == 12){
            if (UserSettings.showSeconds){

                timeFormatter = new SimpleDateFormat("hh:mm:ss aa", Locale.ENGLISH);

            }
            else{
                timeFormatter = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
            }
            shortTimeFormatter = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
        }
        else{
            if (UserSettings.showSeconds){
                timeFormatter = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
            }
            else{
                timeFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            }
            shortTimeFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
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

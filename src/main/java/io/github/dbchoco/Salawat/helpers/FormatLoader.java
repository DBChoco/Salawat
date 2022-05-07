package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.app.UserSettings;

import java.text.SimpleDateFormat;

public class FormatLoader {
    private SimpleDateFormat timeFormatter;

    public FormatLoader(){
        if (UserSettings.timeFormat == 12){
            if (UserSettings.showSeconds){
                timeFormatter = new SimpleDateFormat("hh:mm:ss aa");

            }
            else{
                timeFormatter = new SimpleDateFormat("hh:mm aa");
            }
        }
        else{
            if (UserSettings.showSeconds){
                timeFormatter = new SimpleDateFormat("HH:mm:ss");
            }
            else{
                timeFormatter = new SimpleDateFormat("HH:mm");
            }
        }
    } //TODO TRay icon, fix formatter latitude field

    public SimpleDateFormat getTimeFormatter() {
        return timeFormatter;
    }
}

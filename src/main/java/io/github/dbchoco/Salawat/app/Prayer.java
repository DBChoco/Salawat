package io.github.dbchoco.Salawat.app;

import java.util.Date;

public class Prayer {
    private String name;
    private Date prayerTimes;

    public Prayer(String name, Date prayerTimes){
        this.name = name;
        this.prayerTimes = prayerTimes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrayerTimes(Date prayerTimes) {
        this.prayerTimes = prayerTimes;
    }

    public String getName() {
        return name;
    }

    public Date getPrayerTimes() {
        return prayerTimes;
    }
}

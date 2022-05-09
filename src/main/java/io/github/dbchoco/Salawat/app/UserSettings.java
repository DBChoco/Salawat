package io.github.dbchoco.Salawat.app;

import io.github.dbchoco.Salawat.Main;

import java.util.prefs.Preferences;

public class UserSettings {
    static Preferences prefs = Preferences.systemRoot().node("io/github/dbchoco/Salawat");

    public static String language = prefs.get("language", "en");
    public static Integer timeFormat = prefs.getInt("timeFormat", 12);
    public static Boolean showSeconds = prefs.getBoolean("showSeconds", true);
    public static Boolean notifications = prefs.getBoolean("notifications", true);
    public static Boolean systemTray = prefs.getBoolean("systemTray", true);
    public static Boolean launchStart = prefs.getBoolean("launchStart", true);
    public static Boolean launchMinimized = prefs.getBoolean("launchMinimized", false);
    public static Double latitude = prefs.getDouble("latitude", 50.2);
    public static Double longitude = prefs.getDouble("longitude", 4.2);
    public static String timezone = prefs.get("timezone", "Europe/Brussels");
    public static Boolean enableAdhan = prefs.getBoolean("enableAdhan", true);
    public static String adhanPath = prefs.get("adhanPath",  Main.class.getResource("audio/Adhan - Ahmed Al-Nufais.mp3").toExternalForm());
    public static Boolean customAdhan = prefs.getBoolean("customAdhan", false);
    public static String customAdhanPath = prefs.get("customAdhanPath",  Main.class.getResource("audio/Adhan - Ahmed Al-Nufais.mp3").toExternalForm());
    public static Boolean customFajrAdhan = prefs.getBoolean("customFajrAdhan", false);
    public static String customFajrAdhanPath = prefs.get("customFajrAdhanPath",  Main.class.getResource("audio/Adhan - Ahmed Al-Nufais.mp3").toExternalForm());
    public static Boolean dua = prefs.getBoolean("dua", true);
    public static Boolean startupSound = prefs.getBoolean("startupSound", false);
    public static Boolean darkMode = prefs.getBoolean("darkMode", false);
    public static Boolean bgImage = prefs.getBoolean("bgImage", true);
    public static String bgImagePath = prefs.get("bgImagePath", "TODO"); //TODO
    public static Boolean showMOTN = prefs.getBoolean("showMOTN", false);
    public static Boolean showTOTN = prefs.getBoolean("showTOTN", false);
    public static Boolean showWeather = prefs.getBoolean("showWeather", true);
    public static String weatherUnit = prefs.get("weatherUnit", "C");
    public static String calculationMethod = prefs.get("calculationMethod", "MWL");
    public static String madhab = prefs.get("madhab", "shafi");
    public static String highLatitudeRule = prefs.get("highLatitudeRule", "TA");
    public static String polarCircleResolution = prefs.get("polarCircleResolution", "CC");
    public static String shafaq = prefs.get("shafaq", "general");
    public static Boolean customCalculationSettings = prefs.getBoolean("customCalculationSettings", false);
    public static Double fajrAngle = prefs.getDouble("fajrAngle", 0);
    //public static Integer maghribAngle = prefs.getInt("maghribAngle", 0); //Adhan Java does not currently support custom maghrib angles
    public static Double ishaAngle = prefs.getDouble("ishaAngle", 0);
    public static Boolean enableIshaDelay = prefs.getBoolean("enableIshaDelay", false);
    public static Integer ishaDelay = prefs.getInt("ishaDelay", 0);
    public static Boolean adjustments = prefs.getBoolean("adjustments", false);
    public static Integer fajrAdjustments = prefs.getInt("fajrAdjustments", 0);
    public static Integer dhuhrAdjustments = prefs.getInt("dhuhrAdjustments", 0);
    public static Integer asrAdjustments = prefs.getInt("asrAdjustments", 0);
    public static Integer maghribAdjustments = prefs.getInt("maghribAdjustments", 0);
    public static Integer ishaAdjustments = prefs.getInt("ishaAdjustments", 0);

    public static Double volume = prefs.getDouble("volume", 0.5);

    public static void saveSetting(String key, Object value) throws ClassNotFoundException {
        if (value instanceof String) prefs.put(key, (String) value);
        else if (value instanceof Boolean) prefs.putBoolean(key, (Boolean) value);
        else if (value instanceof Double) prefs.putDouble(key, (Double) value);
        else if (value instanceof Integer) prefs.putInt(key, (Integer) value);
        else throw new ClassNotFoundException();
    }

    public static Object getSettings(String key, Object def) throws ClassNotFoundException {
        if (def instanceof String) return prefs.get(key, (String) def);
        else if (def instanceof Boolean) return prefs.getBoolean(key, (Boolean) def);
        else if (def instanceof Double) return prefs.getDouble(key, (Double) def);
        else if (def instanceof Integer) return prefs.getInt(key, (Integer) def);
        else throw new ClassNotFoundException();
    }

    public static void saveAllSettings() {
        prefs.put("language", language);
        prefs.putInt("timeFormat", timeFormat);
        prefs.putBoolean("showSeconds", showSeconds);
        prefs.putBoolean("notifications", notifications);
        prefs.putBoolean("systemTray", systemTray);
        prefs.putBoolean("launchStart", launchStart);
        prefs.putBoolean("launchMinimized", launchMinimized);
        prefs.putDouble("latitude", latitude);
        prefs.putDouble("longitude", longitude);
        prefs.put("timezone", timezone);
        prefs.putBoolean("enableAdhan", enableAdhan);
        prefs.put("adhanPath", adhanPath);
        prefs.putBoolean("customAdhan", customAdhan);
        prefs.put("customAdhanPath", customAdhanPath);
        prefs.putBoolean("customFajrAdhan", customFajrAdhan);
        prefs.put("customFajrAdhanPath", customFajrAdhanPath);
        prefs.putBoolean("dua", dua);
        prefs.putBoolean("startupSound", startupSound);
        prefs.putBoolean("darkMode", darkMode);
        prefs.putBoolean("bgImage", bgImage);
        prefs.put("bgImagePath", bgImagePath);
        prefs.putBoolean("showMOTN", showMOTN);
        prefs.putBoolean("showTOTN", showTOTN);
        prefs.putBoolean("showWeather", showWeather);
        prefs.put("weatherUnit", weatherUnit);
        prefs.put("calculationMethod", calculationMethod);
        prefs.put("madhab", madhab);
        prefs.put("highLatitudeRule", highLatitudeRule);
        prefs.put("polarCircleResolution", polarCircleResolution);
        prefs.put("shafaq", shafaq);
        prefs.putBoolean("customCalculationSettings", customCalculationSettings);
        prefs.putDouble("fajrAngle", fajrAngle);
        //prefs.putInt("maghribAngle", maghribAngle); //Adhan Java does not currently support custom maghrib angles
        prefs.putDouble("ishaAngle", ishaAngle);
        prefs.putBoolean("enableIshaDelay", enableIshaDelay);
        prefs.putInt("ishaDelay", ishaDelay);
        prefs.putBoolean("adjustments", adjustments);
        prefs.putInt("fajrAdjustments", fajrAdjustments);
        prefs.putInt("dhuhrAdjustments", dhuhrAdjustments);
        prefs.putInt("asrAdjustments", asrAdjustments);
        prefs.putInt("maghribAdjustments", maghribAdjustments);
        prefs.putInt("ishaAdjustments", ishaAdjustments);
        prefs.putDouble("volume", volume);
    }
}

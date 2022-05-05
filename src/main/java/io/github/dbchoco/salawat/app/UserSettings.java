package io.github.dbchoco.salawat.app;

import  java.util.prefs.*;

public class UserSettings {
    static Preferences prefs =  Preferences.systemRoot().node("io/github/dbchoco/salawat");

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
}

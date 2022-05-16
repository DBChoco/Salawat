package io.github.dbchoco.Salawat.app;

import io.github.palexdev.materialfx.controls.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

/**
 * I18N utility class..
 * source: https://riptutorial.com/javafx/example/23068/switching-language-dynamically-when-the-application-is-running
 */
public final class I18N {

    /** the current selected Locale. */
    private static final ObjectProperty<Locale> locale;

    static {
        locale = new SimpleObjectProperty<>(loadSettingsLocale());
        locale.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
    }

    /**
     * get the supported Locales.
     *
     * @return List of Locale objects.
     */
    public static List<Locale> getSupportedLocales() {
        return new ArrayList<>(Arrays.asList(
                Locale.ENGLISH,
                Locale.FRENCH,
                new Locale.Builder().setLanguage("es").build(),
                new Locale.Builder().setLanguage("ar").build(),
                new Locale.Builder().setLanguage("tr").build()));
    }

    /**
     * get the default locale. This is the systems default if contained in the supported locales, english otherwise.
     *
     * @return
     */
    public static Locale loadSettingsLocale() {
        Locale settingsLocale = new Locale.Builder().setLanguage(UserSettings.language).setRegion("US").build();
        return getSupportedLocales().contains(settingsLocale) ? settingsLocale : Locale.ENGLISH;
    }

    public static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale locale) {
        localeProperty().set(locale);
        Locale.setDefault(locale);
    }

    public static ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    /**
     * gets the string with the given key from the resource bundle for the current locale and uses it as first argument
     * to MessageFormat.format, passing in the optional args and returning the result.
     *
     * @param key
     *         message key
     * @param args
     *         optional arguments for the message
     * @return localized formatted string
     */
    public static String get(final String key, final Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle("language", getLocale());
        if (bundle.containsKey(key)) return MessageFormat.format(bundle.getString(key), args);
        else throw new NullPointerException("Unknown key: " + key);
    }

    /**
     * creates a String binding to a localized String for the given message bundle key
     *
     * @param key
     *         key
     * @return String binding
     */
    public static StringBinding createStringBinding(final String key, Object... args) {
        return Bindings.createStringBinding(() -> get(key, args), locale);
    }

    /**
     * creates a String Binding to a localized String that is computed by calling the given func
     *
     * @param func
     *         function called on every change
     * @return StringBinding
     */
    public static StringBinding createStringBinding(Callable<String> func) {
        return Bindings.createStringBinding(func, locale);
    }

    public static void bindString(Label label, String key){
        label.textProperty().bind(createStringBinding(key));
    }

    public static void bindString(MFXButton button, String key){
        button.textProperty().bind(createStringBinding(key));
    }

    public static void bindString(MFXRadioButton button, String key){
        button.textProperty().bind(createStringBinding(key));
    }

    public static void bindString(MFXCheckbox button, String key){
        button.textProperty().bind(createStringBinding(key));
    }

    public static void bindString(MFXComboBox comboBox, String key) {
        comboBox.floatingTextProperty().bind(createStringBinding(key));
    }

    public static void bindString(MFXTextField comboBox, String key) {
        comboBox.floatingTextProperty().bind(createStringBinding(key));
    }
}
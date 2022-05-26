package io.github.dbchoco.Salawat.controllers.main;

import com.batoulapps.adhan.PrayerTimes;
import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.AudioPlayer;
import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.app.PrayerTimesCalculator;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.FontBinder;
import io.github.dbchoco.Salawat.helpers.FormatLoader;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import io.github.palexdev.materialfx.beans.NumberRange;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.effects.Interpolators;
import io.github.palexdev.materialfx.utils.AnimationUtils;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrayerGridController extends BaseController {
    public Label fajrText;
    public Label fajrTime;
    public Label sunriseText;
    public Label sunriseTime;
    public Label dhuhrText;
    public Label dhuhrTime;
    public Label asrText;
    public Label asrTime;
    public Label maghribText;
    public Label maghribTime;
    public Label ishaText;
    public Label ishaTime;
    public MFXProgressBar progressBar;
    public AnchorPane root;
    public GridPane prayerGrid;
    public VBox vbox;
    public HBox hbox;
    public DatePicker datePicker;
    private String pattern;
    private String oldPattern;

    public void initialize() throws ClassNotFoundException {
        Controllers.setPrayerGridController(this);
        progressBar.getRanges1().add(NumberRange.of(0.0,1.0));
        setPrayerTimes(Main.getPrayerTimesCalculator().getPrayerTimes());
        createProgressAnimation();
        makeResizable();
        translate();
        setupDatePicker();
    }

    public void setupDatePicker() {
        if (pattern != null) oldPattern = pattern;

        if (UserSettings.dateformat.equals("ddmmyyyy")) pattern = "dd/MM/yyyy";
        else if (UserSettings.dateformat.equals("mmddyyyy")) pattern = "MM/dd/yyyy";
        else pattern = "yyyy/MM/dd";

        if (oldPattern == null || !oldPattern.equals(pattern)){
            datePicker.setConverter(new StringConverter<LocalDate>() {
                final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                {
                    datePicker.setPromptText(pattern.toLowerCase());
                }

                @Override public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }

                @Override public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
            });
            datePicker.setValue(LocalDate.now());
            datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
                @Override
                public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                    setPrayerTimes(Main.getPrayerTimesCalculator().calculateDatePrayers(t1));
                }
            });
        }
    }

    public void setPrayerTimes(PrayerTimes prayerTimes){
        SimpleDateFormat formatter = (new FormatLoader()).getShortTimeFormatter();
        fajrTime.setText(formatter.format(prayerTimes.fajr));
        sunriseTime.setText(formatter.format(prayerTimes.sunrise));
        dhuhrTime.setText(formatter.format(prayerTimes.dhuhr));
        asrTime.setText(formatter.format(prayerTimes.asr));
        maghribTime.setText(formatter.format(prayerTimes.maghrib));
        ishaTime.setText(formatter.format(prayerTimes.isha));
    }

    public void createProgressAnimation(){
        final double[] progress = {0.00};
        if (AudioPlayer.getIsPlaying()){
            progress[0] = AudioPlayer.getProgressTime();
        }
        else {
            PrayerTimesCalculator prayerTimesCalculator = Main.getPrayerTimesCalculator();
            progress[0] = prayerTimesCalculator.getProgressTime();
        }
        boolean bigger = progressBar.getProgress() > progress[0];
        if (bigger || progressBar.getProgress() + 0.005 < progress[0]){
            Platform.runLater(() -> {
                Animation a1;
                if (bigger | progressBar.getProgress() + 0.05 < progress[0]){
                    a1 = AnimationUtils.TimelineBuilder.build()
                            .add(
                                    AnimationUtils.KeyFrames.of(1000, progressBar.progressProperty(), progress[0], Interpolators.INTERPOLATOR_V1)
                            )
                            .getAnimation();
                }
                else {
                    a1 = AnimationUtils.TimelineBuilder.build()
                            .add(
                                    AnimationUtils.KeyFrames.of(1000, progressBar.progressProperty(), progress[0], Interpolators.LINEAR)
                            )
                            .getAnimation();
                }
                a1.play();
            });
        }
        else{
            progressBar.setProgress(progress[0]);
        }
    }

    @Override
    protected void translate() {
        I18N.bindString(fajrText, "fajr");
        I18N.bindString(sunriseText, "sunrise");
        I18N.bindString(dhuhrText, "dhuhr");
        I18N.bindString(asrText, "asr");
        I18N.bindString(maghribText, "maghrib");
        I18N.bindString(ishaText, "isha");
    }

    @Override
    protected void makeResizable() {
        Platform.runLater(() -> {
            SizeBinder.bindSize(root, 1280, 330, "main");
            SizeBinder.bindSize(hbox, 1280, 330, "main");
            SizeBinder.bindSizeVH(vbox, 320, 330, "main");
            SizeBinder.bindSizeVH(prayerGrid, 320, 280, "main");
            SizeBinder.bindSizeVH(progressBar, 320, 10, "main");
            Label[] labels = {fajrText, fajrTime, sunriseText, sunriseTime, dhuhrText, dhuhrTime, asrText, asrTime,
            maghribText, maghribTime, ishaText, ishaTime};
            for (Label label : labels){
                FontBinder.bindFontSize(label, "small");
            }
        });
    }
}

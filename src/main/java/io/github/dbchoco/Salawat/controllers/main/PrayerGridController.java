package io.github.dbchoco.Salawat.controllers.main;

import com.batoulapps.adhan.PrayerTimes;
import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.PrayerTimesCalculator;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import io.github.palexdev.materialfx.beans.NumberRange;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.effects.Interpolators;
import io.github.palexdev.materialfx.utils.AnimationUtils;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.css.Size;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;

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

    public void setPrayerTimes(PrayerTimes prayerTimes){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
        fajrTime.setText(formatter.format(prayerTimes.fajr));
        sunriseTime.setText(formatter.format(prayerTimes.sunrise));
        dhuhrTime.setText(formatter.format(prayerTimes.dhuhr));
        asrTime.setText(formatter.format(prayerTimes.asr));
        maghribTime.setText(formatter.format(prayerTimes.maghrib));
        ishaTime.setText(formatter.format(prayerTimes.isha));
    }

    public void initialize() throws ClassNotFoundException {
        Controllers.setPrayerGridController(this);
        progressBar.getRanges1().add(NumberRange.of(0.0,1.0));
        setPrayerTimes(Main.getPrayerTimesCalculator().getPrayerTimes());
        createProgressAnimation();
        makeResizable();
    }

    public void createProgressAnimation(){
        PrayerTimesCalculator prayerTimesCalculator = Main.getPrayerTimesCalculator();
        final double[] progress = {0.00};
        Platform.runLater(() -> {
            progress[0] = prayerTimesCalculator.progressTime();
            Animation a1 = AnimationUtils.TimelineBuilder.build()
                    .add(
                            AnimationUtils.KeyFrames.of(1000, progressBar.progressProperty(), progress[0], Interpolators.INTERPOLATOR_V1)
                    )
                    .getAnimation();
            a1.play();
        });
    }

    @Override
    protected void translate() {

    }

    @Override
    protected void makeResizable() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                SizeBinder.bindSize(root, 1280, 290, "main");
                SizeBinder.bindSize(hbox, 1280, 290, "main");
                SizeBinder.bindSizeVH(vbox, 320, 290, "main");
                SizeBinder.bindSizeVH(prayerGrid, 320, 280, "main");
                SizeBinder.bindSizeVH(progressBar, 320, 10, "main");
                Label[] labels = {fajrText, fajrTime, sunriseText, sunriseTime, dhuhrText, dhuhrTime, asrText, asrTime,
                maghribText, maghribTime, ishaText, ishaTime};
                for (Label label : labels){
                    SizeBinder.bindFontSize(label, "medium");
                }
            }
        });
    }
}

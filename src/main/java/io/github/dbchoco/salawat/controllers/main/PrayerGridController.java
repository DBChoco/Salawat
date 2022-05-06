package io.github.dbchoco.salawat.controllers.main;

import com.batoulapps.adhan.PrayerTimes;
import io.github.dbchoco.salawat.Main;
import io.github.dbchoco.salawat.app.PrayerTimesCalculator;
import io.github.dbchoco.salawat.helpers.Controllers;
import io.github.palexdev.materialfx.beans.NumberRange;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.effects.Interpolators;
import io.github.palexdev.materialfx.utils.AnimationUtils;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;

public class PrayerGridController{
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
            System.out.println(progress[0]);
        });
    }
}

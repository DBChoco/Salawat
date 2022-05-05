package io.github.dbchoco.salawat.controllers.main;

import com.batoulapps.adhan.PrayerTimes;
import io.github.dbchoco.salawat.Main;
import io.github.dbchoco.salawat.app.Displayer;
import io.github.dbchoco.salawat.helpers.Controllers;
import io.github.palexdev.materialfx.beans.NumberRange;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.effects.Interpolators;
import io.github.palexdev.materialfx.utils.AnimationUtils;
import javafx.animation.Animation;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

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

    public void initialize(){
        Controllers.setPrayerGridController(this);
        progressBar.getRanges1().add(NumberRange.of(0.0,1.0));
        createAndPlayAnimation(progressBar);
        Controllers.getPrayerGridController().setPrayerTimes(Main.getPrayerTimesCalculator().getPrayerTimes());
    }

    private void createAndPlayAnimation(ProgressIndicator indicator) {
        Animation a1 = AnimationUtils.TimelineBuilder.build()
                .add(
                        AnimationUtils.KeyFrames.of(2000, indicator.progressProperty(), 0.3, Interpolators.INTERPOLATOR_V1),
                        AnimationUtils.KeyFrames.of(4000, indicator.progressProperty(), 0.6, Interpolators.INTERPOLATOR_V1),
                        AnimationUtils.KeyFrames.of(6000, indicator.progressProperty(), 1.0, Interpolators.INTERPOLATOR_V1)
                )
                .getAnimation();

        Animation a2 = AnimationUtils.TimelineBuilder.build()
                .add(
                        AnimationUtils.KeyFrames.of(1000, indicator.progressProperty(), 0, Interpolators.INTERPOLATOR_V2)
                )
                .getAnimation();

        a1.setOnFinished(end -> AnimationUtils.PauseBuilder.build()
                .setDuration(Duration.seconds(1))
                .setOnFinished(event -> a2.playFromStart())
                .getAnimation()
                .play()
        );
        a2.setOnFinished(end -> AnimationUtils.PauseBuilder.build()
                .setDuration(Duration.seconds(1))
                .setOnFinished(event -> a1.playFromStart())
                .getAnimation()
                .play()
        );

        a1.play();
    }
}

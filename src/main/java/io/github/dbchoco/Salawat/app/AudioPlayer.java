package io.github.dbchoco.Salawat.app;


import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.helpers.Controllers;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {

    private static Boolean isPlaying = false;
    static Media media;
    static MediaPlayer mediaPlayer;

    public static void play(String file){
        media = new Media(file);
        playMedia();
    }

    public static void play(String file, Boolean playDua){
        media = new Media(file);
        playMedia(playDua);
    }

    public static void play(){
        if (UserSettings.customAdhan) media = new Media(UserSettings.customAdhanPath);
        else media = new Media(UserSettings.adhanPath);
        playMedia();
    }

    public static void play(Boolean playDua){
        if (UserSettings.customAdhan) media = new Media(UserSettings.customAdhanPath);
        else media = new Media(UserSettings.adhanPath);
        playMedia(playDua);
    }

    public static void stop(){
        mediaPlayer.stop();
        isPlaying = false;
    }

    private static void playMedia(){
        if (isPlaying){
            mediaPlayer.stop();
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(UserSettings.volume);
        mediaPlayer.volumeProperty().bind(Controllers.getMainFooterController().volumeSlider.valueProperty().divide(100));
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            if (UserSettings.dua){
                playDua();
            }
            else {
                isPlaying = false;
            }
        });
        isPlaying = true;
    }

    private static void playMedia(Boolean playDua){
        if (isPlaying){
            mediaPlayer.stop();
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(UserSettings.volume);
        mediaPlayer.volumeProperty().bind(Controllers.getMainFooterController().volumeSlider.valueProperty().divide(100));
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            if (UserSettings.dua && playDua){
                playDua();
            }
            else {
                isPlaying = false;
            }
        });
        isPlaying = true;
    }

    private static void playDua(){
        media = new Media(Main.class.getResource("audio/dua.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(UserSettings.volume);
        mediaPlayer.volumeProperty().bind(Controllers.getMainFooterController().volumeSlider.valueProperty().divide(100));
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            isPlaying = false;
        });
        isPlaying = true;
    }
    public static Boolean getIsPlaying() {
        return isPlaying;
    }
}

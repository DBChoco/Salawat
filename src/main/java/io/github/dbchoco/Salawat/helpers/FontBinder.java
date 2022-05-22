package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.Main;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class FontBinder {
    private static final Font font = Font.loadFont( Main.class.getResourceAsStream( "fonts/IBMPlexSansArabic-Regular.ttf"), 50);
    private static final ObjectProperty<Font> fontLarger = new SimpleObjectProperty<Font>(font);
    private static final ObjectProperty<Font> fontLarge = new SimpleObjectProperty<Font>(font);
    static final ObjectProperty<Font> fontMedium = new SimpleObjectProperty<Font>(font);
    private static final ObjectProperty<Font> fontSmall = new SimpleObjectProperty<Font>(font);
    private static final ObjectProperty<Font> fontSmaller = new SimpleObjectProperty<Font>(font);

    private static final Integer smallerFontRatio = 50;
    private static final Integer smallFontRatio = 35;
    private static final Integer mediumFontRatio = 25;
    private static final Integer largeFontRatio = 18;
    private static final Integer largerFontRatio = 10;

    public static void init(){
        fontSmaller.set(FontChooser.getFont(StageController.getStage().heightProperty().doubleValue() / smallerFontRatio));
        fontSmall.set(FontChooser.getFont(StageController.getStage().heightProperty().doubleValue() / smallFontRatio));
        fontMedium.set(FontChooser.getFont(
                StageController.getStage().heightProperty().doubleValue() / mediumFontRatio));
        fontLarge.set(FontChooser.getFont(StageController.getStage().heightProperty().doubleValue() / largeFontRatio));
        fontLarger.set(FontChooser.getFont(StageController.getStage().heightProperty().doubleValue() / largerFontRatio));
        StageController.getStage().heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                fontSmaller.set(FontChooser.getFont(t1.doubleValue() / smallerFontRatio));
                fontSmall.set(FontChooser.getFont(t1.doubleValue() / smallFontRatio));
                fontMedium.set(FontChooser.getFont(t1.doubleValue() / mediumFontRatio));
                fontLarge.set(FontChooser.getFont(t1.doubleValue() / largeFontRatio));
                fontLarger.set(FontChooser.getFont(t1.doubleValue() / largerFontRatio));
            }
        });
    }

    public static void reloadFonts(){
        fontSmaller.set(FontChooser.getFont(StageController.getStage().heightProperty().doubleValue() / smallerFontRatio));
        fontSmall.set(FontChooser.getFont(StageController.getStage().heightProperty().doubleValue() / smallFontRatio));
        fontMedium.set(FontChooser.getFont(
                StageController.getStage().heightProperty().doubleValue() / mediumFontRatio));
        fontLarge.set(FontChooser.getFont(StageController.getStage().heightProperty().doubleValue() / largeFontRatio));
        fontLarger.set(FontChooser.getFont(StageController.getStage().heightProperty().doubleValue() / largerFontRatio));
    }

    public static void  bindFontSize(Label label, String size){
        switch (size){
            case "smaller" -> label.fontProperty().bind(fontSmaller);
            case "small" -> label.fontProperty().bind(fontSmall);
            case "medium" -> label.fontProperty().bind(fontMedium);
            case "large" -> label.fontProperty().bind(fontLarge);
            case "larger" -> label.fontProperty().bind(fontLarger);
        }
    }
}

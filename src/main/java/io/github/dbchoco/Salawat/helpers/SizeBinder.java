package io.github.dbchoco.Salawat.helpers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.Size;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class SizeBinder {
    private static final ObjectProperty<Font> fontSmall = new SimpleObjectProperty<Font>(Font.getDefault());
    private static final ObjectProperty<Font> fontMedium = new SimpleObjectProperty<Font>(Font.getDefault());
    private static final ObjectProperty<Font> fontLarge = new SimpleObjectProperty<Font>(Font.getDefault());
    private static final ObjectProperty<Font> fontLarger = new SimpleObjectProperty<Font>(Font.getDefault());
    private static final Integer smallFontRatio = 35;
    private static final Integer mediumFontRatio = 25;
    private static final Integer largeFontRatio = 18;
    private static final Integer largerFontRatio = 10;

    private static Double diffWidth;
    private static Double diffHeight;

    public static void init(){
        fontSmall.set(Font.font(StageController.getStage().heightProperty().doubleValue() / smallFontRatio));
        fontMedium.set(Font.font(StageController.getStage().heightProperty().doubleValue() / mediumFontRatio));
        fontLarge.set(Font.font(StageController.getStage().heightProperty().doubleValue() / largeFontRatio));
        fontLarger.set(Font.font(StageController.getStage().heightProperty().doubleValue() / largerFontRatio));
        StageController.getStage().heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                fontSmall.set(Font.font(t1.doubleValue() / smallFontRatio));
                fontMedium.set(Font.font(t1.doubleValue() / mediumFontRatio));
                fontLarge.set(Font.font(t1.doubleValue() / largeFontRatio));
                fontLarger.set(Font.font(t1.doubleValue() / largerFontRatio));
            }
        });
    }
    public static void  bindSize(Pane node, double width, double height, String scene){
        calcDiffDimensions();
        if (scene.equals("main")){
            node.prefWidthProperty().bind(Controllers.getMainController().getWidthProperty().divide(1280).multiply(width));
            node.prefHeightProperty().bind(Controllers.getMainController().getHeightProperty().divide(720).multiply(height));
        }
        else {
            node.prefWidthProperty().bind((StageController.getStage().widthProperty().subtract(diffWidth)).divide(1280).multiply(width));
            node.prefHeightProperty().bind((StageController.getStage().heightProperty().subtract(diffHeight)).divide(720).multiply(height));
        }
    }

    public static void  bindSize(Control node, double width, double height, String scene){
        if (scene.equals("main")){
            node.prefWidthProperty().bind(Controllers.getMainController().getWidthProperty().divide(1280).multiply(width));
            node.prefHeightProperty().bind(Controllers.getMainController().getHeightProperty().divide(720).multiply(height));
        }
        else {
            node.prefWidthProperty().bind((StageController.getStage().widthProperty().subtract(diffWidth)).divide(1280).multiply(width));
            node.prefHeightProperty().bind((StageController.getStage().heightProperty().subtract(diffHeight)).divide(720).multiply(height));
        }
    }

    public static void  bindSizeVH(Pane node, double width, double height, String scene){
        if (scene.equals("main")){
            node.prefWidthProperty().bind(Controllers.getMainController().getHeightProperty().divide(720).multiply(width));
            node.prefHeightProperty().bind(Controllers.getMainController().getHeightProperty().divide(720).multiply(height));
        }
        else {
            node.prefWidthProperty().bind((StageController.getStage().heightProperty().subtract(diffHeight)).divide(720).multiply(height));
            node.prefHeightProperty().bind((StageController.getStage().heightProperty().subtract(diffHeight)).divide(720).multiply(height));
        }
    }

    public static void bindSizeVH(Control node, double width, double height, String scene){
        if (scene.equals("main")){
            node.prefWidthProperty().bind(Controllers.getMainController().getHeightProperty().divide(720).multiply(width));
            node.prefHeightProperty().bind(Controllers.getMainController().getHeightProperty().divide(720).multiply(height));
        }
        else {
            node.prefWidthProperty().bind((StageController.getStage().heightProperty().subtract(diffHeight)).divide(720).multiply(height));
            node.prefHeightProperty().bind((StageController.getStage().heightProperty().subtract(diffHeight)).divide(720).multiply(height));
        }
    }

    public static void bindHeight(Pane node, double height, String scene){
        if (scene.equals("main")){
            node.prefHeightProperty().bind(Controllers.getMainController().getHeightProperty().divide(720).multiply(height));
        }
        else {
            node.prefHeightProperty().bind((StageController.getStage().heightProperty().subtract(diffHeight)).divide(720).multiply(height));
        }
    }

    public Font getFontMedium() {
        return fontMedium.get();
    }

    public static void  bindFontSize(Label label, String size){
        switch (size){
            case "small" -> label.fontProperty().bind(fontSmall);
            case "medium" -> label.fontProperty().bind(fontMedium);
            case "large" -> label.fontProperty().bind(fontLarge);
            case "larger" -> label.fontProperty().bind(fontLarger);
        }
    }

    private static void calcDiffDimensions(){
        if (diffWidth == null || Double.isNaN(diffWidth)){
            diffWidth = StageController.getStage().getWidth() - Controllers.getMainController().getWidthProperty().get();
            diffHeight = StageController.getStage().getHeight() - Controllers.getMainController().getHeightProperty().get();
        }
    }
}

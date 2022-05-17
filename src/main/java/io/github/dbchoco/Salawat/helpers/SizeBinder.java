package io.github.dbchoco.Salawat.helpers;

import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class SizeBinder {

    private static Double diffWidth;
    private static Double diffHeight;

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
        return FontBinder.fontMedium.get();
    }

    private static void calcDiffDimensions(){
        if (diffWidth == null || Double.isNaN(diffWidth)){
            diffWidth = StageController.getStage().getWidth() - Controllers.getMainController().getWidthProperty().get();
            diffHeight = StageController.getStage().getHeight() - Controllers.getMainController().getHeightProperty().get();
        }
    }
}

package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import io.github.dbchoco.Salawat.helpers.StageController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.awt.*;

public class MainController extends BaseController {
    public BorderPane borderPane;
    public Pane root;
    public FlowPane gridFlowPane;

    public void initialize(){
        Controllers.setMainController(this);
        makeResizable();
        loadBGImage();
    }

    public ReadOnlyDoubleProperty getWidthProperty(){
        return root.widthProperty();
    }

    public ReadOnlyDoubleProperty getHeightProperty(){
        return root.heightProperty();
    }

    @Override
    protected void translate() {

    }

    @Override
    protected void makeResizable() {
        SizeBinder.bindSize(borderPane, 1280, 720, "main");
        SizeBinder.bindSize(gridFlowPane, 1280, 300, "main");
    }

    private void loadBGImage(){
        Image image = new Image(Main.class.getResourceAsStream("images/bgImage.jpg"));
        // new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        // new BackgroundImage(image, repeatX, repeatY, position, size)
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        // new Background(images...)
        Background background = new Background(backgroundImage);
        root.setBackground(background);
    }
}

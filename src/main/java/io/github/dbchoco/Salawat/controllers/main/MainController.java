package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.DialogCreator;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;


public class MainController extends BaseController {
    public BorderPane borderPane;
    public Pane root;
    public FlowPane gridFlowPane;
    public ImageView background;

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
        if (UserSettings.bgImage){
            Image image = new Image(UserSettings.bgImagePath);
            // new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
            // new BackgroundImage(image, repeatX, repeatY, position, size)
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

            // new Background(images...)
            Background background = new Background(backgroundImage);

            root.setBackground(background);
        }
        else {
            BackgroundFill backgroundFill;
            if (UserSettings.darkMode) backgroundFill = new BackgroundFill(Color.web("#212121"), null, null);
            else backgroundFill = new BackgroundFill(Color.WHITE, null, null);
            Background background = new Background(backgroundFill);
            root.setBackground(background);
        }
    }

    public void reload() {
        loadBGImage();
    }
}

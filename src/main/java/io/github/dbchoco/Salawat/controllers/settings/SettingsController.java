package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SettingsController extends BaseController {
    public BorderPane borderPane;
    public GridPane gridPane;
    public AnchorPane root;
    public FlowPane mainSettings;

    public void initialize(){
        loadBackground();
        Controllers.setSettingsController(this);
        makeResizable();
    }

    @Override
    protected void translate() {

    }

    @Override
    protected void makeResizable() {
        //SizeBinder.bindSize(root, 1280, 720, "settings");
        SizeBinder.bindSize(borderPane, 1280, 720, "settings");
    }

    public ReadOnlyDoubleProperty getWidthProperty(){
        return root.widthProperty();
    }

    public ReadOnlyDoubleProperty getHeightProperty(){
        return root.heightProperty();
    }

    private void loadBackground(){
        BackgroundFill backgroundFill;
        if (UserSettings.darkMode) backgroundFill = new BackgroundFill(Color.web("#212121"), null, null);
        else backgroundFill = new BackgroundFill(Color.WHITE, null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
    }
    public void loadBackground(Boolean dark){
        BackgroundFill backgroundFill;
        if (dark) backgroundFill = new BackgroundFill(Color.web("#212121"), null, null);
        else backgroundFill = new BackgroundFill(Color.WHITE, null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
    }
}

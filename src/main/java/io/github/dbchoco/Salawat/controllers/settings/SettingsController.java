package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.FontBinder;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;

public class SettingsController extends BaseController {
    public BorderPane borderPane;
    public GridPane gridPane;
    public AnchorPane root;
    public FlowPane mainSettings;
    public Label quranQuote;
    public Label quranSource;

    public void initialize(){
        loadBackground();
        Controllers.setSettingsController(this);
        makeResizable();
        translate();
    }

    @Override
    protected void translate() {
        I18N.bindString(quranQuote, "quote");
        I18N.bindString(quranSource, "source");
    }

    @Override
    protected void makeResizable() {
        //SizeBinder.bindSize(root, 1280, 720, "settings");
        SizeBinder.bindSize(borderPane, 1280, 720, "settings");
        SizeBinder.bindSize(gridPane, 1280, 500, "settings");
        FontBinder.bindFontSize(quranQuote, "small");
        FontBinder.bindFontSize(quranSource, "smaller");
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

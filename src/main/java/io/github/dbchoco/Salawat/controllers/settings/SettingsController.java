package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class SettingsController extends BaseController {
    public BorderPane borderPane;
    public GridPane gridPane;
    public AnchorPane root;

    public void initialize(){
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
}

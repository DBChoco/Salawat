package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class MainAreaController extends BaseController {
    public FlowPane mainSettings;
    public AnchorPane sideBar;
    public HBox innerHBox;
    public AnchorPane root;
    public FlowPane flowPane;

    public void initialize(){
        Controllers.setMainAreaController(this);
        makeResizable();
    }

    @Override
    protected void translate() {

    }

    @Override
    protected void makeResizable() {
        SizeBinder.bindSize(root, 1280, 550);
        SizeBinder.bindSize(flowPane, 1280, 550);
    }
}

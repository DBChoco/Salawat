package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class HeaderController extends BaseController {
    public HBox hbox;
    public FlowPane flowPane;

    public void initialize(){
        makeResizable();
    }

    @Override
    protected void translate() {

    }

    @Override
    protected void makeResizable() {
        SizeBinder.bindSize(hbox, 1280, 70, "settings");
        SizeBinder.bindSize(flowPane, 1280, 70, "settings");
    }
}

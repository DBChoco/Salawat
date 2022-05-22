package io.github.dbchoco.Salawat.controllers.settings;

import io.github.dbchoco.Salawat.app.I18N;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.FontBinder;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class HeaderController extends BaseController {
    public HBox hbox;
    public FlowPane flowPane;
    public Label settingsLabel;

    public void initialize(){
        translate();
        makeResizable();
    }

    @Override
    protected void translate() {
        I18N.bindString(settingsLabel, "settings");
    }

    @Override
    protected void makeResizable() {
        SizeBinder.bindSize(hbox, 1280, 80, "settings");
        SizeBinder.bindSize(flowPane, 600, 80, "settings");
        FontBinder.bindFontSize(settingsLabel, "large");
    }
}

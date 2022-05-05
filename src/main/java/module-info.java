module io.github.dbchoco.salawat {
    requires javafx.controls;
    requires javafx.fxml;
    requires adhan;
    requires MaterialFX;
    requires java.prefs;


    exports io.github.dbchoco.salawat;
    opens io.github.dbchoco.salawat to javafx.fxml;

    exports io.github.dbchoco.salawat.controllers.main;
    opens io.github.dbchoco.salawat.controllers.main to javafx.fxml;

    exports io.github.dbchoco.salawat.controllers.settings;
    opens io.github.dbchoco.salawat.controllers.settings to javafx.fxml;

    exports io.github.dbchoco.salawat.controllers.settings.tabs;
    opens io.github.dbchoco.salawat.controllers.settings.tabs to javafx.fxml;
}
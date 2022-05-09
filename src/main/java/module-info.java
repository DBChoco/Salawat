module io.github.dbchoco.salawat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.prefs;
    requires adhan;
    requires MaterialFX;
    requires FXTrayIcon;

    exports io.github.dbchoco.Salawat;
    opens io.github.dbchoco.Salawat to javafx.fxml;

    exports io.github.dbchoco.Salawat.controllers.main;
    opens io.github.dbchoco.Salawat.controllers.main to javafx.fxml;

    exports io.github.dbchoco.Salawat.controllers.settings;
    opens io.github.dbchoco.Salawat.controllers.settings to javafx.fxml;

    exports io.github.dbchoco.Salawat.controllers.settings.tabs;
    opens io.github.dbchoco.Salawat.controllers.settings.tabs to javafx.fxml;
}
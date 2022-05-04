module io.github.dbchoco.salawat {
    requires javafx.controls;
    requires javafx.fxml;
    requires adhan;
    requires MaterialFX;


    exports io.github.dbchoco.salawat;
    opens io.github.dbchoco.salawat to javafx.fxml;

    exports io.github.dbchoco.salawat.controllers;
    opens io.github.dbchoco.salawat.controllers to javafx.fxml;

    exports io.github.dbchoco.salawat.controllers.settings;
    opens io.github.dbchoco.salawat.controllers.settings to javafx.fxml;
}
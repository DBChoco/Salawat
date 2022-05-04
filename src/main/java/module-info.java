module io.github.dbchoco.salawat {
    requires javafx.controls;
    requires javafx.fxml;
    requires adhan;


    opens io.github.dbchoco.salawat to javafx.fxml;
    exports io.github.dbchoco.salawat;
    exports io.github.dbchoco.salawat.controllers;
    opens io.github.dbchoco.salawat.controllers to javafx.fxml;
}
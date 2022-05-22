package io.github.dbchoco.Salawat.app;


import com.dustinredmond.fxtrayicon.FXTrayIcon;
import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.helpers.StageController;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;

public class TrayMenu {

    private static FXTrayIcon icon;

    private static final Boolean isSupported = FXTrayIcon.isSupported();

    public static void launch() {
        if (isSupported) {
            icon = new FXTrayIcon(StageController.getStage(), Main.class.getResource("images/icon.png"));

            MenuItem open = new MenuItem(I18N.get("open"));
            open.setOnAction(actionEvent -> Platform.runLater(() -> StageController.getStage().show()));
            MenuItem hide = new MenuItem(I18N.get("hide"));
            hide.setOnAction(actionEvent -> Platform.runLater(() -> StageController.getStage().hide()));
            MenuItem close = new MenuItem(I18N.get("quit"));
            close.setOnAction(actionEvent -> Platform.runLater(() -> {
                StageController.getStage().close();
                icon.hide();
                Platform.exit();
            }));
            icon.addMenuItems(open, hide, close);
            icon.show();
        }
    }

    public static void showNotification(String caption, String content){
        if (isSupported) {
            Platform.runLater(() -> icon.showMessage(caption, content));
        }
    }
}

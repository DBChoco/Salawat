package io.github.dbchoco.Salawat.app;


import com.dustinredmond.fxtrayicon.FXTrayIcon;
import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.helpers.StageController;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;


public class TrayMenu {

    private static FXTrayIcon icon;

    private static Boolean isSupported = FXTrayIcon.isSupported();

    public static  void launch(){
        if (isSupported) {
            icon = new FXTrayIcon(StageController.getStage(), Main.class.getResource("images/icon.png"));

            MenuItem open = new MenuItem("Open");
            open.setOnAction(actionEvent -> {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        StageController.getStage().show();
                    }
                });
            });
            MenuItem hide = new MenuItem("Hide");
            hide.setOnAction(actionEvent -> {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        StageController.getStage().hide();
                    }
                });
            });
            MenuItem close = new MenuItem("Close");
            close.setOnAction(actionEvent -> {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        StageController.getStage().close();
                        icon.hide();
                        Platform.exit();
                    }
                });
            });
            icon.addMenuItems(open, hide, close);
            icon.show();
        }
    }

    public static void showNotification(String caption, String content){
        if (isSupported) icon.showMessage(caption, content);
    }
}

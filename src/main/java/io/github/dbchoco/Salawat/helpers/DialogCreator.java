package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.app.I18N;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.stage.Modality;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class DialogCreator {

    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;


    public DialogCreator(){
        Platform.runLater(() -> {
            this.dialogContent = MFXGenericDialogBuilder.build()
                    .makeScrollable(true)
                    .get();
            this.dialog = MFXGenericDialogBuilder.build(dialogContent)
                    .toStageDialogBuilder()
                    .initOwner(StageController.getStage())
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setOwnerNode(Controllers.getMainController().root)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            dialogContent.addActions(
                    Map.entry(new MFXButton("Confirm"), event -> {
                    }),
                    Map.entry(new MFXButton("Cancel"), event -> dialog.close())
            );

            dialogContent.setMaxSize(400, 200);
        });
    }
    public void showUpdateDialog(String version){
        Platform.runLater(() -> {
            MFXFontIcon infoIcon = new MFXFontIcon("mfx-info-circle-filled", 18);
            dialogContent.setHeaderIcon(infoIcon);
            dialogContent.setHeaderText(I18N.get("updateAvailable"));
            dialogContent.setContentText(I18N.get("updateAvailableLong").replace("%version", version));
            convertDialogTo("mfx-info-dialog");
            dialogContent.clearActions();
            dialogContent.addActions(
                    Map.entry(new MFXButton(I18N.get("download")), event -> {
                        System.out.println("salam");
                        try {
                            BrowserOpener.openLink("https://github.com/DBChoco/Salawat/releases/latest");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (URISyntaxException e) {
                            throw new RuntimeException(e);
                        }
                    }),
                    Map.entry(new MFXButton(I18N.get("later")), event -> dialog.close())
            );

            dialog.showDialog();
        });
    }
    private void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }
}

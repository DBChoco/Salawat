package io.github.dbchoco.Salawat.app;

import io.github.dbchoco.Salawat.helpers.ApiRequester;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.DialogCreator;
import javafx.application.Platform;
import org.json.simple.JSONObject;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateChecker {

    private final String currentVersion;
    private String githubVersion;
    private final ApiRequester apiRequester = new ApiRequester();

    public UpdateChecker(){
        ResourceBundle bundle = ResourceBundle.getBundle("data");

        currentVersion = bundle.getString("version");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        githubVersion = apiRequester.requestVersion();
                        if (githubVersion == null){
                            System.out.println("You appear to be offline, we will not look for updates this session");
                            timer.cancel();
                        }
                        else if (compareVersion()){
                            DialogCreator dialogCreator = new DialogCreator();
                            dialogCreator.showUpdateDialog(githubVersion);
                            timer.cancel();
                        }
                    }
                });
            }
        }, 3000, 900000);
    }

    private Boolean compareVersion(){
        if (currentVersion.equals(githubVersion)) return false;
        String[] currentVersionSplit = currentVersion.split("\\.");
        String[] githubVersionSplit = githubVersion.split("\\.");
        for (int i = 0; i < 3; i++){
            if (Integer.parseInt(currentVersionSplit[i]) > Integer.parseInt(githubVersionSplit[i])){
                return false;
            }
        }
        return true;
    }
}

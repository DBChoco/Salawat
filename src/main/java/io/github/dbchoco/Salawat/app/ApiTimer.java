package io.github.dbchoco.Salawat.app;

import io.github.dbchoco.Salawat.helpers.ApiRequester;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.DialogCreator;
import javafx.application.Platform;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ApiTimer {

    private final String currentVersion;
    private String githubVersion;
    private final ApiRequester apiRequester = new ApiRequester();

    public ApiTimer(){
        ResourceBundle bundle = ResourceBundle.getBundle("data");

        currentVersion = bundle.getString("version");
        Timer updater = new Timer();
        updater.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        githubVersion = apiRequester.requestVersion();
                        if (githubVersion == null){
                            System.out.println("You appear to be offline, we will not look for updates this session");
                            updater.cancel();
                        }
                        else if (compareVersion()){
                            DialogCreator dialogCreator = new DialogCreator();
                            dialogCreator.showUpdateDialog(githubVersion);
                            updater.cancel();
                        }
                    }
                });
            }
        }, 3000, 900000*4); //1 hour

        Timer weatherChecker = new Timer();
        weatherChecker.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        Controllers.getMainHeaderController().setWeather(apiRequester.requestWeather());
                    }
                });
            }
        }, 3000, 900000); //15 minutes
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

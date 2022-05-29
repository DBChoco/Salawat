package io.github.dbchoco.Salawat.controllers.main;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.UserSettings;
import io.github.dbchoco.Salawat.controllers.BaseController;
import io.github.dbchoco.Salawat.helpers.Controllers;
import io.github.dbchoco.Salawat.helpers.FontBinder;
import io.github.dbchoco.Salawat.helpers.FontChooser;
import io.github.dbchoco.Salawat.helpers.SizeBinder;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.Arrays;

public class HeaderController extends BaseController {
    public Label weatherIcon;
    public Label weatherLabel;
    public AnchorPane root;
    public HBox hbox;
    public HBox titleBox;
    public HBox weatherBox;
    @FXML
    private Label title;
    private boolean weatherShown = false;
    public void initialize(){
        Controllers.setMainHeaderController(this);
        FontBinder.bindFontSize(title, "smaller");

        weatherLabel.setFont(FontChooser.getFont(30.0));
        weatherIcon.setFont(FontChooser.getWeatherFont(30.0));
        makeResizable();
    }

    public void setWeather(String[] weather){
        if (weather[0] != null){
            weatherIcon.setText(getWeatherIcon(Integer.parseInt(weather[0]), weather[1]));
            weatherLabel.setText(Math.round(Double.parseDouble(weather[2])) + "°" + UserSettings.weatherUnit);
            if (!weatherShown){
                weatherBox.setVisible(true);
                weatherShown = true;
            }
        }
    }

    private String getWeatherIcon(Integer weather, String time){
        if (weather == 210 || weather == 211 || weather == 212 || weather == 221) return "\uf016";
        else if (weather >= 200 && weather <= 232) return "\uf01d";
        else if (weather >= 300 && weather < 321) return "\uf019";
        else if (weather >= 500 && weather <= 504) return "\uf019";
        else if (weather == 511) return "\uf076";
        else if (weather >= 520 && weather <= 531) return "\uf01a";
        else if (weather >= 600 && weather <= 622) return "\uf01b";

        else if (weather == 701 || weather == 741) return "\uf014";
        else if (weather == 711 || weather == 731 || weather == 751 || weather == 761) return "\uf063";
        else if (weather == 721) return "\uf074";
        else if ( weather == 771 || weather == 781 || weather == 900) return "\uf056";

        else if (weather == 762) return "\uf0c8";
        else if (weather == 800 || weather == 951){
            if (time.equals("day")) return "\uf00d";
            else return "\uf02e";
        }
        else if (weather == 801){
            if (time.equals("day")) return "\uf002";
            else return "\uf086";
        }
        else if (weather == 802) return "\uf041";
        else if (weather == 803) return "\uf013";
        else if (weather == 804) return "\uf013";
        else return "\uf07b";
    }

    @Override
    protected void translate() {

    }

    @Override
    protected void makeResizable() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                SizeBinder.bindSize(root, 1280, 50, "main");
                SizeBinder.bindSize(hbox, 1280, 50, "main");
                SizeBinder.bindSize(titleBox, 640, 50, "main");
                SizeBinder.bindSize(weatherBox, 640, 50, "main");
            }
        });
    }
}

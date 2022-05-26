package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.Main;
import io.github.dbchoco.Salawat.app.UserSettings;
import javafx.scene.text.Font;

import java.io.InputStream;

public class FontChooser {
    public static Font getFont(Double size) {
        if (UserSettings.language.equals("ar")){
            return Font.loadFont( Main.class.getResourceAsStream( "fonts/IBMPlexSansArabic-Regular.ttf"), size);
        }
        else{
            return Font.loadFont( Main.class.getResourceAsStream( "fonts/Quicksand-VariableFont_wght.ttf"), size);
        }
    }

    public static Font getIconFont(Double size){
        return Font.loadFont( Main.class.getResourceAsStream( "fonts/Font Awesome 6 Free-Solid-900.otf"), size);
    }
}

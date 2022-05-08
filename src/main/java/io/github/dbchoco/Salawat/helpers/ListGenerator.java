package io.github.dbchoco.Salawat.helpers;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.util.StringConverter;

public class ListGenerator {

    public static void generateList(MFXComboBox comboBox, ListItem[] items){
        comboBox.setItems(FXCollections.observableArrayList(items));

        comboBox.setConverter(new StringConverter() {
            @Override
            public String toString(Object o) {
                if (o != null){
                    return o.toString();
                }
                else return "";
            }

            @Override
            public Object fromString(String s) {
                return null;
            }
        });
        Platform.runLater(new Runnable(){
            @Override
            public void run() {

            }
        });
    }

    public static void generateList(MFXComboBox comboBox, ListItemArray items){
        comboBox.setItems(FXCollections.observableArrayList(items));

        comboBox.setConverter(new StringConverter() {
            @Override
            public String toString(Object o) {
                if (o != null){
                    return o.toString();
                }
                else return "";
            }

            @Override
            public Object fromString(String s) {
                return null;
            }
        });
        Platform.runLater(new Runnable(){
            @Override
            public void run() {

            }
        });
    }
}

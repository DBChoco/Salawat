package io.github.dbchoco.Salawat.helpers;

public class StringShortener {
    public static String shortenString(String string, int length){
        string = string.split("/")[string.split("/").length-1];
        if (string.length() <= length){
            return string;
        }
        else return string.substring(0, length);
    }
}

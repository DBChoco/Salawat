package io.github.dbchoco.Salawat.helpers;

import io.github.dbchoco.Salawat.app.I18N;

public class ListItem {
    private String name;
    private String value;

    public ListItem(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return I18N.get(name);
    }
}

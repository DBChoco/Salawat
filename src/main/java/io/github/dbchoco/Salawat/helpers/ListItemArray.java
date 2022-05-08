package io.github.dbchoco.Salawat.helpers;

import java.util.ArrayList;

public class ListItemArray extends ArrayList<ListItem> {

    private ArrayList<ListItem> arrayList;

    public ListItemArray(){
        arrayList = this;
    }

    public ListItem getItembyValue(String value){
        for (int i = 0; i < arrayList.size(); i ++){
            if ((arrayList.get(i).getValue()).equals(value)){
                return arrayList.get(i);
            }
        }
        return null;
    }

    public ListItem getItembyName(String name){
        for (int i = 0; i < arrayList.size(); i ++){
            if ((arrayList.get(i).getName()).equals(name)){
                return arrayList.get(i);
            }
        }
        return null;
    }

}

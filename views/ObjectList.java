package views;

import java.util.ArrayList;
import java.util.List;

abstract class GUIObjectList extends GUIObject{

    List<GUIObject> items;

    GUIObjectList(){
        //constructor
        items = new ArrayList<GUIObject>();
    }

    public addItem(GUIObject item){
        items.add(item);
    }
}
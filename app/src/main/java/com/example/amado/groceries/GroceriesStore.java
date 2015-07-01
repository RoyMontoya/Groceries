package com.example.amado.groceries;

import java.util.ArrayList;

/**
 * Created by Amado on 29/06/2015.
 */
public class GroceriesStore {

    private static ArrayList<Item> sGroceries;

    public static ArrayList<Item> getGroceries() {
        if(sGroceries== null){
            sGroceries= new ArrayList<Item>();
        }
        return sGroceries;
    }

    public static void addItem(Item item){
        sGroceries.add(item);
    }

    public static void removeItem(Item item){
        sGroceries.remove(item);
    }

}

package com.example.amado.groceries;

import java.util.ArrayList;

/**
 * Created by Amado on 29/06/2015.
 */
public class GroceriesStore {

    private static ArrayList<Grocery> sGroceries;

    public static ArrayList<Grocery> getGroceries() {
        if(sGroceries== null){
            sGroceries= new ArrayList<Grocery>();
        }
        return sGroceries;
    }

}

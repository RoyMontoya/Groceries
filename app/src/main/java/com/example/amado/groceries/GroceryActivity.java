package com.example.amado.groceries;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class GroceryActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        Bundle args = getIntent().getExtras();
        android.app.FragmentManager fm = getFragmentManager();
        GroceryFragment fragment = (GroceryFragment)fm.findFragmentById(R.id.container2);
        if(fragment==null){
            fragment = new GroceryFragment();
        }
        fm.beginTransaction().add(R.id.container2, fragment)
                .commit();
    }

}
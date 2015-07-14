package com.example.amado.groceries;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ParseItemView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_item_view);
        android.app.FragmentManager fm = getFragmentManager();
        ParseItemViewFragment fragment = (ParseItemViewFragment)fm.findFragmentById(R.id.container3);
        if(fragment==null){
            fragment = new ParseItemViewFragment();
        }
        if(fragment.isAdded()){
            return;
        }
        fm.beginTransaction().add(R.id.container3, fragment)
                .commit();
    }



}

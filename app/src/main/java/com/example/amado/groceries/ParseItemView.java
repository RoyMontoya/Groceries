package com.example.amado.groceries;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ParseItemView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_item_view);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.title_text));
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_parse_item_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                    finish();
                    return true;
        }
        return super.onOptionsItemSelected(item);

    }
}

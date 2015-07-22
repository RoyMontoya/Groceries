package com.example.amado.groceries;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ItemViewActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        toolbar.setTitleTextColor(getResources().getColor(R.color.title_text));
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        Bundle args = getIntent().getExtras();
        FragmentManager fm = getSupportFragmentManager();
        ItemViewFragment fragment = (ItemViewFragment)fm.findFragmentById(R.id.container2);
        if(fragment==null){
            fragment = new ItemViewFragment();
        }
        if(fragment.isAdded()){
            return;
        }
        fm.beginTransaction().add(R.id.container2, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(ItemViewFragment.nameEmpty){
            Toast.makeText(this, R.string.clean_name_toast, Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }else {
            ItemViewFragment.saveItem();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_view, menu);
         return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(ItemViewFragment.nameEmpty){
                    Toast.makeText(this, R.string.clean_name_toast, Toast.LENGTH_LONG).show();
                    finish();
                    return true;
                }else {
                    ItemViewFragment.saveItem();
                   finish();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
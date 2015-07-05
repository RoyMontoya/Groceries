package com.example.amado.groceries;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ItemViewActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        setContentView(R.layout.activity_item);
        Bundle args = getIntent().getExtras();
        android.app.FragmentManager fm = getFragmentManager();
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
        getMenuInflater().inflate(R.menu.menu_item, menu);
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
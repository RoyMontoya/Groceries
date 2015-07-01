package com.example.amado.groceries;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;


public class ItemViewActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Bundle args = getIntent().getExtras();
        android.app.FragmentManager fm = getFragmentManager();
        ItemViewFragment fragment = (ItemViewFragment)fm.findFragmentById(R.id.container2);
        if(fragment==null){
            fragment = new ItemViewFragment();
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
            ItemViewFragment.saveGrocery();
            super.onBackPressed();
        }
    }
}
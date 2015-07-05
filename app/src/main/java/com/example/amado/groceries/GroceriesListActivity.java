package com.example.amado.groceries;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class GroceriesListActivity extends ActionBarActivity {

    private GroceriesListFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries_list);
        android.app.FragmentManager fm =getFragmentManager();
        mFragment = (GroceriesListFragment)fm.findFragmentById(R.id.container);
        if(mFragment==null){
            mFragment = new GroceriesListFragment();
        }
        if(mFragment.isAdded()){
            return;
        }
        fm.beginTransaction().add(R.id.container,mFragment )
                .commit();
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
    }
}

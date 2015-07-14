package com.example.amado.groceries;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class GroceriesListActivity extends ActionBarActivity implements MaterialTabListener{
    private MaterialTabHost mTabHost;
    private Fragment mFragment;
    private GroceriesListFragment mGroceriesList;
    private CloudListFragment mCloudList;
    private android.app.FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries_list);

        mFragmentManager = getFragmentManager();
        mTabHost = (MaterialTabHost)findViewById(R.id.tabHost);
        mTabHost.addTab(mTabHost.newTab().setText(getString(R.string.list)).setTabListener(this));
        mTabHost.addTab(mTabHost.newTab().setText(getString(R.string.explore)).setTabListener(this));


       mGroceriesList = (GroceriesListFragment) mFragmentManager.findFragmentById(R.id.container);
        mFragment=mGroceriesList;
        if(mGroceriesList==null){
            mGroceriesList = new GroceriesListFragment();
            mFragment=mGroceriesList;
        }
        if(mGroceriesList.isAdded()){
            return;
        }
        mFragmentManager.beginTransaction().add(R.id.container,mGroceriesList )
                .commit();
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        mTabHost.setSelectedNavigationItem(materialTab.getPosition());
        switch (materialTab.getPosition()) {
            case 0:
                if(mFragment== mGroceriesList) return;
            mFragmentManager.beginTransaction().replace(R.id.container, mGroceriesList)
                    .commit();
                mFragment =mGroceriesList;
                break;
            case 1:
                if(mFragment== mCloudList) return;
                if(mCloudList==null){
                    mCloudList = new CloudListFragment();
                }
                mFragmentManager.beginTransaction().replace(R.id.container, mCloudList)
                        .commit();
                mFragment = mCloudList;
        }
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }
}

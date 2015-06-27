package com.example.amado.groceries;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class GroceriesListFragment extends ListFragment {
    private static final String TAG = "GroceriesListFragment";


    public GroceriesListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        Grocery grocery = new Grocery();
        grocery.setName("avocado");
        grocery.setQuantity(3);
        grocery.setUnit(Grocery.UNIT_PZ);
        ArrayList<Grocery> groceries = new ArrayList<>();
        groceries.add(grocery);
        GroceriesAdapter adapter = new GroceriesAdapter(groceries);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_groceries_list, container, false);


        return v;
    }

    private class GroceriesAdapter extends ArrayAdapter<Grocery>{

        public GroceriesAdapter(ArrayList<Grocery> groceries) {
            super(getActivity(), R.layout.list_grocery_item, R.id.name_grocery, groceries);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_grocery_item, null);
            }


            Grocery grocery = getItem(position);

                TextView groceryName = (TextView)convertView.findViewById(R.id.name_grocery);
                groceryName.setText(grocery.getName());
                TextView groceryQuantity =(TextView)convertView.findViewById(R.id.quantity_grocery);
                groceryQuantity.setText(String.valueOf(grocery.getQuantity()));
                TextView groceryUnit =(TextView)convertView.findViewById(R.id.unit_grocery);
                groceryUnit.setText(grocery.getUnit());

                return convertView;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_groceries_list, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_grocery:
                Intent i = new Intent(getActivity(), GroceryActivity.class);
                startActivity(i);
                return true;
            default:
               return false;
        }
    }
}

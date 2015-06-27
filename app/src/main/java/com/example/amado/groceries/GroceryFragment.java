package com.example.amado.groceries;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A placeholder fragment containing a simple view.
 */
public class GroceryFragment extends android.app.Fragment implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "GroceryFragment";
    private Grocery mGrocery;

    public GroceryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_grocery, container, false);

        mGrocery = new Grocery();

        Spinner unitsSpinner =(Spinner) v.findViewById(R.id.spinner_units);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.units_options,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitsSpinner.setAdapter(adapter);
        unitsSpinner.setOnItemSelectedListener(this);

        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mGrocery.setUnit(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mGrocery.setUnit(parent.getItemAtPosition(0).toString());
    }
}

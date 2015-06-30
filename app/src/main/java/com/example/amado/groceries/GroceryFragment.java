package com.example.amado.groceries;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A placeholder fragment containing a simple view.
 */
public class GroceryFragment extends android.app.Fragment implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "GroceryFragment";
    public static final String INDEX = "array_index";
    private Grocery mGrocery;

    public GroceryFragment() {
    }

    public static GroceryFragment newInstance(int index){
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        GroceryFragment fragment = new GroceryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.grocery_info);
        Bundle args =getActivity().getIntent().getExtras();
        if(args == null){
            mGrocery = new Grocery();
        }else{
            int position = args.getInt(GroceriesListFragment.POSITION);
            mGrocery = GroceriesStore.getGroceries().get(position);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_grocery, container, false);

        Spinner unitsSpinner =(Spinner) v.findViewById(R.id.spinner_units);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.units_options,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitsSpinner.setAdapter(adapter);
        unitsSpinner.setOnItemSelectedListener(this);

        EditText nameEdit = (EditText)v.findViewById(R.id.name_editText);
        nameEdit.setText(mGrocery.getName());
  //      EditText qtyEdit = (EditText)v.findViewById(R.id.qty_editText);
//        qtyEdit.setText(mGrocery.getQuantity());
        EditText notesEdit = (EditText)v.findViewById(R.id.notes_editText);
        notesEdit.setText(mGrocery.getNotes());







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

package com.example.amado.groceries;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class GroceriesListFragment extends ListFragment implements android.app.LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "GroceriesListFragment";
    public static final String POSITION = "position";
    public ArrayList<Item> mGroceries;
    private GroceriesAdapter mAdapter;
    public static ItemDataSource mDataSource;


    public GroceriesListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        getLoaderManager().initLoader(0, null, this);
        mDataSource=new ItemDataSource(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_groceries_list, container, false);



        ListView list =(ListView)v.findViewById(android.R.id.list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.item_list_context_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                GroceriesAdapter adapter =(GroceriesAdapter)getListAdapter();
                switch (item.getItemId()) {
                    case R.id.item_delete:
                        for (int i = mAdapter.getCount(); i >= 0; i--) {
                            if (getListView().isItemChecked(i)) {
                                mDataSource.deleteItem(adapter.getItem(i));
                                getLoaderManager().restartLoader(0,null,GroceriesListFragment.this);
                            }
                        }
                        mode.finish();
                        adapter.notifyDataSetChanged();
                        return true;

                    default:
                        return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });




        return v;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       Intent i = new Intent(getActivity(), ItemViewActivity.class);
        i.putExtra(POSITION, position);
        startActivity(i);
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new GroceriesCursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        ArrayList<Item> items = mDataSource.cursorToItems(data);
        GroceriesStore.setGroceries(items);
        mAdapter = new GroceriesAdapter(items);

        setListAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        setListAdapter(null);
    }


    private class GroceriesAdapter extends ArrayAdapter<Item>{

        public GroceriesAdapter(ArrayList<Item> groceries) {
            super(getActivity(), R.layout.list_item_groceries, R.id.name_item, groceries);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_groceries, null);
            }


            final Item item = getItem(position);

                TextView itemName = (TextView)convertView.findViewById(R.id.name_item);
                itemName.setText(item.getName());
                TextView itemQuantity =(TextView)convertView.findViewById(R.id.quantity_item);
                itemQuantity.setText(String.valueOf(item.getQuantity()));
                TextView itemUnit =(TextView)convertView.findViewById(R.id.unit_item);
                itemUnit.setText(item.getUnit());
                final CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox_item);
                checkBox.setChecked(item.isChecked());
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setChecked(checkBox.isChecked());
                    }
                });



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
            case R.id.add_item:
                Intent i = new Intent(getActivity(), ItemViewActivity.class);
                startActivity(i);
                return true;
            default:
               return false;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.item_list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = menuInfo.position;
        Item selectedItem = mAdapter.getItem(position);
        switch(item.getItemId()){
            case R.id.item_delete:
            GroceriesStore.removeItem(selectedItem);
                mAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }



    public void updateUI(){
        ((GroceriesAdapter)getListAdapter()).notifyDataSetChanged();
    }
}

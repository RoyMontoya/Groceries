package com.example.amado.groceries;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Amado on 02/07/2015.
 */
public class GroceriesCursorLoader extends SQLiteCursorLoader{


    public GroceriesCursorLoader(Context context) {
        super(context);
    }

    @Override
    protected Cursor loadCursor() {
        return GroceriesListFragment.mDataSource.allItemsCursor();
    }
}

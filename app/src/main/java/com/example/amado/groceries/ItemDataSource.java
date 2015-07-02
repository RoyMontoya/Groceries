package com.example.amado.groceries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amado on 01/07/2015.
 */
public class ItemDataSource {
    private static final String TAG ="ItemDataSource";
    private DbHelper mDbHelper;
    private String[] allColumns ={DbHelper.COLUMN_ID, DbHelper.COLUMN_NAME, DbHelper.COLUMN_QTY, DbHelper.COLUMN_UNIT
    , DbHelper.COLUMN_NOTES, DbHelper.COLUMN_DONE};


    public ItemDataSource(Context context){
        mDbHelper = DbHelper.getSingleton(context);
    }

    public void createItem(Item item){
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME, item.getName());
        values.put(DbHelper.COLUMN_QTY, item.getQuantity());
        values.put(DbHelper.COLUMN_UNIT, item.getUnit());
        values.put(DbHelper.COLUMN_NOTES, item.getNotes());
        values.put(DbHelper.COLUMN_DONE, String.valueOf(item.isChecked()));
        item.setId(mDbHelper.getWritableDatabase().insert(DbHelper.GROCERIES_TABLE, null, values));
    }

    public void deleteItem(Item item){
        String[] args = {String.valueOf(item.getId())};
        mDbHelper.getWritableDatabase().delete(DbHelper.GROCERIES_TABLE, mDbHelper.COLUMN_ID + "=?", args);
    }

    public void updateItem(Item item){
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME, item.getName());
        values.put(DbHelper.COLUMN_QTY, item.getQuantity());
        values.put(DbHelper.COLUMN_UNIT, item.getUnit());
        values.put(DbHelper.COLUMN_NOTES, item.getNotes());
        values.put(DbHelper.COLUMN_DONE, String.valueOf(item.isChecked()));
        String[] args = {String.valueOf(item.getId())};
        mDbHelper.getWritableDatabase().update(DbHelper.GROCERIES_TABLE, values, mDbHelper.COLUMN_ID+"=?", args);
    }


    public ArrayList<Item> getAllItems(){
        Cursor cursor = allItemsCursor();
        return cursorToItems(cursor);
    }

    private Item cursorToItem(Cursor cursor){
        Item item = new Item();
        item.setId(cursor.getInt(0));
        item.setName(cursor.getString(1));
        item.setQuantity(cursor.getInt(2));
        item.setUnit(cursor.getString(3));
        item.setNotes(cursor.getString(4));
        item.setChecked(stringToBoolean(cursor.getString(5)));
        return item;
    }

    public ArrayList<Item> cursorToItems(Cursor cursor){
        ArrayList<Item> items = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Item item = cursorToItem(cursor);
            items.add(item);
            cursor.moveToNext();
        }
        return items;
    }


    private Cursor allItemsCursor(){
        return mDbHelper.getReadableDatabase().query(DbHelper.GROCERIES_TABLE, allColumns, null, null, null, null, null);
    }

    private boolean stringToBoolean(String text){
        if(text.equals("true")) {
            return true;
        }
        return false;
    }


}

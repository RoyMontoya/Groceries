package com.example.amado.groceries;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Amado on 01/07/2015.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Groceries.sqlite";
    private static final int TABLE_VERSION = 1;
    public static final String GROCERIES_TABLE = "groceries";
    public static final String COLUMN_ID ="_id";
    public static final String COLUMN_NAME= "name";
    public static final String COLUMN_QTY="quantity";
    public static final String COLUMN_UNIT="unit";
    public static final String COLUMN_NOTES="notes";
    public static final String COLUMN_DONE="done";
    public static  DbHelper singleton = null;

    public DbHelper(Context context){
        super(context, DB_NAME, null, TABLE_VERSION);
    }

    public synchronized static DbHelper getSingleton(Context context) {
        if(singleton==null){
            singleton= new DbHelper(context.getApplicationContext());
        }
        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+GROCERIES_TABLE+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME+" TEXT, " +
                        COLUMN_QTY+" DOUBLE, "+
                        COLUMN_UNIT+" TEXT, " +
                        COLUMN_NOTES+" TEXT, "+
                        COLUMN_DONE+" TEXT"+")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

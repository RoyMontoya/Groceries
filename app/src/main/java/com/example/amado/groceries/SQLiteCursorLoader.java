package com.example.amado.groceries;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Amado on 02/07/2015.
 */
public abstract class SQLiteCursorLoader extends AsyncTaskLoader<Cursor> {

        private Cursor mCursor;

        public SQLiteCursorLoader(Context context){
            super(context);
        }

        protected abstract Cursor loadCursor();


        @Override
        public Cursor loadInBackground() {
            Cursor cursor = loadCursor();
            if(cursor!=null){
                cursor.getCount();
            }
            return cursor;
        }

    @Override
    public void deliverResult(Cursor data) {
        super.deliverResult(data);
    }

    @Override
        protected void onStartLoading() {
            if(mCursor!=null){
                deliverResult(mCursor);
            }
            if(takeContentChanged() || mCursor == null){
                forceLoad();
            }
        }

        @Override
        protected void onStopLoading() {
            cancelLoad();
        }

    @Override
    public void onCanceled(Cursor data) {
        super.onCanceled(data);
    }

    @Override
        protected void onReset() {
            super.onReset();

            onStopLoading();

            if(mCursor!= null &&!mCursor.isClosed()){
                mCursor.close();
            }
            mCursor=null;
        }
    }
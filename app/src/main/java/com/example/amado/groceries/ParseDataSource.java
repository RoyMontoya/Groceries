package com.example.amado.groceries;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by Amado on 09/07/2015.
 */
public class ParseDataSource {
    private static final String TAG = "ParseDataSource";
    private static final String PARSE_ITEM_CLASS = "Item";
    private static Listener mListener;


    public ParseDataSource(Listener listener){
        mListener = listener;
    }


    public static void saveToParse(ParseItem parseItem){
        parseItem.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.d(TAG, "item uploaded");
                }
            }
        });
    }

    public static void queryObjectId(String objectId){
        final ParseItem[] parseItem = {new ParseItem()};
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(PARSE_ITEM_CLASS);
        query.whereEqualTo("objectId", objectId);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                mListener.onFetchedObject(parseItem[0] = (ParseItem) list.get(0));
                Log.d(TAG, "the name is:" + parseItem[0].getName());
            }
        });

    }

    public interface Listener {
        public void onFetchedObject(ParseItem parseItem);
    }
}

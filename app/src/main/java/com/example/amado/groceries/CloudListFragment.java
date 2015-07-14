package com.example.amado.groceries;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by Amado on 10/07/2015.
 */
public class CloudListFragment extends ListFragment {
    private ParseQueryAdapter mAdapter;
    public static final String OBJECT_ID= "objectId";
    private static final String TAG = "CloudListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ParseItemAdapter(getActivity());
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String objectId = mAdapter.getItem(position).getObjectId();
        Log.d(TAG, "the id is: "+objectId);
        Intent i = new Intent(getActivity(), ParseItemView.class);
        i.putExtra(OBJECT_ID, objectId);
        startActivity(i);
    }

    public class ParseItemAdapter extends ParseQueryAdapter<ParseItem>{
     private static final String PARSE_ITEM_CLASS = "Item";


     public ParseItemAdapter(Context context) {
         super(context, new ParseQueryAdapter.QueryFactory<ParseItem>(){

             @Override
             public ParseQuery<ParseItem> create() {
                 ParseQuery query = new ParseQuery(PARSE_ITEM_CLASS);
                 return query;
             }
         });
     }


     @Override
     public View getItemView(ParseItem parseItem, View v, ViewGroup parent) {
         if(v == null){
             v= View.inflate(getContext(), R.layout.list_parse_item, null);
         }

         super.getItemView(parseItem,v, parent );

         ParseImageView itemPreview = (ParseImageView)v.findViewById(R.id.parse_image);
         ParseFile photoFile = parseItem.getFile();
         if(photoFile!= null){
             itemPreview.setParseFile(photoFile);
             itemPreview.loadInBackground(new GetDataCallback() {
                 @Override
                 public void done(byte[] bytes, ParseException e) {

                 }
             });
         }
         TextView parseItemName = (TextView)v.findViewById(R.id.parse_name_item);
         parseItemName.setText(parseItem.getName());

         return v;
     }
 }
}

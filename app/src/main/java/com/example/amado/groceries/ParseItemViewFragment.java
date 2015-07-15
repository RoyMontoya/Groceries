package com.example.amado.groceries;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


/**
 * A placeholder fragment containing a simple view.
 */
public class ParseItemViewFragment extends android.app.Fragment  implements ParseDataSource.Listener{
    private static final String TAG = "ParseItemViewFragment";
    private static final String PARSE_ITEM_CLASS = "Item";
    private String mObjectId;
    private ParseImageView mParseImage;
    private TextView mNameText;
    private byte[] mImageBytes;
    private Item mItem;



    public ParseItemViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mObjectId = getActivity().getIntent().getStringExtra(CloudListFragment.OBJECT_ID);
        Log.d(TAG, mObjectId);
        ParseDataSource dataSource = new ParseDataSource(this);
        dataSource.queryObjectId(mObjectId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_parse_item_view, container, false);

        mParseImage = (ParseImageView) v.findViewById(R.id.parse_view_image);
        mNameText = (TextView) v.findViewById(R.id.parse_view_name);
        Button addButton = (Button)v.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mItem = new Item();
             mItem.setPhotoFile(byteToFile());
                mItem.setName((String) mNameText.getText());
                GroceriesListFragment.mDataSource.createItem(mItem);
                getActivity().finish();
            }
        });

        return v;

    }

    @Override
    public void onFetchedObject(ParseItem parseItem) {
        Log.d(TAG, "The name is: " + parseItem.getParseFile("file"));
        mNameText.setText(parseItem.getName());
        ParseFile photoFile = parseItem.getFile();
        if(photoFile!= null){
            mParseImage.setParseFile(photoFile);
            mParseImage.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {
                    mImageBytes= bytes;
                }
            });
        }

    }

    private File byteToFile(){
        FileInputStream fileInputStream=null;
        mItem.createFile();

        File file = mItem.getPhotoFile();

        byte[] bFile = mImageBytes;

        try {
            //convert array of bytes into file
            FileOutputStream fileOuputStream =
                    new FileOutputStream(file);
            fileOuputStream.write(bFile);
            fileOuputStream.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return file;
    }




}

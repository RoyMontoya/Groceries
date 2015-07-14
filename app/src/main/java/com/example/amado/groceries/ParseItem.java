package com.example.amado.groceries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Amado on 09/07/2015.
 */
@ParseClassName("Item")
public class ParseItem extends ParseObject {
private static final String TAG = "ParseItem";
    private String name;
    private ParseFile file;
    private String fileName;


    public ParseItem(){

    }


    public ParseItem(ParseObject parseObject){
        this.name=parseObject.getString("name");
        this.file=parseObject.getParseFile("file");
    }


    public ParseItem(Item item){
        setName(item.getName());
        Log.d(TAG, item.getName());
        if(item.getPhotoFile()!= null) {
            setFile(scaledFile(item.getPhotoFile()));
            this.fileName = trimName(item.getPhotoFile().getPath());
        }
        Log.d(TAG, trimName(item.getPhotoFile().getPath()));
        getObjectId();
    }

   public ParseFile getFile() {
        return getParseFile("file");
    }

   public void setFile(ParseFile file) {
        put("file", file);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }



    public ParseFile scaledFile(File file){
        Bitmap photoBitmap = BitmapFactory.decodeFile(file.getPath());

        Bitmap scaledPhoto = Bitmap.createScaledBitmap(photoBitmap, 200, 200 * (photoBitmap.getHeight() / photoBitmap.getWidth()), false);


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        scaledPhoto.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] scaledData = bos.toByteArray();

        ParseFile parseFile = new ParseFile(fileName, scaledData);

        return parseFile;
    }

    public String trimName(String photoPath){
        String photoName = photoPath.substring(25);
        return photoName;
    }
}

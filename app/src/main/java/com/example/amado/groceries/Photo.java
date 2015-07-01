package com.example.amado.groceries;

import android.os.Environment;

import java.io.File;
import java.util.UUID;

/**
 * Created by Amado on 30/06/2015.
 */
public class Photo {
    private UUID id;
    private File file;
    private static final File sDirectory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

    public Photo(){
        id= UUID.randomUUID();
    }

    public File getFile(){
        if(file==null) {
            file = new File(sDirectory, id.toString());
        }
        return file;
    }
}

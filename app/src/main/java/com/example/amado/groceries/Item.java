package com.example.amado.groceries;

import android.os.Environment;

import com.parse.ParseObject;

import java.io.File;
import java.util.UUID;

/**
 * Created by Amado on 25/06/2015.
 */
public class Item {
    private long id;
    private String name;
    private String unit;
    private int Quantity;
    private String notes;
    private boolean checked;
    private File photo;

    public static final String UNIT_KG = "Kg";
    public static final String UNIT_GR = "Gr";
    public static final String UNIT_PZ = "Pz";
    public static final String UNIT_DZ = "Dz";
    private static final File sDirectory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

    public void createFile(){
        photo= new File(sDirectory, UUID.randomUUID().toString()+".jpeg");
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}

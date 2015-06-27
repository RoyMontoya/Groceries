package com.example.amado.groceries;

/**
 * Created by Amado on 25/06/2015.
 */
public class Grocery {

    private String name;
    private String unit;
    private int Quantity;
    private String notes;
    private int price;

    public static final String UNIT_KG = "Kg";
    public static final String UNIT_GR = "Gr";
    public static final String UNIT_PZ = "Pz";
    public static final String UNIT_DZ = "Dz";

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

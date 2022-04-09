package com.example.proj4;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Coffee extends MenuItem{
    private static final double SHORT_PRICE = 1.69;
    private static final double TALL_PRICE = 2.09;
    private static final double GRANDE_PRICE = 2.49;
    private static final double VENTI_PRICE = 2.89;
    private static final double ADD_IN_PRICE = 0.30;

    private String size;
    private int addOnCount;
    private ArrayList<String> addOns = new ArrayList<String>();

    private boolean cream = false, syrup = false, milk = false, caramel = false, whippedCream = false;

    @Override
    public double itemPrice() {
        double retPrice = 0;
        if (this.size == "Short") {
            retPrice += SHORT_PRICE;
        } else if (this.size == "Tall") {
            retPrice += TALL_PRICE;
        } else if (this.size == "Grande") {
            retPrice += GRANDE_PRICE;
        } else if (this.size == "Venti") {
            retPrice += VENTI_PRICE;
        } else {
            return INVALID_CASE;
        }
        retPrice += (this.addOnCount * ADD_IN_PRICE);
        return retPrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAddOns(boolean setCream, boolean setSyrup, boolean setMilk,
                          boolean setCaramel, boolean setWhippedCream){
        cream = setCream;
        syrup = setSyrup;
        milk = setMilk;
        caramel = setCaramel;
        whippedCream = setWhippedCream;
    }

    @Override
    public String toString() {
        String coffeeString = "Coffee, " + size + " (" + getQuantity() + ")";
        if(hasAddOns()){
            coffeeString += ", add-ons:";
        }
        if(cream){
            coffeeString += " cream";
        }
        if(syrup){
            coffeeString += " syrup";
        }
        if(milk){
            coffeeString += " milk";
        }
        if(caramel){
            coffeeString += " caramel";
        }
        if(whippedCream){
            coffeeString += " whippedCream";
        }
        return coffeeString;
    }

    private boolean hasAddOns(){
        return cream || syrup || milk || caramel || whippedCream;
    }

    public void addAddOn(String addOn) {
        addOns.add(addOn);
        addOnCount++;
    }


}

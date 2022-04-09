package com.example.proj4;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Coffee extends MenuItem{
    private String size;
    private int addOnCount;
    private ArrayList<String> addOns = new ArrayList<String>();

    private static final double SHORT_PRICE = 1.69;
    private static final double TALL_PRICE = 2.09;
    private static final double GRANDE_PRICE = 2.49;
    private static final double VENTI_PRICE = 2.89;
    private static final double ADD_IN_PRICE = 0.30;

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

    @Override
    public String toString() {
        return "Coffee, " + size + " (" + getQuantity() + ")";
    }

    public void addAddOn(String addOn) {
        addOns.add(addOn);
        addOnCount++;
    }

    public void removeAddOn(String addOn) {
        addOns.remove(addOn);
        addOnCount--;
    }

    public int getAddOnCount() {
        return addOnCount;
    }

    public ArrayList<String> getAddOns() {
        return addOns;
    }


}

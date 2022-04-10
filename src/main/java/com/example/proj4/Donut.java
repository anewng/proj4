package com.example.proj4;

import java.text.DecimalFormat;

public abstract class Donut extends MenuItem {
    private String flavor;

    /**
     Constructor creates a Donut object.
     @param flavor the flavor of the Donut
     */
    public Donut(String flavor) {
        this.flavor = flavor;
    }

    /**
     Gets the flavor of the Cake Donut
     @return String the flavor
     */
    public String getFlavor() {
        return this.flavor;
    }

    /**
     Converts a donut to a string, the abstract method that will be overridden by its child class methods
     @return string representation of cake donut.
     */
    @Override
    public abstract String toString();

    /**
     Returns the donut raw price, the abstract method that will be overridden by its child class methods
     @return double cost of the item.
     */
    @Override
    public abstract double itemPrice();
}

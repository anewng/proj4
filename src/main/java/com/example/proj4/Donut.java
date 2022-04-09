package com.example.proj4;

import java.text.DecimalFormat;

public abstract class Donut extends MenuItem {
    protected String flavor;
    protected double price;

    public Donut(String flavor) {
        this.flavor = flavor;
    }
    public String getFlavor() {
        return this.flavor;
    }

    @Override
    public abstract String toString();

    @Override
    public double itemPrice() {
        return price;
    }
}

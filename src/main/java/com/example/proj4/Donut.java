package com.example.proj4;

import java.text.DecimalFormat;

public abstract class Donut extends MenuItem {
    protected String flavor;
    protected double price;

    public String getFlavor() {
        return this.flavor;
    }

    public Donut(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public abstract String toString();
}

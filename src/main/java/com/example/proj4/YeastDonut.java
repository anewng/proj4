package com.example.proj4;

import java.text.DecimalFormat;

public class YeastDonut extends Donut{

    private static final String FLAVOR_1 = "Fungi";
    private static final String FLAVOR_2 = "E coli";
    private static final String FLAVOR_3 = "Salmonella";

    private static final double YEAST_PRICE = 1.59;

    public YeastDonut(String flavor) {
        super(flavor);
        price = YEAST_PRICE;

    }

    @Override
    public double itemPrice() {
        return YEAST_PRICE;
    }

    @Override
    public String toString() {
        return "Yeast Donut, " + getFlavor() + " (" + getQuantity() + ")";
    }

}

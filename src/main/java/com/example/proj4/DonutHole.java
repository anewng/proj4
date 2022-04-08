package com.example.proj4;

import java.text.DecimalFormat;

public class DonutHole extends Donut{

    private static final String FLAVOR_1 = "Yas";
    private static final String FLAVOR_2 = "Slay";
    private static final String FLAVOR_3 = "Purr";

    private static final double HOLE_PRICE = 0.39;

    public DonutHole(String flavor) {
        super(flavor);
        price = HOLE_PRICE;
    }

    @Override
    public double itemPrice() {
        return HOLE_PRICE;
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        return "Donut Hole, " + getFlavor() + ", " + d.format(itemPrice()) + ", amount: " + getQuantity();
    }
}

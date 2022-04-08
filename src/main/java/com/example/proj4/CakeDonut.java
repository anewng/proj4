package com.example.proj4;

import java.text.DecimalFormat;

public class CakeDonut extends Donut{

    private static final String FLAVOR_1 = "Red Velvet";
    private static final String FLAVOR_2 = "Blueberry Chiffon";
    private static final String FLAVOR_3 = "Raspberry Jam Swirl";

    private static final double CAKE_PRICE = 1.79;

    public CakeDonut(String flavor) {
        super(flavor);
        price = CAKE_PRICE;
    }

    @Override
    public double itemPrice() {
        return CAKE_PRICE;
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        return "Cake Donut, " + getFlavor() + ", " + d.format(itemPrice()) + ", amount: " + getQuantity();
    }

}

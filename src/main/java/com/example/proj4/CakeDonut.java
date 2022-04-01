package com.example.proj4;

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

}

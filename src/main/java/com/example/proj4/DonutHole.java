package com.example.proj4;

public class DonutHole extends Donut{
    private static final String FLAVOR_1 = "Yas";
    private static final String FLAVOR_2 = "Slay";
    private static final String FLAVOR_3 = "Purr";

    private static final double HOLE_PRICE = 0.39;

    /**
     Constructor creates a DonutHole object.
     @param flavor the flavor of the Donut Hole
     */
    public DonutHole(String flavor) {
        super(flavor);
    }

    /**
     Returns the raw price of the donut hole, excluding taxes
     @return double the value of the price.
     */
    @Override
    public double itemPrice() {
        return HOLE_PRICE;
    }

    /**
     Converts a donut hole to a string, with type of donut, flavor, and quantity.
     @return string representation of donut hole.
     */
    @Override
    public String toString() {
        return "Donut Hole, " + getFlavor() + " (" +  getQuantity() + ")";
    }
}

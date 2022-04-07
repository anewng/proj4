package com.example.proj4;

import java.text.DecimalFormat;

public class Coffee extends MenuItem{
    private String size;
    private int addOnCount;

    private static final double SMALL_PRICE = 1.69;
    private static final double MED_PRICE = 2.09;
    private static final double LARGE_PRICE = 2.49;
    private static final double ADD_IN_PRICE = 0.30;

    @Override
    public double itemPrice() {
        double retPrice = 0;
        if (this.size == "Small") {
            retPrice += SMALL_PRICE;
        } else if (this.size == "Medium") {
            retPrice += MED_PRICE;
        } else if (this.size == "Large") {
            retPrice += LARGE_PRICE;
        } else {
            return INVALID_CASE;
        }
        retPrice += this.addOnCount * ADD_IN_PRICE;
        return retPrice;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        return "Coffee, " + size + ", " + d.format(itemPrice());
    }

}

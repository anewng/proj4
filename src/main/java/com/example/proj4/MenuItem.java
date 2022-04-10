package com.example.proj4;

public abstract class MenuItem {

    private int quantity;
    protected static final double INVALID_CASE = -1;
    protected static final String INVALID_TYPE = "";

    public abstract double itemPrice();

    /**
     Converts a menu item to a string, depending on if it is a donut or coffee item
     @return string representation of the menu item.
     */
    @Override
    public String toString() {
        if (this instanceof Coffee) {
            Coffee coffee = (Coffee) this;
            return coffee.toString();
        } else if (this instanceof Donut) {
            Donut donut = (Donut) this;
            return donut.toString();
        } else {
            return INVALID_TYPE;
        }
    }

    /**
     Sets the quantity of the Menu Item
     */
    public void setQuantity(int amount){
        quantity = amount;
    }

    /**
     Gets the quantity of the Menu Item
     @return int returns the quantity
     */
    public int getQuantity(){
        return quantity;
    }


}

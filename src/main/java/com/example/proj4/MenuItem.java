package com.example.proj4;

public abstract class MenuItem {

    private int quantity;
    protected static final double INVALID_CASE = -1;
    protected static final String INVALID_TYPE = "";

    public MenuItem() {}

    public abstract double itemPrice();

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

    public void setQuantity(int amount){
        quantity = amount;
    }

    public int getQuantity(){
        return quantity;
    }


}

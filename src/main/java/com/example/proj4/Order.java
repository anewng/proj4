package com.example.proj4;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order implements Customizable {
    private ArrayList<MenuItem> order;
    private int orderNumber;

    public Order(){
        order = new ArrayList<MenuItem>();
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<MenuItem> getOrderArray(){
        return order;
    }

    @Override
    public boolean addObject(Object obj) {
        MenuItem newOrder = (MenuItem) obj;
        order.add(newOrder);
        return true;
    }

    @Override
    public boolean remove(Object obj) {

        return false;
    }
}

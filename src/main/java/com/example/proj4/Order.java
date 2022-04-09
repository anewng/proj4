package com.example.proj4;

import java.text.DecimalFormat;
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

    public double getSubtotal(){
        double subtotal = 0;
        for (int i = 0; i < order.size(); i++) {
            subtotal += order.get(i).itemPrice()
                    * order.get(i).getQuantity();
        }
        return subtotal;
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

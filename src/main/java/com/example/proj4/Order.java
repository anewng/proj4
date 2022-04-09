package com.example.proj4;

import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> order;
    private int orderNumber;

    public Order(ArrayList<MenuItem> orderList){
        order = orderList;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}

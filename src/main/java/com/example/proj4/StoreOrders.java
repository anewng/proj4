package com.example.proj4;

import java.util.ArrayList;

public class StoreOrders implements Customizable {

    private ArrayList<Order> orders;

    public StoreOrders(){
        orders = new ArrayList<Order>();
    }

    public ArrayList<Order> getOrders(){
        return orders;
    }

    @Override
    public boolean addObject(Object obj) {
        Order newOrder = (Order) obj;
        int lastIndex = orders.size() - 1;
        if(orders.size() == 0){
            newOrder.setOrderNumber(1);
        }else{
            newOrder.setOrderNumber(orders.get(lastIndex).getOrderNumber() + 1);
        }
        orders.add(newOrder);
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            Order removeOrder = (Order) obj;
            int orderNumber = removeOrder.getOrderNumber();
            for(int i = 0; i < orders.size() - 1; i++){
                if(orders.get(i).getOrderNumber() == orderNumber){
                    orders.remove(i);
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}




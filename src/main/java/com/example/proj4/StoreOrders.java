package com.example.proj4;

import java.util.ArrayList;

public class StoreOrders implements Customizable {

    private ArrayList<Order> orders;

    /**
     Constructor creates a StoreOrder object.
     */
    public StoreOrders(){
        orders = new ArrayList<Order>();
    }

    /**
     Gets the arraylist corresponding to the StoreOrder.
     @return ArrayList<Order> the arraylist of orders
     */
    public ArrayList<Order> getOrders(){
        return orders;
    }

    /**
     Adds an order to current store order
     @return boolean denoting if the order was successfully added or not.
     */
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

    /**
     Removes an order from the store order
     @return boolean denoting if the order was successfully removed or not.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            Order removeOrder = (Order) obj;
            int orderNumber = removeOrder.getOrderNumber();
            for(int i = 0; i < orders.size(); i++){
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

    public String toString(){
        String string = "";
        for(int i = 0; i < orders.size(); i++){
            string += "index: " + i + " order number: " + orders.get(i).getOrderNumber() + "\n";
            for(int j = 0; j < orders.get(i).getOrderArray().size(); j++){
                string += orders.get(i).getOrderArray().get(j).toString() + "\n";
            }
        }
        return string;
    }
}




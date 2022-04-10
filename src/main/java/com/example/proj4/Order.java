package com.example.proj4;

import javafx.scene.control.Menu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Order implements Customizable {
    private ArrayList<MenuItem> order;
    private int orderNumber;

    private static final int AUTOMATIC_REMOVAL_INDEX = -1;

    /**
     Constructor creates an Order object.
     */
    public Order(){
        order = new ArrayList<MenuItem>();
    }

    /**
     Sets the order number of the Order object.
     @param orderNumber number of the Order object
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     Gets the order number of the Order object.
     @return int number of the Order object
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     Gets the order array corresponding to the Order object.
     @return ArrayList<MenuItem> order array
     */
    public ArrayList<MenuItem> getOrderArray(){
        return order;
    }

    /**
     Adds a menu item to the order
     @return boolean denoting if the addition was successful or not.
     */
    @Override
    public boolean addObject(Object obj) {
        MenuItem newOrder = (MenuItem) obj;
        order.add(newOrder);
        return true;
    }

    /**
     Removes a menu item from the order
     @return boolean denoting if the removal was successful or not.
     */
    @Override
    public boolean remove(Object obj) {
        String itemType = "", flavorSizeToken = "";

        MenuItem menuItem = (MenuItem) obj;
        if(menuItem instanceof Donut){
            Donut donutItem = (Donut) menuItem;
            if(donutItem instanceof DonutHole) {
                itemType = "DonutHole";
            } else if (donutItem instanceof YeastDonut) {
                itemType = "YeastDonut";
            } else if (donutItem instanceof CakeDonut) {
                itemType = "CakeDonut";
            }
            flavorSizeToken = donutItem.getFlavor();
        }else if (menuItem instanceof Coffee){
            Coffee coffeeItem = (Coffee) menuItem;
            itemType = "Coffee";
            flavorSizeToken = coffeeItem.getSize();
        }

        for(int i = 0; i < order.size(); i++){
            if(order.get(i) instanceof Coffee && itemType.equals("Coffee")
                    && ((Coffee) order.get(i)).getSize().equals(flavorSizeToken)){
                order.remove(i);
                return true;
            } else if(order.get(i) instanceof Donut &&
                    (itemType.equals("DonutHole") || itemType.equals("CakeDonut") || itemType.equals("YeastDonut"))
                    && ((Donut) order.get(i)).getFlavor().equals(flavorSizeToken)){
                order.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     Calculates the raw, pre-tax subtotal of the order, according to the quantity and price of each menu item
     @return double the calculated subtotal.
     */
    public double getSubtotal(){
        double subtotal = 0;
        for (int i = 0; i < order.size(); i++) {
            subtotal += order.get(i).itemPrice()
                    * order.get(i).getQuantity();
        }
        return subtotal;
    }
}

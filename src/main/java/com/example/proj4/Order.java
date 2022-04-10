package com.example.proj4;

import javafx.scene.control.Menu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Order implements Customizable {
    private ArrayList<MenuItem> order;
    private int orderNumber;

    private static final int AUTOMATIC_REMOVAL_INDEX = -1;

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

    private String setDonutFlavor(String thirdToken){
        if(thirdToken.equals("E")){
            return "E coli";
        } else if (thirdToken.equals("Red")) {
            return "Red Velvet";
        } else if (thirdToken.equals("Blueberry")) {
            return "Blueberry Chiffon";
        } else if (thirdToken.equals("Raspberry")) {
            return "Raspberry Jam Swirl";
        } else {
            return thirdToken;
        }
    }

    public double getSubtotal(){
        double subtotal = 0;
        for (int i = 0; i < order.size(); i++) {
            subtotal += order.get(i).itemPrice()
                    * order.get(i).getQuantity();
        }
        return subtotal;
    }
}

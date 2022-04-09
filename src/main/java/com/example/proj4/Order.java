package com.example.proj4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Order {
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

    //@Override
    public boolean addObject(Object obj) {
        MenuItem newOrder = (MenuItem) obj;
        order.add(newOrder);
        return true;
    }

    /*
    @Override
    public boolean remove(Object obj) {
        String yourOrders = (String) obj;
        StringTokenizer string = new StringTokenizer(yourOrders);
        String flavorSizeToken = "";
        String itemType = setItemType(string.nextToken());

        if(itemType.equals("Coffee")){
            flavorSizeToken = string.nextToken(); // getting coffee flavor
        } else if(itemType.equals("invalid item type")) {
            return false;
        } else {
            string.nextToken();
            flavorSizeToken = string.nextToken(); //getting donut flavor
            flavorSizeToken = setDonutFlavor(flavorSizeToken);
        }

        int removalIndex = AUTOMATIC_REMOVAL_INDEX;
        for(int i = 0; i < order.size(); i++){
            if(order.get(i) instanceof Coffee && itemType.equals("Coffee")
                    && ((Coffee) order.get(i)).getSize().equals(flavorSizeToken)){
                removalIndex = i;
                order.remove(removalIndex);
                return true;
            } else if(order.get(i) instanceof Donut && itemType.equals("Donut")
                    && ((Donut) order.get(i)).getFlavor().equals(flavorSizeToken)){
                removalIndex = i;
                order.remove(removalIndex);
                return true;
            }
        }
        return false;
    }

    private String setItemType(String firstToken){
        if(firstToken.equals("Donut") || firstToken.equals("Yeast") || firstToken.equals("Cake")){
            return "Donut";
        } else if (firstToken.equals("Coffee,")) {
            return "Coffee";
        } else {
            return "invalid item type";
        }
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
    }*/
}

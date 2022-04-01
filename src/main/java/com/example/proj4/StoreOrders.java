package com.example.proj4;

import java.util.ArrayList;

public class StoreOrders implements Customizable {

    private ArrayList<MenuItem> menuItems;

    public StoreOrders(){
        menuItems = new ArrayList<MenuItem>();
    }

    @Override
    public boolean addObject(Object obj) {
        if (obj instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) obj;
            menuItems.add(menuItem);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) obj;
            menuItems.add(menuItem);
            return true;
        } else {
            return false;
        }
    }
}




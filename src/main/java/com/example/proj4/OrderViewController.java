package com.example.proj4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderViewController {
    private DonutController donutController;
    private static final double SALES_TAX = 0.06625;
    public ArrayList<MenuItem> yourOrderArrayList = new ArrayList<MenuItem>();
    @FXML
    private ListView yourOrders;
    @FXML
    private TextField subTotal, salesTax, total;


    @FXML
    public void updateListView(){
        yourOrders.getItems().clear();
        for(int i = 0; i < yourOrderArrayList.size(); i ++){
            yourOrders.getItems().add(yourOrderArrayList.get(i));
        }
    }
    @FXML
    public void updateTotals(){
        double subtotalDouble = 0;
        for (int i = 0; i < yourOrderArrayList.size(); i++) {
            subtotalDouble += yourOrderArrayList.get(i).itemPrice() * yourOrderArrayList.get(i).getQuantity();
        }
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        String subtotalString = d.format(subtotalDouble);
        subTotal.setText(subtotalString);
        double taxDouble = subtotalDouble * SALES_TAX;
        String taxString = d.format(taxDouble);
        salesTax.setText(taxString);
        double totalDouble = subtotalDouble + taxDouble;
        String totalString = d.format(totalDouble);
        total.setText(totalString);

    }
    public ArrayList<MenuItem> getYourOrderArrayList() {
        return yourOrderArrayList;
    }

    public void setDonutController(DonutController controller) {
        donutController = controller;
    }
}
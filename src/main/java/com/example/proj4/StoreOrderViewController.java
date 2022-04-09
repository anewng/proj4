package com.example.proj4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.lang.reflect.Array;
import java.nio.file.attribute.AclEntryType;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrderViewController {
    private static final double SALES_TAX = 0.06625;

    public ArrayList<Order> storeOrderArrayList = new ArrayList<Order>();
    public ArrayList<MenuItem> selectedOrderList = new ArrayList<MenuItem>();

    ObservableList<String> orderNumbersList = FXCollections
            .observableArrayList();

    @FXML
    private ListView storeOrders;
    @FXML
    private TextField total;
    @FXML
    private ComboBox orderNumber;

    @FXML
    public void initialize(){
        total.setEditable(false);

        //setting the order combobox options
        for(int i = 0; i < storeOrderArrayList.size(); i++) {
            orderNumbersList.add(String.valueOf(i+1));
        }
        orderNumber.setItems(orderNumbersList);
    }

    @FXML
    public void onOrderNumberSelected(Event itemStateChanged){
        updateListView();
        updateTotalField();
    }

    @FXML
    public void updateListView(){
        storeOrders.getItems().clear();
        if(orderNumber.getValue() == null){
            return;
        }
        int selectedOrderIndex = Integer.parseInt(orderNumber.getValue().toString()) - 1;
        selectedOrderList = storeOrderArrayList.get(selectedOrderIndex);

        for(int i = 0; i < selectedOrderList.size(); i ++){
            storeOrders.getItems().add(selectedOrderList.get(i).toString());
        }
    }

    @FXML
    public void updateTotalField(){
        total.clear();
        double totalDouble = 0;
        for(int i = 0; i < selectedOrderList.size(); i++){
            totalDouble += selectedOrderList.get(i).itemPrice() * selectedOrderList.get(i).getQuantity();
        }
        double taxDouble = totalDouble * SALES_TAX;
        totalDouble = totalDouble + taxDouble;

        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        String totalString = d.format(totalDouble);
        total.setText(totalString);
    }

    @FXML
    protected void onCancelOrderButtonClick(ActionEvent event) {
        storeOrders.getItems().clear();
        total.clear();
        storeOrderArrayList.set(Integer.parseInt(orderNumber.getValue().toString()) - 1, null);
        orderNumbersList.remove(Integer.parseInt(orderNumber.getValue().toString()) - 1);
        orderNumber.setItems(orderNumbersList);
    }

    @FXML
    protected void onExportOrderButtonClick(ActionEvent event) {

    }

}
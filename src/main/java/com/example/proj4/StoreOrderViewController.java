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
    public Order selectedOrderList = new Order();

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
        resetComboBox();
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
        int selectedOrderIndex = Integer.parseInt(orderNumber.getValue().toString());
        selectedOrderList = findSelectedOrder(selectedOrderIndex);

        //displaying the selected order in the list view
        for(int i = 0; i < selectedOrderList.getOrderArray().size(); i ++){
            storeOrders.getItems().add(selectedOrderList.getOrderArray().get(i).toString());
        }
    }

    private Order findSelectedOrder(int selectedOrderIndex){
        //find the selected order by iterating through the store orders array and searching for orderNumber
        for(int j = 0; j < storeOrderArrayList.size(); j++){
            if(storeOrderArrayList.get(j).getOrderNumber() == selectedOrderIndex){
                return storeOrderArrayList.get(j);
            }
        }
        return null;
    }

    @FXML
    public void updateTotalField(){
        total.clear();
        double totalDouble = 0;
        if(selectedOrderList == null){
            return;
        }
        for(int i = 0; i < selectedOrderList.getOrderArray().size(); i++){
            totalDouble += selectedOrderList.getOrderArray().get(i).itemPrice()
                    * selectedOrderList.getOrderArray().get(i).getQuantity();
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
        selectedOrderList = findSelectedOrder(Integer.parseInt(orderNumber.getValue().toString()));
        storeOrderArrayList.remove(selectedOrderList);
        selectedOrderList = null;
        resetComboBox();
        updateTotalField();
    }

    private void resetComboBox(){
        orderNumber.getItems().clear();
        for(int i = 0; i < storeOrderArrayList.size(); i++) {
            orderNumbersList.add(String.valueOf(storeOrderArrayList.get(i).getOrderNumber()));
        }
        orderNumber.setItems(orderNumbersList);
    }

    @FXML
    protected void onExportOrderButtonClick(ActionEvent event) {

    }

}
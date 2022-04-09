package com.example.proj4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.lang.reflect.Array;
import java.nio.file.attribute.AclEntryType;
import java.util.ArrayList;

public class StoreOrderViewController {

    public ArrayList<ArrayList<MenuItem>> storeOrderArrayList = new ArrayList<ArrayList<MenuItem>>();

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

        //seting the order combobox options
        for(int i = 0; i < storeOrderArrayList.size(); i++) {
            orderNumbersList.add(String.valueOf(i+1));
        }
        orderNumber.setItems(orderNumbersList);
    }

    @FXML
    public void onOrderNumberSelected(Event itemStateChanged){
        updateListView();
    }

    @FXML
    public void updateListView(){
        storeOrders.getItems().clear();
        int selectedOrderIndex = Integer.parseInt(orderNumber.getValue().toString()) - 1;
        ArrayList<MenuItem> selectedOrder = storeOrderArrayList.get(selectedOrderIndex);

        for(int i = 0; i < selectedOrder.size(); i ++){
            storeOrders.getItems().add(selectedOrder.get(i).toString());
        }
    }

    @FXML
    protected void onCancelOrderButtonClick(ActionEvent event) {

    }

    @FXML
    protected void onExportOrderButtonClick(ActionEvent event) {

    }

}
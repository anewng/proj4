package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.nio.file.attribute.AclEntryType;
import java.util.ArrayList;

public class StoreOrderViewController {

    public ArrayList<ArrayList<MenuItem>> storeOrderArrayList = new ArrayList<ArrayList<MenuItem>>();

    @FXML
    private ListView storeOrders;

    @FXML
    public void updateListView(){
        /*storeOrders.getItems().clear();
        for(int i = 0; i < yourOrderArrayList.size(); i ++){
            yourOrders.getItems().add(yourOrderArrayList.get(i));
        }*/
    }

    @FXML
    protected void onCancelOrderButtonClick(ActionEvent event) {

    }

    @FXML
    protected void onExportOrderButtonClick(ActionEvent event) {

    }

}
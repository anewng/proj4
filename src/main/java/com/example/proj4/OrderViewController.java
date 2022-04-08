package com.example.proj4;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class OrderViewController {
    private MainController mainController;

    private ArrayList<MenuItem> yourOrderArrayList = new ArrayList<MenuItem>();

    @FXML
    private ListView yourOrders;

    @FXML
    private void initialize() throws IOException {
        updateListView();
    }

    private void updateListView(){
        yourOrders.getItems().clear();
        for(int i = 0; i < yourOrderArrayList.size(); i ++){
            yourOrders.getItems().add(yourOrderArrayList.get(i));
        }
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }

    public ArrayList<MenuItem> getYourOrderArrayList() {
        return yourOrderArrayList;
    }


    public void setRoot(Parent root) {
        Scene cc = yourOrders.getScene();
        cc.setRoot(root);
    }
}
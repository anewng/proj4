package com.example.proj4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class OrderViewController {
    private DonutController donutController;
    public ArrayList<MenuItem> yourOrderArrayList = new ArrayList<MenuItem>();
    @FXML
    private ListView yourOrders;
    @FXML
    private void initialize() throws IOException {
        System.out.println("Slay");
        DonutHole donut = new DonutHole("Slay");
        yourOrders.getItems().add(donut);
        updateListView();
    }

    @FXML
    public void updateListView(){
        yourOrders.getItems().clear();
        for(int i = 0; i < yourOrderArrayList.size(); i ++){
            yourOrders.getItems().add(yourOrderArrayList.get(i));
        }
    }
    public ArrayList<MenuItem> getYourOrderArrayList() {
        return yourOrderArrayList;
    }

    public void setDonutController(DonutController controller) {
        donutController = controller;
    }
}
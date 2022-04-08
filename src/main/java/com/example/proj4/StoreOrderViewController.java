package com.example.proj4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class StoreOrderViewController {
    private DonutController orderViewController;
    private ArrayList<ArrayList<MenuItem>> storeOrderArrayList = new ArrayList<ArrayList<MenuItem>>();
    private MainController mainController;

    @FXML
    private void initialize() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order-view.fxml"));
            orderViewController = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }

}
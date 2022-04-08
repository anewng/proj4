package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class OrderViewController {
    public String test = "hi";

    private DonutController donutController;
    private CoffeeController coffeeController;
    private MainController mainController;

    private ArrayList<MenuItem> yourOrderArrayList = new ArrayList<MenuItem>();

    @FXML
    private TextArea yourOrders;

    @FXML
    private void initialize() throws IOException {
        System.out.println(mainController.donutController.donutArrayList.get(0));
        /*
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
            donutController = fxmlLoader.getController();
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
            coffeeController = fxmlLoader2.getController();
        } catch(Exception e) {
            e.printStackTrace();
        }
         */
        for (int i = 0; i < yourOrderArrayList.size(); i++) {
            yourOrders.appendText(yourOrderArrayList.get(i).toString());
        }
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }

    public ArrayList<MenuItem> getYourOrderArrayList() {
        return yourOrderArrayList;
    }


}
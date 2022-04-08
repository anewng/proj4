package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class OrderViewController {

    private DonutController donutController;
    private CoffeeController coffeeController;

    private ArrayList<MenuItem> yourOrderArrayList = new ArrayList<MenuItem>();

    @FXML
    private void initialize() throws IOException {
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


    }


}
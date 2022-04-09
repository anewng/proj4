package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private DonutController donutController;
    private CoffeeController coffeeController;
    private OrderViewController orderViewController;
    private StoreOrderViewController storeOrderViewController;
    private FXMLLoader donutLoader;
    private FXMLLoader coffeeLoader;
    private FXMLLoader orderViewLoader;
    private FXMLLoader storeOrderLoader;
    private Scene donutViewScene;
    private Scene coffeeViewScene;
    private Scene orderViewScene;
    private Scene storeOrderViewScene;

    @FXML
    private void initialize() throws IOException {
        donutLoader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
        donutLoader.load();
        donutController = donutLoader.getController();
        donutViewScene = new Scene(donutLoader.getRoot());

        coffeeLoader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
        coffeeLoader.load();
        coffeeController = coffeeLoader.getController();
        coffeeViewScene = new Scene(coffeeLoader.getRoot());

        orderViewLoader = new FXMLLoader(getClass().getResource("order-view.fxml"));
        orderViewLoader.load();
        orderViewController = orderViewLoader.getController();
        orderViewScene = new Scene(orderViewLoader.getRoot());

        storeOrderLoader = new FXMLLoader(getClass().getResource("store-order-view.fxml"));
        storeOrderLoader.load();
        storeOrderViewController = storeOrderLoader.getController();
        storeOrderViewScene = new Scene(storeOrderLoader.getRoot());

        donutController.setOrderViewController(this.orderViewController);
        coffeeController.setOrderViewController(this.orderViewController);

    }

    @FXML
    protected void onDonutButtonClick(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setScene(donutViewScene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCoffeeButtonClick(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setScene(coffeeViewScene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onYourOrderButtonClick(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setScene(orderViewScene);
            orderViewController.updateListView();
            orderViewController.updateTotals();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onStoreOrderButtonClick(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setScene(storeOrderViewScene);
            //storeOrderViewController.updateListView();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
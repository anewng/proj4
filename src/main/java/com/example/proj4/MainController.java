package com.example.proj4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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


    /**
     The initialize method configures preliminary settings to clarify GUI interactions.
     */
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
        orderViewController.setStoreOrderViewController(this.storeOrderViewController);

    }

    /**
     Opens new window and sets the stage for Donut orders
     @param event the method is executed when the user clicks on the Donut Order button
     */
    @FXML
    protected void onDonutButtonClick(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setScene(donutViewScene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     Opens new window and sets the stage for Coffee orders
     @param event the method is executed when the user clicks on the Coffee Order button
     */
    @FXML
    protected void onCoffeeButtonClick(ActionEvent event) throws IOException {
        //coffee size list, so that user can select the size of coffee to add to order
        ObservableList<String> coffeeSizeList = FXCollections
                .observableArrayList("Short", "Tall", "Grande", "Venti");

        //quantity list, so that user can select the number of donuts to add to order
        ObservableList<String> coffeeAmountList = FXCollections
                .observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        try {
            Stage stage = new Stage();
            stage.setScene(coffeeViewScene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            coffeeController.coffeeSizeSelect.setItems(coffeeSizeList);
            coffeeController.coffeeAmountSelect.setItems(coffeeAmountList);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     Opens new window and sets the stage for editing the current order
     @param event the method is executed when the user clicks on the Your Order button
     */
    @FXML
    protected void onYourOrderButtonClick(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setScene(orderViewScene);
            orderViewController.updateListView();
            orderViewController.updateTotals();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     Opens new window and sets the stage for editing the store order
     @param event the method is executed when the user clicks on the Store Order button
     */
    @FXML
    protected void onStoreOrderButtonClick(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setScene(storeOrderViewScene);
            storeOrderViewController.initialize();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
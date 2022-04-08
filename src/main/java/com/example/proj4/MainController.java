package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private String testing = "hi";
    public Stage orderViewStage;

    @FXML
    public OrderViewController orderViewController;
    @FXML
    public StoreOrderViewController storeOrderViewController;
    @FXML
    public DonutController donutController;
    @FXML
    public CoffeeController coffeeController;

    @FXML
    private void initialize() throws IOException {
        try {
            FXMLLoader orderFXMLLoader = new FXMLLoader(getClass().getResource("order-view.fxml"));
            Parent orderViewRoot = orderFXMLLoader.load();
            orderViewStage = new Stage();
            orderViewStage.setScene(new Scene(orderViewRoot));
            System.out.println("is this working");

            orderViewController.setMainController(this);
            orderViewController.setRoot(orderViewRoot);
            //orderViewController = orderFXMLLoader.getController();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onDonutButtonClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCoffeeButtonClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onYourOrderButtonClick(ActionEvent event) throws IOException {
        orderViewStage.show();
    }

    @FXML
    protected void onStoreOrderButtonClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader storeOrderFXMLLoader = new FXMLLoader(getClass().getResource("store-order-view.fxml"));
            Parent root1 = storeOrderFXMLLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public OrderViewController orderViewController;
    public StoreOrderViewController storeOrderViewController;

    private FXMLLoader orderFXMLLoader = new FXMLLoader(getClass().getResource("order-view.fxml"));
    private FXMLLoader storeOrderFXMLLoader = new FXMLLoader(getClass().getResource("store-order-view.fxml"));

    @FXML
    private void initialize() throws IOException {
        try {
            orderViewController = orderFXMLLoader.getController();
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            storeOrderViewController = storeOrderFXMLLoader.getController();
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
        try {
            Parent root1 = orderFXMLLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onStoreOrderButtonClick(ActionEvent event) throws IOException {
        try {
            Parent root1 = storeOrderFXMLLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

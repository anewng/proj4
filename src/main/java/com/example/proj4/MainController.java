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
    private Parent donutViewRoot;
    private Parent coffeeViewRoot;
    private Parent orderViewRoot;
    private Parent storeViewRoot;
    private Scene orderViewScene;

    @FXML
    private void initialize() throws IOException {
        orderViewLoader = new FXMLLoader(getClass().getResource("order-view.fxml"));
        orderViewLoader.load();
        orderViewController = orderViewLoader.getController();
        orderViewScene = new Scene(orderViewLoader.getRoot());
        //orderViewController.setMainController(this);
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("store-order-view.fxml"));
        loader2.load();
        StoreOrderViewController storeOrderViewController = loader2.getController();
        storeOrderViewController.setMainController(this);
        donutLoader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
        donutLoader.load();
        donutController = donutLoader.getController();
        donutController.setOrderViewController(this.orderViewController);
        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
        loader4.load();
        CoffeeController coffeeController = loader4.getController();
        coffeeController.setMainController(this);
    }

    @FXML
    protected void onDonutButtonClick(ActionEvent event) throws IOException {

        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(donutLoader.getRoot()));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCoffeeButtonClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
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
            Stage stage = new Stage();
            stage.setScene(orderViewScene);
            orderViewController.updateListView();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onStoreOrderButtonClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("store-order-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

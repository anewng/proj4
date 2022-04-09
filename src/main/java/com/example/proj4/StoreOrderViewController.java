package com.example.proj4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StoreOrderViewController {
    private MainController mainController;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    public void setMainController(MainController controller) {
        mainController = controller;
    }
}
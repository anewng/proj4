package com.example.proj4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
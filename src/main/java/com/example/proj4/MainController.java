package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onDonutButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
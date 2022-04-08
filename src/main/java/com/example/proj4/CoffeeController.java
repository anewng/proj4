package com.example.proj4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class CoffeeController {
    //coffee size list, so that user can select the size of coffee to add to order
    ObservableList<String> coffeeSizeList = FXCollections
            .observableArrayList("Short", "Tall", "Grande", "Venti");

    //quantity list, so that user can select the number of donuts to add to order
    ObservableList<String> coffeeAmountList = FXCollections
            .observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    @FXML
    private ComboBox coffeeSizeSelect;

    @FXML
    private ComboBox coffeeAmountSelect;

    @FXML
    private Button addToCart;


    ArrayList<Coffee> coffeeArrayList = new ArrayList<Coffee>();

    @FXML
    private void initialize(){
        coffeeSizeSelect.setItems(coffeeSizeList);
        coffeeAmountSelect.setItems(coffeeAmountList);

    }

    @FXML
    protected void onAddToCartButtonClick(ActionEvent event) {
        Coffee newCoffee = new Coffee();
        newCoffee.setSize(coffeeSizeSelect.getValue().toString());
        newCoffee.setQuantity(Integer.parseInt(coffeeAmountSelect.getValue().toString()));
        coffeeArrayList.add(newCoffee);

    }
    }
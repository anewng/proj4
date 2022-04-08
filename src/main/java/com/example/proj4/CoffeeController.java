package com.example.proj4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.DecimalFormat;


public class CoffeeController {
    //coffee size list, so that user can select the size of coffee to add to order
    ObservableList<String> coffeeSizeList = FXCollections
            .observableArrayList("Short", "Tall", "Grande", "Venti");

    //quantity list, so that user can select the number of donuts to add to order
    ObservableList<String> coffeeAmountList = FXCollections
            .observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    private double subtotal = 0;
    private MainController mainController;

    @FXML
    private ComboBox coffeeSizeSelect;

    @FXML
    private ComboBox coffeeAmountSelect;

    @FXML
    private Button addToCart;

    @FXML
    private CheckBox cream, syrup, milk, caramel, whippedCream;

    @FXML
    private TextField coffeeSubtotal;

    private Coffee coffee = new Coffee();

    @FXML
    private void initialize(){
        coffeeSizeSelect.setItems(coffeeSizeList);
        coffeeAmountSelect.setItems(coffeeAmountList);

    }

    @FXML
    protected void onCoffeeSizeSelected(ActionEvent event) {
        coffee.setSize(coffeeSizeSelect.getValue().toString());
        updateSubtotalAndCoffee();
    }

    @FXML
    protected void onCoffeeQuantitySelected(ActionEvent event) {
        coffee.setQuantity(Integer.parseInt(coffeeAmountSelect.getValue().toString()));
        updateSubtotalAndCoffee();
    }

    @FXML
    protected void onAddToCartButtonClick(ActionEvent event) {
        Coffee newCoffee = new Coffee();
        newCoffee.setSize(coffeeSizeSelect.getValue().toString());
        newCoffee.setQuantity(Integer.parseInt(coffeeAmountSelect.getValue().toString()));
        mainController.orderViewController.getYourOrderArrayList().add(newCoffee);

    }

    @FXML
    protected void onAddOnsChecked(ActionEvent event) {
        updateSubtotalAndCoffee();
    }

    private void updateSubtotalAndCoffee() {
        coffee.setSize(coffeeSizeSelect.getValue().toString());
        if (cream.isSelected()) {
            coffee.addAddOn("cream");
        }
        if (syrup.isSelected()) {
            coffee.addAddOn("syrup");
        }
        if (milk.isSelected()) {
            coffee.addAddOn("milk");
        }
        if (caramel.isSelected()) {
            coffee.addAddOn("caramel");
        }
        if (whippedCream.isSelected()) {
            coffee.addAddOn("whipped cream");
        }
        subtotal = coffee.itemPrice() * coffee.getQuantity();
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        coffeeSubtotal.setText(d.format(subtotal));

    }
    public void setMainController(MainController controller) {
        mainController = controller;
    }


    /*@FXML
    private Coffee addAddOns() {
        //if ()
    }*/
    }
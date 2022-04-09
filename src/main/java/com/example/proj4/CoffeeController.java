package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;

public class CoffeeController {

    private double subtotal = 0;
    private OrderViewController orderViewController;

    @FXML
    public ComboBox coffeeSizeSelect;
    @FXML
    public ComboBox coffeeAmountSelect;
    @FXML
    private CheckBox cream, syrup, milk, caramel, whippedCream;
    @FXML
    private TextField coffeeSubtotal;

    private Coffee coffee = new Coffee();

    @FXML
    private void initialize(){
        coffeeSubtotal.setEditable(false);
    }

    @FXML
    protected void onCoffeeSizeSelected(ActionEvent event) {
        if(coffeeSizeSelect.getValue() != null){
            coffee.setSize(coffeeSizeSelect.getValue().toString());
        }
        updateSubtotalAndCoffee();
    }

    @FXML
    protected void onCoffeeQuantitySelected(ActionEvent event) {
        if(coffeeAmountSelect.getValue() != null){
            coffee.setQuantity(Integer.parseInt(coffeeAmountSelect.getValue().toString()));
        }
        updateSubtotalAndCoffee();
    }

    @FXML
    protected void onAddToCartButtonClick(ActionEvent event) {
        Coffee newCoffee = new Coffee();
        newCoffee.setSize(coffeeSizeSelect.getValue().toString());
        newCoffee.setQuantity(Integer.parseInt(coffeeAmountSelect.getValue().toString()));
        trackAddOns(newCoffee);

        orderViewController.yourOrderArrayList.getOrderArray().add(newCoffee);

        coffeeSizeSelect.setValue(null);
        coffeeAmountSelect.setValue(null);
        coffeeSubtotal.clear();
        cream.setSelected(false);
        syrup.setSelected(false);
        milk.setSelected(false);
        caramel.setSelected(false);
        whippedCream.setSelected(false);
    }

    @FXML
    protected void onAddOnsChecked(ActionEvent event) {
        updateSubtotalAndCoffee();
    }

    private void updateSubtotalAndCoffee() {
        if(coffeeSizeSelect.getValue() != null){
            coffee.setSize(coffeeSizeSelect.getValue().toString());
        }
        trackAddOns(coffee);

        subtotal = coffee.itemPrice() * coffee.getQuantity();
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        coffeeSubtotal.setText(d.format(subtotal));

    }

    private Coffee trackAddOns(Coffee coffee){
        if (cream.isSelected()) {
            coffee.addObject("cream");
        } else if (!cream.isSelected()){
            coffee.remove("cream");
        }

        if (syrup.isSelected()) {
            coffee.addObject("syrup");
        } else if (!syrup.isSelected()){
            coffee.remove("syrup");
        }

        if (milk.isSelected()) {
            coffee.addObject("milk");
        } else if (!milk.isSelected()){
            coffee.remove("milk");
        }

        if (caramel.isSelected()) {
            coffee.addObject("caramel");
        } else if (!caramel.isSelected()){
            coffee.remove("caramel");
        }

        if (whippedCream.isSelected()) {
            coffee.addObject("whippedCream");
        } else if (!whippedCream.isSelected()){
            coffee.remove("whippedCream");
        }
        return coffee;
    }

    public void setOrderViewController(OrderViewController controller) {
        orderViewController = controller;
    }

    }
package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Optional;

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
    @FXML
    private AnchorPane anchorPane;

    private Coffee coffee = new Coffee();

    /**
     The initialize method configures preliminary settings to clarify GUI interactions.
     */
    @FXML
    private void initialize(){
        coffeeSubtotal.setEditable(false);
    }

    /**
     Sets the coffee size based on user input in the GUI
     @param event the method is executed when the user selects a size from the dropdown menu
     */
    @FXML
    protected void onCoffeeSizeSelected(ActionEvent event) {
        if(coffeeSizeSelect.getValue() != null){
            coffee.setSize(coffeeSizeSelect.getValue().toString());
        }
        updateSubtotalAndCoffee();
    }

    /**
     Sets the coffee order quantity based on user input in the GUI
     @param event the method is executed when the user selects the quantity from the dropdown menu
     */
    @FXML
    protected void onCoffeeQuantitySelected(ActionEvent event) {
        if(coffeeAmountSelect.getValue() != null){
            coffee.setQuantity(Integer.parseInt(coffeeAmountSelect.getValue().toString()));
        }
        updateSubtotalAndCoffee();
    }

    /**
     Adds the coffee order to the cart when the user clicks on the add button
     @param event the method is executed when the user clicks on the button to add to the cart
     */
    @FXML
    protected void onAddToCartButtonClick(ActionEvent event) {
        if (coffeeSizeSelect.getValue() == null) {
            Alert error = new Alert(Alert.AlertType.NONE);
            error.setAlertType(Alert.AlertType.ERROR);
            error.setContentText("No size selected");
            error.show();
        } else if (coffeeAmountSelect.getValue() == null) {
            Alert error = new Alert(Alert.AlertType.NONE);
            error.setAlertType(Alert.AlertType.ERROR);
            error.setContentText("No amount selected");
            error.show();
        } else {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Confirm order addition");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                orderViewController.yourOrderArrayList.getOrderArray().add(coffee);

                coffeeSizeSelect.setValue(null);
                coffeeAmountSelect.setValue(null);
                coffeeSubtotal.clear();
                cream.setSelected(false);
                syrup.setSelected(false);
                milk.setSelected(false);
                caramel.setSelected(false);
                whippedCream.setSelected(false);

                coffee = new Coffee();

                Stage stage = (Stage) anchorPane.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     Makes necessary updates based on the add-ons that are checked
     @param event the method is executed when the user checks or unchecks the add-on check boxes
     */
    @FXML
    protected void onAddOnsChecked(ActionEvent event) {
        updateSubtotalAndCoffee();
    }

    /**
     Updates the add-ons when cream is selected or deselected
     @param event the method is executed when the user checks or unchecks the add-on check box for cream
     */
    @FXML
    protected void onCream(ActionEvent event){
        if (cream.isSelected()) {
            coffee.addObject("cream");
        } else if (!cream.isSelected()){
            coffee.remove("cream");
        }
        updateSubtotalAndCoffee();
    }
    /**
     Updates the add-ons when syrup is selected or deselected
     @param event the method is executed when the user checks or unchecks the add-on check box for syrup
     */
    @FXML
    protected void onSyrup(ActionEvent event){
        if (syrup.isSelected()) {
            coffee.addObject("syrup");
        } else if (!syrup.isSelected()){
            coffee.remove("syrup");
        }
        updateSubtotalAndCoffee();
    }
    /**
     Updates the add-ons when milk is selected or deselected
     @param event the method is executed when the user checks or unchecks the add-on check box for milk
     */
    @FXML
    protected void onMilk(ActionEvent event){
        if (milk.isSelected()) {
            coffee.addObject("milk");
        } else if (!milk.isSelected()){
            coffee.remove("milk");
        }
        updateSubtotalAndCoffee();
    }
    /**
     Updates the add-ons when caramel is selected or deselected
     @param event the method is executed when the user checks or unchecks the add-on check box for caramel
     */
    @FXML
    protected void onCaramel(ActionEvent event){
        if (caramel.isSelected()) {
            coffee.addObject("caramel");
        } else if (!caramel.isSelected()){
            coffee.remove("caramel");
        }
        updateSubtotalAndCoffee();
    }
    /**
     Updates the add-ons when whipped cream is selected or deselected
     @param event the method is executed when the user checks or unchecks the add-on check box for whipped cream
     */
    @FXML
    protected void onWhippedCream(ActionEvent event){
        if (whippedCream.isSelected()) {
            coffee.addObject("whipped cream");
        } else if (!whippedCream.isSelected()){
            coffee.remove("whipped cream");
        }
        updateSubtotalAndCoffee();
    }

    /**
     Updates the subtotal text field and coffee order based on changes in the add-on checkboxes
     */
    private void updateSubtotalAndCoffee() {
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        if(coffeeSizeSelect.getValue() != null && coffeeAmountSelect.getValue() != null){
            coffee.setSize(coffeeSizeSelect.getValue().toString());
        } else{
            coffeeSubtotal.setText(d.format(0));
            return;
        }

        subtotal = coffee.itemPrice() * Integer.parseInt(coffeeAmountSelect.getValue().toString());
        coffeeSubtotal.setText(d.format(subtotal));
    }

    /**
     Connects the current controller with an order view controller
     @param controller the controller that is to be connected with the current one
     */
    public void setOrderViewController(OrderViewController controller) {
        orderViewController = controller;
    }

    }
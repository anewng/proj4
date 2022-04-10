package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 The OrderViewController class dictates the function of the order view GUI.
 @author Annie Wang, Jasmine Flanders
 */
public class OrderViewController {
    private StoreOrderViewController storeOrderViewController;

    private static final double SALES_TAX = 0.06625;
    private static final int AUTOMATIC_REMOVAL_INDEX = -1;

    private Order yourOrderArrayList = new Order();

    @FXML
    private ListView yourOrders;
    @FXML
    private TextField subTotal, salesTax, total;
    @FXML
    private AnchorPane anchorPane;

    /**
     The initialize method configures preliminary settings to clarify GUI interactions.
     */
    @FXML
    public void initialize(){
        subTotal.setEditable(false);
        salesTax.setEditable(false);
        total.setEditable(false);
    }

    /**
     Gets the order array list.
     @return Order the orders list
     */
    public Order getYourOrderArrayList(){
        return yourOrderArrayList;
    }

    /**
     Updates the list view display with all items in the current order.
     */
    @FXML
    public void updateListView(){
        yourOrders.getItems().clear();
        for(int i = 0; i < yourOrderArrayList.getOrderArray().size(); i ++){
            yourOrders.getItems().add(yourOrderArrayList.getOrderArray().get(i).toString());
        }
    }

    /**
     Updates the subtotal, tax, and total text fields with calculated prices.
     */
    @FXML
    public void updateTotals(){
        double subtotalDouble = 0;
        for (int i = 0; i < yourOrderArrayList.getOrderArray().size(); i++) {
            subtotalDouble += yourOrderArrayList.getOrderArray().get(i).itemPrice()
                    * yourOrderArrayList.getOrderArray().get(i).getQuantity();
        }
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        String subtotalString = d.format(subtotalDouble);
        subTotal.setText(subtotalString);

        double taxDouble = subtotalDouble * SALES_TAX;
        String taxString = d.format(taxDouble);
        salesTax.setText(taxString);

        double totalDouble = subtotalDouble + taxDouble;
        String totalString = d.format(totalDouble);
        total.setText(totalString);
    }

    /**
     Adds to order to the cart based on user input in the GUI
     @param event the method is executed when the user clicks the place order button
     */
    @FXML
    protected void onPlaceOrderButtonClick(ActionEvent event) {
        if (yourOrders.getItems().size() == 0) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("No orders");
            error.show();
        } else {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Confirm order");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                storeOrderViewController.getStoreOrderArrayList().addObject(yourOrderArrayList);
                yourOrderArrayList = new Order();
                yourOrders.getItems().clear();
                subTotal.clear();
                salesTax.clear();
                total.clear();

                Stage stage = (Stage) anchorPane.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     Removes menu item from the order based on user input in the GUI
     @param event the method is executed when the user clicks the remove selected item button
     */
    @FXML
    protected void onRemoveSelectedButtonClick(ActionEvent event) {
        if (yourOrders.getSelectionModel().getSelectedItem() == null) {
            Alert error = new Alert(Alert.AlertType.NONE);
            error.setAlertType(Alert.AlertType.ERROR);
            error.setContentText("No item selected");
            error.show();
        } else {
            StringTokenizer string = new StringTokenizer(yourOrders.getSelectionModel().getSelectedItem().toString());
            String flavorSizeToken = "";
            String itemType = setItemType(string.nextToken());

            if(itemType.equals("Coffee")){
                flavorSizeToken = string.nextToken(); // getting coffee flavor
            } else if(itemType.equals("invalid item type")) {
                return;
            } else {
                string.nextToken();
                flavorSizeToken = string.nextToken(); //getting donut flavor
                flavorSizeToken = setDonutFlavor(flavorSizeToken);
            }

            int removalIndex = findRemovalIndex(itemType, flavorSizeToken);
            yourOrderArrayList.getOrderArray().remove(removalIndex);

            updateListView();
            updateTotals();
        }
    }

    /**
     Searches the order list to find the index that corresponds with the item type and flavor size token
     @param itemType indicates if the item is Coffee or Donut
     @param flavorSizeToken indicates the flavor or size based on if the item is Coffee or Donut
     @return int the index of the item, if found. if not found, returns the automatic removal index
     */
    private int findRemovalIndex(String itemType, String flavorSizeToken){
        int removalIndex = AUTOMATIC_REMOVAL_INDEX;
        for(int i = 0; i < yourOrderArrayList.getOrderArray().size(); i++){
            if(yourOrderArrayList.getOrderArray().get(i) instanceof Coffee && itemType.equals("Coffee")
                    && ((Coffee) yourOrderArrayList.getOrderArray().get(i)).getSize().equals(flavorSizeToken)){
                removalIndex = i;
            } else if(yourOrderArrayList.getOrderArray().get(i) instanceof Donut && itemType.equals("Donut")
                    && ((Donut) yourOrderArrayList.getOrderArray().get(i)).getFlavor().equals(flavorSizeToken)){
                removalIndex = i;
            }
        }
        return removalIndex;
    }

    /**
     Identifies and sets the menu item type based on the first token
     @param firstToken the token that indicates the specific menu item
     @return String the full menu item type
     */
    private String setItemType(String firstToken){
        if(firstToken.equals("Donut") || firstToken.equals("Yeast") || firstToken.equals("Cake")){
            return "Donut";
        } else if (firstToken.equals("Coffee,")) {
            return "Coffee";
        } else {
            return "invalid item type";
        }
    }

    /**
     Identifies and sets the donut flavor based on the third token
     @param thirdToken the token that indicates the specific donut flavor
     @return String the full donut flavor
     */
    private String setDonutFlavor(String thirdToken){
        if(thirdToken.equals("E")){
            return "E coli";
        } else if (thirdToken.equals("Red")) {
            return "Red Velvet";
        } else if (thirdToken.equals("Blueberry")) {
            return "Blueberry Chiffon";
        } else if (thirdToken.equals("Raspberry")) {
            return "Raspberry Jam Swirl";
        } else {
            return thirdToken;
        }
    }

    /**
     Connects the current controller with the store order view controller
     @param controller the controller that is to be connected with the current one
     */
    public void setStoreOrderViewController(StoreOrderViewController controller) {
        storeOrderViewController = controller;
    }

}
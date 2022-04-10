package com.example.proj4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;

public class StoreOrderViewController {
    private static final double SALES_TAX = 0.06625;

    private int selectedOrderIndex;
    private StoreOrders storeOrderArrayList = new StoreOrders();
    private Order selectedOrderList = new Order();

    ObservableList<String> orderNumbersList = FXCollections
            .observableArrayList();

    @FXML
    private ListView storeOrders;
    @FXML
    private TextField total;
    @FXML
    private ComboBox orderNumber;
    @FXML
    private AnchorPane anchorPane;

    /**
     The initialize method configures preliminary settings to clarify GUI interactions.
     */
    @FXML
    public void initialize() {
        total.setEditable(false);
        resetComboBox();
    }

    /**
     Gets the store order array list.
     @return Order the store orders list
     */
    public StoreOrders getStoreOrderArrayList(){
        return storeOrderArrayList;
    }

    /**
     Updates various parameters based on user selections on the GUI
     @param itemStateChanged the method is executed when the user modifies the order number selection
     */
    @FXML
    public void onOrderNumberSelected(Event itemStateChanged) {
        updateListView();
        updateTotalField();
    }

    /**
     Updates the list view of current order based on the order number selection
     */
    @FXML
    public void updateListView() {
        storeOrders.getItems().clear();
        if (orderNumber.getValue() == null) {
            return;
        }
        int selectedOrderNumber = Integer.parseInt(orderNumber.getValue().toString());
        selectedOrderList = findSelectedOrder(selectedOrderNumber);

        //displaying the selected order in the list view
        for (int i = 0; i < selectedOrderList.getOrderArray().size(); i++) {
            storeOrders.getItems().add(selectedOrderList.getOrderArray().get(i).toString());
        }
    }

    /**
     Finds the selected order by iterating through the store orders array and searching for the order number
     @param selectedOrderNumber the selected order number to search for
     @return Order the order item that matches with the selected order number
     */
    private Order findSelectedOrder(int selectedOrderNumber) {
        for (int j = 0; j < storeOrderArrayList.getOrders().size(); j++) {
            if (storeOrderArrayList.getOrders().get(j).getOrderNumber() == selectedOrderNumber) {
                selectedOrderIndex = j;
                return storeOrderArrayList.getOrders().get(j);
            }
        }
        return null;
    }

    /**
     Updates the subtotal, tax, and total text fields with calculated prices.
     */
    @FXML
    public void updateTotalField() {
        total.clear();
        double totalDouble = 0;
        if (selectedOrderList == null) {
            return;
        }
        for (int i = 0; i < selectedOrderList.getOrderArray().size(); i++) {
            totalDouble += selectedOrderList.getOrderArray().get(i).itemPrice()
                    * selectedOrderList.getOrderArray().get(i).getQuantity();
        }
        double taxDouble = totalDouble * SALES_TAX;
        totalDouble = totalDouble + taxDouble;

        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        String totalString = d.format(totalDouble);
        total.setText(totalString);
    }

    /**
     Cancels an order based on user selections in the GUI
     */
    @FXML
    protected void onCancelOrderButtonClick(ActionEvent event) {
        if (storeOrders.getSelectionModel().getSelectedItem() == null) {
            Alert error = new Alert(Alert.AlertType.NONE);
            error.setAlertType(Alert.AlertType.ERROR);
            error.setContentText("No order selected");
            error.show();
        } else {
            storeOrders.getItems().clear();
            total.clear();

            selectedOrderList = findSelectedOrder(Integer.parseInt(orderNumber.getValue().toString()));
            storeOrderArrayList.remove(selectedOrderList);
            orderNumbersList.remove(selectedOrderIndex);
            storeOrders.getItems().clear(); //clearing the list view
            resetComboBox();

            selectedOrderList = new Order();
            updateTotalField();
        }
    }

    /**
     Resets the order number selection options based on recent updates to store orders
     */
    private void resetComboBox() {
        orderNumber.getItems().clear();
        for (int i = 0; i < storeOrderArrayList.getOrders().size(); i++) {
            orderNumbersList.add(String.valueOf(storeOrderArrayList.getOrders().get(i).getOrderNumber()));
        }
        orderNumber.setItems(orderNumbersList);
    }

    /**
     Exports all current store orders into a text file.
     */
    @FXML
    protected void onExportOrderButtonClick(ActionEvent event) throws IOException {
        if (storeOrderArrayList.getOrders().size() == 0) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("No orders to export");
            error.show();
        } else {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Export Orders?");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Open Target File for the Export");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
                Stage stage = new Stage();
                File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file

                FileWriter fileWriter = new FileWriter(targetFile);
                for (int i = 0; i < storeOrderArrayList.getOrders().size(); i++) {
                    fileWriter.write("Order #" + storeOrderArrayList.getOrders().get(i).getOrderNumber() + ":\n");
                    for (int j = 0; j < storeOrderArrayList.getOrders().get(i).getOrderArray().size(); j++) {
                        fileWriter.write("- " + storeOrderArrayList.getOrders().get(i).getOrderArray().get(j).toString() + "\n");
                    }
                    double subtotalDouble = storeOrderArrayList.getOrders().get(i).getSubtotal();
                    DecimalFormat d = new DecimalFormat("'$'#,##0.00");
                    String subtotalString = d.format(subtotalDouble);
                    fileWriter.write("Subtotal: " + subtotalString + "\n");

                    double taxDouble = subtotalDouble * SALES_TAX;
                    String taxString = d.format(taxDouble);
                    fileWriter.write("Sales tax: " + taxString + "\n");

                    double totalDouble = subtotalDouble + taxDouble;
                    String totalString = d.format(totalDouble);
                    fileWriter.write("Total: " + totalString + "\n\n");
                }
                fileWriter.close();
                //make sure to clear storeOrderView
                Stage mainStage = (Stage) anchorPane.getScene().getWindow();
                mainStage.close();
            }
        }
    }
}
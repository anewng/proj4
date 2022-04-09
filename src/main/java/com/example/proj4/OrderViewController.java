package com.example.proj4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class OrderViewController {
    private StoreOrderViewController storeOrderViewController;

    private static final double SALES_TAX = 0.06625;
    private static final int AUTOMATIC_REMOVAL_INDEX = -1;

    public ArrayList<MenuItem> yourOrderArrayList = new ArrayList<MenuItem>();

    @FXML
    private ListView yourOrders;
    @FXML
    private TextField subTotal, salesTax, total;

    @FXML
    public void initialize(){
        subTotal.setEditable(false);
        salesTax.setEditable(false);
        total.setEditable(false);
    }

    @FXML
    public void updateListView(){
        yourOrders.getItems().clear();
        for(int i = 0; i < yourOrderArrayList.size(); i ++){
            yourOrders.getItems().add(yourOrderArrayList.get(i));
        }
    }
    @FXML
    public void updateTotals(){
        double subtotalDouble = 0;
        for (int i = 0; i < yourOrderArrayList.size(); i++) {
            subtotalDouble += yourOrderArrayList.get(i).itemPrice() * yourOrderArrayList.get(i).getQuantity();
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

    @FXML
    protected void onPlaceOrderButtonClick(ActionEvent event) {
        storeOrderViewController.storeOrderArrayList.add(yourOrderArrayList);
        yourOrders.getItems().clear();
        subTotal.clear();
        salesTax.clear();
        total.clear();
    }

    @FXML
    protected void onRemoveSelectedButtonClick(ActionEvent event) {
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

        int removalIndex = AUTOMATIC_REMOVAL_INDEX;
        for(int i = 0; i < yourOrderArrayList.size(); i++){
            if(yourOrderArrayList.get(i) instanceof Coffee && itemType.equals("Coffee")
                    && ((Coffee) yourOrderArrayList.get(i)).getSize().equals(flavorSizeToken)){
                removalIndex = i;
            } else if(yourOrderArrayList.get(i) instanceof Donut && itemType.equals("Donut")
                    && ((Donut) yourOrderArrayList.get(i)).getFlavor().equals(flavorSizeToken)){
                removalIndex = i;
            }
        }
        yourOrderArrayList.remove(removalIndex);

        updateListView();
        updateTotals();
    }

    private String setItemType(String firstToken){
        if(firstToken.equals("Donut") || firstToken.equals("Yeast") || firstToken.equals("Cake")){
            return "Donut";
        } else if (firstToken.equals("Coffee,")) {
            return "Coffee";
        } else {
            return "invalid item type";
        }
    }

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

    public void setStoreOrderViewController(StoreOrderViewController controller) {
        storeOrderViewController = controller;
    }

}
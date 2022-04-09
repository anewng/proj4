package com.example.proj4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DonutController {
    private static final int NOT_FOUND = -1;
    private OrderViewController orderViewController;

    //donut type list, so that user can select the type of donut to add to order
    ObservableList<String> donutTypeList = FXCollections
            .observableArrayList("Donut Hole", "Yeast Donut", "Cake Donut");

    //flavor lists depending on the type of donut
    ObservableList<String> flavorsDonutHoleList = FXCollections
            .observableArrayList("Yas", "Slay", "Purr");
    ObservableList<String> flavorsYeastDonutList = FXCollections
            .observableArrayList("Fungi", "E coli", "Salmonella");
    ObservableList<String> flavorsCakeDonutList = FXCollections
            .observableArrayList("Red Velvet", "Blueberry Chiffon", "Raspberry Jam Swirl");

    //quantity list, so that user can select the number of donuts to add to order
    ObservableList<String> donutAmountList = FXCollections
            .observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    @FXML
    private ComboBox donutTypeSelect;
    @FXML
    private ComboBox donutFlavorSelect;
    @FXML
    private ComboBox donutAmountSelect;
    @FXML
    private ListView donutOrderPreview;
    @FXML
    private TextField donutSubtotal;


    ArrayList<Donut> donutArrayList = new ArrayList<Donut>();

    @FXML
    private void initialize() throws IOException {
        donutTypeSelect.setItems(donutTypeList);
        donutAmountSelect.setItems(donutAmountList);
    }

    @FXML
    protected void onDonutTypeSelected(Event itemStateChanged) {
        if(donutTypeSelect.getValue().toString().equals("Donut Hole")) {
            donutFlavorSelect.setItems(flavorsDonutHoleList);
        } else if(donutTypeSelect.getValue().toString().equals("Yeast Donut")) {
            donutFlavorSelect.setItems(flavorsYeastDonutList);
        } else if(donutTypeSelect.getValue().toString().equals("Cake Donut")) {
            donutFlavorSelect.setItems(flavorsCakeDonutList);
        }
    }

    @FXML
    protected void onAddToCartClick(ActionEvent event) {
        Donut newDonut = new DonutHole("");
        if (donutTypeSelect.getValue().toString().equals("Donut Hole")) {
            newDonut = new DonutHole(donutFlavorSelect.getValue().toString());
        } else if(donutTypeSelect.getValue().toString().equals("Yeast Donut")) {
            newDonut = new YeastDonut(donutFlavorSelect.getValue().toString());
        } else if(donutTypeSelect.getValue().toString().equals("Cake Donut")) {
            newDonut = new CakeDonut(donutFlavorSelect.getValue().toString());
        }
        newDonut.setQuantity(Integer.parseInt(donutAmountSelect.getValue().toString()));
        if (findDonutIndex(newDonut) == NOT_FOUND){
            donutArrayList.add(newDonut);
        } else {
            int oldAmount = donutArrayList.get(findDonutIndex(newDonut)).getQuantity();
            donutArrayList.get(findDonutIndex(newDonut)).setQuantity(oldAmount + newDonut.getQuantity());
        }

        updateListView();
        updateSubtotal();
    }

    private double findDonutSubtotal(){
        double subtotal = 0;
        for(int i = 0; i < donutArrayList.size(); i++){
            subtotal += ( donutArrayList.get(i).price * donutArrayList.get(i).getQuantity() );
        }
        return subtotal;
    }

    private int findDonutIndex(Donut newDonut){
        for(int i = 0; i < donutArrayList.size(); i++){
            if(donutArrayList.get(i).flavor.equals(newDonut.flavor)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    private int findFlavorIndex(String flavor){
        for(int i = 0; i < donutArrayList.size(); i++){
            if(donutArrayList.get(i).flavor.equals(flavor)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    @FXML
    protected void onRemoveSelectedButtonClick(ActionEvent event) {
        StringTokenizer string = new StringTokenizer(donutOrderPreview.getSelectionModel().getSelectedItem().toString());
        string.nextToken(); //skipping the first token
        string.nextToken(); //skipping the second token
        String thirdToken = string.nextToken();

        String selectedFlavor = setFlavor(thirdToken); //set the flavor based on the third token
        int removalIndex = findFlavorIndex(selectedFlavor);

        donutArrayList.remove(removalIndex);
        System.out.println(removalIndex);

        updateListView();
        updateSubtotal();
    }

    private void updateListView(){
        donutOrderPreview.getItems().clear();
        for(int i = 0; i < donutArrayList.size(); i ++){
            donutOrderPreview.getItems().add(donutArrayList.get(i));
        }
    }

    private void updateSubtotal(){
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        donutSubtotal.setText(d.format(findDonutSubtotal()));
    }

    private String setFlavor(String thirdToken){
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

    @FXML
    protected void onAddToOrderButtonClick(ActionEvent event) throws IOException {
        for (int i = 0; i < donutArrayList.size(); i++) {
            orderViewController.yourOrderArrayList.add(donutArrayList.get(i));
        }
        donutFlavorSelect.getItems().clear();
        donutAmountSelect.getItems().clear();
        donutAmountSelect.getItems().clear();
        donutOrderPreview.getItems().clear();
        donutSubtotal.clear();
    }

    public void setOrderViewController(OrderViewController controller) {
        orderViewController = controller;
    }
}
package com.example.proj4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.util.Optional;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 The DonutController class dictates the function of the donut view GUI.
 @author Annie Wang, Jasmine Flanders
 */
public class DonutController {
    private static final int NOT_FOUND = -1;
    private OrderViewController orderViewController;

    //donut type list, so that user can select the type of donut to add to order
    private ObservableList<String> donutTypeList = FXCollections
            .observableArrayList("Donut Hole", "Yeast Donut", "Cake Donut");

    //quantity list, so that user can select the number of donuts to add to order
    private ObservableList<String> donutAmountList = FXCollections
            .observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    //flavor lists depending on the type of donut
    private ObservableList<String> flavorsDonutHoleList = FXCollections
            .observableArrayList("Yas", "Slay", "Purr");
    private ObservableList<String> flavorsYeastDonutList = FXCollections
            .observableArrayList("Fungi", "E coli", "Salmonella");
    private ObservableList<String> flavorsCakeDonutList = FXCollections
            .observableArrayList("Red Velvet", "Blueberry Chiffon", "Raspberry Jam Swirl");

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
    @FXML
    private AnchorPane anchorPane;

    private Order donutArrayList = new Order();

    /**
     The initialize method configures preliminary settings to clarify GUI interactions.
     */
    @FXML
    private void initialize() throws IOException {
        donutTypeSelect.setItems(donutTypeList);
        donutAmountSelect.setItems(donutAmountList);

        donutSubtotal.setEditable(false);
    }

    /**
     Sets the donut type based on user input in the GUI
     @param itemStateChanged the method is executed when the user selects a type from the dropdown menu
     */
    @FXML
    private void onDonutTypeSelected(Event itemStateChanged) {
        if(donutTypeSelect.getValue() == null){
            return;
        }
        if(donutTypeSelect.getValue().toString().equals("Donut Hole")) {
            donutFlavorSelect.setItems(flavorsDonutHoleList);
        } else if(donutTypeSelect.getValue().toString().equals("Yeast Donut")) {
            donutFlavorSelect.setItems(flavorsYeastDonutList);
        } else if(donutTypeSelect.getValue().toString().equals("Cake Donut")) {
            donutFlavorSelect.setItems(flavorsCakeDonutList);
        }
    }

    /**
     Adds a donut order to the cart based on user input in the GUI
     @param event the method is executed when the user clicks the add to cart button
     */
    @FXML
    protected void onAddToCartClick(ActionEvent event) {
        if (donutTypeSelect.getValue() == null) {
            Alert error = new Alert(Alert.AlertType.NONE);
            error.setAlertType(Alert.AlertType.ERROR);
            error.setContentText("No type selected");
            error.show();
        } else if (donutFlavorSelect.getValue() == null){
            Alert error = new Alert(Alert.AlertType.NONE);
            error.setAlertType(Alert.AlertType.ERROR);
            error.setContentText("No flavor selected");
            error.show();
        } else if (donutAmountSelect.getValue() == null) {
            Alert error = new Alert(Alert.AlertType.NONE);
            error.setAlertType(Alert.AlertType.ERROR);
            error.setContentText("No amount selected");
            error.show();
        } else{
            executeAddToCart();
        }
    }

    /**
     Adds a donut to the cart once all user inputs into the GUI are valid and present
     */
    private void executeAddToCart(){
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
            donutArrayList.getOrderArray().add(newDonut);
        } else {
            int oldAmount = donutArrayList.getOrderArray().get(findDonutIndex(newDonut)).getQuantity();
            donutArrayList.getOrderArray().get(findDonutIndex(newDonut)).setQuantity(oldAmount + newDonut.getQuantity());
        }

        updateListView();
        updateSubtotal();
    }

    /**
     Returns the raw price of the donut, excluding taxes
     @return double the value of the price.
     */
    private double findDonutSubtotal(){
        double subtotal = 0;
        for(int i = 0; i < donutArrayList.getOrderArray().size(); i++){
            subtotal += ( donutArrayList.getOrderArray().get(i).itemPrice() * donutArrayList.getOrderArray().get(i).getQuantity() );
        }
        return subtotal;
    }

    /**
     Returns the index number of the donut object
     @param newDonut the reference donut
     @return int the index of the donut in the array
     */
    private int findDonutIndex(Donut newDonut){
        for(int i = 0; i < donutArrayList.getOrderArray().size(); i++){
            if(((Donut) donutArrayList.getOrderArray().get(i)).getFlavor().equals(newDonut.getFlavor())){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     Removes the donut item from the cart based on user input in the GUI
     @param event the method is executed when the user clicks on the remove donut from cart button
     */
    @FXML
    protected void onRemoveSelectedButtonClick(ActionEvent event) {
        if (donutOrderPreview.getSelectionModel().getSelectedItem() == null) {
            Alert error = new Alert(Alert.AlertType.NONE);
            error.setAlertType(Alert.AlertType.ERROR);
            error.setContentText("No item selected");
            error.show();
        } else {
            StringTokenizer string = new StringTokenizer(donutOrderPreview.getSelectionModel().getSelectedItem().toString());
            String firstToken = string.nextToken(); //first toke = type of donut
            string.nextToken(); //skipping the second token
            String thirdToken = string.nextToken();
            String selectedFlavor = setFlavor(thirdToken); //set the flavor based on the third token

            Donut newDonut = new DonutHole(selectedFlavor);
            if (firstToken.equals("Donut")) {
                newDonut = new DonutHole(selectedFlavor);
            } else if (firstToken.equals("Yeast")) {
                newDonut = new YeastDonut(selectedFlavor);
            } else if (firstToken.equals("Cake")) {
                newDonut = new CakeDonut(selectedFlavor);
            }
            donutArrayList.remove(newDonut);

            updateListView();
            updateSubtotal();
        }
    }

    /**
     Updates the list view of the donut order based on changes to the order
     */
    private void updateListView(){
        donutOrderPreview.getItems().clear();
        for(int i = 0; i < donutArrayList.getOrderArray().size(); i ++){
            donutOrderPreview.getItems().add(donutArrayList.getOrderArray().get(i));
        }
    }

    /**
     Updates the subtotal text field based on changes to the donut order
     */
    private void updateSubtotal(){
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        donutSubtotal.setText(d.format(findDonutSubtotal()));
    }

    /**
     Sets the flavor based on the third token of the selected item
     @param thirdToken the third token of the selected donut item
     @return String the full string name of the flavor
     */
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

    /**
     Adds the donut order to the cart when the user clicks on the add button
     @param event the method is executed when the user clicks on the button to add to the cart
     */
    @FXML
    protected void onAddToOrderButtonClick(ActionEvent event) throws IOException {
        if (donutOrderPreview.getItems().size() == 0) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Cart is empty");
            error.show();
        } else {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Confirm order addition");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                for (int i = 0; i < donutArrayList.getOrderArray().size(); i++) {
                    orderViewController.getYourOrderArrayList().addObject(donutArrayList.getOrderArray().get(i));
                }
                donutTypeSelect.setValue(null);
                donutFlavorSelect.setValue(null);
                donutAmountSelect.setValue(null);
                donutAmountSelect.setValue(null);
                donutOrderPreview.getItems().clear();
                donutSubtotal.clear();
                donutArrayList = new Order();

                Stage stage = (Stage) anchorPane.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     Connects the current controller with an order view controller
     @param controller the controller that is to be connected with the current one
     */
    public void setOrderViewController(OrderViewController controller) {
        orderViewController = controller;
    }
}
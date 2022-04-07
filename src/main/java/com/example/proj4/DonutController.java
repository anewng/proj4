package com.example.proj4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class DonutController {
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
    private Button donutPreviewOrderButton;
    @FXML
    private ListView donutOrderPreview;
    @FXML
    private Button donutAmountSelect;

    ArrayList<Donut> donutArrayList = new ArrayList<Donut>();

    @FXML
    private void initialize(){
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
    protected void onPreviewOrderClick(ActionEvent event) {
        Donut newDonut = new DonutHole("");
        if(donutTypeSelect.getValue().toString().equals("Donut Hole")) {
            newDonut = new DonutHole(donutFlavorSelect.getValue().toString());
        } else if(donutTypeSelect.getValue().toString().equals("Yeast Donut")) {
            newDonut = new YeastDonut(donutFlavorSelect.getValue().toString());
        } else if(donutTypeSelect.getValue().toString().equals("Cake Donut")) {
            newDonut = new CakeDonut(donutFlavorSelect.getValue().toString());
        }
        donutArrayList.add(newDonut);

    }

    @FXML
    protected void onOrderButtonClick(ActionEvent event) {
        System.out.println("working");
    }
}
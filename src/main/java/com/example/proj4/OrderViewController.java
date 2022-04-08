package com.example.proj4;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class OrderViewController {
    private MainController mainController;

    private ArrayList<MenuItem> yourOrderArrayList = new ArrayList<MenuItem>();

    @FXML
    private ListView yourOrders;

    @FXML
    private void initialize() throws IOException {
        //System.out.println(mainController.donutController.donutArrayList.get(0));

        /*
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
            donutController = fxmlLoader.getController();
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
            coffeeController = fxmlLoader2.getController();
        } catch(Exception e) {
            e.printStackTrace();
        }
         */
        updateListView();
    }

    private void updateListView(){
        yourOrders.getItems().clear();
        for(int i = 0; i < yourOrderArrayList.size(); i ++){
            yourOrders.getItems().add(yourOrderArrayList.get(i));
        }
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }

    public ArrayList<MenuItem> getYourOrderArrayList() {
        return yourOrderArrayList;
    }


    public void setRoot(Parent root) {
        scene.setRoot(root);
    }
}
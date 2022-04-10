package com.example.proj4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    /**
     The start method loads the fxml file for the GUI and creates the stage to be displayed.
     @param stage the stage being launched.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage.setScene(new Scene((root1), 500, 500));
        stage.setTitle("MainView");
        stage.show();
    }

    /**
     Main method for MainApplication.
     Launches the program.
     */
    public static void main(String[] args) {
        launch();
    }
}
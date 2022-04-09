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
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage.setScene(new Scene((root1), 500, 500));
        stage.setTitle("MainView");
        stage.show();
    }

    public class donutButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

        }
    }

    public static void main(String[] args) {
        launch();
    }
}
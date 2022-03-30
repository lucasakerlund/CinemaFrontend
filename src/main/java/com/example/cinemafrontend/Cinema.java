package com.example.cinemafrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Cinema extends Application {

    private static Cinema instance;

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/menu.fxml"));
        Parent root = loader.load();
        stage.setTitle("Hello!");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static Cinema inst(){
        return instance;
    }

    public static void main(String[] args) {
        launch();
    }
}
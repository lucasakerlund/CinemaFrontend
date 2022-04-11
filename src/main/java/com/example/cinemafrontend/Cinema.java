package com.example.cinemafrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Cinema extends Application {

    private static Cinema instance;

    private Stage stage;
    private BorderPane root;

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/menu.fxml"));
        root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static Cinema inst(){
        return instance;
    }

    public Stage getStage(){
        return stage;
    }

    public BorderPane getRoot(){
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}
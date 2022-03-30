package com.example.cinemafrontend.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Cover extends StackPane {

    public Cover() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/movies/cover.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

}

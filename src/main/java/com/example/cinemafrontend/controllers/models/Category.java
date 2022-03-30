package com.example.cinemafrontend.controllers.models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Category extends VBox {

    private Label label;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private HBox coverList;

    public Category() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/movies/category.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        label = (Label) getChildren().stream().filter(node -> node instanceof Label).findFirst().orElse(null);
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
        addCover(new Cover());
    }

    public void setName(String name) {
        label.setText(name);
    }

    public void addCover(Cover cover) {
        coverList.getChildren().add(cover);
    }

}

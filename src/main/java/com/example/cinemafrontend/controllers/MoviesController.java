package com.example.cinemafrontend.controllers;

import com.example.cinemafrontend.controllers.models.*;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;

public class MoviesController {

    private static MoviesController instance;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox categoryList;

    private HashMap<String, Category> categories;

    @FXML
    public void initialize() {
        instance = this;
        categories = new HashMap<>();
        //get all movies from backend
        createCategory("TEST");
        createCategory("ANOTHER ONE");
    }

    public static MoviesController inst(){
        return instance;
    }

    private Category createCategory(String name) {
        Category category = null;
        try {
            category = new Category();
        } catch (IOException e) {
            e.printStackTrace();
        }
        categoryList.getChildren().add(category);
        return category;
    }

}

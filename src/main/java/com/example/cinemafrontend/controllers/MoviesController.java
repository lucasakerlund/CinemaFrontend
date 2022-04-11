package com.example.cinemafrontend.controllers;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.controllers.models.*;
import com.example.cinemafrontend.model.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MoviesController {

    private static MoviesController instance;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox categoryList;

    private HashMap<String, Category> categories;
    private List<Movie> movies;

    @FXML
    public void initialize() {
        instance = this;
        categories = new HashMap<>();
        //get all movies from backend
        movies = Arrays.asList(BackendCaller.inst().getMovies());
        String[] genres = BackendCaller.inst().getGenres().values().toArray(new String[0]);
        for (String genre : genres) {
            createCategory(genre);
        }
    }

    public static MoviesController inst(){
        return instance;
    }

    public List<Movie> getMovies(){
        return movies;
    }

    private Category createCategory(String name) {
        Category category = null;
        try {
            category = new Category(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        categoryList.getChildren().add(category);
        return category;
    }

}

package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.controllers.MoviesController;
import com.example.cinemafrontend.model.Movie;
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

    public Category(String category) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/movies/category.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        label = (Label) getChildren().stream().filter(node -> node instanceof Label).findFirst().orElse(null);
        label.setText(category);
        for (Movie movie : MoviesController.inst().getMovies()) {
            for (String genre : movie.getGenres()) {
                if(genre.equalsIgnoreCase(category)){
                    addCover(new CategoryCover(movie));
                }
            }
        }
    }

    public void setName(String name) {
        label.setText(name);
    }

    public void addCover(CategoryCover categoryCover) {
        coverList.getChildren().add(categoryCover);
    }

}

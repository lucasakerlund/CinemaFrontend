package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.SubSceneHandler;
import com.example.cinemafrontend.model.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class CategoryCover extends StackPane {

    private Movie movie;

    @FXML
    private ImageView imageView;
    @FXML
    private Label label;

    public CategoryCover(Movie movie) throws IOException {
        this.movie = movie;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/movies/categoryCover.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        imageView.setImage(new Image(movie.getCategoryCoverImage() == "" ? "https://www.prokerala.com/movies/assets/img/no-poster-available.jpg" : movie.getCategoryCoverImage()));
        label.setText(movie.getTitle());

        addClickListener();
        addHoverListener();
    }

    private void addClickListener(){
        this.setOnMousePressed(e -> {
            try {
                SubSceneHandler.inst().show(new Cover(movie));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void addHoverListener(){
        this.setOnMouseEntered(e -> {
            label.setVisible(true);
        });
        this.setOnMouseExited(e -> {
            label.setVisible(false);
        });
    }

}

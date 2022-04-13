package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.model.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CreateScheduleMovieItem extends HBox {

    @FXML
    private Label nameLabel;

    private CreateSchedule parent;
    private Movie movie;

    public CreateScheduleMovieItem(Movie movie, CreateSchedule parent) throws IOException {
        this.movie = movie;
        this.parent = parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/schedule/createScheduleItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        nameLabel.setText(movie.getTitle());

        setup();
    }

    public Movie getMovie(){
        return movie;
    }

    private void setup(){
        this.setOnMousePressed(e -> {
            parent.setMovieFocus(this);
        });
    }

}

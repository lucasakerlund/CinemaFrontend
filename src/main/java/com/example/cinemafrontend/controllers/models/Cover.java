package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.model.Movie;
import com.example.cinemafrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Cover extends StackPane {

    private Movie movie;

    @FXML
    private ImageView imageView;
    @FXML
    private Label bookButton;

    public Cover(Movie movie) throws IOException {
        this.movie = movie;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/movies/cover.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        imageView.setImage(new Image(movie.getCoverImage() == "" ? "https://www.prokerala.com/movies/assets/img/no-poster-available.jpg" : movie.getCoverImage()));

        addBookListener();
    }

    private void addBookListener(){
        bookButton.setOnMousePressed(e -> {
            try {
                //Get all schedules for the movie and display them here
                ScheduleSelector selector = new ScheduleSelector();
                JSONArray schedules = BackendCaller.inst().getSchedulesByMovie(movie.getMovieId());
                for (int i = 0; i < schedules.length(); i++) {
                    JSONObject schedule = schedules.getJSONObject(i);
                    selector.addItem(new ScheduleSelectorItem(schedule.getInt("schedule_id"), schedule.getString("date"), schedule.getString("time"), selector));
                }
                SubSceneHandler.inst().show(selector);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}

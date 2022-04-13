package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.SubSceneHandler;
import com.example.cinemafrontend.model.Movie;
import com.example.cinemafrontend.model.Schedule;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ScheduleItem extends StackPane {

    @FXML
    private ImageView imageView;
    @FXML
    private Label titleLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label salonLabel;
    @FXML
    private Label bookButton;

    private Schedule schedule;
    private Movie movie;

    public ScheduleItem(Schedule schedule, Movie movie) throws IOException {
        this.schedule = schedule;
        this.movie = movie;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/schedule/scheduleItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        imageView.setImage(new Image(movie.getCategoryCoverImage()));
        titleLabel.setText(movie.getTitle());
        dateLabel.setText(schedule.getDate());
        timeLabel.setText(schedule.getTime());
        salonLabel.setText(schedule.getSalon()+"");

        setup();
    }

    private void setup(){
        bookButton.setOnMousePressed(e -> {
            try {
                SubSceneHandler.inst().show(new SalonView(schedule.getScheduleId()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}

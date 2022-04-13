package com.example.cinemafrontend.controllers;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.abstracts.SubSceneHandler;
import com.example.cinemafrontend.controllers.models.CreateSchedule;
import com.example.cinemafrontend.controllers.models.ScheduleItem;
import com.example.cinemafrontend.model.Movie;
import com.example.cinemafrontend.model.Schedule;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class ScheduleController {

    private static ScheduleController instance;

    @FXML
    private FlowPane box;
    @FXML
    private Label createButton;

    @FXML
    public void initialize() {
        instance = this;
        loadSchedule();
        setup();
    }

    public static ScheduleController inst(){
        return instance;
    }

    public void loadSchedule(){
        for (Schedule schedule : BackendCaller.inst().getSchedules()) {
            Movie movie = BackendCaller.inst().getMovieById(schedule.getMovieId());
            try {
                box.getChildren().add(new ScheduleItem(schedule, movie));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setup(){
        createButton.setOnMousePressed(e -> {
            try {
                SubSceneHandler.inst().show(new CreateSchedule());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}


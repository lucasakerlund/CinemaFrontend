package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.abstracts.SubSceneHandler;
import com.example.cinemafrontend.controllers.ScheduleController;
import com.example.cinemafrontend.model.Movie;
import com.example.cinemafrontend.model.Salon;
import com.example.cinemafrontend.model.Schedule;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CreateSchedule extends BorderPane {

    @FXML
    private VBox movieBox;
    @FXML
    private VBox salonBox;
    @FXML
    private TextField dateInput;
    @FXML
    private TextField timeInput;
    @FXML
    private Label errorLabel;
    @FXML
    private Label createButton;

    private CreateScheduleMovieItem movieFocused;
    private CreateScheduleSalonItem salonFocused;

    public CreateSchedule() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/schedule/createSchedule.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        setup();
        loadMovies();
        loadSalons();
    }

    public void setMovieFocus(CreateScheduleMovieItem item){
        if(movieFocused != null){
            movieFocused.setId("");
        }
        movieFocused = item;
        movieFocused.setId("create-schedule-movie-item-focused");
    }

    public void setSalonFocus(CreateScheduleSalonItem item){
        if(salonFocused != null){
            salonFocused.setId("");
        }
        salonFocused = item;
        salonFocused.setId("create-schedule-salon-item-focused");
    }

    private void loadMovies() {
        for (Movie movie : BackendCaller.inst().getMovies()) {
            try {
                movieBox.getChildren().add(new CreateScheduleMovieItem(movie, this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadSalons(){
        for (Salon salon : BackendCaller.inst().getSalons()) {
            try {
                salonBox.getChildren().add(new CreateScheduleSalonItem(salon, this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setup(){
        createButton.setOnMousePressed(e -> {
            if(dateInput.getText().equals("") || timeInput.getText().equals("")){
                errorLabel.setText("Du måste fylla i alla rutor.");
                errorLabel.setVisible(true);
                return;
            }
            if(movieFocused == null){
                errorLabel.setText("Du måste välja en film.");
                errorLabel.setVisible(true);
                return;
            }
            if(salonFocused == null){
                errorLabel.setText("Du måste välja en salong.");
                errorLabel.setVisible(true);
                return;
            }
            if(!dateInput.getText().matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")){
                errorLabel.setText("Datumet måste vara formaterat: yyyy-MM-dd");
                errorLabel.setVisible(true);
                return;
            }
            if(!timeInput.getText().matches("[0-9][0-9]:[0-9][0-9]")){
                errorLabel.setText("Tiden måste vara formaterad: HH:mm");
                errorLabel.setVisible(true);
                return;
            }
            if(!timeInput.getText().matches("((0|1)[0-9]|(2)[0-3]):[0-5][0-9]")){
                errorLabel.setText("Tiden måste gå enligt 24h klockan");
                errorLabel.setVisible(true);
                return;
            }
            for (Schedule schedule : BackendCaller.inst().getSchedules()) {
                if(schedule.getDate().equals(dateInput.getText()) && schedule.getTime().equals(timeInput.getText())){
                    errorLabel.setText("En film är redan lagd på denna tid.");
                    errorLabel.setVisible(true);
                    return;
                }
            }
            BackendCaller.inst().createSchedule(dateInput.getText(), timeInput.getText(), movieFocused.getMovie().getMovieId(), salonFocused.getSalon().getSalonId());
            ScheduleController.inst().loadSchedule();
            SubSceneHandler.inst().hide();
        });
    }

}

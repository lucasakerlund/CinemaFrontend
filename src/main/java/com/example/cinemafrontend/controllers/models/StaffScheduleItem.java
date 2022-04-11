package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.controllers.StaffController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class StaffScheduleItem extends VBox {

    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label taskLabel;

    public StaffScheduleItem(String date, String time, String name, String task) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/StaffScheduleItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        dateLabel.setText(date);
        timeLabel.setText(time);
        nameLabel.setText(name);
        taskLabel.setText(task);
    }

}

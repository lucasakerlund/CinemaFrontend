package com.example.cinemafrontend.controllers.models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    private TextArea taskLabel;

    public StaffScheduleItem(String date, String time, String name, String task) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/staff/StaffScheduleItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        dateLabel.setText(date);
        timeLabel.setText(time);
        nameLabel.setText(name);
        taskLabel.setText(task);
    }

}

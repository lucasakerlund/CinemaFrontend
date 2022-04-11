package com.example.cinemafrontend.controllers.models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class ScheduleSelectorItem extends HBox {

    @FXML
    private Label date;
    @FXML
    private Label time;

    private int scheduleId;
    private ScheduleSelector parent;

    public ScheduleSelectorItem(int scheduleId, String date, String time, ScheduleSelector parent) throws IOException {
        this.scheduleId = scheduleId;
        this.parent = parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/scheduleSelectorItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        this.date.setText(date);
        this.time.setText(time);

        addClickListener();
    }

    public int getScheduleId(){
        return scheduleId;
    }

    private void addClickListener(){
        this.setOnMousePressed(e -> {
            parent.setFocus(this);
        });
    }

}

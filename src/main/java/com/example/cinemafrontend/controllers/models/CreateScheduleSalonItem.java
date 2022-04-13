package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.model.Salon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CreateScheduleSalonItem extends HBox {

    @FXML
    private Label nameLabel;

    private CreateSchedule parent;
    private Salon salon;

    public CreateScheduleSalonItem(Salon salon, CreateSchedule parent) throws IOException {
        this.salon = salon;
        this.parent = parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/schedule/createScheduleItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        nameLabel.setText(salon.getSalonId()+"");

        setup();
    }

    public Salon getSalon(){
        return salon;
    }

    private void setup(){
        this.setOnMousePressed(e -> {
            parent.setSalonFocus(this);
        });
    }

}

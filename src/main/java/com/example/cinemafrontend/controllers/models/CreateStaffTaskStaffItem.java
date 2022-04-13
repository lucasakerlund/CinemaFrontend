package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.model.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CreateStaffTaskStaffItem extends HBox {

    @FXML
    private Label nameLabel;

    private Staff staff;
    private CreateStaffTask parent;

    public CreateStaffTaskStaffItem(Staff staff, CreateStaffTask parent) throws IOException {
        this.staff = staff;
        this.parent = parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/staff/createStaffTaskStaffItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        nameLabel.setText(staff.getName());

        setup();
    }

    public Staff getStaff(){
        return staff;
    }

    private void setup(){
        this.setOnMousePressed(e -> {
            parent.setFocus(this);
        });
    }

}

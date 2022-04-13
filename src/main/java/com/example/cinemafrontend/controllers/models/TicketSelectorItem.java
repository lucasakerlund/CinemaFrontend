package com.example.cinemafrontend.controllers.models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class TicketSelectorItem extends HBox {

    public enum Type{
        ADULT, CHILD, SENIOR, STUDENT
    }

    @FXML
    private Label adultButton;
    @FXML
    private Label childButton;
    @FXML
    private Label seniorButton;
    @FXML
    private Label studentButton;

    private Type type;
    private Label focused;

    public TicketSelectorItem() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/booking/ticketSelectorItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        addButtonListeners();
    }

    public Type getType(){
        return type;
    }

    private void setFocus(Label label){
        if(focused != null){
            focused.setId("");
        }
        label.setId("ticket-selector-item-focused");
        focused = label;
    }

    private void addButtonListeners(){
        adultButton.setOnMousePressed(e -> {
            type = Type.ADULT;
            setFocus(adultButton);
        });
        childButton.setOnMousePressed(e -> {
            type = Type.CHILD;
            setFocus(childButton);
        });
        seniorButton.setOnMousePressed(e -> {
            type = Type.SENIOR;
            setFocus(seniorButton);
        });
        studentButton.setOnMousePressed(e -> {
            type = Type.STUDENT;
            setFocus(studentButton);
        });
    }

}

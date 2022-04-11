package com.example.cinemafrontend.controllers.models;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Chair extends StackPane {

    public enum State{
        TAKEN, MARKED, AVAILABLE
    }

    private int id;
    private SalonView parent;

    private State state = State.AVAILABLE;

    public Chair(int id, SalonView parent){
        this.id = id;
        this.parent = parent;
        this.setPrefHeight(40);
        this.setPrefWidth(40);
        this.getStyleClass().add("chair");
        addClickListener();
    }

    public int getID(){
        return id;
    }

    public void setState(State state){
        switch(state){
            case AVAILABLE:
                this.state = state;
                this.setId("");
                break;
            case MARKED:
                this.state = state;
                this.setId("chair-marked");
                break;
            case TAKEN:
                this.state = state;
                this.setId("chair-taken");
                break;
        }
    }

    private void addClickListener(){
        this.setOnMousePressed(e -> {
            if(state == State.TAKEN){
                return;
            }
            switch(state){
                case MARKED:
                    setState(State.AVAILABLE);
                    parent.removeChair(this);
                    break;
                case AVAILABLE:
                    setState(State.MARKED);
                    parent.addChair(this);
                    break;
            }
        });
    }

}

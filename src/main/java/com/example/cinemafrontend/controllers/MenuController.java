package com.example.cinemafrontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class MenuController {

    private static MenuController instance;

    @FXML
    private HBox moviesBox;
    @FXML
    private HBox scheduleBox;
    @FXML
    private HBox ticketsBox;

    private HashMap<String, MenuButton> buttons;

    @FXML
    private BorderPane content;

    @FXML
    public void initialize() throws Exception {
        instance = this;
        buttons = new HashMap<>();
        buttons.put("movies", new MenuButton(moviesBox, FXMLLoader.load(getClass().getResource("/com/example/cinemafrontend/fxml/movies.fxml"))));
        buttons.put("schedule", new MenuButton(scheduleBox, FXMLLoader.load(getClass().getResource("/com/example/cinemafrontend/fxml/schedule.fxml"))));
        buttons.put("tickets", new MenuButton(ticketsBox, FXMLLoader.load(getClass().getResource("/com/example/cinemafrontend/fxml/tickets.fxml"))));

        openPage(buttons.get("movies").getContent());
    }

    public void openPage(Parent page){
        content.setCenter(null);
        content.setCenter(page);
    }

    private class MenuButton {
        private HBox box;
        private Parent content;

        public MenuButton(HBox box, Parent content){
            this.box = box;
            this.content = content;

            addClickListener();
        }

        public Parent getContent(){
            return content;
        };

        private void addClickListener() {
            box.setOnMousePressed(e -> {
                openPage(content);
                System.out.println(1);
            });
        }


    }
}

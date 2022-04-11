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
    @FXML
    private HBox accountBox;
    @FXML
    private HBox staffBox;

    private HashMap<String, MenuButton> buttons;

    @FXML
    private BorderPane content;

    private MenuButton focused;

    @FXML
    public void initialize() throws Exception {
        instance = this;
        buttons = new HashMap<>();
        buttons.put("movies", new MenuButton(moviesBox, FXMLLoader.load(getClass().getResource("/com/example/cinemafrontend/fxml/movies.fxml"))));
        buttons.put("schedule", new MenuButton(scheduleBox, FXMLLoader.load(getClass().getResource("/com/example/cinemafrontend/fxml/schedule.fxml"))));
        buttons.put("tickets", new MenuButton(ticketsBox, FXMLLoader.load(getClass().getResource("/com/example/cinemafrontend/fxml/tickets.fxml"))));
        buttons.put("account", new MenuButton(accountBox, FXMLLoader.load(getClass().getResource("/com/example/cinemafrontend/fxml/account.fxml"))));
        buttons.put("staff", new MenuButton(staffBox, FXMLLoader.load(getClass().getResource("/com/example/cinemafrontend/fxml/staff.fxml"))));

        openPage(buttons.get("movies").getContent());
        setFocus(buttons.get("movies"));
    }

    public void openPage(Parent page){
        content.setCenter(null);
        content.setCenter(page);
    }

    private void setFocus(MenuButton button){
        if(focused != null){
            focused.box.setId("menu-bar-button");
        }
        focused = button;
        focused.box.setId("menu-bar-button-focused");
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
                setFocus(this);
                openPage(content);
            });
        }


    }
}

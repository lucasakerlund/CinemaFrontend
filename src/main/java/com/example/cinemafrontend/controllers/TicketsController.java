package com.example.cinemafrontend.controllers;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.controllers.models.TicketItem;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class TicketsController {

    private static TicketsController instance;

    @FXML
    private FlowPane box;

    @FXML
    public void initialize() {
        instance = this;

    }

    public static TicketsController inst(){
        return instance;
    }

    public void loadTickets(){
        box.getChildren().clear();
        int customerId = AccountController.inst().getCustomerID();
        JSONArray array = BackendCaller.inst().getTickets(customerId);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            try {
                box.getChildren().add(new TicketItem(
                        object.getInt("booking_id"),
                        object.getString("title"),
                        object.getString("image"),
                        object.getString("date"),
                        object.getString("time"),
                        object.getInt("salon_id"),
                        object.getInt("adults"),
                        object.getInt("children"),
                        object.getInt("seniors"),
                        object.getInt("students")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

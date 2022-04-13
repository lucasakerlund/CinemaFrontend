package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.abstracts.SubSceneHandler;
import com.example.cinemafrontend.controllers.AccountController;
import com.example.cinemafrontend.controllers.TicketsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketSelector extends BorderPane {

    @FXML
    private VBox box;
    @FXML
    private Label costLabel;
    @FXML
    private Label confirmButton;
    @FXML
    private Label errorLabel;

    private int scheduleID;
    private SalonView parent;
    private List<TicketSelectorItem> tickets;

    private int cost;

    public TicketSelector(int scheduleID, SalonView salon) throws IOException {
        this.scheduleID = scheduleID;
        this.parent = salon;
        tickets = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/booking/ticketSelector.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        addConfirmListener();
    }

    public void calculateCost(){
        HashMap<String, Integer> prices =  BackendCaller.inst().getPrices();
        int sum = 0;
        for (TicketSelectorItem ticket : tickets) {
            if(ticket.getType() == null){
                continue;
            }
            switch(ticket.getType()){
                case ADULT:
                    sum+=prices.get("adult_ticket");
                    break;
                case CHILD:
                    sum+=prices.get("child_ticket");
                    break;
                case SENIOR:
                    sum+=prices.get("senior_ticket");
                    break;
                case STUDENT:
                    sum+=prices.get("student_ticket");
                    break;
            }
        }
        costLabel.setText(sum+"kr");
    }

    public void addItem(TicketSelectorItem item){
        box.getChildren().add(item);
        tickets.add(item);
    }

    private void addConfirmListener(){
        confirmButton.setOnMousePressed(e -> {
            if(AccountController.inst().getCustomerID() == -1){
                errorLabel.setText("Du måste logga in");
                errorLabel.setVisible(true);
                return;
            }
            if(tickets.stream().filter(t -> t.getType() == null).count() != 0){
                errorLabel.setText("Välj biljettyper");
                errorLabel.setVisible(true);
                return;
            }
            BackendCaller.inst().addTicket(AccountController.inst().getCustomerID(),
                    tickets.stream().filter(ticket -> ticket.getType() == TicketSelectorItem.Type.ADULT).toArray().length,
                    tickets.stream().filter(ticket -> ticket.getType() == TicketSelectorItem.Type.CHILD).toArray().length,
                    tickets.stream().filter(ticket -> ticket.getType() == TicketSelectorItem.Type.SENIOR).toArray().length,
                    tickets.stream().filter(ticket -> ticket.getType() == TicketSelectorItem.Type.STUDENT).toArray().length,
                    scheduleID, parent.getChairs());
            SubSceneHandler.inst().hide();
            TicketsController.inst().loadTickets();
        });
    }

}

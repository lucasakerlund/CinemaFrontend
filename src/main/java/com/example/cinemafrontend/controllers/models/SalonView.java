package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalonView extends BorderPane {

    private String[][] chairLayout = {
            {"1","2","3","4","5","6","7","8","9","10","11"},
            {"12","13","14","15","16","17","18","19","20","21","22"},
            {"23","24","25","26","27","28","29","30","31","32","33"},
            {"34","35","36","37","38","39","40","41","42","43","44"}
    };

    @FXML
    private Label confirmButton;
    @FXML
    private Label errorLabel;
    @FXML
    private VBox chairBox;

    private int scheduleID;
    private List<Chair> selectedChairs;
    private List<String> takenChairs;

    //Maybe in the future: take in a multidimensional array as an argument to represent where to put all chairs and rows
    public SalonView(int scheduleID) throws IOException {
        this.scheduleID = scheduleID;
        this.selectedChairs = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/booking/salon.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        takenChairs = Arrays.asList(BackendCaller.inst().getTakenChairs(scheduleID));

        setupChairs();
        addConfirmListener();
    }

    public List<Chair> getChairs(){
        return selectedChairs;
    }

    public void addChair(Chair chair){
        if(!selectedChairs.contains(chair)){
            selectedChairs.add(chair);
        }
    }

    public void removeChair(Chair chair){
        selectedChairs.remove(chair);
    }

    private void setupChairs(){
        try {
            for (int row = 0; row < chairLayout.length; row++) {
                HBox rowBox = createRow();
                for (int i = 0; i < chairLayout[row].length; i++) {
                    Chair chair = new Chair(Integer.parseInt(chairLayout[row][i]), this);
                    if(takenChairs.contains(String.valueOf(chair.getID()))){
                        chair.setState(Chair.State.TAKEN);
                    }
                    rowBox.getChildren().add(chair);
                }
                chairBox.getChildren().add(rowBox);
            }
        }catch(NumberFormatException e){
            System.err.println(e);
        }
    }

    private HBox createRow(){
        HBox row = new HBox(5);
        row.setPadding(new Insets(20,0,0,0));
        row.setAlignment(Pos.CENTER);
        return row;
    }

    private void addConfirmListener(){
        confirmButton.setOnMousePressed(e -> {
            if(selectedChairs.isEmpty()){
                errorLabel.setText("Välj minst en plats");
                errorLabel.setVisible(true);
                return;
            }
            try {
                TicketSelector selector = new TicketSelector(scheduleID, this);
                for (Chair selectedChair : selectedChairs) {
                    selector.addItem(new TicketSelectorItem());
                }
                SubSceneHandler.inst().show(selector);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}

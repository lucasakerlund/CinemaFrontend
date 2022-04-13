package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.abstracts.SubSceneHandler;
import com.example.cinemafrontend.controllers.StaffController;
import com.example.cinemafrontend.model.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CreateStaffTask extends BorderPane {

    @FXML
    private VBox staffsBox;

    @FXML
    private TextField dateInput;
    @FXML
    private TextField timeInput;
    @FXML
    private TextArea taskInput;
    @FXML
    private Label errorLabel;
    @FXML
    private Label createButton;

    private CreateStaffTaskStaffItem focused;

    public CreateStaffTask() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/staff/createStaffTask.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        setup();
    }

    public void setFocus(CreateStaffTaskStaffItem item){
        if(focused != null){
            focused.setId("");
        }
        focused = item;
        focused.setId("create-staff-task-staff-item-focused");
    }

    private void setup(){
        createButton.setOnMousePressed(e -> {
            if(dateInput.getText().equals("") || timeInput.getText().equals("") || taskInput.getText().equals("")){
                errorLabel.setText("Du måste fylla i alla rutor.");
                errorLabel.setVisible(true);
                return;
            }
            if(focused == null){
                errorLabel.setText("Du måste välja en personal.");
                errorLabel.setVisible(true);
                return;
            }
            if(!dateInput.getText().matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")){
                errorLabel.setText("Datumet måste vara formaterat: yyyy-MM-dd");
                errorLabel.setVisible(true);
                return;
            }
            if(!timeInput.getText().matches("[0-9][0-9]:[0-9][0-9]")){
                errorLabel.setText("Tiden måste vara formaterad: HH:mm");
                errorLabel.setVisible(true);
                return;
            }
            if(!timeInput.getText().matches("((0|1)[0-9]|(2)[0-3]):[0-5][0-9]")){
                errorLabel.setText("Tiden måste gå enligt 24h klockan");
                errorLabel.setVisible(true);
                return;
            }
            if(BackendCaller.inst().getStaffTask(focused.getStaff().getStaffId(), dateInput.getText(), timeInput.getText()) != null){
                errorLabel.setText("Personen är upptagen denna tid.");
                errorLabel.setVisible(true);
                return;
            }
            BackendCaller.inst().createTask(focused.getStaff().getStaffId(), dateInput.getText(), timeInput.getText(), taskInput.getText());
            SubSceneHandler.inst().hide();
            StaffController.inst().loadStaffSchedule();
        });
        try {
            for(Staff staff : BackendCaller.inst().getStaffs()) {
                staffsBox.getChildren().add(new CreateStaffTaskStaffItem(staff, this));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

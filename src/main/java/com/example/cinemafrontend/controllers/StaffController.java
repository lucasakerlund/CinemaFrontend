package com.example.cinemafrontend.controllers;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.abstracts.SubSceneHandler;
import com.example.cinemafrontend.controllers.models.CreateStaffTask;
import com.example.cinemafrontend.controllers.models.StaffScheduleItem;
import com.example.cinemafrontend.model.Staff;
import com.example.cinemafrontend.model.StaffSchedule;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.io.IOException;

public class StaffController {

    private static StaffController instance;

    @FXML
    private TextField loginInput;
    @FXML
    private Label loginButton;
    @FXML
    private Label loginErrorLabel;
    @FXML
    private VBox content; //Use this to disable content when nobody is logged in.

    @FXML
    private Label nameLabel;
    @FXML
    private Label mailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label positionLabel;

    @FXML
    private FlowPane scheduleBox;
    @FXML
    private Label createTaskButton;

    private Staff staff;

    @FXML
    public void initialize(){
        instance = this;

        setup();
    }

    public static StaffController inst(){
        return instance;
    }

    public Staff getStaff(){
        return staff;
    }

    public void login(String securityNumber){
        Staff staff = BackendCaller.inst().getStaff(securityNumber);
        if(staff == null){
            loginErrorLabel.setText("Kontot finns inte");
            loginErrorLabel.setVisible(true);
            return;
        }
        loginErrorLabel.setVisible(false);
        this.staff = staff;

        nameLabel.setText(staff.getName());
        mailLabel.setText(staff.getMail());
        addressLabel.setText(staff.getAddress());
        salaryLabel.setText(staff.getSalary()+"");
        positionLabel.setText(staff.getPosition());

        TicketsController.inst().loadTickets();
    }

    private void setup(){
        addLoginListener();
        loadStaffSchedule();
        loginInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]+") && !newValue.equals("")){
                loginInput.setText(oldValue);
            }
        });
        createTaskButton.setOnMousePressed(e -> {
            try {
                SubSceneHandler.inst().show(new CreateStaffTask());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void loadStaffSchedule(){
        scheduleBox.getChildren().clear();
        for (StaffSchedule staffSchedule : BackendCaller.inst().getStaffSchedules()) {
            Staff staff = BackendCaller.inst().getStaff(staffSchedule.getStaffId());
            try {
                scheduleBox.getChildren().add(new StaffScheduleItem(staffSchedule.getDate(), staffSchedule.getTime(), staff.getName(), staffSchedule.getTask()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addLoginListener(){
        loginButton.setOnMousePressed(e -> {
            login(loginInput.getText());
        });
    }

}

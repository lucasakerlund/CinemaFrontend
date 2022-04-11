package com.example.cinemafrontend.controllers;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.abstracts.SubSceneHandler;
import com.example.cinemafrontend.controllers.models.CreateAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.io.IOException;

public class AccountController {

    private static AccountController instance;

    @FXML
    private TextField loginInput;
    @FXML
    private Label loginButton;
    @FXML
    private Label loginErrorLabel;
    @FXML
    private Label registerButton;
    @FXML
    private VBox content; //Use this to disable content when nobody is logged in.

    @FXML
    private Label nameLabel;
    @FXML
    private Label mailLabel;

    private int customerID = -1;
    private String name;
    private String securityNumber;
    private String mail;

    @FXML
    public void initialize(){
        instance = this;

        addLoginListener();
        setup();
    }

    public static AccountController inst(){
        return instance;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public void login(String securityNumber){
        if(BackendCaller.inst().isSecurityNumberAvailable(securityNumber)){
            loginErrorLabel.setText("Kontot finns inte");
            loginErrorLabel.setVisible(true);
            return;
        }
        loginErrorLabel.setVisible(false);
        JSONObject object = BackendCaller.inst().getCustomer(securityNumber);
        customerID = object.getInt("customer_id");
        name = object.getString("name");
        securityNumber = object.getString("security_number");
        mail = object.getString("mail");

        nameLabel.setText(name);
        mailLabel.setText(mail);

        TicketsController.inst().loadTickets();
    }

    private void setup(){
        loginInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]+") && !newValue.equals("")){
                loginInput.setText(oldValue);
            }
        });
        registerButton.setOnMousePressed(e -> {
            try {
                SubSceneHandler.inst().show(new CreateAccount());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void addLoginListener(){
        loginButton.setOnMousePressed(e -> {
            login(loginInput.getText());
        });
    }

}

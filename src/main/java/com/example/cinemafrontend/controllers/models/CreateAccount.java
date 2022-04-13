package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.controllers.AccountController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class CreateAccount extends BorderPane {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField mailInput;
    @FXML
    private TextField securityNumberInput;
    @FXML
    private Label errorLabel;
    @FXML
    private Label registerButton;

    public CreateAccount() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/account/createAccount.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        addRegisterListener();
    }

    private void addRegisterListener(){
        registerButton.setOnMousePressed(e -> {
            if(nameInput.getText().equals("") || mailInput.getText().equals("") || securityNumberInput.getText().equals("")){
                errorLabel.setText("Alla rutor måste vara ifyllda");
                errorLabel.setVisible(true);
                return;
            }
            //Call backend
            if(!BackendCaller.inst().isSecurityNumberAvailable(securityNumberInput.getText())){
                errorLabel.setText("Personnumret är redan registrerat");
                errorLabel.setVisible(true);
                return;
            }
            BackendCaller.inst().createCustomer(nameInput.getText(), securityNumberInput.getText(), mailInput.getText());
            AccountController.inst().login(securityNumberInput.getText());
        });
    }

}

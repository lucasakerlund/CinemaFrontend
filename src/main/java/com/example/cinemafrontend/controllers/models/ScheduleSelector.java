package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ScheduleSelector extends BorderPane {

    @FXML
    private VBox dateBox;
    @FXML
    private Label errorLabel;
    @FXML
    private Label confirmButton;

    private ScheduleSelectorItem focused;

    public ScheduleSelector() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/booking/scheduleSelector.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        addConfirmListener();
    }

    public void addItem(ScheduleSelectorItem item){
        dateBox.getChildren().add(item);
    }

    public void setFocus(ScheduleSelectorItem item){
        if(focused != null){
            focused.setId("");
        }
        this.focused = item;
        focused.setId("schedule-selector-item-focused");
    }

    private void addConfirmListener(){
        confirmButton.setOnMousePressed(e -> {
            if(focused == null){
                errorLabel.setVisible(true);
                return;
            }
            try {
                SubSceneHandler.inst().show(new SalonView(focused.getScheduleId()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}

package com.example.cinemafrontend.abstracts;

import com.example.cinemafrontend.Cinema;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SubSceneHandler {

    private static SubSceneHandler instance = new SubSceneHandler();

    private Stage stage;
    private Scene scene;
    private BorderPane root;

    private SubSceneHandler(){
        stage = new Stage(StageStyle.TRANSPARENT);
        stage.initModality(Modality.NONE);
        stage.initOwner(Cinema.inst().getStage());
        root = new BorderPane();
        scene = new Scene(root);
        stage.setScene(scene);
        addCloseEvent();
        stage.show();
        centerContent();
    }

    public static SubSceneHandler inst(){
        return instance;
    }

    private void addCloseEvent(){
        stage.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(!newValue){
                stage.hide();
                Cinema.inst().getRoot().getCenter().setOpacity(1);
            }
        }));
    }

    private void centerContent(){
        stage.setX(Cinema.inst().getStage().getX() + (Cinema.inst().getStage().getWidth()/2 - stage.getWidth()/2));
        stage.setY(Cinema.inst().getStage().getY() + (Cinema.inst().getStage().getHeight()/2 - stage.getHeight()/2));
    }

    public void show(Node node){
        root.setCenter(node);
        stage.sizeToScene();
        stage.show();
        centerContent();
        Cinema.inst().getRoot().getCenter().setOpacity(.3);
    }

    public void hide(){
        stage.close();
    }

}

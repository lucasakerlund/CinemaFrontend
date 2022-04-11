package com.example.cinemafrontend.controllers.models;

import com.example.cinemafrontend.abstracts.BackendCaller;
import com.example.cinemafrontend.controllers.TicketsController;
import com.example.cinemafrontend.model.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TicketItem extends StackPane {

    @FXML
    private ImageView imageView;
    @FXML
    private Label titleLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label salonLabel;
    @FXML
    private Label adultsLabel;
    @FXML
    private Label childrenLabel;
    @FXML
    private Label seniorsLabel;
    @FXML
    private Label studentsLabel;
    @FXML
    private Label removeButton;

    private int bookingId;
    private int salon;

    public TicketItem(int bookingId, String title, String image, String date, String time, int salon, int adults, int children, int seniors, int students) throws IOException {
        this.bookingId = bookingId;
        this.salon = salon;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cinemafrontend/fxml/ticketItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        imageView.setImage(new Image(image == "" ? "https://www.prokerala.com/movies/assets/img/no-poster-available.jpg" : image));

        titleLabel.setText(title);
        dateLabel.setText(date);
        timeLabel.setText(time);
        salonLabel.setText(salon+"");
        adultsLabel.setText(adults+"");
        childrenLabel.setText(children+"");
        seniorsLabel.setText(seniors+"");
        studentsLabel.setText(students+"");

        addListener();
    }

    private void addListener(){
        removeButton.setOnMousePressed(e -> {
            BackendCaller.inst().deleteTicket(bookingId);
            TicketsController.inst().loadTickets();
        });
    }

}

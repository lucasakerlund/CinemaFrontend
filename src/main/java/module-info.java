module com.example.cinemafrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires com.fasterxml.jackson.annotation;
    requires gson;
    requires java.sql;

    opens com.example.cinemafrontend to javafx.fxml, gson;
    opens com.example.cinemafrontend.model to gson;
    opens com.example.cinemafrontend.controllers to javafx.fxml;
    opens com.example.cinemafrontend.controllers.models to javafx.fxml;
    opens com.example.cinemafrontend.abstracts to gson;
    exports com.example.cinemafrontend;
}